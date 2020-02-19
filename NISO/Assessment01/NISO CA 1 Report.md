# NISO CA 1 Report

### 1 .Introduction

- ~~SA~~
- ~~GA~~
- ~~TS~~
- ~~Design decisions e.g., encoding scheme~~
- Flowchart
- ~~pseudo-code~~



### 2. Parameters

- Tuning
- Max 30 trial runs



### 3. Results

- Mean
- Standard deviation
- From 30 runs with 3000 iterations



### 4. Discussion

- Compare results from SA and TS
- Wilcoxon signed-rank test
- Statistically



code + pdf

| 10   | 128  |
| ---- | ---- |
| 11   | 287  |
| 12   | 637  |
| 13   | 966  |



## I. Introduction



### 1. Genetic Algorithm

​	Genetic algorithm is a metaheuristic for search inspired by the theory of natural evolution. It is considered as an adaptive strategy for searching the global optimisation, where solutions can be represented by vectors with permutation. The whole paradigm of genetic algorithms inherits from the modern synthesis of biology, focusing on the evolution on a scale of population genetics. By producing offsprings, the population can evolve over reproduction and a proportion will be selected. The evolution of solutions are carried out by the changes in the intrinsic characteristics of populations over generations. Eventually, the genetic variations that enhance the survivability within the population become more common and dominate the population of later generations.

​	For individuals of a population,  the contribution to their population is represented as genotype, which is expressed by their phenotype to the environment in the form of offspring. This representation of solutions in the population enables variation operators to explore the evolutionary search space of the population by mating and mutating. Then  individuals with better fitness will be selected and reproduced to create the next generation. The performance of genetic algorithm relies on the strategy of balancing exploration and exploitation to find global optimum. 

| <img src="NISO CA 1 Report.assets/ga.png" alt="ga" style="zoom: 80%;" /> |
| :----------------------------------------------------------: |
|           *Fig 1. Flowchart of genetic algorithm*            |



​	For the Travelling Salesman Problem (TSP), the goal of genetic algorithm is to find the shortest route between cities minimising the total distance (pseudo-Euclidean). In this context, a single route constrained as above is firstly represented as individuals with the cities as their gene. Each of them are evaluated over the distance as their fitness. The population is the collection of individuals of possible routes, among which the selected solutions for creating the next generation are called parents. New solutions are then reproduced as offsprings by crossover. For better exploration of the solution space, a mutation process is applied to offsprings by random permutation. After mating and mutation, offsprings and the best individuals of parents will be used to reproduce the next generation.

​	Algorithm below provides a pseudocode of genetic algorithm for solving TSP: 



#### Pseudocode for Genetic Algorithm

---

**Input:** $Population_{size}, Problem_{size}, P_{crossover}, P_{mutation}$

**Output:** $S_{best}$

Population $\leftarrow$ InitialisePopulation($Population_{size}, Problem_{size}$)

EvaluatePopulation(Population)

$S_{best} \leftarrow$ GetBestSolution(Population)

**While** ($\neg$StopCriteria())

​		Parents $\leftarrow$ MatingPool(Population, $Population_{size}$)

​		Children $\leftarrow$ $\empty$

​		**For** ($Parent_1, Parent_2 \in$ Parents)

​				$Child_1, Child_2$ $\leftarrow$ Crossover($Parent_1, Parent_2, P_{crossover}$)​

​				Children $\leftarrow$ Mutate($Child_1, P_{mutation}$)

​				Children $\leftarrow$ Mutate($Child_2, P_{mutation}$)

​		**End**

​		EvaluatePopulation(Children)

​		$S_{best}$ $\leftarrow$ GetBestSolution(Children)

​		Population $\leftarrow$ Replace(Population, Children)

**End**

**Return ** $S_{best}$

---



### 2. Simulated Annealing

​	Simulated annealing is a heuristic search algorithm for approximating the global optimum. It was first proposed by Kirkpatrick in 1983. The inspiration of this algorithm comes from the real annealing procedure in practical engineering. It is suggested that to achieve better structural and material properties, the substance is best processed by first melting it done then slowly lowering the temperature at a suitable level in the vicinity of freezing point. In the optimisation, this algorithm can escape from local optimal solutions.

​	Simulated annealing essentially applies a stochastic local search with random non-improving steps. After initiation, the algorithm first searches neighbour solutions and compare their fitness value with the current solution. The better solutions will be always accepted. We also accept worse solutions with a probability $P = exp(\frac {e-e_{new}}{T})$, which is parameterised by the temperature $T$. Generally, the temperature starts high and slowly decreases as the process goes. Heuristically, the probability of accepting worse solutions will decrease when it is closer to the optimum.

​	In the context of Travelling Salesman Problem (TSP), simulated annealing is efficient to explore the vast search space without being easily trapped in the local optimum.  The neighbour solutions are generated by 2-opt algorithm in a stochastic approach. The accepted worse route solutions introduce random steps at the early stage and eventually become a local hill-climbing search approaching the final state.

​	Algorithm below provides a pseudocode of  simulated annealing for solving TSP: 



#### Pseudocode for Simulated Annealing

---

**Input:** $Problem_{size}, iterations_{max}, temp_{max}$

**Output:** $S_{best}$

$S_{current}$ $\leftarrow$ InitialiseSolution($Problem_{size}$)

$S_{best}$ $\leftarrow$ $S_{current}$

**For ** ($i = 1$ to $iterations_{max}$)

​		$S_i $ $\leftarrow$ TwoOpt($S_{current}$)

​		$temp_{current}$ $\leftarrow$ CalculateTemperature($i, temp_{max}$)

​		**If** (Cost($S_i$) $\leq$ Cost($S_{current}$)

​				$S_{current}$ $\leftarrow$ $S_i$

​		**Elseif** (Exp((Cost($S_{current}$) - Cost($S_i$))/$temp_{curr}$) $>$ Rand())

​				$S_{current}$ $\leftarrow$ $S_i$

​		**End**

**End**

**Return** $S_{best}$

---



### 3. Tabu Search

​	Tabu search is a widely applied search optimisation algorithm with an embedded heuristic. The main idea is to keep a memory structure called tabu list to constrain the local search process from visiting previous solutions. Other long-term memories can introduce more bias to avoid local optimum.

| <img src="NISO CA 1 Report.assets/tabu.png" alt="tabu" style="zoom: 50%;" /> |
| :----------------------------------------------------------: |
|              *Fig 2. Flowchart for Tabu search*              |

​	For the Travelling Salesman Problem (TSP), Tabu search is applied to find the best route with shortest distance when traversing cities. A simple tabu list with short memory is maintained to exclude recently visited solutions in the neighbourhood. The neighbour solutions are generated by stochastic 2-opt local search. Heuristically, Tabu search guides the optimisation process away from local optima.

​	Algorithm below provides a pseudocode of  Tabu search for solving TSP: 



#### Pseudocode for Tabu Search

---

**Input: ** $TabuList_{size}$

**Output:** $S_{best}$

$S_{best}$ $\leftarrow$ InitialiseSolution()

TabuList $\leftarrow$ $\empty$

**While** ($\neg$ StopCriteria())

​		CandidateList $\leftarrow$ $\empty$

​		**For** ($S_{candidate} \in$ Neighbourhood($S_{best}$))

​				**If** ($\neg$ ContainsAnyFeatures($S_{candidate}$, TabuList))

​						CandidateList $\leftarrow$ $S_{candidate}$

​				**End**

​		**End**

​		$S_{current}$ $\leftarrow$ Two-Opt(CandidateList)

​		**If** (Cost($S_{current}$) $\leq$ Cost($S_{best}$)

​				$S_{best}$ $\leftarrow$ $S_{current}$

​				TabuList $\leftarrow$ ()

​				**While** TabuList $>$ $TabuList_{size}$

​						DeleteFirst(TabuList)

​				**End**

​		**End**

**End**

**Return** $S_{best}$ 

---





## II. Parameters



### 1. Tuning in Genetic Algorithm

- 





### 2. Tuning in Simulated Annealing

- initial temp
- temperature()

| iter/temp | 100   | 1000  | 10000 | 100000 | 3000  | 6000  |
| --------- | ----- | ----- | ----- | ------ | ----- | ----- |
| 100       | 32555 | 34659 | 45545 | 49677  |       |       |
| 500       | 18108 | 18520 | 20169 | 23041  |       |       |
| 1000      | 14549 | 14245 | 14896 | 15752  |       |       |
| 2000      | 12248 | 12127 | 12401 | 12643  |       |       |
| 3000      | 11674 | 11595 | 11574 | 11853  | 11654 | 11797 |
|           |       |       |       |        |       |       |



### 3. Tuning in Tabu Search

- tabu size
- 





## III. Results







## IV. Discussion

