# Intelligent Data Analysis

---

## Lecture 10 : Gaussian Mixture Models



### 1. Discrete Random Variables

- Suppose that $Y$ is a **random variable** which can take any value in  a discrete set $X= \{x_1, x_2,..., x_M\}$.
- Suppose that $y_1, y_2,...,y_N$ are samples of the random variable $Y$.
- If $c_m$ is the **number of times** that $y_n = x_m$ then an estimate of the probability that $y_n$ takes the value $x_m$ is given by:

$$
P(x_m) =p(y_n=x_m)\approx \frac {c_m} {N}
$$



### 2. Continuous Random Variables

- In most practical applications, the data can take any value in real $N$-dimensional space.
- Counting the number of occurrences of each value is enviable.
- A **probability density function** (PDF) on $N$-dimensional space $V$ is a function $p:V \rightarrow R$ such that:

$$
p(v)\geq 0, \forall v \in V, \int_vp(v)dv=1
$$

- A **random variable** $X$ defined on $V$ is governed by a probability density function $p$ if, for any $U \sube V$:

$$
prob(X \in U) = \int_Up(v)dv=1
$$



### 3. Gaussian PDF

- A univariate Gaussian PDF is defined by two parameters:

  - **mean** $\mu$
  - **variance** $\sigma$
  - **Standard deviation** $\sqrt\sigma$ - 68%, 95%, 99%

  $$
  p(x) = p(x|\mu,\sigma)=\frac {1}{\sqrt {2\pi\sigma}}\exp(-\frac{(x-\mu)^2}{2\sigma}) \leftarrow \text {define bell shape}\\
  \ \ \ \ \ \ \ \  \ \ \ \ \ \ \ \ \ \ \ \uparrow \text {ensure area under curve is 1}
  $$

  

- A multivariate Gaussian PDF is defined on a vector space:

  - **mean vector** $\mathbf m$
  - **covariance matrix** $\sigma$

  $$
  p(x)=\frac {1}{\sqrt{(2\pi)^N\vert\Sigma\vert}}\exp(\frac {-1}2(\mathbf m-x)^T\Sigma^{-1}(\mathbf m-x))
  $$

  ​		where $\mathbf m$ is the $N$-dimensional **vector mean** and $\Sigma$ is the $N \times N$ **covariance matrix**.

  - Visualising multivariate Gaussian PDF is by drawing the **1-standard deviation contour**, i.e., the set of points that lie one standard deviation from the mean.



### 4. Fitting a Gaussian PDF to Data

- Suppose $y=y_1, ...,y_n,...,y_N$ is a set of $N$ data values.
- For a Gaussian PDF $p$ with mean $\mu$ and variance $\sigma$, define:

$$
p(y|\mu,\sigma)=\prod\limits_{n=1}^Np(y_n|\mu,\sigma)
$$

- How to choose $\mu$ and $\sigma$ to **maximise** $p(y|\mu, \sigma)$?



### 5. Maximum Likelihood Estimation

- The *best fitting* Gaussian maximises  $p(y|\mu, \sigma)$.
- Terminology:
  - $p(y|\mu, \sigma)$ as a function of $y$ is the **probability (density)** of $y$.
  - $p(y|\mu,\sigma)$ as a function of $\mu, \sigma$ is the **likelihood** of $\mu, \sigma$.
- Maximising $p(y|\mu,\sigma)$ with respect to $\mu, \sigma$ is **maximum likelihood** estimation of $\mu, \sigma$.
  - The ML estimate of $\mu$ should be the   **sample mean** of $y_1, ..., y_N$.
  - The ML estimate of $\sigma$ should be the **sample variance** of $y_1, ..., y_N$.
- $p(y|\mu,\sigma)$ is maximised by setting:

$$
\mu=\frac {1}{N}\sum\limits_{n=1}^Ny_n, \sigma=\frac {1}{N}\sum\limits_{n=1}^N(y_n-\mu)^2
$$

- Proof:
  - Suppose $Y=\{y_1, y_2,..., y_N\} \in \mathbb R$, we choose $\mu, \sigma$ s.t. $P(Y)|max$.
  - Maximising $P(Y)=\prod\limits_{n=1}^NP(y_n) = \prod\limits_{n=1}^N\frac {1}{\sqrt{2\pi\sigma}}\exp(-\frac {(x-\mu)^2}{2\sigma})$.
  - Take log on both sides, $\log(P(Y)) = \sum\limits_{n=1}^{N}[\log(\frac {1}{\sqrt{2\pi\sigma}})-\frac{(x-\mu)^2}{2\sigma}]$.
  - Differentiate the equation, $\frac{d\mu}{dm} = \sum\limits_{n=1}^N[0-\frac{1}{2\sigma}.2(x-\mu)(-1)] = \frac 1 N.\sum\limits_{n=1}^Nx_m$.
  - We then have the MLE of $\mu$ is the sample mean.



### 6. Multi-modal Distributions

- In practice, the natural distributions do not follow the simple bell-shaped Gaussian curve.
- Include several distinct peaks if the data rises from several different sources.
- These peaks are the **modes** of the distribution which is called **multi-modal**.



### 7. Gaussian Mixture Models

- Gaussian mixture models are used to model multi-modal and other non-Gaussian distributions.
- A GMM is a **weighted** average of several Gaussian PDFs, the **component** PDFs.
- Example: 2 component mixture model
  - Component 1: $\mu =0, \sigma=0.1$
  - Component 2: $\mu=2, \sigma=1$
  - $w_1=0.2, w_2=0.8$

| <img src="IDA_Lecture 10.assets/Screenshot from 2020-03-12 09-52-38.png" alt="Screenshot from 2020-03-12 09-52-38" style="zoom: 67%;" /> |
| :----------------------------------------------------------: |
|                *Fig 1. 2 components of a GMM*                |

- In general, an $M$ component Gaussian mixture PDF is defined by:

$$
p(y)=\sum\limits_{m=1}^Mw_mp_m(y)
$$

​		where each $p_m$ is a Gaussian PDF and $0\leq w_m \leq 1, \sum\limits_{m=1}^Mw_m=1$.



### 8. Relationship with Clustering

- Both model data using a set of centroids and means.
- In clustering there is no parameters specifying *spread* of a cluster, while in a GMM component this is done by the covariance matrix.
- In clustering, we assign a sample to the **closest** centroid. In a GMM a sample is assigned to **all** components with varying probability.



### 9. Parameter Estimation

- A Gaussian Mixture Model with $M$ components has:
  - $M$ means $\mu_1, ..., \mu_M$.
  - $M$ variances $\sigma_1, ..., \sigma_M$.
  - $M$ mixture weights $w_1, ...,w_M$.
- How to estimate these parameters given $Y$?
  - i.e., how to find a maximum likelihood estimate of all parameters?

- We don't know which component each sample belongs to.



### 10. E-M Algorithm

1. Choose **number** of GMM components $M$ and **initial** GMM parameters: ($\mu, \sigma, w$).
2. For each sample $x_t$ and each GMM component $m$ calculate $P(m|y_t)$ using **Bayes** theorem and **current** parameters.
3. Define new **estimate** of $m_i$ as:

$$
\bar m_i=\frac {\sum\limits_{t=1}^TP(m|y_t)y_t}{\sum\limits_{t=1}^TP(m|y_t)}
$$

- From Bayes' theorem:

$$
P(m|y_t)=\frac {p(y_t|m)P(m)}{p(y)}= \frac {p_m(y_t)w_m}{\sum\limits_{k=1}^Mp_k(y_t)w_k}
$$



| <img src="IDA_Lecture 10.assets/Screenshot from 2020-03-12 14-04-32.png" alt="Screenshot from 2020-03-12 14-04-32" style="zoom:50%;" /> |
| :----------------------------------------------------------: |
|               *Fig 2. Fitting GMM parameters*                |

- Example:
  - $g_1(x)\approx0$
  - $g_2(x) = 0.054$
  - $P(1|x) \approx \frac {0\times 0.3}{0\times 0.3+0.054\times 0.7}=0$
  - $P(2|x) \approx 1$



### 11. E-M and K-means Clustering

- Comparison
  - E-M: Estimating GMM component means.
  - K-means: Estimating centroids in clustering.
- Notation
  - GMM component means $m_1, ..., m_N$.
  - Cluster centroids $c_1, ..., c_N$.
- Given a sample $y$
  - E-M: Calculate $P(n|y)$ for each GMM component $n.$
  - K-means: Calculate $d(c_n,y)$ for each centroid $c_n$.
- Reestimation
  - E-M: For each $n$, allocate $P(n|y_n)y_n$ to reestimate $m_n$.
  - K-means: Allocate all of $y_n$ to the closest centroid by $\min \{d(c_n,y)\}$.

- In some implementations of E-M, $y$ is used **only** to reestimate the mean $m_n$ for the most probable GMM component $n$, i.e. $\max\{P(n|y)\}$.

- If the GMM component variances are equal, and all of the weights are equal, then the following are equivalent:

$$
\arg\min\{d(y, m_n)\} \approxeq \arg\max\{P(n|y)\}
$$

​		that is $m_n$ is closest centroid to $x$, while $n$ is the most probable GMM component.

- E-M cannot return global optimum.



### 12. Supervectors

- Suppose that an item of data consists of a **variable length sequence** of vectors $Y=y_1,...,y_t,...,t_T$.

- Since the length $T$ varies, $Y$ cannot be directly treated as a vector, e.g. PCA inapplicable.

- **Vectorisation of continuous data**

  1. We choose $M$ number of GMM components.

  2. Apply E-M algorithm together with the data set $Y$ to create a $M$-component GMM $\mathbf M$.
  3. Obtain a supervector representation $sup(Y) (M \times N)$ of $Y$ by stacking the **mean vectors** of the components of $M$.

- Example:

  - $N=3, M=4$ and means of components of $M$ are:

  $$
  m_1 = \begin{bmatrix}1 \\2 \\1\end{bmatrix}, m_2 = \begin{bmatrix}-3 \\4 \\1\end{bmatrix}, m_3 = \begin{bmatrix}10 \\12 \\-12 \end{bmatrix}, m_4 = \begin{bmatrix}-1 \\-3 \\-2\end{bmatrix}
  $$

  

  - Then the supervector:

  $$
  sup(Y) = \begin{bmatrix}1 \\2 \\1 \\-3\\4\\1\\10\\12\\-12\\-1\\-3\\-2\end{bmatrix}
  $$

- Problems:
  - Different initiation lead to different optimised GMMs and supervectors.
  - Even if two GMMs are identical, the order of components may be different.
  - It is difficult to **compare** supervectors for two different sequences.
  - The solution is to use a **universal background model** (UBM).



### 13. Universal Background Model

- For **speaker verification**, we first build a **single** GMM, called the universal background model from **all** of the data of all speakers.
  - Inventory of speech sounds.
- Given a new sequence $Y_s$ from a speaker $s$, use the E-M algorithm and the UBM to create a GMM $M_s$, then the supervector $sup(Y_s)$.
- $M_s$ can be interpreted as the **speaker-specific** inventory of sounds for the speakers.
- $sup(Y_s)$ is a **vector representation** of the inventory of speech for speaker $s$.

- Since they come from the **same** initial UBM, $sup(Y_s)\ \& \ sup(Y_r)$ are **comparable**.
- Using **i-vectors** for robust dimension reduction.

