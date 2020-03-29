# argparse for parsing all the arguments
parsed = ['add', ['mul', 1, 3], ['exp', 4, None]]
operator = ['add', 'sub', 'mul', 'div', 'exp']


class Node:

    def __init__(self, sexp):
        self.value = sexp[0]
        self.left = sexp[1]
        self.right = sexp[2]

    def __str__(self):
        return '{} \t {} \t {}'.format(
            self.value, self.left, self.right)


expressionTree = None
stack = []


def tree(root, sexp):
    root = Node(sexp)
    if isinstance(stack[0], list):
        stack.pop()
        stack.append(root.left)
        tree(root.left, stack[0])
        print(root.left)
    # if isinstance(stack[0], list):
    #     stack.pop()
    #     stack.append(root.left)
    #     tree(root.left, stack[0])
    #     print(root.right)



tree(expressionTree, parsed)