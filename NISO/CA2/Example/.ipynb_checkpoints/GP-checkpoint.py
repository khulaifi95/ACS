import math
import argparse
from random import random, randint, seed
from copy import deepcopy
from datetime import datetime

from ParseTool import ParseTool

MIN_DEPTH = 2  # minimal initial random tree depth
MAX_DEPTH = 5  # maximal initial random tree depth
GENERATIONS = 100  # maximal number of generations to run evolution
TOURNAMENT_SIZE = 5  # size of tournament for tournament selection
XO_RATE = 0.8  # crossover rate
PROB_MUTATION = 0.2  # per-node mutation probability

FUNCTIONS = ["add", "sub", "mul", "div", "max"]
TERMINALS = [0, 1, 2]

parse_tool = ParseTool()


class Cal:
    def __init__(self, n, x, expr):
        """

        :param n: the dimension of the input vector n
        :param x: the input vector
        :param expr: an expression
        """
        self.n = n
        self.input = list(map(float, x.split()))
        self.expr = expr
        self.parsed = parse_tool.parse_sexp(self.expr)
        # print("Parsed to Python:", self.parsed)
        return

    def calculate_expression(self):
        result = self.calculate_meta_expression(self.parsed)
        return result

    def calculate_meta_expression(self, me):
        """

        :param me: meta_expression
        :return:
        """
        # print("input: ", me)
        if len(str(me)) == 1: return me
        operator = me[0]
        for i in range(1, len(me)):
            if isinstance(me[i], list) is True:
                # print(">>>>")
                # print(me[i])
                me[i] = self.calculate_meta_expression(me[i])
            # else:
            # print(item + " is not list")
        if operator == "add":
            return me[1] + me[2]
        elif operator == "sub":
            return me[1] - me[2]
        elif operator == "mul":
            return me[1] * me[2]
        elif operator == "div":
            if me[2] != 0:
                return float("%0.3f" % (me[1] / me[2]))
            else:
                return 0
        elif operator == "pow":
            return float("%0.3f" % pow(me[1], me[2]))
        elif operator == "sqrt":
            return math.sqrt(me[1])
        elif operator == "log":
            return math.log(me[1], 2)
        elif operator == "exp":
            return math.exp(me[1])
        elif operator == "max":
            return max(me[1], me[2])
        elif operator == "ifleq":
            if me[1] <= me[2]:
                return me[3]
            else:
                return me[4]

        elif operator == "data":
            return self.input[me[1] % self.n]
        elif operator == "diff":
            k = me[1] % self.n
            l = me[2] % self.n
            return self.input[k] - self.input[l]
        elif operator == "avg":
            k = me[1] % self.n
            l = me[2] % self.n
            start = min(k, l)
            end = max(k, l)
            sum = 0
            for i in range(start, end):
                sum += self.input[i]
            return sum / abs(k - l)


class GPTree:
    def __init__(self, data=None, left=None, right=None):
        self.data = data
        self.left = left
        self.right = right

    def node_label(self):  # string label
        if (self.data in FUNCTIONS):
            return self.data
        else:
            return str(self.data)

    def translate_tree(self):
        if self.data in TERMINALS:
            estimate_equsion = self.node_label()
        else:
            estimate_equsion = [self.node_label()]

        if self.left: estimate_equsion.append(self.left.translate_tree())
        if self.right: estimate_equsion.append(self.right.translate_tree())
        return estimate_equsion

    def random_tree(self, grow, max_depth, depth=0):  # create random tree using either grow or full method
        if depth < MIN_DEPTH or (depth < max_depth and not grow):
            self.data = FUNCTIONS[randint(0, len(FUNCTIONS) - 1)]
        elif depth >= max_depth:
            self.data = TERMINALS[randint(0, len(TERMINALS) - 1)]
        else:  # intermediate depth, grow
            if random() > 0.5:
                self.data = TERMINALS[randint(0, len(TERMINALS) - 1)]
            else:
                self.data = FUNCTIONS[randint(0, len(FUNCTIONS) - 1)]
        if self.data in FUNCTIONS:
            self.left = GPTree()
            self.left.random_tree(grow, max_depth, depth=depth + 1)
            self.right = GPTree()
            self.right.random_tree(grow, max_depth, depth=depth + 1)

    def mutation(self):
        if random() < PROB_MUTATION:  # mutate at this node
            self.random_tree(grow=True, max_depth=2)
        elif self.left:
            self.left.mutation()
        elif self.right:
            self.right.mutation()

    def size(self):  # tree size in nodes
        if self.data in TERMINALS: return 1
        l = self.left.size() if self.left else 0
        r = self.right.size() if self.right else 0
        return 1 + l + r

    def build_subtree(self):  # count is list in order to pass "by reference"
        t = GPTree()
        t.data = self.data
        if self.left:  t.left = self.left.build_subtree()
        if self.right: t.right = self.right.build_subtree()
        return t

    def scan_tree(self, count, second):  # note: count is list, so it's passed "by reference"
        count[0] -= 1
        if count[0] <= 1:
            if not second:  # return subtree rooted here
                return self.build_subtree()
            else:  # glue subtree here
                self.data = second.data
                self.left = second.left
                self.right = second.right
        else:
            ret = None
            if self.left and count[0] > 1: ret = self.left.scan_tree(count, second)
            if self.right and count[0] > 1: ret = self.right.scan_tree(count, second)
            return ret

    def crossover(self, other):  # xo 2 trees at random nodes
        if random() < XO_RATE:
            second = other.scan_tree([randint(1, other.size())], None)  # 2nd random subtree
            self.scan_tree([randint(1, self.size())], second)  # 2nd subtree "glued" inside 1st tree


# end class GPTree

def init_population(pop_size):  # ramped half-and-half
    pop = []
    for md in range(3, MAX_DEPTH + 1):
        for i in range(int(pop_size / 6)):
            t = GPTree()
            t.random_tree(grow=True, max_depth=md)  # grow
            pop.append(t)
        for i in range(int(pop_size / 6)):
            t = GPTree()
            t.random_tree(grow=False, max_depth=md)  # full
            pop.append(t)
    missing_num = pop_size - len(pop)
    if missing_num > 0:
        for i in range(missing_num):
            t = GPTree()
            t.random_tree(grow=False, max_depth=md)  # full
            pop.append(t)
    return pop


def fitness(expr, path):
    """
    Calculate the fitness value
    :param expr: an expression
    :param data: the name of a file containing the training data
    :return: float 123.5
    """
    data = load_data(path)
    # the dimension of the input vector
    n = len(data[0].split()) - 1
    # the size of the training data (X;Y)
    m = len(data)
    distance_sum = 0
    for i in range(0, m):
        cal = Cal(n, data[i], expr)
        e = float('%0.3f' % cal.calculate_expression())
        # print("sub value: ", e)
        y = float('%0.3f' % float(data[i].split()[n]))
        if abs(e) / abs(y) > 1000000 or abs(e) / abs(y) < 0.000001:
            distance_sum += 99999
        else:
            distance_sum += float('%0.3f' % pow((y - e), 2))
        # print("total value: ", distance_sum)
    fitness_value = distance_sum / m
    return fitness_value


def selection(population, fitnesses):  # select one individual using tournament selection
    tournament = [randint(0, len(population) - 1) for i in range(TOURNAMENT_SIZE)]  # select tournament contenders
    tournament_fitnesses = [fitnesses[tournament[i]] for i in range(TOURNAMENT_SIZE)]
    return deepcopy(population[tournament[tournament_fitnesses.index(max(tournament_fitnesses))]])


def load_data(path):
    """
    Load data from file
    :param path: The path of the data file
    :return: list ["1.0 2.0,...", "2.0, 4.0, ...", ...]
    """
    f = open(path)
    line = f.readline()
    data = []
    while line:
        data.append(line)
        line = f.readline()
    f.close()
    return data


def gen_terminaters(data_demention):
    TERMINALS.extend([":data_" + str(i) for i in range(data_demention)])


def translate_data_placeholder(individual, data_demention):
    for i in range(data_demention):
        individual = individual.replace(":data_{}".format(i), '(data {})'.format(i))
    return individual


def question_1(n, x, expr):
    cal = Cal(n, x, expr)
    result = cal.calculate_expression()
    print(result)


def question_2(path, expr):
    print(fitness(expr, path))


def question_3(path, pop_size, time_budget):
    # init stuff
    seed()  # init internal state of random number generator
    dataset = load_data(path)
    # the dimension of the input vector
    data_demention = len(dataset[0].split()) - 1
    gen_terminaters(data_demention)
    population = init_population(pop_size)
    best_of_run = None
    best_of_run_f = 0
    best_of_run_gen = 0

    fitnesses = [
        fitness(translate_data_placeholder(parse_tool.print_sexp(population[i].translate_tree()), data_demention), path)
        for i in range(pop_size)]
    best_of_run_f = min(fitnesses)
    best_of_run = deepcopy(population[fitnesses.index(min(fitnesses))])

    # evolution
    start_time = datetime.now()
    for gen in range(GENERATIONS):
        end_time = datetime.now()
        if (end_time - start_time).seconds >= time_budget: break
        # print("now process gen: ", gen)
        nextgen_population = []
        for i in range(pop_size):
            parent1 = selection(population, fitnesses)
            parent2 = selection(population, fitnesses)
            parent1.crossover(parent2)
            parent1.mutation()
            nextgen_population.append(parent1)
        population = nextgen_population
        fitnesses = [
            fitness(translate_data_placeholder(parse_tool.print_sexp(population[i].translate_tree()), data_demention),
                    path) for i in range(pop_size)]
        if min(fitnesses) < best_of_run_f:
            best_of_run_f = min(fitnesses)
            best_of_run_gen = gen
            best_of_run = deepcopy(population[fitnesses.index(min(fitnesses))])
            # print("________________________")
            # print("generation:", gen, ", best fitness value:", min(fitnesses))
            # # best_of_run.print_tree()
            # print("best individual is: ", translate_data_placeholder(parse_tool.print_sexp(best_of_run.translate_tree())))
        if best_of_run_f == 1: break

    # print("\n\n_________________________________________________\nEvolution End\nbest individual appears at generation " + str(
    #     best_of_run_gen) + \
    #       " and has fitness value=" + str(best_of_run_f))
    # best_of_run.print_tree()
    print("best individual is: ",
          translate_data_placeholder(parse_tool.print_sexp(best_of_run.translate_tree()), data_demention))


if __name__ == "__main__":
    # main()

    # --------------- TEST CASES ---------------#
    # test question 1:
    #   cmd: python GP.py -question 1 -n 1 -x "1.0" -expr "(mul (add 1 2) (log 8))"
    #   output: 9.0

    # test question 2:
    #   cmd: python GP.py -question 2 -n 5 -m 4 -expr "(add (mul (max (data 0) (data 1)) (max (data 2) (data 3))) (data 4))" -data "./data.txt"
    #   output: 124.5

    # test question 3:
    #   cmd: python GP.py -question 3 -lambda 20 -n 5 -m 4 -data "./data.txt" -time_budget 10
    #   output: str, the best individual
    # --------------- TEST CASES ---------------#

    parser = argparse.ArgumentParser()
    parser.add_argument("-question", type=int)
    parser.add_argument("-expr", type=str, help="an expression")
    parser.add_argument("-n", type=int, help="the dimension of the input vector n")
    parser.add_argument("-x", type=str, help="the input vector")

    parser.add_argument("-m", type=int, help="the size of the training data (X;Y)")
    parser.add_argument("-data", type=str, help="the name of a file")

    parser.add_argument("-lambda", "--pop_size", type=int, help="population size")
    parser.add_argument("-time_budget", type=int, help="the number of seconds to run the algorithm")

    args = parser.parse_args()
    if args.question is 1:
        question_1(args.n, args.x, args.expr)
    elif args.question is 2:
        question_2(args.data, args.expr)
    elif args.question is 3:
        question_3(args.data, pop_size=args.pop_size, time_budget=args.time_budget)
