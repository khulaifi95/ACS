# NISO CA 2



## Question 4: Pseudo-code of Genetic Programming

Input:

***\*`Input`\****: , , , , , , 
***\*`Output`\****: 
`Population` `InitializePopulation`(, , )
`EvaluatePopulation`(`Population`)
 `GetBestSolution`(`Population`)
**`While`** (`StopCondition`())
  `Children` 
  **`While`** (`Size`(`Children`) < )
    `Operator` `SelectGeneticOperator`(, , , )
    **`If`** (`Operator` `CrossoverOperator`)
      ,  `SelectParents`(`Population`, )
      ,  `Crossover`(, )
      `Children` 
      `Children` 
    **`ElseIf`** (`Operator` `MutationOperator`)
       `SelectParents`(`Population`, )
       `Mutate`()
      `Children` 
    **`ElseIf`** (`Operator` `ReproductionOperator`)
       `SelectParents`(`Population`, )
       `Reproduce`()
      `Children` 
    **`ElseIf`** (`Operator` `AlterationOperator`)
       `SelectParents`(`Population`, )
       `AlterArchitecture`()
      `Children` 
    **`End`**
  **`End`**
  `EvaluatePopulation`(`Children`)
   `GetBestSolution`(`Children`, )
  `Population` `Children`
**`End`**
**`Return`** ()





## Question 5: Tuning Parameters

1. Mutation rate
2. Crossover rate
3. Reproduction rate
4. Alteration rate



