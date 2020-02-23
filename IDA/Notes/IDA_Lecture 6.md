# Intelligent Data Analysis

---

## Lecture 6 : Linear Discriminant Analysis



### 1 . PCA vs LDA

- PCA finds the direction of *maximum variance* of the data.
- LDA finds the direction of *maximum* class separation in the dataset.



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