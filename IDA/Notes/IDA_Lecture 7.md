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



### 3. Latent Semantic Classes