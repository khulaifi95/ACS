# Nature-inspired Search & Optimisation



## Lecture 16 : Co-Evolution



### 1. What is co-evolution?

- Fitness of an individual **depends** on other individuals.
  - Fitness **landscape** changes.
  - Fitness of an individual may be different in different runs
- Change in one individual will **change** the fitness **landscape** in others.
- Iterated Prisoner's Dilemma experiment:
  - Evolving strategies played against each other.



### 2. Types of Co-Evolution

- By evaluation:
  - Competitive
  - Cooperative
- By population-organisation:
  - Inter-population - between group
  - Intra-population - within group



### 3. Example 1: Sorting Algorithm

- Goal: Place the elements in a data structure (list or tree) in some specified **order**.

- Sorting network: Knuth

  - Horizontal lines $\rightarrow$ elements in the list
  - Vertical arrows $\rightarrow$ **comparisons** to be made (in parallel)
  - **Swap** if elements in wrong order.

- For 16 elements:

  - Make them **correct** and **efficient**.

  - Reduce the No. of comparisons necessary for correct sorting.
    - Hot Q around the '60s.

1. Encoding the sorting net:

   - Ordered list of pairs to be compared $\rightarrow$ phenotype

   - Diploid chromosomes (DNA structure) $\rightarrow$ genotype
     - 1 individual = 15 pairs of 32 bit chromosomes, each for 4 comparisons.

2. **Fitness** measure: **Percentage** of cases sorted correctly.

3. Problem: How to compute fitness?

   - All possible inputs - slow

   - Fixed set of inputs - which?



### 4. GA Solution of Sorting Algorithm

- Method:
  - To foster speciation, individuals placed on a **2D** grid (i.e. spatial distance).
  - Fitness computed from random **subsamples**.
  - Half of population with lower fitness deleted and **replaced** with a copy of a surviving neighbor.
  - Pairing in the local neighborhoods.
  - Special crossover for diploids, followed by mutation with p_m = 0.001.
  - Population size between 512—1million.
  - 5000 generation.

- Result: 65 comparisons
- Why didn't GA do better?
  - After early generations, with randomly generated test cases used to compute fitness, the **difficulty** of test cases stayed roughly the **same**.

- Solution: Co-Evolution - 61 comparisons
  - Evolve both **algorithms** and **test cases**.
    - Algorithms try to sort.
    - Test cases try to fail algorithms.
  - Predator/ prey relationship inspired from nature.
  - As the algorithms got better, the test cases got harder.

| <img src="NISO_Lecture 16.assets/0312202001.png" alt="0312202001" style="zoom: 67%;" /> | <img src="NISO_Lecture 16.assets/0312202002-1584030831737.png" alt="0312202002" style="zoom:67%;" /> |
| -----------------------------------------------------------: | :----------------------------------------------------------- |
|                                    *Fig 1. Inter-population* | *competitive co-evolution*                                   |



### 5. Example 2: Game Playing

- Type: 

  - Intra-population competitive co-evolution.

- Task: 

  - Evolve a Backgammon player.

- Problem: Evaluation

  - Against human players
  - Against 'conventional' program
  - Against internet players

- Solution: Co-Evolution

  - Evolve and evaluate by playing against other evolving programs.

- Intra-population

  - All genotypes are of the same type.
  - Only one population.

- Simple Backgammon learner:

  Evolve a NN that plays Backgammon.

  1. Initiate $NN$ as $NN(k); k=0$.

  2. Generate a mutant challenger $NN'(k)$ of $NN(k)$.

  3. If $NN'(k)$ is beaten by $NN(k)$:

     ​				$NN(k+1) = NN(k)$

     else

     ​				$NN(k+1) = 0.95 \times NN(k) + 0.05 \times NN'(k)$

  4. $k=k+1$, iterate back to step 2 unless finished.

- Settings:
  - 197-20-1 FC NN.
  - No training for NN.
  - EA with population size 1.
  - Simple mutation: add Gaussian noise to weights.
  - No recombination.



### 6. When and Why co-evolution?

- No fitness function known.
  - Bootstrapping by co-evolution.
- Too many fitness cases.
  - Co-evolve fitness and cases.
- Modularisable problem.
  - Divide and conquer.



### 7. Other Examples

- Robot behaviour
  - Coupling cooperative co-evolution.
- Pattern recognition
  - Different Individuals specialize on different letters.
- Creative design systems
  - Evolve design and design specifications.
- Games
  - Tic-Tac-Toe.
  - Prisoner's dilemma.
  - Checkers.
- Artificial life
  - Complex simulated ecosystems.