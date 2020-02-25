# Nature-inspired Search & Optimisation

---

## Lecture 11 : Niching and Speciation



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

- The sharing function can be defined as

$$
sh(d_{ij}) = \begin{cases}
    1-(\frac{d_{ij}}{\sigma_{share}})^\alpha,& d_{ij} < \sigma_{share}\\
    0 & otherwise
\end{cases}
$$

​		where $d_{ij}$

