# Intelligent Data Analysis



## Lecture 8 : Clustering



### 1.Motivation

Clustering is to discover structure in multi-source data.



### 2. Metrics

- Let $X$ be a set of vectors. $X \times X$ denotes the set of pairs $\{(x,y):x,y\in X\}$.
- A **metric** is a function $d: X\times X \rightarrow \mathbb R^+$ such that:

$$
\begin{align}
d(x, y) &\geq 0, \forall x,y \in X \\
d(x, y) &= 0,\ \text{iff} \ x=y\\
d(x, y) &= d(y,x), \forall x,y\in X \\
d(x,y) &\leq d(x,z)+d(z,y), \forall x,y,z \in X
\end{align}
$$

- A metric is sometimes called a **distance function**.



### 3. $L^p$ metrics

In general, for any positive integer $p$, the $L^p$ metrics denoted by $d_p$ is
$$
d_p(x,y) = [\sum_{n=1}^N(x_n-y_n)^p]^{\frac1p}
$$

- Case when $p=1$: $d_1(x,y)=\sum_{n=1}^N|x_n-y_n| $. 
- Case when $p=2$: $d_2(x,y)=\sqrt{\sum_{n=1}^N(x_n-y_n)^2}$.
- Case when $p=\infty$: $d_\infty(x,y) = \max_{n=1,...,N}|x_n-y_n|$.



### 4. Unit spheres

The **unit sphere** for a metric $d$ is the set
$$
U_d = \{x:d(x,0)=1\}
$$


| <img src="/Users/kevinxu95/ACS/IDA/Notes/IDA_Lecture 8.assets/440px-Vector-p-Norms_qtl1.svg.png" style="zoom:67%;" /> |
| :----------------------------------------------------------: |
|           **Fig 1**: Unit spheres for $L^p$ space            |



### 5. Distortion

Distortion is a measure of how well a set of centroids $C=\{c_1,...,c_K\}$ fits a set of data $X=\{x_1,...,x_N\}$.

- Let $d$ be a metric.
- Let $c_{i(n)}$ be the **closest** centroid to $x_n\ (n=1,...,N)$

$$
d(x_n, c_{i(n)}) = \min_{k=1,...,K}d(x_n, c_k)
$$

- The **distortion** for the centroids $C$ relative to the data set $X$ is

$$
Dist(C,X) = \frac 1N \sum_{n=1}^Nd(x_n, c_{i(n)})
$$

​	where $Dist(C,X)$ is the *average distance* between $x_n$ and its closest centroid.

- The best centroid set is the set $\bar C$ where

$$
Dist(\bar C, X) = \min_{c_n\in C}Dist(C,X)
$$

- A clustering algorithm is required to find a set of centroids that is *locally optimal*.



### 6. Find the best set of centroids

- The best set of $K$ centroids $\bar C$ minimises

$$
Dist(C,X) = \frac 1N \sum_{n=1}^Nd(x_n, c_{i(n)})
$$

- For each $k$ let $X(k)$ be the set of data points for which $c_k$ is the closest centroid.
- The above equation can be re-written as 

$$
Dist(C,X) = \frac 1N\sum_{k=1}^K\sum_{x\in X(k)}d(x, c_k)
$$

- Suppose the dimension of the vector space is $D$, write

$$
\mathbf c_k = \begin{bmatrix}c_{k,1} \\ \vdots \\ c_{k,D}\end{bmatrix}, \mathbf x_n = \begin{bmatrix}x_{n,1}\\ \vdots \\ x_{n,D}\end{bmatrix}
$$

- To **minimise** $Dist(C,X)$, differentiate it *w.r.t.* each $c_{k,d}$, set the result to zero and solve

$$
\begin{align}
\frac {d}{dc_{k,d}}D(C,X) &= \frac {d}{dc_{k,d}}\frac 1N\sum_{j=1}^K\sum_{x\in X(j)}d(x, c_j) \ \ \leftarrow \text{0 except when j=k}
\\ &= \frac 1N \sum_{x\in X(k)} \frac {d}{dc_{k,d}}d(x,c_k)
\end{align}
$$

- Suppose $d$ is the squared Euclidean metric, the equation becomes

$$
\begin{align}
\frac 1N \sum_{x\in X(k)} \frac {d}{dc_{k,d}}d(x,c_k) &= \frac 1N \sum_{x\in X(k)} \frac {d}{dc_{k,d}}\sum_{e=1}^D(x_e-c_{k,e})^2 \\
&=\frac 1N \sum_{x\in X(k)} \frac {d}{dc_{k,d}}(x_d-c_{k,d})^2 \\
&= \frac 1N \sum_{x\in X(k)} 2(x_d-c_{k,d})(-1)

\end{align}
$$

- Setting the *l.h.s.* to be zero and solve

$$
0 = \sum_{x\in X(k)}(x_d-c_{k,d})
\\ c_{k,d} = \frac 1{|X(k)|} \sum_{x\in X(k)}x_d
$$

​	where $|X(k)|$ is the number of data points in $X(k)$, i.e. the number of data in the cluster.



### 7. K-means clustering algorithm

As shown in the last section, to minimize the distortion set the $d^{th}$ coordinate of $c_k$ to be the average of the $d^{th}$ coordinates of the data points for which $c_k$ is the closest centroid.

However, $X(k)$ depends on $c_k$, so it is **not** a **closed** solution for $c_k$.

An *iterative* algorithm can solve the equation:

1. Estimate initial centroid values $c_1^0,..., c_K^0$.
2. Set $i=0$.
3. For $n=1,...,N$ and $k=1,..., K$, calculate $d(x_n, c_k^i)$.
4. For $k = 1,...,K$, let $X^i(k)$ be the set of $x_n$ that are closest to $c_k^i$.
5. Define $c_k^{i+1}$ to be the average of the data points in $X^i(k)$

$$
c_k^{i+1} = \frac 1{|X^i(k)|}\sum_{x\in X^i(k)}x
$$

6. $i = i+1$. Repeat step 3 to 5.



### 8. Optimality

Q: Is the set of k centroids $\bar C$ created by k-means *globally* optimal? i.e.
$$
Dist(C,X) \geq Dist(\bar C, X)?
$$
A: No. K-means clustering is only guaranteed to find a **local optimum**.

​	The solution obtained from k-means clustering depends on the initial centroids.