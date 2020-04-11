import math
import time
import argparse
from random import random, randint, seed
from copy import deepcopy

from ParseTool import ParseTool

MIN_DEPTH = 2  # minimal initial random tree depth
MAX_DEPTH = 5  # maximal initial random tree depth
GENERATIONS = 100  # maximal number of generations in evolution
TOURNAMENT_SIZE = 2  # candidates in tournament selection 6,5,4,3,2
XO_RATE = 0.9  # individuals crossover rate 0.8, 0.85, 0.9, 0.95, 0.98
MUTATION_RATE = 0.2  # node mutation rate # 0.2, 0.1, 0.05, 0.03, 0.01

FUNCTIONS = ["add", "sub", "mul", "div", "max"]  # original functions
TERMINALS = [0, 1, 2]  # original operands

parse_tool = ParseTool()  # sexp parser


# Evaluation
class Cal:

    def __init__(self, n, x, expr):
        self.n = n
        self.input = list(map(float, x.split()))  # read str dataset
        self.expr = expr
        self.parsed = parse_tool.parse_sexp(self.expr)  # parse in sexp

    def calculate_expression(self):  # first-class
        result = self.calculate_meta_expression(self.parsed)
        return result

    def calculate_meta_expression(self, me):  # sexp evaluation
        if not isinstance(me, list):
            return me  # return single expr
        operator = me[0]
        for i in range(1, len(me)):
            if isinstance(me[i], list):  # iterate until single expr
                # replaced by own value
                me[i] = self.calculate_meta_expression(me[i])

        # Operation
        if operator == "add":
            return me[1] + me[2]
        elif operator == "sub":
            return me[1] - me[2]
        elif operator == "mul":
            return me[1] * me[2]
        elif operator == "div":
            if me[2] != 0:  # not divided by 0
                return float(('%0.3f' % (me[1] / me[2])).rstrip('0').rstrip('.'))
            else:
                return 0
        elif operator == "pow":
            if me[1] <= 0 and me[2] != int(me[2]):  # no decimal power for <= 0
                return 0
            else:
                return float(('%0.3f' % (pow(me[1], me[2]))).rstrip('0').rstrip('.'))
        elif operator == "sqrt":
            if me[1] >= 0:  # no square root on negatives
                return math.sqrt(me[1])
            else:
                return 0
        elif operator == "log":
            if me[1] > 0:  # no log on negatives and 0
                return math.log(me[1], 2)
            else:
                return 0
        elif operator == "exp":
            try:
                ans = math.exp(me[1])
            except OverflowError:
                ans = float('inf')
            return ans
            # return float(('%0.3f' %
            # (math.exp(me[1]))).rstrip('0').rstrip('.'))
        elif operator == "max":
            return max(me[1], me[2])

        elif operator == "ifleq":
            if me[1] <= me[2]:
                return me[3]
            else:
                return me[4]

        elif operator == "data":  # assign an index in the dataset
            return self.input[int(abs(math.floor(me[1])) % self.n)]

        elif operator == "diff":  # return difference between two elements
            k = int(abs(math.floor(me[1])) % self.n)
            l = int(abs(math.floor(me[2])) % self.n)
            return self.input[k] - self.input[l]

        elif operator == "avg":  # average value within a range
            k = int(abs(math.floor(me[1])) % self.n)
            l = int(abs(math.floor(me[2])) % self.n)
            sum = 0
            if k > l:
                for i in range(l, k):
                    sum += self.input[i]
                sum = sum / (k - l)
            elif k < l:
                for i in range(k, l):
                    sum += self.input[i]
                sum = sum / (l - k)

            return sum


# Tree structure
class GPTree:

    def __init__(self, data=None, left=None, right=None):
        self.data = data
        self.left = left
        self.right = right

    def node_label(self):  # string label
        if (self.data in FUNCTIONS):
            return self.data
        else:
            return str(self.data)  # uncommon functions

    # Create nested list of sexp from tree
    def translate_tree(self):
        if self.data in TERMINALS:
            estimate_equation = self.node_label()
        else:
            estimate_equation = [self.node_label()]  # give parenthesis in list

        if self.left:  # traverse left child
            estimate_equation.append(self.left.translate_tree())
        if self.right:  # traverse right child
            estimate_equation.append(self.right.translate_tree())
        return estimate_equation  # appended evaluation of expr

    # Create random tree using either grow or full method
    def random_tree(self, grow, max_depth, depth=0):
        if depth < MIN_DEPTH or (depth < max_depth and not grow):
            # generate root operators
            self.data = FUNCTIONS[randint(0, len(FUNCTIONS) - 1)]
        elif depth >= max_depth:
            # generate leaf operands
            self.data = TERMINALS[randint(0, len(TERMINALS) - 1)]
        else:  # intermediate depth, grow
            if random() > 0.5:
                self.data = TERMINALS[randint(0, len(TERMINALS) - 1)]
            else:
                self.data = FUNCTIONS[randint(0, len(FUNCTIONS) - 1)]
        # generating new tree
        if self.data in FUNCTIONS:
            self.left = GPTree()
            self.left.random_tree(grow, max_depth, depth=depth + 1)
            self.right = GPTree()
            self.right.random_tree(grow, max_depth, depth=depth + 1)

    # Mutate a node into a single GP tree
    def mutation(self):
        if random() < MUTATION_RATE:  # mutate at this node
            self.random_tree(grow=True, max_depth=2)  # limit to 2
        elif self.left:
            self.left.mutation()  # mutate on left
        elif self.right:
            self.right.mutation()  # mutate on right

    # Tree size in nodes
    def size(self):
        if self.data in TERMINALS:  # operand
            return 1
        l = self.left.size() if self.left else 0
        r = self.right.size() if self.right else 0
        return 1 + l + r

    # Copy to a subtree
    def build_subtree(self):  # count is list in order to pass by reference
        t = GPTree()
        t.data = self.data
        if self.left:
            t.left = self.left.build_subtree()
        if self.right:
            t.right = self.right.build_subtree()
        return t

    # Helper function for crossover
    def scan_tree(self, count, second):
        count[0] -= 1  # list
        if count[0] <= 1:
            if not second:  # return subtree rooted here
                return self.build_subtree()
            else:  # glue subtree here
                self.data = second.data
                self.left = second.left
                self.right = second.right
        else:
            selected = None  # generate tree pattern
            if self.left:
                selected = self.left.scan_tree(count, second)
            if self.right:
                selected = self.right.scan_tree(count, second)
            return selected

    # Crossover of two parent trees on random nodes
    def crossover(self, other):
        if random() < XO_RATE:
            second = other.scan_tree(
                [randint(1, other.size())], None)  # 2nd random subtree
            # 2nd subtree "glued" inside 1st tree
            self.scan_tree([randint(1, self.size())], second)


# Evolution
def init_population(pop_size):
    pop = []
    for md in range(3, MAX_DEPTH + 1):  # MD > 2
        for i in range(int(pop_size / 6)):
            t = GPTree()
            t.random_tree(grow=True, max_depth=md)  # grow
            pop.append(t)
        for i in range(int(pop_size / 6)):
            t = GPTree()
            t.random_tree(grow=False, max_depth=md)  # full
            pop.append(t)
    missing_num = pop_size - len(pop)
    if missing_num > 0:  # add up to the total population by full
        for i in range(missing_num):
            t = GPTree()
            t.random_tree(grow=False, max_depth=md)
            pop.append(t)
    return pop


def fitness(expr, path, n, m):
    data = load_data(path)
    # the dimension of the input vector
    n = len(data[0].split()) - 1
    # the size of the training data (X, Y)
    m = len(data)
    distance_sum = 0
    for i in range(0, m - 1):
        cal = Cal(n, data[i], expr)
        e = float(('%0.3f' % cal.calculate_expression()).rstrip('0').rstrip('.'))
        y = float(('%0.3f' % float(data[i].split()[n])).rstrip(
            '0').rstrip('.'))
        # if abs(e) / (abs(y) + 1e-6) > 1000000 or abs(e) / (abs(y) + 1e-6) < 0.000001:
        #     distance_sum += 99999  # ignore large errors
        # else:
        distance_sum += float(('%0.3f' %
                               math.pow((y - e), 2)).rstrip('0').rstrip('.'))
    fitness_value = distance_sum / m
    return fitness_value


# Tournament selection
def selection(population, fitnesses):
    tournament = [randint(0, len(population) - 1)
                  for i in range(TOURNAMENT_SIZE)]
    tournament_fitnesses = [fitnesses[tournament[i]]
                            for i in range(TOURNAMENT_SIZE)]  # map fitness function
    # return argmax
    return deepcopy(population[tournament[tournament_fitnesses.index(max(tournament_fitnesses))]])


def load_data(path):
    with open(path) as f:
        line = f.readline()
        data = []
        while line:
            data.append(line)
            line = f.readline()
    return data


# Add data() as new placeholder leaves
def gen_terminators(data_dimension):
    TERMINALS.extend([":data_" + str(i) + "_" for i in range(data_dimension)])


# Transform placeholder afterwards
def translate_data_placeholder(individual, data_dimension):
    for i in range(data_dimension):
        individual = individual.replace(
            ":data_{}_".format(i), '(data {})'.format(i))
    return individual


def question_1(n, x, expr):
    cal = Cal(n, x, expr)
    result = cal.calculate_expression()
    print(result)


def question_2(path, expr, n, m):
    print(fitness(expr, path, n, m))


def question_3(path, pop_size, n, m, time_budget):
    seed()  # set random state
    dataset = load_data(path)
    data_dimension = m
    gen_terminators(data_dimension)  # add data indices as leaf
    population = init_population(pop_size)

    # records
    best_of_run = None  # individual
    best_of_run_f = 0   # fitness
    best_of_run_gen = 0  # generation

    fitnesses = [fitness(translate_data_placeholder(parse_tool.print_sexp(
        population[i].translate_tree()), data_dimension), path, n, m) for i in range(pop_size)]
    best_of_run_f = min(fitnesses)
    best_of_run = deepcopy(population[fitnesses.index(min(fitnesses))])

    # evolution
    start_time = time.time()  # start timer
    for gen in range(GENERATIONS):
        if time.time() - start_time >= time_budget:
            break
        # print("now process gen: ", gen)
        nextgen_population = []  # next generation
        for i in range(pop_size):
            parent1 = selection(population, fitnesses)
            parent2 = selection(population, fitnesses)
            parent1.crossover(parent2)
            parent1.mutation()
            nextgen_population.append(parent1)
        population = nextgen_population
        fitnesses = [fitness(translate_data_placeholder(parse_tool.print_sexp(
            population[i].translate_tree()), data_dimension), path, n, m) for i in range(pop_size)]
        if min(fitnesses) < best_of_run_f:
            best_of_run_f = min(fitnesses)
            best_of_run_gen = gen
            best_of_run = deepcopy(population[fitnesses.index(min(fitnesses))])
            # print("generation:", gen, ", best fitness value:", min(fitnesses))
        if best_of_run_f == 1:  # if get the same population
            break
    print(best_of_run_f)
    # print("\n\nEvolution End\nbest individual appears at generation " + str(best_of_run_gen) +
    #       " and has fitness value=" + str(best_of_run_f))
    # print(translate_data_placeholder(parse_tool.print_sexp(
    #     best_of_run.translate_tree()), data_dimension))


if __name__ == "__main__":

    parser = argparse.ArgumentParser()
    parser.add_argument("-question", type=int)
    parser.add_argument("-expr", type=str, help="an expression")
    parser.add_argument(
        "-n", type=int, help="the dimension of the input vector n")
    parser.add_argument("-x", type=str, help="the input vector")
    parser.add_argument(
        "-m", type=int, help="the size of the training data (X;Y)")
    parser.add_argument("-data", type=str, help="the name of a file")

    parser.add_argument("-lambda", "--pop_size",
                        type=int, help="population size")
    parser.add_argument("-time_budget", type=int,
                        help="the number of seconds to run the algorithm")

    args = parser.parse_args()

    if args.question is 1:
        question_1(args.n, args.x, args.expr)
    elif args.question is 2:
        question_2(args.data, args.expr, args.n, args.m)
    elif args.question is 3:
        question_3(args.data, pop_size=args.pop_size, n=args.n,
                   m=args.m, time_budget=args.time_budget)
