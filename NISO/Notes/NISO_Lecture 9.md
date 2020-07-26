# Nature-inspired Search & Optimisation



## Lecture 9 : Constraint Handling II



### 1. Stochastic ranking

#### 1.1 What is stochastic ranking?

Stochastic ranking is a special **rank-based** selection scheme that handles constraints.

- Balance objective function and penalty stochastically.

- There is no need to use any penalty functions.
- **Self-adaptive**: few parameters to set and also **not sensitive**.

It becomes one of the popular constraint handling techniques due to its effectiveness and simplicity.



#### 1.2 Ranking selection

Sort population of size $M$ from **best to worst** according to their fitness values:
$$
x'_{M-1}, x'_{M-2}, x'_{M-3}, \cdots,x'_{0}, 
$$
Select the top $\gamma$-ranke individual $x'_\gamma$ with probability $p(\gamma)$ as a ranking function:

- Linear ranking.
- Exponential ranking.
- Power ranking.
- Geometric ranking.



#### 1.3 Why not change the rank directly?

Ranking is sorting. 

- We can modify the sorting algorithm in EA to consider constraint violation.

- Stochastic ranking essentially is a modified bubble sort algorithm with some additional rules to handle $G$.



#### 1.4 Stochastic ranking

**Stochastic Ranking Algorithm**:

> **For** $j=1 \ \text{to} \ M$ do
>
> ​		**For** $i=2 \ \text{to} \ M$ do // *iterate over all pairs of individuals*
>
> ​				$u=U(0,1)$; // *uniformly distributed random number*
>
> ​				**If** $G(x'_{i-1})=G(x'_i)=0$ **OR** $u\leq P_f$ **then** // no constraint violation or random
>
> ​						**If** $f(x'_{i-1})<f(x'_i)$ **then** // *so that smaller one is at the front*
>
> ​								swap$(I_i, I_{i-1})$;
>
> ​								swap$(f(x'_i), f(x'{i-1}))$;
>
> ​								swap$(G(x'_i), G(x'_{i-1}))$;
>
> ​				**else** // *when there are constraint violations*
>
> ​						**If** $G(x'_{i-1})<G(x'_i)$ **then** // *only compare constraint violations*
>
> ​								swap$(I_i,I_{i-1})$;
>
> ​								swap$(f(x'_i), f(x'_{i-1}))$;
>
> ​								swap$(G(x'_i), G(x'_{i-1}))$;

where:

- $M$ is the number of individuals.
- $I$ is the indices of the individuals.
- $G(\cdot)$ is the sum of constraint violation.
- $P_f$ is a constant that indicates the **probability using objective function** for comparison.



#### 1.5 The role of $P_f$

**Question**: Why introduce $P_f$, which essentially enables infeasible solutions with better fitness to be ranked higher than feasible solution that are worse in fitness values with some probability?

**Answer**: To better deal with marginal solutions, we accept some infeasible ones with higher fitness values to encourage exploration to the edge of the feasible space.

- $P_f>0.5$:
  - Most comparisons are based on $f(x)$ only.
  - Infeasible solutions are more likely to occur.
- $P_f<0.5$:
  - Most comparisons are based on $G(x)$ only.
  - Infeasible solutions are less like to occur, but solutions might be poor.

As recommended in the paper, $P_f$ is often set between 0.45 and 0.5.



### 2. Feasibility rules

Based on binary tournament selection, after randomly choose $k\ (k=2)$ individuals.

Apply the following rules:

1. Between 2 feasible solutions, the one with better fitness value wins.
2. Between a feasible and an infeasible solution, the feasible one wins.
3. Between 2 infeasible solutions, the one with lowest penalty wins.

- **Pros**: Simple and parameter-free.
- **Cons**: Premature convergence without stochastic process.



### 3. Repair approach

Instead of modifying an EA or fitness function, infeasible individuals can be *repaired* into feasible ones.

- Let $I_s$ be an infeasible individual and $I_r$ be a feasible individual.

- We maintain two populations of individuals:
  - Population of evolving individuals. (feasible or infeasible)
  - Population of feasible reference individuals. (changing but not evolving)



| <img src="NISO_Lecture 9.assets/Screenshot 2020-07-26 at 11.10.34.png" style="zoom:67%;" /> |
| :----------------------------------------------------------: |
| **Fig 9.1** Repairing an infeasible individual with a reference |



**Repairing Algorithm**:

> **Initiation**: Select a reference individual $I_r$.
>
> **Do** the following update
>
> ​		$z_i = a_iI_s+(1-a_i)I_r, a_i\in (0,1)$
>
> **Until** individual $z_i$ is feasible.
>
> Calculate the fitness value of $z_i:f(z_i)$.
>
> **If** $f(z_i)\leq f(I_r)$ **then**
>
> ​		Replace $I_s$ by $z_i$.
>
> **Elseif** $u=U(0,1)\leq P_r$ **then**
>
> ​		Replace $I_s$ by $z_i$.

