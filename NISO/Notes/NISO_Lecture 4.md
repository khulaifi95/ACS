# Nature-inspired Search & Optimisation

---

## Lecture 4 : Stochastic Local Search Algorithms



### 1. 2-Opt Algorithm

- Question: 

  How these tours of the 4 cities TSP differ?
  
  | <img src="NISO_Lecture 4.assets/Screenshot from 2020-02-11 13-30-42.png" alt="Screenshot from 2020-02-11 13-30-42" style="zoom: 67%;" /> |
  | :----------------------------------------------------------: |
  |       *Fig 1. Differences between neighbour solutions*       |

- Answer:

  Two **immediate neighbour** solutions can be two routes only differ from two edges.	

- Idea:

  **Swapping two edges** results in an immediate neighbour solution.



Given a route, e.g., A $\rightarrow$ C $\rightarrow$ B $\rightarrow$ D $\rightarrow$ A, how to swap two edges?

- Steps:
  1. Removal of two edges from the current route, which results in two parts of the route.
  2. Reconnect by two other edges to obtain a new solution.

- Example:

| <img src="NISO_Lecture 4.assets/Screenshot from 2020-02-11 13-35-39.png" alt="Screenshot from 2020-02-11 13-35-39" style="zoom:50%;" /> |
| :----------------------------------------------------------: |
|                *Fig 2. 6 cities TSP Problem*                 |

​	We need to **reverse the order** of B $\leftarrow$ C $\leftarrow$ D $\leftarrow$ E to get a one-directional cycle.



#### 2-Opt Algorithm:

```pseudocode
route := initial TSP solution
i,j := two cities for swapping
Step 1: take route[1] to route[i − 1] and add them in order to newroute
Step 2: take route[i] to route[j] and add them in REVERSE order to newroute
Step 3: take route[j + 1] to end and add them in order to new newroute
Output newroute
```



### 2. Randomised Search vs Local Search

- Randomised search
  - Good at *exploration*, e.g., to search large unknown region of the search space.
  - Bad at *exploitation*, e.g., to search small region around the current solution.
  - Especially bad for problems where good solutions are just a small portion of all possible solutions.

- Local search
  - Good at *exploitation*, e.g., capable to find local optimum.
  - Bad at exploration, e.g., **gets stuck into local optimum.**



### 3. Stochastic Local Search

- Question:

  How to find global optimum?

| <img src="NISO_Lecture 4.assets/Screenshot from 2020-02-11 13-48-32.png" alt="Screenshot from 2020-02-11 13-48-32" style="zoom: 50%;" /> |
| :----------------------------------------------------------: |
|     *Fig 3. Fitness Landscape with global/ local optima*     |

- Main idea: **escape** or **avoid** local optima

  - Introduce **randomness** into local search algorithm to escape from local optima.

  - Escape strategies

    - Random restart

      Simply restart the local search from a random initial solution.

    - Perform random non-improving step

      Randomly move to a **less fit neighbour** - *Simulated Annealing*.
  
  - Avoid strategies
    
    - *Tabu Search*
  
  

#### Hill Climbing Algorithm with Random Restart:

```pseudocode
for k := 0 to k_max
	x_0 := randomly generated initial solution
	terminationflag := False
	x_i := x_0
	while (terminationflag != True)
		Modify the current solution to an IMMEDIATE neighbour one
		if f(v) < f(x_i) then x_i := v
		if a termination criterion is met then terminationflag := True
	Store x_i
Output x_bset = min(x_i, for i=1,...,k_max)
```



### 4. Simulated Annealing

​	A generic heuristic algorithm for optimisation problems by Kirkpatrick in 1983.

- Inspiration: Real annealing

  1. Heating a material to above its critical temperature.
  2. Maintaining a suitable temperature.
  3. Cooling.

  

#### Generic Simulated Annealing Algorithm For Minimisation:

```pseudocode
x := x_0; e := f(x)	// initial solution
x_best := x; e_best := f(x)	// setting best solution
k := 0	// count evaluation number
while (k<k_max)
	T := temperature(t_0)	// temperature calculation
	x_new := neighbour(x)	// pick neighbours
	e_new := f(x_new)	// calculate objective function
	if P(e, e_new, T) > R(0,1) then	// move?
		x := x_new; e := e_new	// yes, change it
    if e_new < e_best then	// compare to previous best value
    	x_best := x_new; e_best := e_new
    k := k+1	// increase counter
Output x_best
```

​	where $p = \begin{cases}1 & if \ e_{new}<e \\ exp(\frac {e-e_{new}}{T}) & otherwise \end{cases}$, and 

​	$temperature()$ defines how to decrease temperature from an initial temperature $t_0$.



- Essentially a **stochastic local search** algorithm.
- Escape from local optima by a **random non-improving step**:
  - Accepting all better solutions, e.g., $P = 1$ when $e_{new}<e$.
  - Accepting **worse solutions** with a certain probability, e.g.,  $P = exp(\frac {e-e_{new}}{T})$ when $e_{new} > e$.

- This allows SA to explore more of the possible search space of solutions.

- $T \propto temperature()$, $T \downarrow $,  $\frac 1{T} \uparrow$, $e-e_{new}<0$, so $\frac {e-e_{new}}{T} \downarrow$, $P \downarrow$, e.g., 

  the probability of accepting worse solutions will decrease with iteration.

| <img src="NISO_Lecture 4.assets/Screenshot from 2020-02-11 14-46-31.png" alt="Screenshot from 2020-02-11 14-46-31" style="zoom: 50%;" /> |
| :----------------------------------------------------------: |
|      *Fig 4. Search trajectory of simulated annealing*       |

