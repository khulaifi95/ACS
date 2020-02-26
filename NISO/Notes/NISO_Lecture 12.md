# Nature-inspired Search & Optimisation

---

## Lecture 12 : Niching and Speciation II



### 1. Crowding

​	Crowing techniques insert new individuals into the population by **replacing similar** individuals.

- Crowding strives to maintain the **pre-exiting diversity** of a population.
- Crowding does not modify fitness.



#### Deterministic Crowding

```pseudocode
P(0) := initialise();
For t = 1 to g do
	P(t) := shuffle(P(t-1));
	For i = 0 to mu/2-1 do
		p_1 = a_2i+1(t);
		p_2 = a_2i+2(t);
		{c_1, c_2} = recombine(p_1, p_2);
		c_1* := mutate(c_1);
		c_2* := mutate(c_2);
		If [d(p_1, c_1*) + d(p_2, c_2*)] < [d(p_1,c_2*) + d(p_2, c_1*)] 
			If f(c_1*) > f(p_1) Then a_2i+1(t) := c_1* Fi;
			If f(c_2*) > f(p_2) Then a_2i+2(t) := c_2* Fi;
        Else
        	If f(c_2*) > f(p_1) Then a_2i+1(t) = c_2* Fi;
        	If f(c_1*) > f(p_2) Then a_2i+2(t) = c_1* Fi;
        End
    End
End
```



The last if statements is to ensure offsprings replace similar individuals.



### 2. Discussions

- Capable of niching, i.e., locating and maintaining peaks.
- Minimal replacement error.
- Few parameters to tune.
- Fast because of no distance calculations.
- Population size must be large enough.
- Should use full crossover, i.e., crossover rate = 1.0.



### 3. Speciation in a Narrow Sense

​	Speciation in a narrow sense focuses on search within a **peak**.

- A speciation method **restricts mating** to **similar** individuals and discourages mating of individuals from **different species**.
- In order to apply such a speciation method, individuals **representing** each species must be found first. The speciation method cannot be used independently.
- Niching and speciation are complementary.
- Similarity can be measured at either genotypic or phenotypic levels.



### 4. Mating Restriction

​	Each individual consists of a tag and a functional string to restrict mating.

| Template | Tag   | Functional string     |
| -------- | ----- | --------------------- |
| #1#0     | 10010 | 1010 ... 1000 ... 101 |



1. Only individuals with the same **tag** are allowed to mate.
   - Tags participate in crossover and mutation, but not fitness evaluation.
   - Templates can also be used.
2. Two individuals are allowed to mate only when their **distance** is smaller than a threshold parameter, $\sigma_{mate}$.
   - EAs with niching and mating restriction can distribute the population **across the peaks** better than those with sharing alone.



- Mating restriction is always applied during **recombination**.



### 5. Fitness Sharing by Speciation

- Use tags to identify species. (peaks)
- For a given problem, let $k$ be the number of different tags. 
- Let $\{S_0, S_1, ..., S_{k-1}\}$ be $k$ species of individuals and $\Vert . \Vert$ be the **cardinality** of a set. Then we have:

$$
f_i^{share} = \frac {f_i^{raw}}{\Vert S_j \Vert}, \ i \in S_j, \ j = 0, 1, ..., k-1
$$

- Recombination occurs only among individuals with the same tag.
- A tag can be mutated. (self-adaptive)
- Speciation = sharing + mating restriction.



### 6. Summary of Niching and Speciation

​	Niching is concerned more with locating peaks (locating basins of attraction), while speciation is more focused on converging to the actual peaks.

1. **Fitness sharing** modifies fitness.
   - Explicit fitness sharing
   - Implicit fitness sharing
   - Fitness sharing with mating restriction
2. **Crowding** is about replacement strategies.
   - Deterministic crowding
3. **Speciation** occurs during recombination. It is about mating restriction.
   - By tags
   - By distances