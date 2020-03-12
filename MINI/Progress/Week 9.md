## 1. Sparse Clustering Framework

Suppose we wish to cluster $n$ observations on $p$ dimensions. $\mathbf X$ is of dimension $n \times p$. We take a general approach to the problem of sparse clustering.

- Let $\mathbf X_j \in \mathbb R^n$ denote feature $j$.
- Many clustering methods can be expressed as an optimisation problem of the form:

$$
\max\limits_{\mathbf \Theta \in D} \Bigg\{ \sum\limits_{j=1}^pf_j(\mathbf X_j, \mathbf \Theta)\Bigg\}
$$

​		where $f_j(\mathbf X_j, \mathbf \Theta)$ is some function that involves only the $j$th feature of the data, and $\mathbf \Theta$ is a parameter restricted to lie in a set $D$.

- We define *sparse clustering* as the solution to the problem

$$
\max\limits_{\mathbf w;\mathbf \Theta \in D}\Bigg\{ \sum\limits_{j=1}^pw_jf_j(\mathbf X_j, \mathbf \Theta) \Bigg\} \\ \text {subject to} \ \Vert\mathbf w\Vert^2 \leq 1,\ \Vert\mathbf w\Vert_1\leq s, \\
w_j \geq 0 \ \ \ \ \ \ \forall j,
$$

​		where $w_j$ is a **weight** corresponding to feature $j$ and $s$ is a **tuning parameter**, $1 \leq s \leq \sqrt p$. 

- Observations about (2):

  1. If $w_1 = ... = w_p$ in (2), then the criterion reduces to (1).

  2. The $L_1$ penalty on $\mathbf w$ results in **sparsity** for small values of the tuning parameter $s$. 

     i.e., some of the $w_j$'s will equal to zero.

  3. The value of $w_j$ can be interpreted as the **contribution** of feature $j$ to the resulting sparse clustering.
  4. For (2) to result in a nontrivial sparse clustering, it is necessary that $f_i(\mathbf X_j, \mathbf \Theta)>0$ for some or all $j$. That is, if $f_i(\mathbf X_j, \mathbf \Theta)\leq 0$, then $w_j = 0$. If $f_i(\mathbf X_j, \mathbf \Theta)>0$, then the nonegativity constraint on $w_j$ has no effect.

- We optimise (2) by an iterative algorithm: holding $\mathbf w$ fixed, we optimise (2) w.r.t. $\mathbf \Theta$, then holding $\mathbf \Theta$ fixed, we optimise w.r.t. $\mathbf w$ instead. 

  - No global optimum is achieved by this approach. 

  - However, we are guaranteed that each iteration increases the objective function.

  - The first optimisation involves application of a standard clustering procedure to a weighted version of data.

  - The second problem can be rewritten as:
    $$
    \max\limits_\mathbf w \Big\{ \mathbf w^T\mathbf a \Big\}\\
    \text {subject to} \ \ \Vert\mathbf w \Vert^2 \leq 1, \ \ \ \ \Vert \mathbf w\Vert_1 \leq s,\\
    w_j \geq 0 \ \ \ \ \ \ \forall j,
    $$
    where $a_j = f_j(\mathbf X_j, \mathbf \Theta)$. This is solved by **soft-thresholding**.

    

## 2. Sparse K-Means Clustering

### 2.1 The Sparse K-Means Method

K-means clustering minimises the *within-cluster sum of squares* (WCSS). That is , it seeks to partition the $n$ observations into $K$ sets, or clusters, such that the WCSS
$$
\sum\limits_{k=1}^K\frac{1}{n_k}\sum\limits_{i,i' \in C_k}\sum\limits_{j=1}^pd_{i,i',j}
$$
is **minimal**, where $n_k$ is the number of observations in cluster $k$ and $C_k$ contains the indices of the observations in cluster $k$. In general, $d_{i,i',j}$ can denote **dissimilarity measure** between observations $i$ and $i'$ along feature $j$. Here, we take $d_{i,i',j} = (X_{ij}-X_{i'j})^2$. 

- If we define the *between-cluster sum of squares* (BCSS) as

$$
\sum\limits_{j=1}^p(\frac1n\sum\limits_{i=1}^n\sum\limits_{i'=1}^nd_{i, i', j} - \sum\limits_{k=1}^K\frac1{n_k}\sum\limits_{i,i' \in C_k}d_{i'i',j})
$$

​		then minimising the WCSS is equivalent to maximising the BCSS.

- One could try to develop a method for sparse K-means clustering by optimising a **weighted** WCSS, subject to constraints on the weights:

$$
\max\limits_{C_1, ..., C_K, \mathbf w}\Bigg\{ \sum\limits_{j=1}^pw_j(-\sum\limits_{k=1}^K\frac1{n_k}\sum\limits_{i,i' \in C_k}d_{i.i',j})\Bigg\} \\
\text {subject to} \ \Vert \mathbf w\Vert^2 \leq 1,\ \Vert \mathbf w\Vert_1\leq s,\\
w_j \geq 0 \ \ \ \ \ \ \forall j.
$$

​		Here $s$ is a tuning parameter. Since each element of the weighted sum is **negative**, the maximum occurs when all weights are zero, regardless of the value of $s$. We instead maximise a weighted BCSS, subject to constraints on the weights. Our *sparse K-means clustering criterion* is as follows:
$$
\max\limits_{C_1, ..., C_K, \mathbf w} \Bigg\{ \sum\limits_{j=1}^pw_j(\frac1n\sum\limits_{i=1}^n\sum\limits_{i'=1}^nd_{i,i',j}-\sum\limits_{k=1}^K\frac1{n_k}\sum\limits_{i,i' \in C_k}d_{i,i',j}\Bigg\} \\
\text {subject to} \ \Vert \mathbf w\Vert^2 \leq 1,\ \Vert \mathbf w\Vert_1\leq s,\\
w_j \geq 0 \ \ \ \ \ \ \forall j.
$$
