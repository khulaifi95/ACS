# Intelligent Data Analysis

---

## Lecture 5 : Principal Components Analysis



### 1. Covariance

​	The **sample covariance** for X between the $i^{th}$ and $j^{th}$ coordinates is given by
$$
\sigma^{ij}=\frac {1}{N-1}\sum_{n=1}^{N} (\mu^i-x_n^i)(\mu^j-x_n^j)
$$
​	The sample covariance is represented as a $D \times D$ **real symmetric** matrix $\Sigma$
$$
\Sigma =\begin{bmatrix}
\sigma^{11} & \sigma^{12} & \cdots & \sigma^{1D}\\
\sigma^{21} & \sigma^{22} & \cdots & \sigma^{2D}\\
\vdots & \vdots & \vdots & \vdots\\
\sigma^{D1} & \sigma^{D2} & \cdots & \sigma^{DD}
\end{bmatrix}
$$

[^Real symmetric matrix]: A matrix has the same number of rows and columns, which is equivalent to its transpose.



### 2. Motivation

<img src="/home/kevinxu95/Pictures/Screenshot from 2020-02-06 15-03-38.png" alt="Screenshot from 2020-02-06 15-03-38" style="zoom:50%;" />

- Positive covariance with respect to standard coordinates
- No covariance with respect to "red" coordinate system
- How can we identify the red coordinate system **automatically**?



### 3. Derivation of PCA

- Intuitively, we want **direction of maximum variance**
- For a potential first coordinate $\vec u$,
  1. Project data onto $\vec u$
  2. Calculate variance in direction $\vec u$
  3. Maximise with respect to $\vec u$
- Constrained optimisation problem: $\vec u$ needs to be a **unit vector**  ($\Vert \vec u\Vert = 1$)
  - Heuristic solution: $\vec u$ as the **eigenvector** of covariance matrix corresponding to the **biggest eigenvalue**.



### 4. Principle Components Analysis

​	PCA is a tool to reveal the structure of a data set $X \in \mathbb{R}^N$ .

- Apply **eigenvector decomposition** to covariance matrix $C$ of $X$
  $$
  C = UDU^T
  $$
  
  where $D$ is a **diagonal** matrix with non-negative real values and $U$ is an orthogonal matrix.
  
  - Columns of $U$ are a **new basis** {$u_1, ..., u_N$} for $\mathbb R^N$. Basis vectors $u_n$ point in directions of maximum variance of $X$
  - **Eigenvalue** (element of D) $d_n$ is the **variance** of the data in the direction $u_n$.



### 5. PCA in Steps

#### Step 1:

- Calculate the sample mean $\mu$

- Subtract $\mu$ from each data sample

#### Step 2:

- Calculate the covariance matrix $C$

- Apply eigenvector decomposition to $C$

$$
C = \begin{bmatrix}6.56 & 3.64 \\ 3.64 & 2.35\end{bmatrix}, C= UDU^T,
$$
​	where 
$$
D = \begin{bmatrix}0.25 & 0 \\ 0 & 8.67\end{bmatrix}, U = \begin{bmatrix}0.5 & -0.867 \\ -0.867 & -0.5\end{bmatrix}
$$

#### Step 3:

- Eigenvectors are new basis / coordinate system
- Apply orthogonal matrix $U$ to **change coordinate** system
- (Alternatively, project data onto new basis vectors)

#### Step 4:

- Variance **normalisation** (if required)
- **Divide** $i^{th}$ coordinate of each data point by $i^{th}$ eigenvalue
- Resulting data has covariance matrix $I$



### 6. 2-D Example

- $X = \big\{ \begin{bmatrix}1 \\\ 1\end{bmatrix}, \begin{bmatrix}0 \\\ 0\end{bmatrix}, \begin{bmatrix}-1 \\\ -1\end{bmatrix}\big\}$

- $\mu = \begin{bmatrix}0 \\\ 0\end{bmatrix}, C = cov(X) = \begin{bmatrix}1 &1 \\\ 1 & 1\end{bmatrix}$

- For a general matrix $A$, a scalar $\lambda$ is an eigenvalue of $A$ if and only if there is a *nonzero* vector $\mathbf v$, called an **eigenvector**, such that

$$
A\mathbf v = \lambda \mathbf v
$$

​			or equivalently:
$$
(\lambda I -A)\mathbf v=0
$$
​			And the characteristic equation has the eigenvalues as roots. 
$$
\lambda (\lambda -2) =0
$$
​			hence $\lambda = 2$ or $\lambda = 0$ - just one eigenvalue.

- For $\lambda =2$ solve $(6)$ and we have  $\begin{bmatrix}x \\y\end{bmatrix}=\begin{bmatrix}1 \\1\end{bmatrix}$.

- After scaling, the first unit basis vector is $u_1 = \begin{bmatrix} \frac {1}{\sqrt 2} \\ \frac {1}{\sqrt 2} \end{bmatrix}$, and so $u_2 = \begin{bmatrix} -\frac {1}{\sqrt 2} \\ \frac {1}{\sqrt 2} \end{bmatrix}$

- Therefore
  $$
  D = \begin{bmatrix}2 & 0 \\ 0 & 0\end{bmatrix}, U = \begin{bmatrix}\frac {1}{\sqrt 2} & -\frac {1}{\sqrt 2} \\ \frac {1}{\sqrt 2} & \frac {1}{\sqrt 2}\end{bmatrix}
  $$

  $$
  C = UDU^T = \begin{bmatrix}\frac {1}{\sqrt 2} & -\frac {1}{\sqrt 2} \\ \frac {1}{\sqrt 2} & \frac {1}{\sqrt 2}\end{bmatrix}\begin{bmatrix}2 & 0 \\ 0 & 0\end{bmatrix}\begin{bmatrix}\frac {1}{\sqrt 2} & \frac {1}{\sqrt 2} \\ -\frac {1}{\sqrt 2} & \frac {1}{\sqrt 2}\end{bmatrix}
  $$

  

<img src="IDA_Lecture 5.assets/Screenshot from 2020-02-06 16-27-47.png" alt="Screenshot from 2020-02-06 16-27-47" style="zoom: 50%;" />