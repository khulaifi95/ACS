# Neural Computation



## Lecture 5: Backpropagation



### 1. Feedforward neural networks

#### 1.1 Computation graph

We describe machine learning models using computation graphs where

- **Nodes** as variables (values, vectors, matrices)
- **Edges** as functional dependencies, i.e. edge from x to y indicates that y is a function of x.



#### 1.2 Structure and notations

| <img src="/Users/kevinxu95/ACS/NC/Notes/NC_Lecture 5.assets/Screenshot 2020-05-08 at 02.12.23.png" style="zoom:40%;" /> |
| :----------------------------------------------------------: |
| **Fig 5.1** A representation of multi-layer feedforward neural network |



A representation of feedforward neural network is denoted above, where

- $L$:    the number of **layers** in the network, where layer 1 is the *input layer*, layer $L$ is the *output layer*.
- $m$:    the **width** of network, can vary between layers.
- $w_{jk}^l$:  the **weight** of connection from $k$-th unit in layer $l-1$ to $j$-th unit in layer $l$.
- $b_j^l$:    the **bias** of $j$-th unit in layer $l$.
- $z_j^l$:    the weighted **input** to unit $j$ in layer $l$.
- $a_j^l=\phi(z_j^l)$:    the **activation** of unit $j$ in layer $l$, where $\phi$ is an activation function.



#### 1.3 Training of feedforward neural networks

The parameters of the netowrk are the weights $w_{jk}^l$ and biases $b_j^l$.

**Method**: to apply gradient descent to optimise a weight $w$ or bias $b$ in a network, we apply the chain rule
$$
\frac{\partial c}{\partial w} = \frac{\partial c}{\partial v}\cdot \frac{\partial v}{\partial w }
$$




| <img src="/Users/kevinxu95/ACS/NC/Notes/NC_Lecture 5.assets/Screenshot 2020-05-08 at 02.46.54.png" style="zoom:50%;" /> |
| :----------------------------------------------------------: |
|          **Fig 5.2** Activation on a single neuron           |



The weighted input $z_j^l$ is calculated and then used to activate the neuron
$$
z_j^l = \sum_{k=1}^mw_{jk}^la_k^{l-1}+b_j^l,\ a_j^l = \phi(z_j^l)
$$
By the chain rule, we have
$$
\frac{\partial c}{\partial w_{jk}^l}=\frac{\partial c}{\partial z_j^l}\cdot \frac{\partial z_j^l}{\partial w_{jk}^l} = \frac{\partial c}{\partial z_j^l}\cdot a_k^{l-1}
$$

$$
\frac{\partial c}{\partial b_j^l} = \frac{\partial c}{\partial z_j^l} \cdot \frac{\partial z_j^l}{\partial b_j^l} = \frac{\partial c}{\partial z_j^l} \cdot 1
$$

The partial derivatives can be calculated if we know the **local gradient**
$$
\delta_j^l = \frac{\partial c}{\partial z_j^l}
$$



#### 1.4 Local gradient for output layer

Given activation $a_j^L = \phi(z_j^L)$, the local gradient for the output layer is:
$$
\delta_j^L = \frac{\partial c}{\partial z_j^L} \\ = \frac{\partial c}{\partial a_j^L}\cdot \frac{\partial a_j^L}{\partial z_j^L} \\ = \frac{\partial c}{\partial a_j^L}\cdot \phi'(z_j^L)
$$
where the partial derivative $\frac{\partial c}{\partial a_j^L}$ depends on the **cost function**.

In a regression problem of $m$ dimensions, one could define
$$
c(a_1^L, ..., a_m^L) = \frac12 \sum_{k=1}^m(y_k - a_k^L)^2
$$
where the partial derivative is
$$
\frac{\partial c}{\partial a_j^L}=a_j^L - y_j
$$



#### 1.5 Local gradient for hidden layers

By the definition of local gradient and chain rule,  we can get 
$$
\begin{align}\delta_j^l &= \frac{\partial c}{\partial z_j^l} \\ &= \frac{\partial c}{\partial a_j^l} \cdot \frac{\partial a_j^l}{\partial z_j^l} \\ &= \Big(\sum_k \frac{\partial c}{\partial z_k^{l+1}}\cdot \frac{\partial z_k^{l+1}}{\partial a_j^l}\Big)\cdot \phi'(z_j^l) \\ &= \phi'(z_j^l)\sum_k\delta_k^{l+1}\cdot w_{kj}^{l+1} \end{align}
$$
which depends on local gradients of the next layer.



#### 1.6 Summary

For all weights and biases,
$$
\frac{\partial c}{\partial w_{jk}^l} = \delta_j^l\cdot a_k^{l-1}
$$

$$
\frac{\partial c}{\partial b_j^l} = \delta_j^l
$$

where the local gradient $\delta_j^l$ is defined as
$$
\delta_j^l = \cases {\phi'(z_j^L)\cdot \frac{\partial c}{\partial a_j^L} & output layer\\ \phi'(z_j^l)\cdot \sum\limits_k\delta_k^{l+1}\cdot w_{kj}^{l+1}& hidden layer}
$$



### 2. Backpropagation

#### 2.1 Matrix description

For two matrices $A\in \mathbb R^m \times \mathbb R^n$ and $B\in \mathbb R^n \times \mathbb R^l$:
$$
\text{transpose}:(A^T)_{ij} = A_{ji}
$$

$$
\text{multiplication}:(AB)_{ij} = \sum_kA_{ik}B_{kj}
$$

For two vectors $u,v \in \mathbb R^m$:
$$
\text{dot product}:u\cdot v = \sum_{i=1}^m u_iv_i
$$

$$
\text{hadamard product}:u \odot v = (u_1v_1, ..., u_mv_m)
$$

$$
\text{outer product}:(uv^T)_{ij} = u_iv_j
$$



#### 2.2 Weighted inputs and activations

The weighted inputs can be represented for all nodes
$$
\begin{align}z_l &= (z_1^l,...,z_m^l) \\ &= (\sum_{k=1}^mw_{1k}^la_k^{l-1}+b_1^l,...,\sum_{k=1}^mw_{mk}^la_k^{l-1}+b_m^l) \\ &= \mathbf w^l\mathbf a^{l-1} + \mathbf b \end{align}
$$
while the activations for all nodes in a layer are
$$
\begin{align} \mathbf a^l &= (a_1^l,...,a_m^l) \\ &= (\phi(z_1^l), ..., \phi(z_m^l)) \\ &= \boldsymbol \phi(z^l) \end{align}
$$



#### 2.3 Local gradients

Thus the local gradients in a layer can be represented as

- Output layer:

$$
\begin{align}\boldsymbol \delta^L &= (\delta_1^L,...,\delta_m^L) \\ &= (\frac {\partial c}{\partial a_1^L}\cdot \phi'(z_1^L), ..., \frac {\partial c}{\partial a_m^L}\cdot \phi'(z_m^L)) \\ &= \nabla_{a^L}\mathbf C\odot \boldsymbol\phi'(\mathbf z^L) \end{align}
$$

- Hidden layer:

$$
\begin{align}\boldsymbol\delta^L &= (\delta_1^L,...,\delta_m^L) \\&= \Big(\phi'(z_1^l)\cdot \sum_k\delta_k^{l+1}\cdot w_{k1}^{l+1}, ..., \phi'(z_m^l)\cdot \sum_k\delta_k^{l+1}\cdot w_{km}^{l+1}\Big) \\&=\boldsymbol\phi'(z^l)\odot (\sum_k(w^{l+1})^T_{1k}\delta_k^{l+1}, ..., \sum_k(w^{l+1})^T_{mk}\delta_k^{l+1}) \\&= \boldsymbol\phi'(z^l) \odot (\mathbf w^{l+1})^T\boldsymbol\delta^{l+1} \end{align}
$$



#### 2.4 Backpropagation **algorithm**

**Input**: A training example$ (x,y) \in \mathbb R^m \times \mathbb R^{m'}.$

1. Set the activation in the input layer

$$
a^1 = x
$$

2. For each $l=2$ to $L$, feed forward

$$
z^l = w^la^{l-1}+b^l\\ a^l = \phi(z^l) \ \ \ \ \ \ \ \ \ \ \
$$

3. Compute local gradient for output layer

$$
\boldsymbol\delta^L = \nabla_{a^L}\mathbf C\odot \boldsymbol\phi'(\mathbf z^L)
$$

4. **Backpropagate** the local gradients for hidden layers $l=L-1$ to $2$

$$
\boldsymbol\delta^l = \boldsymbol\phi'(z^l) \odot (\mathbf w^{l+1})^T\boldsymbol\delta^{l+1}
$$

5. Return the partial derivatives:

$$
\frac{\partial c}{\partial w_{jk}^l} = a_k^{l-1}\delta_j^l \\ \frac{\partial c}{\partial b_j^l} = \delta_j^l \ \ \ \
$$



### 3. Training feedforward networks

#### 3.1 Gradient descent process

Assume $n$ training examples $(x^i, y^i),...,(x^n,y^n)$, with a cost function
$$
C = \frac 1n\sum_{i=1}^nC^i
$$
where $C^i$ is the cost on the i-th example. With MSE, we can define
$$
C^i = \frac12(y^i-a^L)^2
$$
where $a^L$ is the output of the network when $a^1=x^i$.

Backpropagation gives the *average* graident of the overall cost function
$$
\frac{\partial c}{\partial w^l} = \frac1n \sum_{i=1}^n\frac{\partial c^i}{\partial w^l} \\ \frac{\partial c}{\partial b^l} = \frac1n\sum_{i=1}^n\frac{\partial c^i}{\partial b^l}
$$
Now we can use gradient descent to optimise weights and biases.



#### 3.2 Mini-batch gradient descent

However, computing the gradients is expensive when the number of training examples $n$ is large.

We can instead **approximate** the gradients
$$
\frac{\partial c}{\partial w^l} \approx \frac 1b \sum_{i=1}^b \frac{\partial c^i}{\partial w^l}\\ \frac{\partial c}{\partial b^l} \approx \frac 1b \sum_{i=1}^b\frac{\partial c^i}{\partial b^l}
$$
using a random *mini-batch* of $b\leq n$ training examples.

- $1<b<n$ : mini-batch gradient descent
- $b=1$: stochastic gradient descent
- $b=n$: batch gradient descent

Common to use mini-batch with size $b\in[20,100]$.

