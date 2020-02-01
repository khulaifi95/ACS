# Intelligent Data Analysis

------

## Lecture 1 : Introduction



### 1. Course Structure 2020

- Text Retrieval (1)
- Dimension Reduction
  
  - PCA
- Visualization of High-dimensional Data
  
- PCA, Topographic maps, t-SNE
  
- Clustering and Vector Quantization
- Text Retrieval (2)
  
- Synonym relationships, *Latent Semantic Analysis*, Page Rank
  
- Classification

  

### 2. Assessment

- 1.5 hour exam: 3 questions
- Assignment: PCA report for extended module
- Weekly exercise sheets
- After-lecture homework



### 3. Office Hours

 - Tuesday 2.30 - 3.30 pm

 - Thursday 2.30 - 3.30 pm (&larr; Week 5)

 - Friday 2.30 -3.30 pm (Week 6 &rarr;)

   ​	@ Gisbert Kapp building, GK-N429



### 4. Motivation

- Moore's Law

- Implications for data storage

  - Potentially record and store online everything

- Availability of **huge corpora of data**

  

### 5. Accessing Data - "Aboutness"

- How to find the relevant items?
- Need to know:
  - What each item in a **corpus** is about
  - *Relationships* between different corpus items
  - *Relationships* between '**queries**' and corpus items

- Need to **automatically** determine what a text is **about**
- The problem of "aboutness": **semantics**
  - Example: I saw the man / on the hill / with the telescope.



### 6. Text Understanding

- Words have different **meanings** and **grammatical** roles
- A word sequence may have **multiple interpretations**
- An **ungrammatical** sentence may be in the grammar (over-generation)



### 7. Analysis

- Different grammatical **parses** of same word sequence

  - *the man on the hill* vs *hill with the telescope*

- Identical parses but different **interpretations** of words

  - *saw the man with the telescope*

- Move towards **Machine Learning**

  

[^Parsing]:  Parsing is the process of analysing a string of symbols, either in natural language, computer languages or data structures, conforming to the rules of a formal grammar.



### 8. Data Mining

- Analysis of large data corpora: biomedical, acoustic, video, text, 

  … to discover **structure**, **patterns** and **relationships**

- Corpora too large for human inspection

- Patterns and structure may be hidden

<img src="IDA_Lecture01.assets/image-20200130121819369.png" alt="image-20200130121819369"  />

​													*Fig 1. Single, spherical cluster centred at origin*



### 9. Information Retrieval (IR)

- Underlying principles of Search Engine
- *[Belew] <Finding out About>*
- Retrieving information from
  - Text data
  - Spoken data
  - Bio-informatics

- Focus on **text retrieval**



### 10. IR vs Database Retrieval

- IR is not 'database retrieval'.
- Databases are characterised by:
  - Strong **prior assumptions** of data
    - Format
    - Salient properties
    - Logical relations
    - Likely user queries
  - **Formal**, restrictive query syntax
  - Dedicated maintenance
  - **Specific replies** to specific queries

- IR
  - No prior assumptions
  - Less specific 'natural language' queries
  - Source information up-to-date
  - Less focussed replies