import math
import argparse
from random import random, randint, seed
from copy import deepcopy
from datetime import datetime

from ParseTool import ParseTool

parse_tool = ParseTool()  # s-expression parser


# Expression evaluation
class Eval:

    def __init__(self, n, x, expr):
        """
        Arguments:
            n -- the dimension of the input vector
            x -- the input vector
            expr -- the expression to evaluate
        """
        self.n = n  # set dimension parameter
        self.input = list(map(float, x.split()))  # string -> list of values
        self.expr = expr
        self.parsed = parse_tool.parse_sexp(self.expr)  # set parsed sexp
        print("Parsed to Python:", self.parsed)
        return

    def calculate_expression(self):
        res = self.calculate_meta_expression(self.parsed)
        return res

    def calculate_meta_expression(self, me):
        """[summary]

        [description]

        Arguments:
            me {[type]} -- meta expression
        """
        if len(str(me)) == 1:
            return me  # return sexp with no nested list
        operator = me[0]  # operator at the first index

        # Unpack nested list
        for i in range(1, len(me)):  # loop over all elements in expression
            if isinstance(me[i], list):  # recursion condition
                # print(me[i])
                me[i] = self.calculate_meta_expression(me[i])

        # Evaluate arithmetic expressions
        if operator == 'add':
            return me[1] + me[2]
        elif operator == 'sub':
            return me[1] - me[2]
        elif operator == 'mul':
            return me[1] * me[2]
        elif operator == 'div':  # limit float precision
            return float("%0.3f" % (me[1] / me[2])) if me[2] != 0 else 0
        elif operator == 'pow':
            return float("%0.3f" % (pow(me[1], me[2])))
        elif operator == 'sqrt':
            return math.sqrt(me[1])
        elif operator == 'log':
            return math.log(me[1], 2)
        elif operator == 'exp':
            return math.exp(me[1])
        elif operator == 'max':
            return max(me[1], me[2])
        elif operator == 'ifleq':
            return me[3] if me[1] <= me[2] else me[4]

# Special expression operators
        elif operator == 'data':
            return self.input[me[1] % self.n]
        elif operator == 'diff':
            k = me[1] % self.n
            l = me[2] % self.n
            return self.input[k] - self.input[l]
        elif operator == 'avg':
            k = me[1] % self.n
            l = me[2] % self.n
            start = min(k, l)
            end = max(k, l)
            sum = 0
            for i in range(start, end):
                sum += self.input[i]
            return sum / abs(k - l)


# Build expression tree
class GPTree:

    def __init__(self, data=None, left=None, right=None):
        self.data = data
        self.left = left
        self.right = right

    def node_label(self):  # create string label
        if self.data in FUNCTIONS:
            return self.data
        else:
            return str(self.data)

    def translate_tree(self):
        if self.data in TERMINALS:
            estimate_equation = self.node_label()
        else:
            estimate_equation = [self.node_label()]

        if self.left:
            estimate_equation.append(self.left.translate_tree())
        if self.right:
            estimate_equation.append(self.right.translate_tree())
        return estimate_equation
    ##########

    def random_tree(self, grow, max_depth, depth=0):
        if depth < MIN_DEPTH or (depth < max_depth and not gorw):
            self.data = FUNCTIONS

    def mutation(self):
        if random() < PROB_MUTATION:
            self.random_tree(grow=True, max_depth=2)
        elif self.left:
            self.left.mutation()
        elif self.right:
            self.right.mutation()

    def size(self):
        if self.data in TERMINALS:
            return 1
        l = self.left.size() if self.left else 0
        r = self.right.size() if self.right else 0
        return 1 + l + r

    def build_subtree(self):
        t = GPTree()
        t.data = self.data
        if self.left:
            t.left = self.left.build_subtree()
        if self.right:
            t.right = self.right.build_subtree()
        return t

    def scan_tree(self, count, second):
        count[0] -= 1
        if count[0] <= 1:
            if not second:
                return self.build_subtree()
            else:
                self.data = second.data
                self.left = second.left
                self.right = second.right

        else:
            ret = None
            if self.left and count[0] > 1:
                ret = self.left.scan_tree(count, second)
            if self.right and count[0] > 1:
                ret = self.right.scan_tree(count, second)
            return ret

    def crossover(self, other):
        if random() < XO_RATE:
            second = other.scan_tree([randint(1, other.size())], None)
            self.scan_tree([randint(1, self.size())], second)


def init_population(pop_size):
    pop = []
    for md in range(3, MAX_DEPTH + 1):
        for i in range(int(pop_size / 6)):
            t = GPTree()
            t.random_tree(grow=True, max_depth=md)
            pop.append(t)
        for i in range(int(pop_size / 6)):
            t = GPTree()
            t.random_tree(grow=False, max_depth=md)
            pop.append(t)
    missing_num = pop_size - len(pop)
    if missing_num > 0:
        for i in range(missing_num):
            t = GPTree()
            t.random_tree(grow=False, max_depth=md)
            pop.append(t)
    return pop


def fitness(expr, path):
    data = load_data(path)
    n = len(data[0].split()) - 1
    m = len(data)
    distance_sum = 0
    for i in range(m):
        res = Eval(n, data[i], expr)
        e = float('%0.3f' % res.calculate_expression())
        y = float('%0.3f' % float(data[i].split()[n]))
        if abs(e) / abs(y) > 1e6 or abs(e) / abs(y) < 1e-6:
            distance_sum += 99999
        else:
            distance_sum += float('%0.3f' % pow((y - e), 2))
    fitness_value = distance_sum / m
    return fitness_value


def selection(population, fitnesses):
    tournament = [randint(0, len(population) - 1) for i in range(TOURNAMENT_SIZE)]
    tournament_fitnesses = [fitnesses[tournament[i]] for i in range(TOURNAMENT_SIZE)]
    return deepcopy(population[tournament_fitnesses.index(max(tournament_fitnesses))])


def load_data(path):
    with open(path, 'r') as f:
        line = f.readline()
        data = []
        while line:
            data.append(line)
            line = f.readline()
    return data


def gen_terminator(data_dimension):
    TERMINALS.extend([":data_" + str(i) for i in range(data_dimension)])


def translate_data_placeholder(individual, data_dimension):
    for i in range(data_dimension):
        individual = individual.replace(":data_{}".format(i), "(data {})".format(i))
    return individual


def question_1(n, x, expr):
    eva = Eval(n, x, expr)
    res = eva.calculate_expression()
    print(res)


def question_2(path, expr):
    print(fitness(expr, path))


def question_3(path, pop_size, time_budget):
    
