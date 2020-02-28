# Discrete PCA



- We have a total count of $L$ words to sample.
- We partition these $L$ words into $K$ components: $c_1, c_2, ..., c_K$ where $\sum_{k=1, ..., K} c_k  = L$.
  - This is done by a hidden proportion vector $\mathbf m =(m_1, m_2, ..., m_K)$. 
- In each partition, we sample words according to the multinomial for the component. This then yield a bag of word counts for the $k$-th partition, $\mathbf w_k = (w_{k,1}, w_{k,2}, ..., w_{k,J})$, where $J$ is the dictionary size, the size of the basic multinomials on words.
- 