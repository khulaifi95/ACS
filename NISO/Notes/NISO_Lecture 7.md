# Nature-inspired Search & Optimisation



## Lecture 7 : Real-valued Coded Evolutionary Algorithms



### 1. Advantages of Using Binary Coding

- The binary alphabet maximises the level of **implicit parallelism**

- **Schema** - a template that identifies a subset of strings with **similarities** at certain **string positions**

  | <img src="NISO_Lecture 7.assets/Screenshot from 2020-02-04 11-10-43.png" alt="Screenshot from 2020-02-04 11-10-43" style="zoom: 50%;" /> |
| :----------------------------------------------------------: |
  |             *Fig 1. Schema for Chromosome 1 & 2*             |
  
  [^]: The '*' symbol is a *wildcard* that represent either a $0$ or $1$.
  
  

### 2. Implicit Parallelism

- If a chromosome is of length $L$ then it contains $3^L$ **schemata** (0, 1 or *)
- For a population of $M$ individuals we are evaluating up to $M.3^L$ schemata
- Some schemata are fitter and some are weaker, but by selection and reproduction, we will create a population that is full of **fitter schemata**.

- **Implicit parallelism**: Evolving $M$ individuals but also manipulating $M.3^L$ schemata.

  &rarr; Binary coding requires fewer strings to construct more schemata to sample larger search space.



### 3. Hamming Cliff Problem

- One-bit mutation could make a large jump; a multi-bit mutation could make a smaller jump.
  - 000 &rarr; 100; 0 &rarr; 4. Changing 1 bit, diff = 3
  - 011 &rarr; 100; 3 &rarr; 4. Changing 3 bit, diff = 1

#### Solution - Gray encoding

- Adjacent numbers have a single digit differing by 1.

- For $a \in$ {0,1}$^L$ and $b$ $\in$ {0,1}$^L$ where $a$ is the standard binary encoded, and $b$ is Gray encoded, then
  $$
  b_i =
  \begin{cases}
      a_i,& if \ i=1\\
      a_{i-1} \oplus a_i,& if \ i>1
  \end{cases}
  $$
  where $\oplus$ means "exclusive or", i.e., logical operation that outputs True only when inputs differ.

- Example:

  | Binary encoded | 000  | 001  | 010  | 011  | 100  | 101  | 110  | 111  |
  | :------------- | :--: | :--: | :--: | :--: | :--: | :--: | :--: | :--: |
  | Gray encoded   | 000  | 001  | 011  | 001  | 111  | 001  | 011  | 001  |
  | Phenotype      |  0   |  1   |  2   |  3   |  4   |  5   |  6   |  7   |
  | Gray phenotype |  0   |  1   |  2   |  1   |  7   |  1   |  3   |  1   |

  

### 4. Drawbacks of Binary Coding

- Problem in Discrete Search Spaces - **Redundancy**

  - When the variables belongs to a finite discrete set with a cardinal different from a power of two, some binary strings are redundant, which correspond to infeasible solutions.

  - Example: In a combinatorial optimisation problem whose feasible set $A = [0,2,3]$, the cardinal of the set is 3 but we need a binary string of length 2 to encode it.

    | Genotype      | 00   | 01        | 10   | 11   |
    | ------------- | ---- | --------- | ---- | ---- |
    | **Phenotype** | 0    | ~~**1**~~ | 2    | 3    |

    

- Problem in Continuous Search Spaces - **Precision**

  - Decoding function $h(K_i)$ need to divide genotype $\vec a \in $ {0,1}$^L$ into segments and decode each of them into an integer. Then map the integer linearly into the interval bound $x_i \in [u_i, v_i]$:
    $$
    h(K_i) = u_i + K_i. \frac {v_i - u_i} {2^{\frac {L}{n}}-1}
    $$

  - The precision depends on $L$.



### 5. Real-valued Vector Representation

​	For continuous optimisation problems, real-valued vector representation is a **natural way** to represent solutions.

- **No difference** between genotype and phenotype
- Each gene in the chromosome represents a **variable** of the problem
- The precision is not restricted by the decoding/ encoding function

#### Advantages

- Simple, natural and faster - no encode and decode
- Better precision and easy to handle large dimensional problems



### 7. Real-valued Mutation

- Randomly select a **parent** with probability $p_m \in [0,1]$ for mutation.

- Randomly select a **gene** $c_i$ and apply mutation operator

- Real number *mutation operators*:

  1. **Uniform mutation**

     Replace $c_i$ with a random uniform number $c_i^\prime$ generated from the **interval bound** of the variable $x_i \in [u_i, v_i]$.
  
     
  
  2. **Gaussian mutation**

     Replace $c_i$ with a random number $c_i^\prime$ which is calculated from:
  
  $$
  c^\prime_i = min(max(N(c_i,\sigma_i), u_i),v_i)
  $$
  ​		where $N(c_i, \sigma_i)$ is a Gaussian distribution with mean $c_i$ and standard deviation $\sigma_i$ which may depend on the length. 
  
  ​		It chooses the maximum over a Gaussian distribution $N$ and the lower bound $u_i$, then choose the minimum between the result and the upper bound.
  
  
  
  3. **Non-uniform mutation**
  
     Replace $c_i$ with $c_i\prime$ which is calculated from:
  
  $$
  c_i^\prime =
  \begin{cases}
      c_i + \Delta(t, v_i - c_i),& if \ \tau \geq 0.5\\
      c_i - \Delta(t, c_i - u_i),& if \ \tau < 0.5
  \end{cases}
  $$
  ​		where $t$ is the number of **current generation** and $\tau$ is a random number $\in$ [0,1], and
  $$
  \Delta(t,y) = y(1-r^{1-\frac {t}{g_m}})^b
  $$
  
  ​		where $r$ is a random number $\in$ [0,1], $g_m$ is the maximum number of **generations** and $b$ is a constant.
  
  - **Intuition**
    - $\tau$ determines the region to generate mutations. (towards upper/ lower bound)
    - When $t \to 0,\ \frac {t}{g_m} \to 0$,  cardinal $\to 1$, $RHS \to y$
    - When $t \to g_m, \ \frac {t}{g_m} \to 1$, cardinal $\to 0$, $RHS \to 0$
    - Which means when $ t \uparrow$, the less mutation $\downarrow$ we take by replacing $c_i$.



### 8. Real-valued Crossover

- Randomly select two parents $\mathbf x_1$ = {$x_1^{[1]}, x_2^{[1]}, ..., x_n^{[1]}$} and $\mathbf x_2$ = {$x_1^{[2]}, x_2^{[2]}, ..., x_n^{[2]}$}, then apply a crossover operator.

- Real number *crossover operators*:

  1. **Flat crossover**

     One offspring, h = {$h_1 , h_2 , · · · , h_n$ }, is generated, where $h_i$ is a randomly (uniformly) chosen value in the interval [$ x_i^{[1]} , x_i^{[2]}$ ].

  2. **Simple crossover** 

     a crossover point $i \in $ {1, ..., n} is randomly chosen, and the variables beyond this point are swap to create two new offspring.

  3. **Whole arithmetical crossover**

     Two offspring $\mathbf h_k$ = {$h_1^k, h_2^k, ..., h_n^k$} with k= 1, 2 are generated, where $h_i^{[1]} = \alpha x_i^{[1]}+ (1-\alpha)x_i^{[2]}$, and $h_i^{[2]} = \alpha x_i^{[2]}+ (1-\alpha)x_i^{[1]}$, $\alpha \in [0,1]$.

  4. **Local arithmetical crossover**

     The same as whole arithmetic crossover, except $\alpha \in \mathbb R^n$ is a **vector**, with elements $\in$ [0,1].

  5. **Single arithmetical crossover**

     choose a gene and then replace it with the arithmetic average of genes at the position of two parents, other genes are copied from the parents.

  6. **BLX-$\alpha$ crossover**

     One offspring is generated $\mathbf h$ = {$h_1, h_2, ..., h_n$}, where $h_i$ is a randomly (uniformly) generated number of an interval [$h_min - I.\alpha, h_max + I.\alpha$], where $h_{max} = max(x_i^{[1]}, x_i^{[2]})$, $h_{min} = min(x_i^{[1]}, x_i^{[2]})$ and $I = h_{max} - h_{min}$.

     

     | <img src="NISO_Lecture 7.assets/Screenshot from 2020-02-04 16-40-28.png" alt="Screenshot from 2020-02-04 16-40-28" style="zoom:50%;" /> |
     | :----------------------------------------------------------: |
     |           *Fig 2. Real valued crossover examples*            |
     
     