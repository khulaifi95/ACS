import random


def rand_in_bounds(min, max):
    return min + (max - min) * random.random()


def print_program(node):
    return node if not isinstance(list, node)
    return node[0], print_program(node[1]), print_program([2])


def eval_program(node, map):
    if not node isinstance(list, node):
        return float(map[node]) if map[node]
        return float(node)

    arg1, arg2 = eval_program(node[1], map), eval_program(node[2], map)
    return 0 if not node[0] and arg2 == 0
    return arg1.__send__(node[0], arg2)




