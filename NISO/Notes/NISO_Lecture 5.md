# Nature-inspired Search & Optimisation

---

## Lecture 5: Evolutionary Algorithms I



### 1. Evolution

​		**Change** in the **inherited characteristics** of  populations over generations.

 - Inherited characteristics - heritable traits

 - Change

    - *Mutations*: changes in the DNA sequence

    - *Crossover*: reshuffling of genes between populations

      

   1) Driving force of evolution - natural selection

    - Living species are *reproductions* of earlier species with genetic variations

    - genetic variations that **enhance** survival remain **more common**. 

      

   2) Relationship between evolution and optimisation

   - Living species are **reproductions** of earlier species with genetic variations.
   - Fitness $\rightarrow$ objective functions in optimisation
   - Individuals of a specie $\rightarrow$ solutions in optimisation



### 2. Evolutionary Algorithms

​	A subset of metaheuristic algorithm inspired by biological evolution.

- Essentially a kind of **stochastic local search** algorithm.
  - Genetic Algorithm
  - Evolutionary Programming/ Strategies
  - Differential Evolution

- **Population based**: 

  Generate, maintain and optimise a population of candidate solutions.



#### Generic Evolutionary Algorithm:

```pseudocode
X_0 := generate initial population of solutions
terminationflag := False
t := 0
E_0 := fitness evaluation of each individual in X_0
while (terminationflag != True)
	1) Selection: Select parents from X_t based on E_0.
	2) Variation: Breed new individuals by applying variaton operators to parents.
	3) Fintess calculation: Evaluate the fitness of new individuals.
	4) Reproduction: Generate population X_t+1 by replacing least-fit individuals.
	t := t+1
	if a termination criterion is met then terminationflag := True
Output x_best
```



### 3. Building Blocks of Evolutionary Algorithms

- An evolutionary algorithm consists of:
  - **representation**
  - **fitness function**
  - **variation operators** - mutation and crossover
  - **selection and reproduction** - survival of the fittest

- Finding global optimum is about the balance of **exploration** and **exploitation**.
- How to achieve the balance?
  - Variation operators - *Exploration*, e.g., expand the search space.
  - Selection and reproduction - *Exploitation,* e.g., focus on better solutions.



### 4. Representation

​	A way to represent (encode) solutions.

| Solutions                   | Representation      |
| --------------------------- | ------------------- |
| Phenotype                   | Genotype            |
| Fitness function evaluation | Variation operators |

| <img src="NISO_Lecture 5.assets/Screenshot from 2020-02-11 15-31-47.png" alt="Screenshot from 2020-02-11 15-31-47" style="zoom:50%;" /> |
| :----------------------------------------------------------: |
|               *Fig 1. Representation process*                |

- Types of representations
  - Binary
  - Real number
  - **Random key**
  - Permutation



### 5. Binary Representation

​	The most popular representation used in Genetic Algorithms.

- Represent an individual solution as a **bit string** of length $L$.
  - solutions - phenotype
  - $\vec a \in$ {0,1}$^L$ - genotype
  - Encoding function - mapping phenotype to genotype
  - Decoding function - mapping genotype to phenotype
- **Question**: How to use a bit string to represent a continuous solution?
  - Divide $\vec a \in${0,1}$^L$ into $n$ segments of equal length $\vec s_i \in$ {0,1}$^\frac{L}{n}$, $i = 1, ..., n$.
  - Decode each segment into an integer $K_i, i=1,..., n$, and $K_i = \sum^{\frac {L}{n}}_{j=0}s_{i_j}.2^j$.
  - Apply decoding function $h(K_i)$, i.e., map the integer linearly into the interval bound $x_i \in [u_i, v_i]$.

$$
h(K_i) = u_i + K_i.\frac{v_i-u_i}{2^{\frac L{n}}-1}
$$

- **Example**: assume $\mathbf x$ = {$x_1, x_2, x_3$}  and $\mathbf x \in [-5,5]$. Use a bit string of length $L =12$, therefore 4 bits per segment $s$. 

| <img src="NISO_Lecture 5.assets/Screenshot from 2020-02-11 16-06-02.png" alt="Screenshot from 2020-02-11 16-06-02" style="zoom:50%;" /> |
| :----------------------------------------------------------: |
|                *Fig 2. Decoding example of S*                |
|           $h(K_i) = -5+15*\frac {5-(-5)}{15} = 5$.           |



### 6. Mutation

​	**Flip** each bit with a probability $p_m$, a mutation rate.

- Standard mutation rate: $p_m = \frac 1{L}$, but can be $\in [\frac 1 L, \frac 1 2]$.
- **Example**:

| Parent   | Mutation                       |
| -------- | ------------------------------ |
| 00101011 | 0**<u>1</u>**1010**<u>0</u>**1 |
| 10110010 | 1011**<u>1</u>**0**<u>01</u>** |

- If mutation rate $p_m$ is **small**, mutation can be seen as creating a small *random perturbation* on the parent genotype.
  - Mutated offspring is very similar to its parent, i.e., it stays close to the parent in the genotype search space.(Hamming distance)
  - Together with selection, mutation actually is **stochastic local search**.
    - Exploit current good solutions.
    - Explore the search space around them.



### 7. Crossover

​	Randomly select two parents with probability $p_c \in [0,1]$ for crossover.

- One-point crossover:

  Select a single crossover point on two strings, swap the data beyond that point in both strings.

- N-point crossover:
  - Select multiple crossover points.
  - Split strings into parts using these points.
  - Alternate between the two parents then glue the parts.
- Uniform crossover:
  - For each $i \in \{1,...,L\}$: toss a coin (50%:50%)
    - if 'head': copy bit $i$ from parent 1 to offspring 1, parent 2 to offspring 2.
    - if 'tail': copy bit $i$ from parent 2 to offspring 2, parent 1 to offspring 2.

| <img src="NISO_Lecture 5.assets/Screenshot from 2020-02-11 22-16-59.png" alt="Screenshot from 2020-02-11 22-16-59" style="zoom: 50%;" /> |
| :----------------------------------------------------------: |
|               *Fig 3. Crossover illustration*                |

