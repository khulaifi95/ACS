# Nature-inspired Search & Optimisation



## Lecture 11 : Niching and Speciation I



### 1. Niching

​	Niching refers to the formation of groups of individuals in a population. Individuals within a group are **similar** to each other, while individuals from different groups are very **different**.

- Niching is useful in a number of cases:
  - Encourage and maintain population diversity, thus explore a search space better.
  - Optimise **multiple objectives** simultaneously.
  - Learn an ensemble of machine learning systems that cooperate.
  - Simulate complex and adaptive systems, e.g. artificial ecological systems.
- Niching techniques:
  - Fitness sharing
  - Crowding
  - Sequential niching
  - Parallel hill-climbing



### 2. Fitness Sharing

​	Fitness sharing transforms the raw fitness of an individual into the shared one (lower).

- Only limited and fixed amount of "resources" (i.e., fitness) available at each niche.
- Individuals occupying the same niche will have to share the resources.
- Encourage different individuals but belittle individuals in a dense niche.



### 3. Sharing Radius

​	Sharing radius $\sigma_{share}$ defines the niche size. Individuals within this radius will be regarded as being similar to each other and thus need to share fitness.

- Similarity between two individuals is defined by the distance between them.

| <img src="NISO_Lecture 11.assets/Screenshot from 2020-02-25 10-41-40.png" alt="Screenshot from 2020-02-25 10-41-40" style="zoom: 50%;" /> |
| :----------------------------------------------------------: |
|               *Fig 1. Individuals in a niche*                |



### 4. Sharing Functions and Shared Fitness

- The **sharing function** can be defined as

$$
sh(d_{ij}) = \begin{cases}
    1-(\frac{d_{ij}}{\sigma_{share}})^\alpha,& d_{ij} < \sigma_{share}\\
    0 & otherwise
\end{cases}
$$

​		where $d_{ij}$ is the distance between individuals $i$ and $j$.

- The **shared fitness** of individual $i$ can be defined as

$$
f_{share}(i) = \frac {f_{raw}(i)}{\sum^\mu_{j=1}sh(d_{ij})}
$$

​		where $\mu$ is the population size.



### 5. Discussions

- Sharing can be done at genotypic or phenotypic levels.

  - Genotype $\rightarrow$ Hamming distance
  - Phenotype $\rightarrow$ Euclidean distance

  The key is to define the distance.

- Sharing radius $\sigma_{share}$ is difficult to set.
- Population size is particularly important in sharing.
- A population may not be able to locate all peaks. It may lose peaks located.
- Fitness sharing needs extra computation time. $O(MN^2)$
- Fitness sharing as described above may not work well.



### 6. Fitness Scaling

- To make fitness sharing work, raw fitness scaling is often needed as follows:

$$
f_{share}(i) = \frac {(f_{raw}(i))^\beta}{\sum^\mu_{j=1}sh(d_{ij})}
$$

​	where $\beta > 1$ is a scaling factor.

- If $\beta$ is too low: 
  - Individuals won't converge to the real optima because they are not attractive.
- If $\beta$ is too high:
  - "Super individuals" in initial populations may dominate the population quickly.
- Solution:
  - Large population
  - Soft selection
  - Anneal $\beta$, i.e., staring from 1 and increasing it gradually.



### 7. Implicit Fitness Sharing

​	The idea comes from the immune system, where antibody that best matches an invading antigen receives the payoff for that antigen.

- Assume we are dealing with a machine learning problem.
- For each test case $i$ to be solved, do the following $C$ times: ($C\uparrow$ $P\uparrow$ $T\uparrow$)
  1. Select a sample of $\sigma$ individuals from the population.
  2. Find the individual in the sample that achieves the best performance for solving $i$.
  3. This best individual receives the payoff, which would be shared evenly among all best individuals if they both have the best performance.



### 8. Comparison Between Two Sharing Methods

1. Implicit fitness sharing covers optima more **comprehensively** even when those optima have **small** basins of attraction, if the population size is large enough for a species to form at each optimum. - Specialise individuals

2. Explicit fitness sharing can find the optima with larger basins of attraction and **ignore** the ones with **smaller** bases, if the population size is not large enough to cover all optima.