# Mini Project

---

## Latent Dirichlet Allocation



Latent Dirichlet Allocation is a generative probabilistic model for collections of **discrete** dataset such as text corpora. It is also a topic model that is used for discovering **abstract topics** from a collection of documents.



The graphical model of LDA is a three-level generative model:



| <img src="LDA.assets/lda_model_graph.png" alt="lda_model_graph" style="zoom: 67%;" /> |
| :----------------------------------------------------------: |
|   *Fig 1. A three-level generative graphical model of LDA*   |

- The corpus is a collection of $D$ documents.
- A document is a sequence of $N$ words.
- There are $K$ topics in the corpus.
- The boxes represent repeated sampling.



In the graphical model, each node is a random variable and has a role in the generative process. A **shaded** node indicates an **observed** variable and an **unshaded** node indicates a **hidden (latent)** variable. In this case, words in the corpus are the only data that we observe. The latent variables determine the random mixture of topics in the corpus and the distribution of words in the documents. The goal of LDA is to use the observed words to infer the hidden topic structure.