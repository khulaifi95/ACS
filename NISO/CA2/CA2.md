# NISO CA 2



### Exercise 1

​	Implement a routine to parse and evaluate expressions. 

- Input: -n 1 -x "1.0" -expr "(mul (add 1 2) (log 8))"
- Output: 9.0

1. The expression was parsed in as a nested list..
2. First place all the elements in a binary expression tree.
3. Then use post-fix traverse to evaluate the expression.
4. Fix the issue of input $\neq$ 3.
5. Fix the issue of multi-dimensional input.
6. 