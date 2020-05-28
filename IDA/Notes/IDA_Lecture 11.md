# Intelligent Data Analysis



## Lecture 11 : Page Rank



### 1. Motivation

- So far, whether or not a document $d$ is retrieved in response to query q depends only on $sim(q,d)$.

- All documents *assumed equal* - relevance of a document for a query depends only on the similarity score. This is not true.

-  The **Page rank** of a document is its **prior importance**.

  - Prior importance: 

    measure of **probability** that the document is the one you want **before** you formulate the query.



### 2. Prior probability of a document

Assign a probability $P(d)$ to each document $d$ in our corpus.

- $P(d)$ as the prior (or *a priori*) probability of $d$.

- $P(d)$ as the probability that $d$ is relevant to $q$ before the user creates query $q$.
- $P(d)$ as the **Page rank** of $d$.

**Assumption**:

The prior relevance of a document to any query is related to how **often** that document is accessed.



### 3. Authority of a document

For a document $d$ on the web, we could define the **authority** of $d$, denoted as $x_d$, based on the number of documents that have a *hyperlink* to $d$.

- Relies on the underlying democracy of the web users vote with their mouse buttons.
- Now the ranking of a document $d$ in response to $q$ depends on both $sim(q, d)$ and $x_d$.
- **Not** all links are **equal**.

The authority of a document takes into account:

1. Number of incoming links (citations)
2. Authority of pages $c$ that cite $d$
3. Selective citations from $c$ more valuable than uniform citations of a large number of documents

**Self-referencing** $\rightarrow$ *Markov*



### 4. Markov model

An N-state Markov model consists of:

- A set of N **states** $\{\sigma_1,...,\sigma_N\}$.
- An **initial state** probability distribution

$$
P^{(0)} = \begin{bmatrix}P^{(0)}(1) \\ P^{(0)}(2) \\ \vdots \\ P^{(0)}(N)\end{bmatrix}, \sum_{n=1}^NP^{(0)}(n) = 1
$$

​		where $P^{(0)}(n)$ is the probability that the model starts in state $n$. 

- An $N\times N$ state transition probability matrix $A$, where $a_{ij}$ is the probability of a transition from state $i$ to state $j$ at a time step

$$
A = \begin{bmatrix}
a_{11} & a_{12} & \cdots & a_{1j} & \cdots & a_{1N} \\ 
\vdots & \vdots & \vdots & \vdots & \vdots & \vdots & \\
a_{i1} & a_{i2} & \cdots & a_{ij} & \cdots & a_{iN} \\ 
\vdots & \vdots & \vdots & \vdots & \vdots & \vdots & \\
a_{N1} & a_{N2} & \cdots & a_{Nj} & \cdots & a_{NN} \\ 
\end{bmatrix}, \sum_{n=1}^Na_{in}=1, \forall i =1,...,N
$$



### 5. Convergence of Markov processes

The probability $P^{(t+1)}(k)$ i.e. the probability of being in state $k$ at the next time step is calculated by:
$$
P^{(t+1)}(k)=\sum_{n=1}^NP^{(t)}(n)\cdot a_{nk}
$$
In matrix form with reference to the state probability distribution $P^{(t)}$ and transition matrix $A$:
$$
P^{(t)} = A^TP^{(t-1)}
$$
Extrapolate to the initial state:
$$
P^{(t)} = A^TP^{(t-1)} = A^TA^TP^{(t-2)} = \cdots = (A^T)^tP^{(0)}
$$
Suppose $P^{(t)}\rightarrow P$ as $t\rightarrow \infty$, then:
$$
P = A^TP
$$
we can show that $P$ is an **eigenvector** of $A^T$ with **eigenvalue** of 1.



**Example**:

A company intranet consists of three pages $W_1, W_2, W_3$. The way in which staff access and move between the pages in a browsing session is modelled as a 4-state Markov model $\mathcal M$, with initial state probability distribution $P^{(0)}$ and state transition probability matrix $A$ given by:
$$
P^{(0)}=\begin{bmatrix}0.6 \\ 0.2 \\ 0.2 \\ 0\end{bmatrix},
A = \begin{bmatrix}
0.3 & 0.3 & 0.3 & 0.1 \\
0.2 & 0.2 & 0.2 & 0.4 \\
0.2 & 0.3 & 0.2 & 0.3 \\
0 & 0 & 0 & 1
\end{bmatrix}
$$

- Pages $W_1, W_2, W_3$ correspond to states 1, 2 and 3 of the Markov model.
- We can think of state 4 as a **terminal** state. Once state 4 is entered the browsing session **stops**

$$
P^{(2)} = A^TP^{(1)} = A^TA^TP^{(0)}
$$

Thus,
$$
P^{(1)} = \begin{bmatrix}
0.3 & 0.2 & 0.2 & 0 \\
0.3 & 0.2 & 0.3 & 0 \\
0.3 & 0.2 & 0.2 & 0 \\
0.1 & 0.4 & 0.3 & 1
\end{bmatrix}\begin{bmatrix}0.6 \\ 0.2 \\ 0.2 \\ 0\end{bmatrix} = \begin{bmatrix}0.26 \\ 0.28 \\ 0.26 \\ 0.2\end{bmatrix}
$$
Therefore,
$$
P^{(1)} = \begin{bmatrix}
0.3 & 0.2 & 0.2 & 0 \\
0.3 & 0.2 & 0.3 & 0 \\
0.3 & 0.2 & 0.2 & 0 \\
0.1 & 0.4 & 0.3 & 1
\end{bmatrix}\begin{bmatrix}0.26 \\ 0.28 \\ 0.26 \\ 0.2\end{bmatrix} = \begin{bmatrix}0.196 \\ 0.212 \\ 0.186 \\ 0.416\end{bmatrix}
$$
From observation, we can deduce that as $t\rightarrow \infty, P^{(t)}(4)\rightarrow 1$, and in the limit:
$$
P^{(t)}\rightarrow\begin{bmatrix}0 \\ 0 \\ 0 \\ 1\end{bmatrix}
$$


| <img src="/Users/kevinxu95/ACS/IDA/Notes/IDA_Lecture 11.assets/Screenshot 2020-05-28 at 20.34.00.png" style="zoom:50%;" /> |
| :----------------------------------------------------------: |
| **Fig 1.** Calculate the state probability distribution at time 1 |





### 6. Conditions for convergence

Q: Does the state probability distribution $P^{(t)}$ always converge as $t\rightarrow \infty$?

A: No, consider:
$$
P^{(0)}=\begin{bmatrix}0.3 \\ 0.1 \\ 0.6\end{bmatrix}, A = \begin{bmatrix}0 & 1 & 0\\1 & 0 & 0\\ 0 & 1 & 0\end{bmatrix}
$$
**Sufficient** conditions to ensure convergences are:

1. The model must be **irreducible**:
   - For every pair of states $s_0, s_1$, there must be a time $t$ and a state sequence $x_1,x_2,...,x_t$ with $s_0=x_1, s_1=x_t$ and $P(x_1,x_2,...,x_t)>0.$
2. The model must be **aperiodic**:
   - A state is aperiodic if the *HCF* of the set of return times for the state must be 1.
   - A model is aperiodic if all of its states are aperiodic.



### 7. Simplified Page rank

Given a set of documents $D=\{d_1,...,d_N\}$, define:

- $pa(n)$: the *set* of pages pointing to $d_n$.
- $h_n$: the number of hyperlinks from $d_n$.

The simplified **Page rank** $x_n$ for document $d_n$ is given by:
$$
x_n = \sum_{d_m\in pa(n)}\frac{x_m}{h_m}
$$
Since this equation is **self-referencing**, re-write as:
$$
x_n^{(i+1)} = \sum_{d_m\in pa(n)}\frac {x_m^{(i)}}{h_m}
$$


| <img src="/Users/kevinxu95/ACS/IDA/Notes/IDA_Lecture 11.assets/Screenshot 2020-05-28 at 20.41.01.png" style="zoom:33%;" /> |
| :----------------------------------------------------------: |
|               **Fig 2.** Simplified Page rank                |



### 8. Matrix formulation

Let $W$ be the $N\times N$ matrix whose entry is given by:
$$
w_{m,n} = \cases{\frac 1{h_n} \ \ \ \ \ \ \text{if there is a hyperlink from} \ x_n \text{to} \ x_m \\ 0 \ \ \ \ \ \ \ \ \text{otherwise}}
$$
Let $x^{(i)}$ be the $N\times 1$ column vector whose $n^{th}$ entry is $x_n^{(i)}$, then:
$$
x^{(i+1)} = Wx^{(i)}
$$
The matrix multiplication could be in the following form:
$$
Wx^{(i)} =\begin{bmatrix}
\frac1{h_1} & \frac1{h_2} & \cdots & \frac1{h_n} & \cdots & \frac1{h_N} \\ 
0 & \frac1{h_2} & \vdots & \frac1{h_n} & \vdots & \frac1{h_N} & \\
\frac1{h_1} & 0 & \cdots & \frac1{h_n} & \cdots & \frac1{h_N} \\ 
\vdots & \vdots & \vdots & \vdots & \vdots & \vdots & \\
\frac1{h_1} & \frac1{h_2} & \cdots & \frac1{h_n} & \cdots & \frac1{h_N} \\ 
\end{bmatrix}
\begin{bmatrix}x_1^{(i)} \\ x_2^{(i)} \\ x_3^{(i)} \\ \vdots \\ x_N^{(i)} \\ \end{bmatrix}
$$
This is a **Markov** model of simplified Page rank, where
$$
P^{(0)} = x^{(0)}\\
A = W^T
$$
In other words:

- $x^{(0)}$ is the **initial state** of Page rank.
- $W$ is the **transpose** of the state transition probability matrix.



### 9. The damping factor

From above equations, all the authority $x_n$ of a page $d_n$ comes from the pages that have hyperlinks to it.

- A page with no incoming hyperlinks will have authority 0.
- A solution is the **damping factor** $d\in \mathbb R, 0<d<1$.
  - $d$ is the proportion of authority that a page gets *by default.*
- The simplified Page rank equation becomes:

$$
x^{(i+1)} = (1-d)Wx^{(i)}+\frac dN1_N
$$

​		where $1_N$ is a $N\times 1$ column vector of 1s.

- Consider the expression as a dynamical system:

$$
x^{(t+1)} = (1-d)Wx^{(t)}+\frac dN1_N
$$

​		**converges** for any initial condition $x^{(0)}$ to a **unique** fixed point $x^*$ such that
$$
x^* = (1-d)Wx^*+\frac dN1_N
$$


### 10. Dangling pages

A page which contains no hyperlinks (out) is called a **dangling page**.

- If $d_n$ is a dangling page then the n-th column of $W$ consists entirely of zeros.
- Hence the n-th column sums to 0 and $W$ is no longer a (column) **stochastic** matrix.
- In this case some parts of the above analysis no longer holds.

**Solution**:

- Introduce a new “dummy” page $d_{N+1}$.

- Add a hyperlink to $d_{N+1}$ from every dangling page.

- Extend the transition matrix $W$ to get a new $(N +1)\times(N +1)$ matrix $\bar W$.

  - Introduce a dangling page indicator $\vec r = [r_1, r_2,\cdots , r_N ]$ where

  $$
  r_n = \cases {1 & if is a dangling page \\ 0 & otherwise}
  $$

  - Add $\vec r$ to the bottom of $W$.
  - Add an additional column of N 0s followed by a single 1.

- Suppose that page $n$ is a dangling page:

$$
\bar W = \begin{bmatrix}
\frac1{h_1} & \frac1{h_2} & \cdots & 0 & \cdots & \frac1{h_N} & 0\\ 
0 & \frac1{h_2} & \vdots & 0 & \vdots & \frac1{h_N} & 0\\
\frac1{h_1} & 0 & \cdots & 0 & \cdots & \frac1{h_N} & 0\\ 
\vdots & \vdots & \vdots & \vdots & \vdots & \vdots & 0\\
\frac1{h_1} & \frac1{h_2} & \cdots & 0 & \cdots & \frac1{h_N} & 0\\ 
0 & 0 & 0 & 1 & \cdots & 0  & 1
\end{bmatrix}
$$

1. Each dangling page has a hyperlink to $d_{N+1}$.

2. $d_{N+1}$ only has a link to itself.



**Alternative**: Create links from dangling pages to **all** pages.



### 11. Probabilistic interpretation of Page rank

- Think of each page as a **state** of a Markov model or **nodes** connected by hyperlink structure.
- Connection between node $p$ and $q$ is **weighted** by the probability of its **usage**.
- Weights depend only on **current** node, not how we got there. (**Markov**)
- At any time $t$, the surfer becomes bored with probability $1 − d$ and jumps to any web page with equal probability $\frac 1N$.

