# Nature-inspired Search & Optimisation

---

## Lecture 2 : Randomised Algorithms



> “For many problems, a randomised algorithm is the simplest, the
> fastest or both.” - Prabhakar Raghavan.



### 1. Categories of Algorithms by Design Paradigm

- Divide and conquer
- Dynamic programming
- Mathematical programming
- Search and enumeration
  - Brute force
  - Improved brute force
  - **Heuristic algorithms**
    - Local search
    - **Randomised algorithms**, including *Evolutionary Computation*, etc.



### 2. Heuristic Algorithms

- **Heuristic**: a usually simple algorithm that produces a **good enough** solution for a problem in a **reasonable time frame**.
- Solutions are usually non-optimal but satisfactory
  - Faster
  - Trade off completeness and accuracy for speed
- Usually to solve problems that are otherwise difficult
- Includes deterministic and **randomised algorithms**



### 3. Randomised Algorithms

​	An heuristic algorithm that **makes random choices** during execution to produce a result.

- Takes a source of random numbers to make random choices

- Behaviour (output) or running time can *vary* even on a fixed input.
- **Goal**: To design an algorithm and analyse it to show that its behaviour is likely to be good on **every input**.

- Two categories:
  - [x] Using random numbers to **find** a solution to a problem
  - [ ] Using random numbers to **improve** a solution to a problem



### 4. Las Vegas & Monte Carlo Algorithms

​	Given an array of $n$ elements, find the first element of which the value equals to $x$.

#### Las Vegas algorithm:

```pseudocode
begin
	repeat
		Randomly select one element a out of n elements.
	until a == x
end
```

​	A randomised algorithm that always gives **correct results**, the only variation from one run to another is the running time.



#### Monte Carlo algorithm:

```pseudocode
begin
	i:=0
	repeat
		Randomly select one element a out of n elements.
		i:=i+1
	until (a==x)||(i==k)
end
```

​	A randomised algorithm whose running time is deterministic, but whose results may be incorrect with a certain probability.



#### Differences:

- Monte Carlo algorithm runs for a fixed number of steps
- Las Vegas algorithm runs in an infinite loop until the correct results are found
- Las Vegas algorithm can be converted to Monte Carlo using early termination



### 5. Why use randomised algorithms?

​	For the simple element search problem,

- Deterministic sequential search algorithms, e.g.,  search the array one by one from the beginning.
  - Average time complexity: $O(\frac {n+1} {2}) \cong O(n)$
  - Worst running time: $O(n)$
- Las Vegas algorithm
  - Average time: depends on the input (probability model)
  - Worst running time is **unbound**. (could be super unlucky)

- Monte Carlo algorithm
  - The running time complexity is fixed: $O(1)$
  - But with some probability of error (not finding the element)



### 6. Randomised Quicksort Algorithm

​	A disorganised carpenter has a collection of $n$ nuts of distinct sizes and $n$ bolts and would like to find the **corresponding pairs** of bolts and nuts. Each nut matches exactly one bolt (and vice versa), and he can only compare nuts to bolts, i.e., he can neither compare nuts to nuts nor bolts to bolts.

- **Think out of the box**: What if the carpenter can compare nuts to nuts or bolts to bolts?
  - He can sort both nuts and bolts then pair them with the same rank.
- The sorting problem: given an array $A$ of $n$ numbers, sort the numbers in increasing order.



#### Randomised Quicksort Algorithm:

```pseudocode
less, equal, greater := three empty arrays
if length(array) > 1
	pivot := select an random element of array
	for each x in array
		if x < pivot then add x to less
		if x = pivot then add x to equal
		if x > pivot then add x to greater
quicksort(less)
quicksort(greater)
array:=concatenate(less, equal, greater)
```



| Quicksort     | Average Complexity | Worst Complexity               |
| ------------- | ------------------ | ------------------------------ |
| Deterministic | $O(n\log n)$       | $O(n^2)$                       |
| Randomised    | $O(n \log n)$      | $O(2n\log n) \cong O(n\log n)$ |
|               |                    |                                |

### 7. Applications of Randomised Algorithms

- Mathematics
  - Number theory - primality test
  - Computational geometry - graph algorithms
  - Linear algebra - matrix computations
- Computer Science
  - Data analysis - PageRank
  - Parallel computing - Deadlock avoidance
  - Optimisation - Nature-inspired optimisation and search
- Computational Biology
  - DNA read alignment



### 8. Advantages/ Disadvantages of Randomised Algorithms

#### Advantages:

- Simplicity
- Performance

#### Disadvantages:

- Getting a wrong answer with a finite probability
  - Solution - repeat the algorithm
- Difficult to analyse the running time and probability of getting an incorrect solution.
- Impossible to get truly random numbers.

