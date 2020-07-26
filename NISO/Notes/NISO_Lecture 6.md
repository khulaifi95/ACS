# Nature-inspired Search & Optimisation



## Lecture 6 : Evolutionary Algorithms II



### 1. Selection

​	Selection is not a search operator but influences **search performance** significantly.

- Usually is performed before variation operators to select better fit individuals.

- Emphasises on **exploiting** better solutions in a population.
  - Select one or more copies of *good* solutions.
  - *Inferior* solutions will be selected but with a much less **probability**.

- Selection Schemes
  - Relative fitness
    - Fitness proportional selection
  - Ranking
    - Ranking selection
    - Truncate selection
    - Tournament selection
    - ($\mu + \lambda$) and ($\mu, \lambda$) selection



### 2. Fitness Proportional Selection

​	Selecting individual $i$ with a probability:
$$
p_i = \frac {f_i}{\sum^M_{j=1} f_j},
$$
​	where $f_i$ is the **fitness value** of individual $i$, $M$ is the number of individuals.

- Does not allow negative fitness value.
- $p_i$ is essentially the **proportion** of the fitness value of individual $i$ to the total fitness value.
  - Individuals fitness values $\uparrow$, selected $\uparrow$.
  - Individuals with low fitness may survive the selection process.
    - Allows weak individuals who may help escape from local optima.

- Problems:
  1. "Super individuals" in early generations could cause **premature** convergence to a local optimum.
  2. Not many separation among individuals in later generations indicates a **slow** convergence.
- Need to maintain the same selection pressure throughout the run.
  - **Linear scaling**:

$$
f^\prime_i=a+b.f_i
$$

​					where $a$ and $b$ are constants, usually set as:
$$
a = max(\mathbf f)\\b=min(\mathbf f)/M <1
$$
​					where $\mathbf f = f_1, f_2,..., f_M$ .

| <img src="NISO_Lecture 6.assets/Screenshot from 2020-02-12 00-28-45.png" alt="Screenshot from 2020-02-12 00-28-45" style="zoom:33%;" /> |
| :----------------------------------------------------------: |
|        *Fig 1. Fitness scaling using linear scaling*         |



### 3. Ranking Selection

​	Sort population size of $M$ from **best to worst** according to fitness values:
$$
x^\prime_{(M-1)},x^\prime_{(M-2)},x^\prime_{(M-3)},...,x^\prime_{(0)},
$$
​	Select the top $\gamma$-ranked individual $x'_\gamma$ with a **probability** $p(\gamma)$, where $\gamma$ is the rank and $p(\gamma)$ is a ranking function.

- Linear ranking function:

$$
p(\gamma) = \frac {\alpha+(\beta-\alpha).\frac{\gamma}{M-1}}{M}
$$

​		where $\sum^{(M-1)}_{\gamma=0}p(\gamma)=1$ implies that $\alpha + \beta =2$ and $1 \leq \beta \leq 2$. (*Sum up RHS*)

  - Expectation:
	- For best individual, i.e., $\gamma = M-1$, reproduced $\beta$ times: $p(M-1) = \frac {\beta}{M}$.
	- For worst individual, i.e., $\gamma = 0$, reproduced $\alpha$ times: $p(0) = \frac \alpha M$.

​		**Question**: How to set parameters so that EA behave like a random search?

​		**Answer**: Set $\alpha = \beta$.

| <img src="NISO_Lecture 6.assets/Screenshot from 2020-02-12 00-08-19.png" alt="Screenshot from 2020-02-12 00-08-19" style="zoom:33%;" /> |
| :----------------------------------------------------------: |
|        *Fig 2. Parameters of linear ranking function*        |



- Exponential ranking function:

$$
p(\gamma) = \frac {1-e^{-\gamma}}{C}
$$



- Power ranking function:

$$
p(\gamma) = \frac {\alpha+(\beta-\alpha).(\frac {\gamma}{M-1})^k}{C}
$$



- Geometric ranking function:

$$
p(\gamma) = \frac {\alpha.(1-\alpha)^{M-1-\gamma}}{C}
$$



### 4. Truncate Function

​	The simplest, deterministic ranking selection.

- Steps:
  1. Rank individuals by fitness values.
  2. Select some proportion $k$ of the top ranked individuals.
- Usually, $k = 0.5$ (top 50%) or $k = 0.3$ (top 30%).



### 5. Tournament Selection

​	One of the most popular selection methods in genetic algorithms.

- Tournament selection with tournament size $k$:
  1. Randomly sample a subset $P'$ of $k$ individuals from population $P$.
  2. Select the individual in $P'$ with highest fitness.
  3. Repeat until enough offspring are created.
- Binary tournament selection ($k =2$) is the most used.



### 6. ($\mu + \lambda$) and ($\mu, \lambda$) selection

​	First proposed in Evolution Strategies.

- ($\mu + \lambda$) selection:
  - Parent population of size $\mu$
  - Generate $\lambda$ offspring from randomly chosen parents.
  - Select $\mu$ best individuals **among parents and offspring**. ($\lambda + \mu \rightarrow \mu$)
- ($\mu, \lambda$) selection: ($\lambda > \mu$)
  - Parent population of size $\mu$
  - Generate $\lambda$ offspring from randomly chosen parents.
  - Select $\mu$ best individuals **among offspring**. ($\lambda \rightarrow \mu$)



### 7. Selection Pressure

​	Degree to which selection emphasises on the **better** individuals.

- **Question 1**: How does selection pressure affect the balance between exploitation and exploration?

  - Higher selection pressure $\rightarrow$ exploitation $\rightarrow$ fast convergence to local optimum, e.g., premature.
  - Lower selection pressure $\rightarrow$ exploration $\rightarrow$ slow convergence.

  

- **Question 2**: Given an EA with a selection scheme, how to measure selection pressure?

  - **Take-over time** $\tau^*$:

    Assume population is $M$ and initial population with one unique fittest individual $x^*$. Apply selection repeatedly without other operator, $\tau^*$ is the **number of generations** until population consists of $x^*$ only.

  - Take-over time &uarr; $\rightarrow$  Selection pressure &darr; 

  

- Selection pressure for different methods:

  | Selection function   | $\tau^* \approx$                         | Note                                                 |
  | -------------------- | ---------------------------------------- | ---------------------------------------------------- |
  | Fitness proportional | $\frac {M\ln M}{c}$                      | Assuming a power law objective function $f(x) = x^c$ |
  | Linear ranking       | $\frac {2\ln(M-1)}{\beta-1}$             | $1 \leq \beta \leq 2$                                |
  | Truncation           | $\ln M$                                  |                                                      |
  | Tournament           | $\frac {\ln M}{\ln \lambda}$             | Tournament size k                                    |
  | ($\mu + \lambda$)    | $\frac {\ln \lambda}{\ln (\lambda/\mu)}$ |                                                      |
  |                      |                                          |                                                      |

### 8. Reproduction

​	Reproduction controls how genetic algorithm creates the next generation.

- Generational EA:

  Also called standard EA, uses **all new individuals** after variations to replace the  worse individuals in the old population to create a new population.

- Steady state EA:

  Only use **a few or even one** single new individual to replace the old population at any one time.

- $n$-Elitism: 

  Always copy the $n$ best individuals to the next generation.

- Generational gap:

  The overlap between the old and new generations.