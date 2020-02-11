# Intelligent Data Analysis

---

## Lecture 4 : Vector Representation of Documents



### 1. Vector Notation for Documents

1. Suppose that we have a set of documents $D$ = {$\mathbf {d_1, d_2, ..., d_N}$} 
   - As the **corpus** for IR

2. Suppose that the number of different words in the **whole corpus** is $\mathbf V$
   - As the **vocabulary size**

3. Suppose a document $d$ in $D$ contains $\mathbf M$ **different terms**: {$\mathbf {t_{i(1)}, t_{i(2)}, ..., t_{i(M)}}$}
4. Finally, suppose term $\mathbf t_{i(m)}$ occurs $f_{i(m)}$ times



### 2. Uniqueness

1. Is the mapping between documents and vectors *one-to-one*?

   - If $d_1, d_2$ are documents, is it true that $vec(d_1) = vec(d_2)$ if and only if $d_1 = d_2$? 

   **Ans**: *False*. The sequence of each document could be different.

2. If $\lambda$ is a scalar and $vec(d_1) = \lambda vec(d_2)$, what does this tell you about $d_1$ and $d_2$?

   **Ans**: That the term existed in $d_1$ is $\lambda$ times frequent than that in $d_2$.

   

### 3. Example



|           | d1   | d2   | d3   | #Doc | IDF  | **$W_{(t,d1)}$** | **$W_{(t,d2)}$** | **$W_{(t,d3)}$** |
| --------- | ---- | ---- | ---- | ---- | ---- | ---------------- | ---------------- | ---------------- |
| **cat**   | 2    | 1    |      | 2    | 0.41 | 0.82             | 0.41             |                  |
| **chase** |      | 1    |      | 1    | 1.1  |                  | 1.1              |                  |
| **dog**   |      | 1    |      | 1    | 1.1  |                  | 1.1              |                  |
| **home**  |      |      | 1    | 1    | 1.1  |                  |                  | 1.1              |
| **mat**   | 1    |      |      | 1    | 1.1  | 1.1              |                  |                  |
| **mouse** |      |      | 1    | 1    | 1.1  |                  |                  | 1.1              |
| **sat**   | 1    |      |      | 1    | 1.1  | 1.1              |                  |                  |
| **stay**  |      |      | 1    | 1    | 1.1  |                  |                  | 1.1              |


$$
vec(d_1) = \begin{bmatrix} {0.82\\0\\0\\0\\1.1\\0\\1.1\\0}\end{bmatrix}
vec(d_2) = \begin{bmatrix} {0.41\\1.1\\1.1\\0\\0\\0\\0\\0}\end{bmatrix},
vec(d_3) = \begin{bmatrix} {0\\0\\0\\1.1\\0\\1.1\\0\\1.1}\end{bmatrix}
$$


### **4**. Document Length

- The length **(norm)** of a vector $x = (x_1, ..., x_N)$ is given by:

- In the case of a **document vector**,
  $$
  \Vert vec(d)\Vert = \sqrt {w^2_{i(1)d}+w^2_{i(2)d}+,,,+w^2_{i(M)d}}
  $$
  where $\Vert d \Vert$ is the length of the document $d$.

  

### 5. Document Similarity

​	Suppose $d$ is a document and $q$ is a query,

- If $d$ and $q$ contain the **same words** in the **same proportions**, then $vec(d)$ and $vec(q)$ will point the same direction
- If $d$ and $q$ contain different words, then $vec(d)$ and $vec(q)$ will point in different directions
- Intuitively, the **greater** $\uparrow$ the angle between $vec(d)$ and $vec(q)$, the **less similar** $\downarrow$ the document $d$ is with the query $q$. 



### 6. Cosine Similarity

- Define the **cosine similarity** between document $d$ and query $q$ by:
  $$
  CSim(q,d) = cos \theta
  $$
  where $\theta$ is the angle between $vec(c)$ and $vec(d)$.

- If $u=(x_1,y_1)$ and $v = (x_2, y_2)$ are vectors in 2 dimensions, then
  $$
  cos(\theta) = \frac {x_1x_2+y_1y_2}{\Vert u \Vert \Vert v \Vert}= \frac {u.v}{\Vert u \Vert \Vert v \Vert}
  $$
  This result holds for vectors in any $\mathbf N$ dimensional space.

| <img src="IDA_Lecture 4.assets/Screenshot from 2020-02-06 14-40-15.png" alt="Screenshot from 2020-02-06 14-40-15" style="zoom: 67%;" /> |
| :----------------------------------------------------------: |
|            *Fig 1. The angle between two vectors*            |



- Hence, if $q$ is a query, $d$ is a document, and $\theta$ is the angle between $vec(q)$ and $vec(d)$, then:
  $$
  CSim(q,d)=cos(\theta)=\frac {vec(q).vec(c)}{\Vert q\Vert \Vert d \Vert}= \frac {\sum\limits_{t\in q \cap d}w_{tq}.w_{td}}{\Vert q\Vert \Vert d\Vert} \\
  \nonumber = Sim(q,d)
  $$

- Cosine similarity is **equivalent** to TF-IDF similarity.