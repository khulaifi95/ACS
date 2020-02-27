#### NSGA-II (One-generation)

- Require: 
  - A parent population $P_t$ of $N$ individuals
  - An offspring population $Q_t$ of $N$ individuals



1. Sort $R_t: P_t \cup  U_t$ into non-dominated front $F_1, F_2,...$

2. Set $i = 1$ and $P_{t+1} = \empty$

3. While $\vert P_{t+1} \vert + \vert F_i \vert < N$ do

   ​		Set $P_{t+1} := P_{t+1} \cup F_i$

   ​		Set $i := i + 1$

4. Perform 'crowding sort' on the individuals from next front $F_{i+1}$

5. Add $N- \vert P_{t+1}\vert$ most wildly spread solutions from $F_i$ to $P_{t+1}$.

6. Create $Q_{t+1}$ from $P_{t+1}$ using crowded tournament, mutation, crossover...

7. Return $(P_{t+1}, Q_{t+1})$



#### Non-dominated Sorting

- Require: A population of individuals $P$

1. For each individual $i \in P$ do $\rightarrow$ O(N)

   ​		Set individuals dominated by $i$ $S_i = \empty$ and dominated $n_i = 0$

   End for

2. For all pairs $(i, j) \in P, i \ne j$ do $\rightarrow$ $O(MN^2)$

   ​		if $j$ dominates $i$ then

   ​				$S_j := S_j \cup {i}$

   ​		else if $i$ dominates $j$ then

   ​				$n_j$ := $n_j + 1$

3. For each $i \in P$ do

   ​		if $n_j$ = 0, keep $i$ in the first non-dominated front $F_1$

4. Set k = 1

   While $P_K \ne \empty$ do

   ​		for each $i \in P_k$ and $j \in S_i$

   ​				Set $n_j := n_j -1$

   ​				if $n_j$ = 0 then

   ​						$Q = Q \cup {j}$

   ​				End if

   ​		End for

   ​		Set $k=k+1$ and $P_k = Q$ and $Q = \empty$				

   End while

