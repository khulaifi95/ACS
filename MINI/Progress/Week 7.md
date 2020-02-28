# Week 7 MINI

---

### Problem Formative



- Let $A$ = ($A_1, A_2, ..., A_p$) be a known partition with $p$ clusters, NumA = $(na_1, na_2,..., na_p)$ be the number of objects in each cluster in $A$.

- We look for another partition B = ($B_1, B_2, ..., B_p$), which maximises the agreement between $A$ and $B$.

- Represent $A$ and $B$ as a $n$ x $p$ partition matrices, where each row represents an object, and each column is for a cluster.

$$
A = \begin{bmatrix} 0\ 1\ 1\ 0\ 1\ 0\ 0\ \\ 1\ 1\ 0\ 1\ 1\ 0\ 1\ \\ 1\ 1\ 1\ 0\ 1\ 1\ 0\ \\ ...\\ 1\ 0\ 1\ 1\ 0\ 1\ 0 \end{bmatrix}
$$

​		where $ \sum_{i=1}^n a_{ij}=na_j$, j = 1, ..., p, and $\sum_{j=1}^pa_{ij}=1,$ i =1, ..., n.

- $AA^T$ is an $n$ x $n$ matrix with the values 1 if $i$ and $j$ are in the same group, 0 otherwise.
- The problem is to find another partition $B$, which minimises $\Vert AA^T-BB^T\Vert$, satisfying $\sum_{i=1}^n b_{ij}=nb_j$, j = 1,...,p, and $\sum_{j=1}^p b_{ij}=1$, i = 1,...,n.



### Heuristic Solution

- We first define

$$
D_a = diag(na_1, na_2,..., na_p)
$$

​		and
$$
D_b = diag(nb_1, nb_2,..., nb_p)
$$

- Let
  $$
  U_j = \frac 1 {\sqrt {na_j}} \begin{bmatrix} a_{1j}\\ a_{2j}\\ ...\\...\\a_{nj}\end{bmatrix}, j = 1, ..., p
  $$
  where each $a_{ij} \in \{0, 1\}$ and $\sum_i a_{ij}=na_j$, j = 1, ..., p,  $\sum_j a_{ij}=1$, i = 1, ..., n. 

- We can see $U = A(D_a)^{(-1/2)}$. On the other hand, let

$$
V_j = \frac 1 {\sqrt {nb_j}} \begin{bmatrix} b_{1j}\\ b_{2j}\\ ...\\...\\b_{nj}\end{bmatrix}, j = 1, ..., p
$$

​			where each $b_{ij} \in \{0, 1\}$, and $\sum_i b_{ij}=nb_j$, j = 1, ..., p,  $\sum_j b_{ij}=1$, i = 1, ..., n. 

- Similarly, $V = B(D_b)^{(-1/2)}$.
- We can get $AA^T = UD_a^TU$, $BB^T = UD_b^TU$.
- Thus, $\Vert AA^T-BB^T \Vert^2 = \Vert UD_aU^T-VD_bV^T\Vert^2 = \Vert D_a - U^TVD_b(U^TV)^T\Vert ^2 $.
- If there exist a $V$ so that $U^TV=J$ is a diagonal, then we can get 

$$
\Vert AA^T - BB^T \Vert ^2 = \Vert D_a - D_b \Vert^2
$$



B would be the best approximated clustering as $A$ and satisfies the size constraints.