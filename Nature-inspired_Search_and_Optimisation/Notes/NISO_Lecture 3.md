## Lecture 3 : Optimisation Problems and Local Search Algorithms



### 1. Travelling Salesman Problem (TSP)

​	Given a list of cities and the distances between each pair of them, to find the **shortest route** that visits each city **exactly once** and returns to the origin city.

- Irish mathematician, William. R. Hamilton 
- One of the most prominent and widely studied combinatorial optimisation problems
- Many optimisation problems can be formulated as TSP or its variations
- Conceptually simple but **computationally difficult**



### 2. What is optimisation?

​	To find the best or optimal solution to a problem.

- Definition
  - Given a function $f(x): A \rightarrow \mathbb R$ from set $A$ to the real numbers, to find an element $x^* \in A$ such that $f(x^*) \leq/\geq f(x)$ for all $x$ in $A$. (Min./ Max.)
- Function $f(x)$ is called **objective function**, or cost function in minimisation, fitness function in maximisation and Evolutionary Computation.
- $A$ is called **feasible set**, which is some subset of the Euclidean space specified by a set of constraints.
- The domain $A$ of $f(x)$ is called the **search space**, while the elements of $A$ e.g. $x \in A$ are called candidate solutions or feasible solutions.



### 3. Categories of Optimisation Problems

​	Depends on the nature of objective function:

- Linear vs non-linear
  - Linear function
    - Additivity: $f(x+y) = f(x) + f(y)$
    - Homogeneity: $f(\alpha x) = \alpha f(x)$ for all $\alpha$
  - Non-linear
    - Convex vs non-convex
    - Much more difficult
- Multi-objective vs single-objective
- Constrained vs non-constrained

​	Depends on the nature of solutions:

- Continuous vs discrete (combinatorial)



### 4. Which algorithm to solve TSP?

- Heuristic algorithms
  - TSP is difficult for other algorithms.

- Randomised algorithms
  - Monte Carlo algorithm
    - No optimised target.

- Using array of random permutation to generate random solutions.



### 5. Randomised Search Algorithms

​	A small TSP problem: 48 capitals of the US (called ATT48), of which the known
optimal solution by some brute force algorithms search is 10628.

- Observation:

  Monte Carlo returns **bad results**. Why?

- Answer:

  The number of good solutions of TSP is just a very tiny portion of a vast search space.



### 6. Local Search Algorithms

​	A heuristic algorithm for solving hard optimisation problems.

- Idea: 

  Start with an initial guess at a solution and **incrementally improve it** until found.

- Incremental improvement:

  **Local changes**, e.g., the algorithm iteratively moves to a **neighbour solution**.

- Neighbour solution:

  Depends on the definition of a neighbourhood relation on the search space, but generally based on **similarity** (distance) measure.

  

#### Generic local search algorithm:

```pseudocode
x0 := generate initial solution
terminationflag := False
x := x0
while (terminationflag != True)
	Modify the current solution to a neighbour one v in A
	if f(v) < f(x) then x := v
	if a termination criterion is met
		terminationflag := True
Output x
```

[^Note]: A termination criterion could be maximum iteration is reached or no improvement for a certain iterations.



### 7. Hill climbing algorithm:

- One of the simplest local search algorithms
- An iterative algorithm:
  - Starts with an arbitrary solution to a problem
  - Searches a better solution from the current solution's **immediate neighbour solutions** (most similar solutions to the current one)

- Two types of hill climbing:
  1. Simple hill climbing: chooses the **first** better solution
  2. Steepest ascent/ descent hill climbing: compares all neighbour solutions and chooses **the best** from them - greedy search



#### Simple hill climbing algorithm:

```pseudocode
x0 := generate initial solution
terminationflag := False
x := x0
while (terminationflag != True)
	Modify the current solution to a **immediate** beighbour one v in A
	if f(v) < f(x) then x := v
	if a termination criterion is met
		terminationflag := True
Output x
```

- Question:

  How to construct the **immediate neighbour solutions** of the current one for TSP?