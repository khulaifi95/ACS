# Intelligent Data Analysis

---

## Lecture 7. Latent Semantic Analysis



### 1. Vector Notation for Documents

- Suppose we have a set of documents $D = \{d_1, d_2, ... ,d_N\}$ as **corpus**
- Suppose number of different words in the whole corpus is $\mathbf V$ as **vocabulary size**
- Now suppose a document $d$ in $D$ contains $\mathbf M$ different **terms**: $\{t_{i(1)}, ..., t_{i(M)}\}$
- Finally, suppose term $t_{i(m)}$ occurs $f_{i(m)}$ **times**



​	The vector representation $vec(d)$ of $d$ is the $V$ dimensional vector:
$$
(0, ..., 0, w_{i(1),d}, 0, ..., 0, w_{i(2),d}, 0, ..., 0, w_{i(M),d}, 0, ..., 0)
$$
​	where $w_{i(m),d} = f_{i(m),d}.IDF_{i(m)}$ is the weighting.



### 2. Latent Semantic Analysis

- Suppose we have a corpus with a large number of documents
- For each document vector $vec(d)$ the dimension is potentially several thousands.
- Intuitively, focusing on some dimensions, when a document $d$ includes term 'sea' will also include 'beach'.
- Equivalently, if $vec(d)$ has a non-zero entry in the 'sea' component, it will often have a non-zero entry in the 'beach' component.

| <img src="IDA_Lecture 7.assets/Screenshot from 2020-02-25 04-54-33.png" alt="Screenshot from 2020-02-25 04-54-33"  /> |
| :----------------------------------------------------------: |
|           *Fig 1. Pair of terms about the seaside*           |



### 3. Finding Latent Semantic Classes

- If we can detect this type of structure, then we can discover relationships between words **automatically** from data.
- As shown in the example, an equivalence set of terms including 'beach' and 'sea' is both **about the seaside**.
- We first construct the word-to-vector matrix $A$
- Then decompose $A$ using **Singular Value Decomposition**.

```matlab
	[U, S, V] = svd(A);
```



### 4. Singular Value Decomposition

- Think of SVD as a more general version of eigenvector decomposition, which works not only for symmetric matrices but also **general** ones.
- An eigenvector of a square matrix $A$ is a vector $e$ such that $Ae = \lambda_e e$, where $\lambda$ is scalar.
- For certain matrices $A$ we can write $A = UDU^T$, where $U$ is an **orthogonal** matrix and $D$ is **diagonal**.
  - The elements of $D$ are the eigenvalues.
  - The columns of $U$ are the eigenvectors.

- **Word-document matrix**: The word-document matrix is the $N × V$ matrix whose $n$-th row is the document vector for the nth document.

| <img src="IDA_Lecture 7.assets/Screenshot from 2020-02-25 04-54-42.png" alt="Screenshot from 2020-02-25 04-54-42" style="zoom: 80%;" /> |
| :----------------------------------------------------------: |
|                *Fig 2. Word-document matrix*                 |

- We apply SVD on the word-document matrix $W$ by

$$
W = USV^T
$$

- The non-zero elements on the diagonal in $S$ are the **singular values** of most **significant correlation**, and the rows in $V$ are **directions** of most significant correlation.

| <img src="IDA_Lecture 7.assets/Screenshot from 2020-02-25 05-02-31.png" alt="Screenshot from 2020-02-25 05-02-31"  /> |
| :----------------------------------------------------------: |
| *Fig 3. Singular Value Decomposition of Word-Document Matrix* |



### 5. Interpretation of LSA

- The matrices $U$ and $V$ are **orthogonal**.

  - Their entries are real numbers.
  - $U$ is $N × N$($N$ as the number of documents), $V$ is $V × V$($V$ as the vocabulary size)
  - $UU^T = I = U^TU, VV^T = I = V^TV$.

- The **singular values** in $S$ are positive and satisfy
  $$
  s_1 \ge s_2 \ge s_3 \ge ...\ge s_N
  $$

- For the matrix $V$:
  - The columns $\{v_1, ..., v_V \}$ are unit vectors **orthogonal** to each other.
  - They form a new **orthonormal basis** (coordinate system) for the document vector space.
  - Each column of $V$ is a document vector corresponding to a **semantic class**(*topic*) in the corpus. - Super Vectors
  - The importance of the topic $v_n$ is indicated by magnitude of the singular vector $s_n$.
  - Since $v_n$ is a document vector, its $j$-th value corresponds to TF-IDF weight for $j$-th term in the vocabulary for the corresponding document/ topic.
    - A large value of $v_{nj}$ indicates that the $j$-th term is significant for the topic.



- For the matrix $U$:

  - It is easy to show that 

  $$
  Wv_n = USV^Tv_n = s_{nn}u_n
  $$

  - While $v_n$ describes the $n$-th topic as a combination of terms/ words, $u_n$ describes it as a combination of documents.



### 6. Proof: LSA $\cong$ PCA

- For the word-document matrix $W$, we define $m$ as the average document vector where

$$
\mathbf m = \frac 1 N \sum_{n=1}^N vec(d_n)
$$

- Subtract the average document vector from each row

$$
\mathbf W = \mathbf W - \mathbf m
$$

- Compute the covariance matrix $C$ of document vectors, where $W = USV^T$

$$
C = W^TW = (USV^T)^T(USV^T)
$$

- **Rule of multiple matrices transpose**: Transposing a product of matrices is the product of transposed matrices in the reversed order.

- We can derive $C$ by the rule of matrices transpose

$$
= V^{TT}S^TU^TUSV^T = VSISV^T = VS^2V^T
$$

​			which is the eigenvalue decomposition.

- While in PCA, $C = VDV^T$, $D = S^2$, in LSA, $U$ matrix is unique.



### 7. Topic-based Representation

- Since columns of $V$, $v_1, ..., v_V$ are an **orthonormal basis** for the document vector space, for a document $d$, $vec(d).v_n$ is the magnitue of the component of $vec(d)$ in the direction of $v_n$, e.g., the component of $vec(d)$ that are corresponding to topic $n$.

- Hence, the vector

$$
top(d) = \begin{bmatrix}vec(d).v_1 \\ vec(d).v_2 \\ . \\ . \\ vec(d).v_n\\ .\\ vec(d).v_V\end{bmatrix}
$$

​			is a **topic-based representation** of document $d$ in terms of topic $v_1,..., v_V$.



### 8. Topic-based Dimension Reduction

- Since the singluar value $s_n$ indicates the importance of topic $v_n$, we can choose to **truncate** the vector $top(d)$ when $s_n$ is too small

$$
top(d) \approx \begin{bmatrix}vec(d).v_1 \\ vec(d).v_2 \\ . \\ .\\ vec(d).v_n\end{bmatrix} = V^T_{(n)}vec(d)
$$

​		where $V_{(n)}$ is the $V × n$ matrix comprising the first $n$ columns of $V$.

- $top(d)$ is a **reduced n-dimensional vector representation** of document $d$.

  

### 9. Topic-based Word Representation

- Suppose $w$ is the $i$-th term in the vocabulary, the **one-hot vector** $h(w)$ is

$$
h(w) = \begin{bmatrix}0 \\ . \\ . \\ .\\ 0 \\ 1\\ 0\end{bmatrix} \leftarrow i^{th} \ entry
$$

- $h(w)$ is like the document vector for a document consisting of only the word $w$.

- We can use $h(w)$ to obtain a vector $top(w)$ that describes $w$ in terms of the topics that it contribute to:

$$
top(w) = \begin{bmatrix}h(w).v_1 \\ h(w).v_2 \\ . \\ .\\ h(w).v_n\end{bmatrix} = V^T_{(n)}top(w)
$$

​		where $V_{(n)}$ is the $V × n$ matrix comprising the first $n$ columns of $V$.

- Intuitively, two synonyms will contribute in a similar way to similar topics, and their vectors will **point in similar directions**.
- $top(w)$ is referred to as a **word embedding**.

- **Latent Dirichlet Allocation** is another approach for topic-based document analysis.
  - topics ~ distribution of words 



### 10. Thoughts on Document Vectors

- Once $d$ is replaced by $vec(d)$ it becomes a **point** in a vector space.
- How does the **structure** of the vector space reflect properties of the documents in it?
- Do clusters of vectors correspond to semantically related documents?
- Can we **partition** the vector space into semantically different regions?
- These ideas are a **link** between IR and Data Mining.