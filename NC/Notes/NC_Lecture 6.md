# Neural Computation



## Lecture 6: Softmax



### 1. Probabilistic model for classification

The per-example cost function obtained in the last lecture using the maximum likelihood method
$$
C^i=\sum_{j=1}^m \frac12(y^i_j-a_j^i)^2
$$
made the assumption that the examples belong to a Gaussian distribution. This is acceptable for regression problems.

However, for **classification** problems with $m$ **discrete** class labels $1,...,m$, better have one output unit $p_j$ per class $j$, where $p_j$ is interpreted as the probability of class $j$. These units should therefor satisfy
$$
\forall j \in \{1,...,m\}, \ \ P_j \geq 0, \ \ \sum_{j=1}^m P_j =1
$$



### 2. Softmax

#### 2.1 Softmax layer

We replace the last layer by a *softmax* layer:
$$
P_j := \frac {e^{z_j^L}}{Q}, \ \text{where} \ Q := \sum_{k=1}^me^{z_k^L}
$$


| <img src="/Users/kevinxu95/ACS/NC/Notes/NC_Lecture 6.assets/Screenshot 2020-05-11 at 03.45.10.png" style="zoom:33%;" /> |
| :----------------------------------------------------------: |
|                  **Fig 6.1** Softmax layer                   |



Since $P_k\geq 0$ and $\sum_{k=1}^mP_k=1$, we can interpret the output of the network as a probabilistic model
$$
P_y= P_{wb}(y|x)
$$
i.e. the probability of class $y$ given input $x$.

The likelihood of weight and bias parameters $w, b$ is 
$$
\mathcal L(w,b|(x^1, y^1),...,(x^n,y^n)) = \prod_{i=1}^nP_{wb}(y^i|x^i) = \prod_{i=1}^nP_{y^i}
$$



#### 2.2 Cost function

We can then define a cost function using maximum likelihood principle
$$
C=-\log\mathcal L(w,b|((x^1,y^1), ...,(x^n,y^n)) = \frac1n\sum_{i=1}^nC^i
$$

$$
\begin{align} \text{where} \ C^i &= -logP_{wb}(y^i|x^i) \\ &= -\log P_{y^i} \\ &=\log Q-z^L_{y^i} \end{align}
$$

To apply gradient descent to minimise the cost function, we need to compute the gradients of $w, b$ by computing the **local gradient** for the softmax layer using backpropagation.



#### 2.3 Local gradient

The local gradient in the output layer is given as
$$
\delta_j^L =\frac{\partial C^i}{\partial z_j^L} = P_j - \delta_{y^ij}
$$

where $\delta_{ab}$ represents the Kronecker delta function ($\delta_{ij} = [i==j]$)
$$
\delta_{ab} = \cases {1 & a=b\\ 0 & otherwise}
$$
**Proof**:
$$
\begin{align}\frac {\partial C^i}{\partial z_j^L} &= -\frac {\partial \log P(y^i|x^i)}{\partial z_j} \\ &= - \frac {\partial \log P_{y^i}}{\partial z_j}, \  \ \text {substitute}\  P_{y^i}=\frac {e^{a_{y^i}}}{Q} \\ &= - \frac {\partial }{\partial z_j}(z_{y^i}-\log Q) \\&=-(\delta_{y^{i}j} - \frac{\partial \log Q}{\partial z_j}) \\ &= -(\delta_{y^{i}j} - \frac{\partial \log Q}{\partial Q}\cdot\frac{\partial Q}{\partial z_j}) \\&= -(\delta_{y^{i}j}-\frac 1Q e^{z_j}) \\&= P_j-\delta_{y^ij}  \end{align}
$$



### 3. Numerical issues

When computing the numerator in $\frac{e^{z_j^l}}{Q}$, we can easily cause **overflow** in most programmes.

Note that for any constant *r*:
$$
P_j = \frac{e^{z_j^l}}{\sum\limits_ke^{z_k^l}}=\frac{e^r\cdot e^{z_j^l}}{e^r\cdot \sum\limits_ke^{z_k^l}}=\frac{e^{z_j^l+r}}{\sum\limits_ke^{z_k^l+r}}
$$
To avoid too large exponents, it is common to implement the softmax function as the rightmost expression above with the constant
$$
r :=-\max_kz_k^L
$$
