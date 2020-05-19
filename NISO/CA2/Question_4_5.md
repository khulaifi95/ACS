# NISO CA 2



## Question 4: Pseudo-code of Genetic Programming



**Input:**$Population_{size}, P_{crossover}, P_{mutation}, Size_{tournament}, Max_{depth}, Node_{sexp}$

**Output:** $S_{best}$

Population $\leftarrow$ InitialisePopulation($Population_{size}, Max_{depth}$)

ParseIn($Node_{sexp}$)

EvaluatePopulation(Population)

$S_{best} \leftarrow$ GetBestSolution(Population)

**While** ($\neg$TimeOver())

​		Parents $\leftarrow$ Selection(Population, $Population_{size}$)

​		Children $\leftarrow$ $\empty$

​		**For** ($Parent_1, Parent_2 \in$ Parents)

​				$Child_1, Child_2$ $\leftarrow$ Crossover($Parent_1, Parent_2, P_{crossover}$)​

​				Offspring +$\leftarrow$ Mutate($Child_1, P_{mutation}$)

​				Offspring +$\leftarrow$ Mutate($Child_2, P_{mutation}$)

​		**End**

​		ParseIn($Node_{sexp}$)

​		EvaluatePopulation(Offspring)

​		$S_{best}$ $\leftarrow$ GetBestSolution(Offspring)

​		Population $\leftarrow$ Replace(Population, Offspring)

**End**

**Return ** $S_{best}$

---





## Question 5: Tuning Parameters



To improve the performance and tune the parameters, the algorithm is tested on a real-world dataset which records the weekly price of corn from 2013 to 2017. The data is truncated and reshaped by a window of 80 rows, which includes the current price and the price in previous 80 weeks.

​	**1. Population size:** The size of population in each generation provides the search space for the expression of estimation. Considering the extra time budget constraint on the evolution process, population size is probably trickier than other parameters, because the number of generations tend to be insufficient to return an optimal in a short time window. It means that reducing the number of individuals in every generation does not necessarily diminish the performance of evolution. The task is to find a proper threshold where the solution is able to find the optimal fast. In this setting, the time budget is inherited from the test case as 5 seconds. The population size starts at 100 individuals per generation by default. The experiment explores other candidates from 50 to 400, whose performance are plotted as below [figure 1]. It shows an improved performance with the increase of size. Population of 400 individuals are chosen for its faster evolution and a good performance close to 800.



<img src="Question_4_5.assets/Pop_size.png" alt="Pop_size" style="zoom:24%;" />





​	**2. Crossover rate:** After selection of parents, some of them will breed offsprings. The crossover is operated to product individuals with features from both parents. In this problem, the crossover is produced by generating a random tree on one parent's branch and replacing the branch in another parent with it. A terminal node is not preferred in crossover. The convention of the crossover rate for genetic programming is to be set over 90%. The default rate for the ordered crossover on individuals is 0.9. The experiment explores other candidates ranging from 0.8 to 0.98. The results are plotted in against the best fitness value[figure 2]. It shows no significant difference in mean value.



<img src="Question_4_5.assets/Crossover_rate.png" alt="Crossover_rate" style="zoom:24%;" />





​	**3. Mutation rate:** A small operator is applied to mutate the offsprings. Since the highly randomised process of expression estimation thanks to the structure of expression tree, not much mutation is  required.  By default, the mutation rate is set as 20%.  The result from the box-plot proved the setting[figure 3].



<img src="Question_4_5.assets/Mutation_rate.png" alt="Mutation_rate" style="zoom:24%;" />





​	**4. Tournament size:** In each generation, tournament selection is conducted to create the mating pool to produce offsprings. The number of candidates in the tournament can have an impact on the selection pressure. Inheriting the tested results of the parameters above,  the algorithm are tested with the number of candidates 8, 5, 4 , 2, respectively. The plot below [figure 4] shows the best solution is 2 candidates.



<img src="Question_4_5.assets/Tournament_size.png" alt="Tournament_size" style="zoom:24%;" />





​	**5. Next generation:** A new generation consists all the offsprings produced in the current generation. Considering the moderate crossover rate and mutation rate, no elite parents are selected in each generation.



To summarise, the best fitness value e.g., the best individual solution of price estimation can be achieved with a crossover rate of 0.9, mutation rate of 20%, tournament size of 2 in a population of 400 individuals per generation.