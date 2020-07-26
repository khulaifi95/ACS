# Nature-inspired Search & Optimisation



## Lecture 8 : Constraint Handling I



### 1. Constrained optimisation

#### 1.1 What is constrained optimisation?
The general constrained optimisation problem is:
$$
\min_x{f(x)}
$$
subject to:
$$
 g_i(\mathbf x)\leq 0, i =1, \cdots, m \\ h_i(\mathbf x) = 0, j = 1, \cdots, p
$$
where:

- $\mathbf x$ is the *n* dimensional vector, $$ x=(x_1, x_2,\cdots,x_n)$$.
- $f(x)$ is the objective function.

- $g_i(x)$ is the inequality constraint.

- $h_j(x)$ is the equality constraint.



#### 1.2 Optimality in search space

Denote the whole search space as $\mathcal S$, the feasible space as $\mathcal F$. 

- We have $$ \mathcal F \in \mathcal S$$.
- The global optimum in $\mathcal F$ might not be the same as that in $\mathcal S$.



#### 1.3 Different types of constraints

- Linear constraints

  - Equality

  - Inequality

- Non-linear constraints

  - Equality

  - Inequality



### 2.	Constraint handling techniques

#### 2.1 Types of techniques
- **Purist approach**: rejects all infeasible solutions in search.
- **Separatist approach**: considers the objective function and constraints separately.

- **Penalty function approach**: converts a constrained problem into an unconstrained one by introducing a penalty function into the objective function.
- **Repair approach**: maps an infeasible solution into a feasible one.
- **Hybrid approach**: mixes two or more different constraint handling techniques.



#### 2.2 Penalty function approach

We introduce a new objective function:
$$
=\text{Original Objective Function} + \text{Penalty Coefficient} * \text{Degree of Constraint Violation}.
$$
The general form of the penalty function approach is
$$
 f'(\mathbf x) = f(\mathbf x)+\Big(\sum_{i=1}^mr_iG_i(\mathbf x) + \sum_{j=1}^pc_jH_j(\mathbf x)\Big)
$$
where $r_i, c_j$ are penalty factors/ coefficients, and $G_i, H_j$ are penalty functions for inequality and equality constraints respectively:
$$
G_i(\mathbf x) = \max(0, g_i(\mathbf x))^\beta \\ H_j(\mathbf x) = \max(0, |h_j(\mathbf x)|)^\gamma
$$
where $\beta, \gamma$ are usually chosen as 2.



#### 2.3 Static penalty functions
The static penalty function is pre-defined and **fixed **during evolution.

The general form of static penalty functions is:
$$
f'(\mathbf x) = f(\mathbf x) +\sum_{i=1}^m r_i(G_i(\mathbf x))^2
$$

- Equality constraints can be converted into inequality ones:

$$
 h_j(\mathbf x) \rarr h_j(\mathbf x) - \epsilon \leq 0
$$

​		where $\epsilon > 0$ is a small number.

- $r_i$ is determined with rich domain knowledge and a set of heuristic rules.



#### 2.4 Dynamic penalty functions
The dynamic penalty function changes according to a pre-defined sequence, which often depends on the **generation number**.

The general form of dynamic penalties is:
$$
 f'(\mathbf x) = f(\mathbf x)+r(t)\sum_{i=1}^mG_i^2(\mathbf x)+c(t)\sum_{j=1}^pH_j^2(\mathbf x)
$$
where $r(t)$ and $c(t)$ are two penalty coefficients.

- **General principle**: the larger the generation number *t*, the larger the penalty coefficients $r(t)$ and $c(t)$.
- **Heuristic**: There are less infeasible solutions in the final generations.



#### 2.5 Dynamic penalty coefficients
Common dynamic penalty coefficients include:

**Polynomials**: $$r(t)=\sum_{k=1}^Na_{k-1}t^{k-1}, c(t) = \sum_{k=1}^Nb_{k-1}t^{k-1}$$.

**Exponentials**: $$ r(t) = e^{at}, c(t) = e^{bt}$$.

**Hybrid**: $$ r(t) = e^{\sum_{k=1}^Nb_{k-1}t^{k-1}}, c(t) = e^{\sum_{k=1}^Nb_{k-1}t^{k-1}}$$.

where *a,b* are user-defined parameters.



#### 2.6 Penalty function and fitness selection
We now formulate the **minimisation **problem with a penalty function
$$
 \Phi(\mathbf x) = f(\mathbf x) + rG(\mathbf x), r>0
$$
Given two individual $\mathbf {x_1, x_2}$, their fitness values are now determined by $\Phi(\mathbf x)$.

- **Fitness proportional selection**: 

  Fitness values are used primarily in selection, thus changing fitness $\rarr$ changing selection probabilities.

- **Ranking selection**: For two individual $\mathbf {x_1, x_2}$,

$$
f(\mathbf x_1)+rG(\mathbf x_1)<f(\mathbf x_2)+rG(\mathbf x_2) \\ [f(\mathbf x_1)-f(\mathbf x_2)]+r[G(\mathbf x_1)-G(\mathbf x_2)]<0
$$

1. If $f(\mathbf x_1)\leq f(\mathbf x_2), G(\mathbf x_1)\leq G(\mathbf x_2)$: *r* has no impact on the comparison.
2. If $f(\mathbf x_1)< f(\mathbf x_2), G(\mathbf x_1)>G(\mathbf x_2)$: increasing *r* will change the comparison.
3. If $f(\mathbf x_1)> f(\mathbf x_2), G(\mathbf x_1)< G(\mathbf x_2)$: decreasing *r* will change the comparison.



#### 2.7 Penalties and fitness landscape transformation
- Different penalty functions can lead to different new objective functions.
- Inappropriate penalty functions can lead to infeasible results.

In the following figure, we should avoid $\Phi_2(x)$ which could select infeasible region as minimum over the global feasible optimum.

| <img src="NISO_Lecture 8.assets/Screenshot 2020-07-26 at 10.19.22.png" style="zoom:67%;" /> |
| :----------------------------------------------------------: |
| **Fig 8.1** Fitness landscape transformation with penalties  |



#### 2.8 Penalty functions demystified

Penalty function essentially:

- Transforms fitness.
- Changes rank $\rarr$ changes selection.

We avoid changing the rank directly in an evolutionary algorithm.

