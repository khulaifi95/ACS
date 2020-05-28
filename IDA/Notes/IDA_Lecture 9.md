# Intelligent Data Analysis



## Lecture 9 : Self-organising Maps



### 1. Motivation

A linear embedding of low-dimensional object may not represent the actual structure of the data.



| <img src="/Users/kevinxu95/ACS/IDA/Notes/IDA_Lecture 9.assets/Screenshot 2020-05-28 at 14.09.46.png" style="zoom:50%;" /> | <img src="/Users/kevinxu95/ACS/IDA/Notes/IDA_Lecture 9.assets/Screenshot 2020-05-28 at 14.10.00.png" style="zoom:50%;" /> |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
|            **Fig 1.** Linear embedding of object             |                Non-linear embedding of object                |



### 2. Alternative to k-means

- For k-means, distances between **all** data points and **all** centroids are calculated before centroids are updated.
- But centroid locations could be **improved** after seeing just **one** data point $x_n$.
- Online clustering update centroids with each sample

$$
c_k^{new} = c_k^{old} + \eta (x_n-c_k^{old})
$$

- where $\eta > 0$ is the learning rate:
  - if $\eta$ is too small, slow convergence.
  - if $\eta$ is too large, unstable algorithm.

- Start with big $\eta$ then shrink $\downarrow \eta$ as time/ iteration increases

$$
\eta(t) = \eta(0) \times e^{\frac {-t}{\tau}}
$$

​		where $\tau > 0$ is the **time scale**. Determines how fast $\eta$ will decrease. (*annealing*)



| <img src="/Users/kevinxu95/ACS/IDA/Notes/IDA_Lecture 9.assets/Screenshot 2020-05-28 at 14.20.53.png" style="zoom:50%;" /> |
| :----------------------------------------------------------: |
|     **Fig 2.** Centroid update step after one data point     |



### 3. Online clustering algorithm

1. Choose the number of centroids $K$.
2. (Randomly) choose initial codebook $\{c_1, · · · , c_K \}$.
3. Cycle through the data points and for each data point $x_n$ do:
   - Find the closest centroid $c_{i(n)}$.
   - Move $c_{i(n)}$ closer to $x_n$:

$$
c_{i(n)}^{new} = c_{i(n)}^{old} + \eta(t)(x_n-c_{i(n)}^{old})
$$

​				where $\eta(t)>0$ is a small *learning rate* which is annealed with time
$$
\eta(t) = \eta(0) \times e^{\frac {-t}{\tau}}
$$


| <img src="/Users/kevinxu95/ACS/IDA/Notes/IDA_Lecture 9.assets/Screenshot 2020-05-28 at 14.55.33-0674285.png" style="zoom:50%;" /> |
| :----------------------------------------------------------: |
|    **Fig 3.** Learning rate change with different $\tau$     |



### 4. Enhancements to online clustering

- **Batch training** accumulates changes to centroids over (small) subsets of the training set.
- **Stochastic** batch training accumulates changes to centroids over (small) randomly-chosen subsets of the training set.
- Compare with gradient descent and **stochastic gradient descent** in Neural Network training.



### 5. Neighbourhood structure

We impose neighbourhood structure on the centroid set, including 1-dimensional and higher dimensional topographic maps.



| <img src="/Users/kevinxu95/ACS/IDA/Notes/IDA_Lecture 9.assets/Screenshot 2020-05-28 at 14.31.37.png" style="zoom:50%;" /> | <img src="/Users/kevinxu95/ACS/IDA/Notes/IDA_Lecture 9.assets/Screenshot 2020-05-28 at 14.31.46.png" style="zoom:50%;" /> |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
|           $N_j(d) = \{c_k|\Vert k-j\Vert\leq d\}$            | $N_{ij}(d)=\{c_{kl}|\Vert\begin{bmatrix}k \\ l\end{bmatrix} - \begin{bmatrix}i \\ j\end{bmatrix}\Vert\ \leq d\}$ |
|              **Fig 4.** 1-dimensional structure              |                   2-dimensional structure                    |



### 6. Self-organsing map

Discover a **hidden** 1-dimensional structure of high-dimensional data by clustering, but **constrain** centroids $\{c_1,...,c_K\}$ to lie on a 1-dimensional *elastic*.

- Online clustering:

$$
c_{i(n)}^{new} = c_{i(n)}^{old} + \eta(t)(x_n-c_{i(n)}^{old})
$$

- **Self-organising map** (SOM):

$$
c_K^{new} = c_k^{old} + h(i(n),k)\cdot \eta(t)\cdot (x_n-c_k^{old})
$$

​		where $h(i(n), k)$ indicates how close the $k^{th}$ centroid is to the centroid $c_{i(n)}$ closest to $x_n$.

- We need: 
  - $h(i(n), i(n)) = 1$.
  - $h(i(n),k)\downarrow$ decreases as $c_k$ becomes further away from $c_{i(n)}$.
- We can choose:

$$
h(i(n),k) = e^{\frac {-\Vert i(n)-k\Vert}{\sigma}}
$$

​		where $\sigma$ is the **neighbourhood width**, i.e. strength of the elastic.



| <img src="/Users/kevinxu95/ACS/IDA/Notes/IDA_Lecture 9.assets/Screenshot 2020-05-28 at 14.51.03.png" style="zoom:50%;" /> |
| :----------------------------------------------------------: |
|       **Fig 5.** Neighbourhood updates topographic map       |



### 7. Neighbourhood width

- Initially choose a large value of $σ$ to allow **broad cooperation** between centroids.
- As algorithm proceeds, reduce the value of $σ$ for **fine tuning** of topographic structure of codebook.
- e.g. by analogy with the learning rate:

$$
\sigma(t) = \sigma(0)\cdot e^{\frac{-t}{v}}
$$

​		where $v>0$ is the *timescale*, $\sigma(0)$ is the *initial* neighbourhood width.



| <img src="/Users/kevinxu95/ACS/IDA/Notes/IDA_Lecture 9.assets/Screenshot 2020-05-28 at 14.55.33.png" style="zoom:50%;" /> |
| :----------------------------------------------------------: |
| **Fig 6.** Distance function decreases as neighbourhood width decreases |

