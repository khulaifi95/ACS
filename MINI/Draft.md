# I. Introduction

13 Apr

15.00 95

19.30 150







1. data analysis
2. clustering
3. k-means
4. same-size/ scenario
5. problem



#### ML Application

Recent improvements on data analysis methods have brought machine learning to applications in a wide range of industries. Generally, we categorise machine learning into three areas, including supervised learning, unsupervised learning and reinforcement learning. Among them, supervised learning and reinforcement learning approaches rely on labels and environmental data as prior knowledge to generate a robust model of estimation. However, in practical applications, we are often provided with insufficient information or unlabelled data, which require human efforts to identify or verify. Unsupervised approaches can learn intrinsic structures of the unlabelled data set by only inspecting the relationship of the properties of each data. In a real world application, a peek into the data set like this is enough to make better decisions and predictions than human.



#### Clustering

Clustering is a widely used unsupervised learning technique in the application of exploratory data mining. The goal of clustering is to find a partition of the original data, where data within the same cluster are similar to each other but all different from those in other clusters. The most notable approaches include K-means clustering, agglomerate clustering and expectation-maximisation algorithm, etc. Being implemented in many algorithms, the clustering task itself falls into a domain of multi-objective optimisation, which iterates over the goal of finding similar data points in the same cluster and  differentiating between different clusters.



#### K-Means

K-Means clustering is one of the most popular clustering approaches in application for its simple heuristic and wide effectiveness. It specially minimises a criteria of the within-cluster sum-of-squares distance by iteratively assign centroids to clusters and modify them according to the mean value of points in the cluster. The number of clusters need to be predefined before iteration. Another drawback of K-Means clustering is that a random initiation of cluster centroids at the start could lead to completely different partition of data, which then requires multiple runs of the algorithm to find the preferred solution. Being this case, K-Means clustering is not ensured to find the global optimum of the task.



#### Problem

The application problem to be discussed in this project is to find the most effective allocation of orders onto a given number of production lines in a logistic factory, which deals with over 30k orders per day. More specifically , the orders are consisted of about 170 categories of products. The numbers of products in each order range from 1 to 30 with an average of 3.5 per order.  Every order is assembled and packaged on 4 production lines . To facilitate the packaging process and increase the daily throughput in the factory, a clustering approach is required to efficiently partition the orders. Due to the fact that each product line has limited access to the stock, it is easy to see that distributing orders with similar composition onto the same line can reduce the waste of time in the process of product collection and transportation. Based on the assumption of equivalent processing time of each order regardless of its size, we can deduce that balancing the number of orders on each production line is the most efficient way of order allocation. The analysis of this problem suggests a clustering method which are capable of returning clusters with same sizes. The sections below will follow this rationale to discuss the solution of the problem.



#### Propose

Given the 



This report proposes a variant of K-Means clustering algorithm by adding a size constraint to the 



The major contributions of this paper can be summarised as follows

The rest of this paper is organised as follows. 



The kmeans tend to return similar sized clusters, but not when the distribution is not uniform.



# II. Data

1. Description
2. Use of toy data
3. Properties
4. Issues
5. Solution







# IV. Method

1. K-means
2. Heuristics
3. Same-size K-means 
4. Sparse K-means
5. Balanced K-means



K-means 











# V. Experiment

1. Metrics
2. Plots







# VI. Discussion

1. Comparison
2. Drawbacks
3. Improvements