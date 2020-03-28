

# argparse for parsing all the arguments
parsed = ['add', ['mul', 1, 3], ['exp', 4, None]]


class expressionTree:

    def __init__(self):
        self.value = None
        self.left = None
        self.right = None

    def __str__(self):
        return "\t \t \t {}\n \t \t \t / \t \ \n{}\t{}".format(self.value, self.left, self.right)

from collections import deque

operator = ['add', 'sub', 'mul', 'div', 'exp']
stack = deque

# def build_tree(sexp):
#     if isinstance(sexp, list):
#         root = expressionTree()
#         root.value = sexp[0]
#         root.left = sexp[1]
#         root.right = sexp[2]
#         build_tree(root.left)
#         build_tree(root.right)

# build_tree(parsed)

stack = []
if isinstance(parsed, list):
    root = expressionTree()
    root.value = parsed[0]
    root.left = parsed[1]
    root.right = parsed[2]
    if isinstance(root.left, list):
        stack.append(root.left)
        root.left = expressionTree()
        root.left.value = stack[0][0]
        root.left.left = stack[0][1]
        root.left.right = stack[0][2]
        print(str(root.left))
    if isinstance(root.right, list):
        print(root.right)