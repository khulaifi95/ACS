# Nature-inspired Search & Optimisation



## Lecture 13 : Multi-Objective Optimisation I



### 1. Domination

$\mathbf x^{(1)}$ dominates $\mathbf x^{(2)}$ if

1. $\mathbf x^{(1)}$ is **no worse than** $\mathbf x^{(2)}$ in all objectives.
2. $\mathbf x^{(1)}$ is strictly better than $\mathbf x^{(2)}$ in at least one objective.



### 2. Pareto-optimal solutions

Among a set of solutions $P$, the **non-dominated** set of solutions $P'$ are not dominated by any member of the set $P$.

When $P=\mathcal S$, the resulting solutions $P'$ is the **Pareto-optimal set**.



| <img src="NISO_Lecture 13.assets/Screenshot 2020-07-26 at 15.46.46.png" style="zoom:67%;" /> |
| :----------------------------------------------------------: |
|              **Fig 13.1** Pareto-optimal fronts              |



### 3. Preference-based approach

Classical approaches follow steps:

1. Define a multi-objective optimisation problem optimising $f_1,f_2,\cdots,f_M$.
2. Estimate a relative importance vector $w_1,w_2,\cdots, w_M$.
3. Formulate a single-objective optimisation problem $F=w_1f_1+w_2f_2+\cdots+w_Mf_M$ or a composite function.
4. Find one optimum solution for the single-objective optimiser.



### 4. Weighted sum method

We construct a weighted sum of objectives and optimise:
$$
F(\mathbf x)=\sum_{m=1}^Mw_mf_m(\mathbf x)
$$
**Difficulties**:

- Need to supply $\mathbf w$.
- Non-uniformity in Pareto-optimal solutions.
- Inability to find some Pareto-optimal solutions.



### 5. Ideal multi-objective optimisation

The ideal multi-objective optimisation can be done in 2 steps:

1. Find a set of Pareto-optimal solutions.
2. Choose one from the set.

The ideal multi-objective optimisation should achieve 2 goals:

1. **Converge** on the Pareto-optimal front.
2. Maintain as **diverse** a **distribution** as possible.



### 6. Multi-objective evolutionary algorithms

Evolutionary algorithms are suitable methods for ideal MOO.

- **Population** approach suits well to find multiple solutions.
- **Niche-preservation** methods can be exploited to find diverse solutions.

History of development:

1. Early penalty-based approaches.
2. VEGA.
3. MOGA, NSGA, NPGA.
4. Elitist MOEAs.



| <img src="NISO_Lecture 13.assets/Screenshot 2020-07-26 at 16.03.31.png" style="zoom:67%;" /> |
| :----------------------------------------------------------: |
|    **Fig 13.2** Modify fitness computation in EA for MOO     |



### 7. Non-dominated sorting

We first need to identify the non-dominated set in population.

**Finding non-dominated set**:

> **Initiation**: $i=1$, create an empty set $P'$.
>
> **For** solution $j\in P, j\neq i$ do
>
> ​		**If** $j$ dominates $i$ **then**
>
> ​				$i = i+1$;
>
> ​				**If** $i > N$ **then**
>
> ​						break;
>
> ​		**Else if** more solutions are left in $P$ **then**
>
> ​				$j = j+1$;
>
> ​		**Else**
>
> ​				$P'=P'\cup\{i\}$;
>
> **End for**
>
> **Output** $P'$ as non-dominated set.



A simple non-dominated sorting algorithm follows:

1. Identify the best non-dominated set.
2. Discard them from population.
3. Identify the next-best non-dominated set,
4. Continue till all solutions are classified.

A $O(MN^2)$ complexity algorithm exists.

