# Mini-Project Plan

This project is aimed to apply a novel clustering analysis technique to solve a real-world recycle-and-distribute logistic problem. By definition, the clustering in this scenario is to first analyse 

1. Problem 

   The problem is stated as the following. A logistic factory consists of a warehouse and 4 production lines. The daily tasks for the whole factory is to recycle packed cigarettes. All the retailers in this region send back their backlogs to the factory with different brands of cigarettes. Then the factory stores the products in its warehouse waiting for repackaging. According to everyday orders of different products from the local distributors, the factory creates its daily production job list. Packs in each order are repackaged into cartons on the production line. At the final stage, all the orders are transported to shops in different towns. The problem here is how to assign the orders to each production line so that the overall throughput of this factory is optimised.

   Since the variance in the composition of orders and the immutable supply path from the warehouse to each line, it is conceived that a better way of order sorting is to assign similar orders (size and composition) to each production line. However, due to the workload on the production lines, a pre-set quota of production on each line must be met, which means every line can only work on the percentage of packs distributed to them.

   

2. Hypothesis

   After analysing the problem, I propose a cluster analysis on the orders to determine the assignment. To meet the specific requirement in the scenario, it is also constrained by the pre-set proportion of elements in each cluster. A heuristic algorithm is required to solve this problem because the existing clustering methods do not return in designated quantities.

3. Dataset

   The dataset is acquired from the order log in the database of a logistic factory in China. Each row includes order ID, quantity,  category of cigarette and distributed destination. Logs within the same order is rearranged into a multi-column vector with quantities of categories as entries. 

4. Design

   See mind-map.

5. Experiment

   See proposal.

6. Implementation

   Examples in Python, R, MATLAB.

7. Evaluation

   See proposal.



k-means doesn't work in high-dimension.

PCA for binary data - dimension reduction

or

customised similarity? size and proportion - mutual distances between destinations