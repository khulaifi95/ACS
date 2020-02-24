# Intelligent Data Analysis

---

## Lecture 6 : Linear Discriminant Analysis



### 1 . PCA vs LDA

- PCA finds the direction of *maximum variance* of the data.
- LDA finds the direction of *maximum* class separation in the dataset.
- PCA does better in spreading out the data for better visualisation.
- LDA does better in separating the classes of data.



### 2. Linear Discriminant Analysis

- Suppose we have a dataset $X$ where each data point belongs to one of $K$ classes $c_1, ..., c_K$
- Let $X_k$ be the set of data points in $X$ that belong to class $c_k$

$$
X = X_1 \cup X_2 \cup \ ... \ \cup X_K
$$

- Let $C_k$ be the *covariance* matrix of the data set $X_k$
- Define the average **within-class covariance matrix** $C_w$ by

$$
C_w = \frac {C_1+C_2+...+C_K}{K}
$$

- Let $X_b$ be the matrix with $K$ rows whose $k^{th}$ row is the average of vectors in $X_k$
- The **between-class covariance matrix** is the *covariance* matrix $C_b$ of $X_b$



- Intuitively, to separate the classes we want to find a direction in the vector space that, simultaneously,
  - Maximises the between-class variance ($C_b$)
  - Minimises the within-class variance ($C_w$)
- The vector that has this property is the **eigenvector** of $C_bC_w^{(-1)}$ corresponding to the biggest eigenvalue.



- Notice, with LDA, the number of non-zero eigenvalues will be no greater than $c-1$. (Rank of covariance matrix)



### 3. Data Visualisation

- i-vector

  - Replace maximum variance criterion with a **probability-based** criterion.

- Neural networks

  - Encoder - decoder

- t-SNE

  - Represent high dimensional data with Gaussian distribution

  $$
  PDF_G = \frac {1}{\sigma \sqrt{2\pi}}exp(-\frac {(x-\mu)^2}{2\sigma^2})
  $$

  ​		  Each point in the dataset can be represented as 
  $$
  p_{ij} = \frac {exp\frac{(-\Vert x_i-x_j\Vert^2)}{2\sigma^2}}{\Sigma \frac {exp(-\Vert x_k - x_l \Vert^2)}{2\sigma^2}}
  $$
  

  - Approximate low dimensional data with t-distribution as

$$
PDF_t = \frac {\Gamma(\frac {v+1}{2})}{\sqrt {v\pi}\Gamma(\frac v 2)}(1+\frac {t^2} {v})^{\frac{-(v+1)}{2}}
$$

​					Each point in the dataset can be represented as
$$
q_{ij} = \frac {(1+\Vert y_i - y_i \Vert^2)^{-1}}{\Sigma(1+ \Vert y_k - y_l \Vert ^2)^{-1}}
$$
