# PCA Report

---



### I. Introduction (15%)

- [x] Data description
- [x] Potential classes
- [x] Hypotheses about expected information
- [ ] Explain PCA and other methods



### II. Pre-processing (10%)

- [ ] Separate basic info & attributes value
- [ ] Fill in NA values

- [ ] Standardisation: z-stat, boxplot

### III. Labelling (15%)

- [ ] Use different labelling schemes

  - ATK/ MID/ DEF vs. Attributes
  - Age vs. Attributes
  - Value vs. Attributes

  - Younger alternatives: clustering?

### IV. Projection (15%)

- In the case of visualisation, compare projections onto the principal
  components from PCA with straightforward projections onto the existing
  coordinates/dimensions.

- Inspect correlations for a better projection



### V. PCA (25%)

- Eigenvector and eigenvalue analysis

- Relationship between PC and original data dimensions



### VI. Other Analysis Methods (20%)

- LDA/ LSA
- K-means



### VII. Own Data Set (10%)



## Plots:

1. DataFrame
2. Binned histogram
3. Pool 2D projection
4. Reasonable projection on existing axis
5. PCA 2D projection
6. Table of Eigenvectors
7. Eigenvalues (separate/ cumulative vs. 0.9, 0.95)
8. 



### I. Introduction (15%)

#### Data Description

I have chosen to use the FIFA 19 complete player dataset which is an scraped data set from an [online FIFA database](https://sofifa.com/). It contains the basic information and rated attributes of all available football players in FIFA 19, which is a simulation game of football-related events in the top leagues worldwide. The collection and quantisation of the artificial data are strictly subject to the performance of individuals and advanced aspects of sports statistics in the real world. Due to the difficulty of collecting real data, the analysis of this data set can be extrapolated to that of real-world football players.

#### Hypothesis

This report is intended to investigate the individual attribute ratings of football players and how they contribute to differentiate categories of players. The target classes include age, position and potential of players. Specifically, the following hypothesis will be tested, whose results will be visualised:

1. Players with different roles in the game have significant differences in attributes.
2. Players who show great potential have something special with their current attributes.
3. Alternatives for a certain player can be found by investigating this data set.
4. *The 'Overall Rating' of players are calculated by different weights depending on their position in the pitch.

#### PCA



#### K-Means



 The data are indexed by a key 'Player ID' and are mostly consisted of discrete and categorical values preset by the developer.





ten [outfield](https://en.wikipedia.org/wiki/Outfield) players who fill various defensive, midfield, and attacking positions depending on the [formation](https://en.wikipedia.org/wiki/Formation_(association_football)) deployed. These positions describe both the player's main role and their area of operation on the pitch.



ADD other 