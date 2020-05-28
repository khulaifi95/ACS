# Intelligent Data Analysis



## Lecture 12 : Query Eepansion



### 1. Query expansion

Add terms to the query to increase the **overlap** between it and potentially **relevant** documents.

- Approaches:
  1. Apply user feedback
  2. Exploit semantic relationships between words



### 2. Query reformation

- User provides feedback on the results of retrieval.
- Revise the query in response to user feedback.
- Query **expansion**: Add terms in relevant documents that are not in query.
- Term **reweighting**: Increase the weight of query terms in relevant documents and decrease the weight of query terms in irrelevant documents:

$$
w_{td} = \lambda \times f_{td} \times IDF(t)
$$



### 3.Knowledge-based methods

- Synonyms: (*Thesaurus*)

  If $w_1$ is in $q$ and $w_1, w_2$ are synonyms, add $w_2$ to $q$.

- Hyponyms: (subordinate words)

- Hypernyms: (generalisations)

**WordNet** is a lexical database of semantic relations between words organised as a set of hierarchical trees.



### 4. Data-driven methods

- Let $w$ be the n-th word in the vocabulary.
- Represent $w$ as a one-hot vector:

$$
vec(w) = \begin{bmatrix}0 \\ \vdots \\ 0 \\1 \\ 0\end{bmatrix}
$$

​		where $vec(w)_m=1$ if $m=n$, otherwise 0.

- From LSA, recall that $V_{(n)}$ is the $V\times n$ matrix comprising first n columns of $V$.
- $top(w)=V^Tvec(w)$ is a **topic-based** representation of $w$.
- Thus we have $top(w)=V_{(n)}^Tvec(w)$ is a **dimensional-reduced** topic-based representation of $w$.
- $top(w)$ represents a word $w$ in terms of the **topics for which it is significant**.



### 5. Vector representation of words

Suppose $u$ and $w$ are words.

- If $u$ and $w$ are synonyms, they will be similarly important for the same topics.
- In this case, $top(u)$ and $top(w)$ will point in similar directions.

- Since the measure of similarity between two words is given by:

$$
CSim(u,w) = \frac {top(u)\cdot top(w)}{\Vert top(u)\Vert\Vert top(w)\Vert} = \cos(\theta)
$$

- If $CSim(u,w)$ is sufficiently large, we treat $u,w$ as synonyms for query expansion.



### 6. Word2vec

Google’s *word2vec* uses a Neural Network to predict the neighbouring words (or the next word) in a document from the current (and previous) words.

- One-hot input vector is randomly chosen from a neighbourhood of the word.

-  The network tries to map a word $w$ onto each of the words that occur in a neighbourhood of $w$ in documents.
- The number of times that word $v$ appears as a target output depends on the number of times $v$ co-occurs with $w$.
- For each $w$, the NN will learn the distribution of words that co-occur with $w$.
- Output of the hidden layer is *word2vec* - a low-dimensional encoding of the probability distribution of words that co-occur with $w$.



| <img src="/Users/kevinxu95/ACS/IDA/Notes/IDA_Lecture 12.assets/Screenshot 2020-05-28 at 22.20.08.png" style="zoom:50%;" /> |
| :----------------------------------------------------------: |
|                **Fig 1.** Word2vec embedding                 |



### 7. Query-document scoring

Recall that for a document $d$ with terms $t$:
$$
w_{td} = f_{td}\cdot IDF(t)
$$

$$
Sim(q,d) = \frac {\sum_{t\in q\cup d}w_{td}\cdot w_{tq}}{\Vert d\Vert \Vert q\Vert}
$$

We expand query $q$ to include synonyms.

- Suppose $t'$ is a synonym of $t$ which occurs in $d$.
- We could define:

$$
w_{t',d} = \lambda_{tt'}\times f_{t'd} \times IDF(t)
$$

​		where $\lambda_{tt'}\geq0$ is a weighting depending on how *far* $t, t'$ are apart.

