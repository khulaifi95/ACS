import numpy as np
import re
 
dbg = False
 
term_regex = r'''(?mx)
    \s*(?:
        (?P<brackl>\()|
        (?P<brackr>\))|
        (?P<num>\-?\d+\.\d+|\-?\d+)|
        (?P<sq>"[^"]*")|
        (?P<s>[^(^)\s]+)
       )'''
 
def parse_sexp(sexp):
    stack = []
    out = []
    if dbg: print("%-6s %-14s %-44s %-s" % tuple("term value out stack".split()))
    for termtypes in re.finditer(term_regex, sexp):
        term, value = [(t,v) for t,v in termtypes.groupdict().items() if v][0]
        if dbg: print("%-7s %-14s %-44r %-r" % (term, value, out, stack))
        if   term == 'brackl':
            stack.append(out)
            out = []
        elif term == 'brackr':
            assert stack, "Trouble with nesting of brackets"
            tmpout, out = out, stack.pop(-1)
            out.append(tmpout)
        elif term == 'num':
            v = float(value)
            if v.is_integer(): v = int(v)
            out.append(v)
        elif term == 'sq':
            out.append(value[1:-1])
        elif term == 's':
            out.append(value)
        else:
            raise NotImplementedError("Error: %r" % (term, value))
    assert not stack, "Trouble with nesting of brackets"
    return out[0]
 
def print_sexp(exp):
    out = ''
    if type(exp) == type([]):
        out += '(' + ' '.join(print_sexp(x) for x in exp) + ')'
    elif type(exp) == type('') and re.search(r'[\s()]', exp):
        out += '"%s"' % repr(exp)[1:-1].replace('"', '\"')
    else:
        out += '%s' % exp
    return out

def add(exp):
	return np.add(exp[1], exp[2])

def sub(exp):
	return np.subtract(exp[1], exp[2])

def mul(exp):
	return np.multiply(exp[1], exp[2])

def div(exp):
	if exp[2] != 0:
		return np.true_divide(exp[1], exp[2])
	else:
		return 0

def pow(exp):
	return np.power(exp[1],exp[2])

def sqrt(exp):
	return np.sqrt(exp[1])

def log(exp):
	return np.log(exp[1])

def exp(exp):
	return np.exp(exp[1])

def max(exp):
	return max(exp[1], exp[2])

def ifleq(exp):
	if exp[1] <= exp[2]:
		return exp[3]
	else:
		return exp[4]

# def data(exp):

# def diff(exp):

# def avg(exp):





 
if __name__ == '__main__':
    sexp = ''' ( ( add (mul 1 3) (exp 4)) (log 45) (ifleq 12 4 1 0))'''
 
    print('Input S-expression: %r' % (sexp, ))
    parsed = parse_sexp(sexp)
    print("\nParsed to Python:", parsed)
 
    print("\nThen back to: '%s'" % print_sexp(parsed))