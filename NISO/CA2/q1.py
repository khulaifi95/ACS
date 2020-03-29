parsed = ['add', ['mul', ['sub', 5, 1], 3], ['exp', 4, None]]
operator = ['add', 'sub', 'mul', 'div', 'exp']

stack = []
def evaluate(parsed):
    for item in parsed:
        if item is in operator:
            stack.append(item)
