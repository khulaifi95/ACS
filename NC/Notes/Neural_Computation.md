# Neural Computation



## Lecture 1: Introduction

### 1.1 Definition of machine learning

Performance $P$ of algorithm at task $T$ improves with experience $E$.

#### 1.1.1 Machine learning task T

- regression

- classification

- transcription

#### 1.1.2 Performance meansure P

- accuracy
- precision
- ROC

#### 1.1.3 Experience E

- supervised learning
- unsupervised learning
- reinforcement learning





## Lecture 2. Linear Regression

### 2.1 Linear regression models

#### 2.1.1Experience E

The dataset consists of $n$ data points
$$
(x^1,y^1),...,(x^n,y^n) \in \mathbb R^D \times \mathbb R
$$
where $x^i \in \mathbb R^d$ is the **input** for the i-th data point as a feature vector with $d$ elements, $y^i \in \mathbb R$ is the **output** for the i-th data point.

#### 2.1.2 Task T

The task of linear regression is to find a *model*, i.e. a function
$$
f: \mathbb R^d \rightarrow \mathbb R
$$
such that on future observations, the predicted output $f(x)$ is *close to* the true output $Y$, where the observations come from an unknown distribution
$$
(x,y) \sim \mathcal D
$$
A linear regression model has the form
$$
f(x) = \sum\limits_{i=1}^dw_ix_i +b
$$
where

- $x\in\mathbb R^d$ is the input vector  - **features**
- $w\in\mathbb R^d$ is a **weight** vector - **parameters**
- $b\in\mathbb R$ is a **bias** parameter
- $f(x)\in \mathbb R$ is the predicted **output**

#### 2.1.3 Performance measure P

We want a function $J(w)$ which quantifies the error in the predictions for a given parameter $w$.

The *error* of prediction on the i-th data point can be defined as
$$
e^i = y^i - f(x^i)
$$
The following **empirical loss function** $J$ takes into account of the errors for all $n$ data points
$$
J(w) = \frac 1n \sum\limits_{i=1}^n\frac 1 2(y^i - wx^i)^2
$$
which is to find the parameter $w$ which **minimises** the loss $J(w)$.

By squaring the error value, we can:

- ignore the sign of the errors
- Penalise large errors more



### 2.2 Optimisation

#### 2.2.1 Unconstrained minimisation

Given a loss function $f$, an element $x\in\mathbb R^d$ is 

- a global minimum of $f$ if

$$
\forall m \in \mathbb R^d, \ \ f(x) \leq f(m)
$$

- a local minimum of $f$ if

$$
\exist \ \epsilon>0 \ \ \forall \ m \in \mathbb R^d, \forall i\in [1,d]\ |x_i-m_i|<\epsilon,\ \ f(x)\leq f(m).
$$



| <img src="Neural_Computation.assets/Screenshot 2020-05-07 at 16.54.02.png" style="zoom:50%;" /> |
| :----------------------------------------------------------: |
|        **Fig 2.1**: Local minimum and global minimum         |



#### 2.2.2 Theorem

For any continuous function $f:\mathbb R \rightarrow \mathbb R$, if $x$ is a local optimum, then 
$$
f'(x) = 0
$$


### 2.3 Derivatives

#### 2.3.1 Definition

The first derivative of a function $f:\mathbb R \rightarrow \mathbb R$ is 
$$
f'(x) = \lim_{\Delta x\rightarrow0}\frac {f(x+\Delta x)-f(x)} {\Delta x}
$$

#### 2.3.2 Leibniz's notation

If $y=f(x)$, then
$$
\frac {dy}{dx} = f'(x)
$$

#### 2.3.3 Differentiation rules

- $(cf(x))'=cf'(x)$
- $(x^k)' = kx^{k-1}$
- $(f(x) + g(x))' = f'(x) + g'(x)$
- *Chain rule* - $(f(g(x)))' = f'(g(x))\cdot g'(x)$

#### 2.3.4 Chain rule in Leibniz's notation

Assume that $z=h(x)$, $y=g(z)=g(h(x))$, then
$$
\frac {dy}{dx} = \frac {dy}{dz}.\frac{dz}{dx}
$$

#### 2.3.5 Example: chain rule

- What is the derivative of $f(w) = \frac 1 2 (y-wx)^2$?

- Define the functions in the form of $f(w) = g(h(w))$

$$
\cases {g(e) = \frac12e^2  \\ h(w) = y - wx }
$$

​	  In Leibniz's notation, we have:
$$
\frac {dz}{de} = e, \ \ \ \frac {de}{dw}=-x
$$
 	  The chain rule gives:
$$
f'(w) = \frac {dz}{dw} = \frac {dz}{de} .\frac{de}{dw}=-x.e=x(wx-y)
$$


### 2.4 Approach I: Ordinary Least Square

Optimise $J$ by directly solving $J'(w) = 0$:
$$ {align}
J(w) = \frac {1}{2n} \sum\limits_{i=1}^n(y^i - wx^i)^2 \\
\text{Let}\ J'(w) = \frac 1n \sum_{i=1}(wx^i-y^i)x^i =0\\
w\sum\limits_{i=1}^n(wx^i)^2= \sum\limits_{i=1}^nx^iy^i
$$

$$
w = \frac {\sum\limits_{i=1}^nx^iy^i}{\sum\limits_{i=1}^n(x^i)^2} \\
$$

- Only one solution to $J'(w) = 0$, hence **globally** optimal.



### 2.5 Approach II: Gradient Descent

However, it is often difficult or impossible to solve $J'(w) =0$ for non-linear models with many parameters such so in neural networks.

**Idea**: Start with an initial guess $w$, while $J'(w)\neq0$, move $w$ ***slightly*** in the ***right direction***.

#### 2.5.1 Attempt 1 (Failed)

``` pseudocode
w <- initial weight
repeat
		if J`(w) < 0
    		w <- w + e
  	else if J`(w) > 0
  			w <- w - e
```

- **Problems**:
  - $w$ could oscillate in the interval $[w_{opt}- \epsilon, w_{opt}+\epsilon]$.
  - $w$ fails to **converge**.

#### 2.5.2 Attempt 2 (Gradient)

```pseudocode
w <- initial weight
repeat
		w <- w - eJ`(w)		
		# if J`(w)< 0, w increase
		# if J`(w)> 0, w decrease
```

- $\epsilon$ : **learning rate** - hyperparameter 





## Lecture 3: Maximum Likelihood

### 3.1 Probabilistic models

Different from a deterministic model, a probabilistic model can account for **variance** in the data. For example:
$$
F(x) = wx+N
$$
where $N\sim \mathcal N(0,\sigma^2)$ is a *noise term* which is normally distributed.

- Here, the function $F(x)$ is a **random variable** which can be described by a *conditional density* $P(y|x,w)$.



### 3.2 [Concepts](/Users/kevinxu95/Selfstudy/Annotation/2 Probability.md)

#### 3.2.1 Random variables

A random variable takes a value depending on a random phenomenon.

#### 3.2.2 Density function

The density function of a continuous random variable $x$ is a function $p: \mathbb R \rightarrow \mathbb R$, such that:
$$
\int_a^bp(x)dx=Pr(a\leq x\leq b)
$$

#### 3.2.3 Normal distribution

The *pdf* is defined by the mean value and standard deviation
$$
\mathcal N(x|\mu, \sigma^2) = \frac1{\sqrt{2\pi\sigma^2}} \exp(-\frac{(x-u^2)}{2\sigma^2})
$$

#### 3.2.4 Expectation

The expected value of the distribution of a random variable is
$$
\mathbb E_{x\sim p}x \triangleq \int_{-\infty}^\infty xp(x)dx
$$

- Example:

$$
\mathbb E_{x\sim\mathcal N(\mu,\sigma^2)}x = \mu
$$

#### 3.2.5 Joint distributions and independence

- The joint density function of $n$ random variables $x^i, ...,x^n$ is a function $p: \mathbb R^n \rightarrow \mathbb R$ such that:

$$
\int_Dp(x^1,...,x^n)\ dx^1,...,dx^n=Pr((x^1, ...,x^n)\in D)
$$

​		for any n-dimensional domain D.

- If $x^1,...x^n$ are n **independent** random variables with density functions $p^1,..., p^n$ and joint density $p$, then 

$$
p(x^1,...,x^n) = \prod\limits_{i=1}^nP^i(x^i)
$$

#### 3.2.6 Empirical distribution

Given $n$ independent samples from an unknown distribution $\mathcal D$, we can construct an *approximation* of $\mathcal D$ by uniformly sampling from the set $\{x^1,...,x^n\}$. The *pdf* of the empirical distribution of $\mathcal D$ is defined as
$$
p_n(x) \triangleq \frac 1n \sum\limits_{i=1}^n\delta(x^i-x)
$$
where $\delta$ is the **Dirac delta**, i.e. $\delta(x)=0$ for $x\neq0$ or 1 for $x=0$. Note:
$$
\mathbb E_{x\sim p_n}f(x) = \frac 1n\sum\limits_{i=1}^nf(x^i)
$$


#### 3.3 Maximum likelihood

#### 3.3.1 Learning task

Instead of deterministically predicting an output, we train a probabilistic model represented by a **conditional density function**
$$
p_{model}(y|x;\theta)
$$
where $\theta$ is the parameter of model.

- We need to choose from a family of probabilistic models to find the parameter $\theta$ which is appropriate for the data. 

#### 3.3.2 Likelihood function

The likelihood function of given independent training dataset $(x^1,y^1),...,(x^i,y^i)$ and a probabilistic model $p_{model}$ with parameter $\theta$ is defined as:
$$
\mathcal L(\theta; (x^1,y^1),...,(x^i,y^i)) \triangleq \prod_{i=1}^np_{model}(y^i|x^i;\theta)
$$
where $\mathcal L$ is the **likelihood** that the observation comes from the model.

#### 3.3.3 Maximum likelihood estimate

**Goal**: Given training data and a family of models indexed by parameter $\theta$, which of the models are most likely to have produced the data?
$$
\Theta_{MLE}\triangleq \arg\max_\theta \mathcal L(\theta;  (x^1,y^1),...,(x^i,y^i)) \\
= \arg\max_\theta\prod_{i=1}^np_{model}(y^i|x^i;\theta)
$$

#### 3.3.4 Log-likelihood

For numerical and analytical reasons, a convenient reformulation is
$$
\Theta_{MLE} = \arg\max_\theta\mathcal L(\theta)
\\= \arg\max_\theta \log\mathcal L(\theta) \ \ \ \ \ \ \ \ \ 
\\= \arg\max_\theta \log\prod_{i=1}^np_{model}(y^i|x^i;\theta)
\\= \arg\max_\theta \sum_{i=1}^n\log p_{model}(y^i|x^i;\theta)
\\= \arg\min_\theta \frac1n\sum-\log p_{model}(y^i|x^i;\theta)
\\= \arg\min_\theta \mathbb E_{(x,y)\sim \mathcal D^n}-\log p_{model} (y|x;\theta)
$$

#### 3.3.5 Learning via log-likelihood

Neural network models are often trained by **minimising the negative log-likelihood** of the model given the training data, the cost function
$$
J(\theta) = \mathbb E_{(x,y)\sim \mathcal D^n}-\log p_{model}(y|x;\theta)
$$
where $\mathcal D$ is the empirical distribution of data.

#### 3.3.6 Example: Linear regression

Predicting from the model $F(x)=wx+\mathcal N$, where $\sim \mathcal N(0,\sigma^2)$, hence:
$$
F(x) \sim \mathcal N(wx,\sigma^2)
$$
i.e., a model with conditional density function
$$
p_{model}(y|x;w) = \frac1{\sqrt{2\pi\sigma^2}}\exp(-\frac{(y-xw)^2}{2\sigma^2})
$$
The parameter $w$ which maximises the likelihood is given by
$$
w_{MLE} 
= \arg\min_w\sum_{i=1}^n-\log p_{model}(y^i|x^i;w)\ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ 
\\ = \arg\min_w\sum_{i=1}^n-\log(\frac1{\sqrt{2\pi\sigma^2}})+\frac{(y^i-x^iw)^2}{2\sigma^2}
\\ = \arg\min_w\frac1n\sum_{i=1}^n\frac12(y^i-x^iw)^2 \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \
$$

- Identical to the MSE-solution.





## Lecture 4: Gradient Descent

### 4.1 Functions of multiple variables

#### 4.1.1 Vectors

Vectors are *arrays* of numbers. We can consider a vector as a point in space, where each element $v_i$ giving the coordinate along the i-th axis.
$$
v=(v_1,...,v_m)\in \mathbb R^m
$$

#### 4.1.2 Norms

Norms assign *length* to vectors. The $L^p$-norm of a vector $v\in \mathbb R^m$ is
$$
\Vert v\Vert_p=(\sum|v_i|^p)^{1/p}
$$
Where $p\in \mathbb R$ and $p\geq 1$.

- $L^2$ is a special case known as Euclidean norm, denoted $\Vert v\Vert_2$.

#### 4.1.3 Operations

For all $a\in\mathbb R$, $u= (u_1,...,u_m)\in\mathbb R^m$, $v=(v_1, ..., v_m)\in \mathbb R^m$:

- scalar multiplication: $au = (au_1,...,au_m)$
- vector addition: $u+v = (u_1+v_1,...,u_m+v_m)$
- dot product: $u\cdot v=\sum_{i=1}^m u_iv_i$

#### 4.1.4 Theorem

Geometric interpretation of dot product states that if the angle between two vectors $u,v\in\mathbb R^m$ is $\theta$, then
$$
u\cdot v = \Vert u \Vert\cdot \Vert v\Vert \cdot \cos\theta
$$
The new length $\Vert u\Vert\cdot \cos\theta$ is exactly the projection of $u$ onto $v$.



| <img src="Neural_Computation.assets/Screenshot 2020-05-07 at 16.54.35.png" style="zoom:80%;" /> |
| :----------------------------------------------------------: |
|     **Fig 4.1** Geometric interpretation of dot product      |



### 4.2 Partial derivatives and the chain rule

#### 4.2.1 Partial derivative

The partial derivative of a function $f(x_1,...,x_m)$ in the direction of variable $x_i$ at the point $u=(u_1,...,u_m)$ is
$$
\frac{\part f}{\part x_i}(u_1,...,u_m) = \lim_{h\rightarrow0}\frac{f(u_1,...,u_i+ h, ..., u_m) - f(u_1,...,u_m)}{h}
$$
where intuitively all variables except $x_i$ are fixed as constraints.

#### 4.2.2 Geometric interpretation

As in **Fig 4.2**, for the function $f(x_1, x_2)$:

-  $\frac {\part f}{\part x_1}$ is the rate of change of $f$ along dimension $x_1$.

-  $\frac {\part f}{\part x_2}$ is the rate of change of $f$ along dimension $x_2$.



| <img src="Neural_Computation.assets/Screenshot 2020-05-07 at 17.11.02.png" style="zoom:50%;" /> |
| :----------------------------------------------------------: |
|        **Fig 4.2** Partial derivative in a 2-D space         |



### 4.3 Gradients

#### 4.3.1 Definition

The gradient of a function $f(x_1, ..., x_m)$ is 
$$
\nabla f \triangleq (\frac{\part f}{\part x_1},...,\frac{\part f}{\part x_m})
$$

- Example: $f(x_1, x_2)= 2x_1^2+x_2^2+3x_1x_2+4$

$$
\nabla f(x_1, x_2)=(\frac {\part f}{\part x_1}, \frac{\part f}{\part x_2}) 
=(4x_1+3x_2, 2x_2+3x_1)
$$

#### 4.3.2 Visualisation of the gradient

The gradient is a vector-valued function that maps vectors to vectors. i.e.
$$
\text {if} \ f: \mathbb R^m \rightarrow \mathbb R, \text{then} \ \nabla f: \mathbb R^m \rightarrow \mathbb R^m
$$


| <img src="Neural_Computation.assets/Screenshot 2020-05-07 at 17.22.18.png" style="zoom:50%;" /> |
| :----------------------------------------------------------: |
|      **Fig 4.3** Visualisation of gradient in 2-D space      |



#### 4.3.3 Special case of chain rule

- For one-dimensional functions, if $y=f(u) $ and $u = g(x)$, then

$$
\frac{dy}{dx}=\frac{dy}{du}\cdot \frac{du}{dx}
$$

- For higher-dimensional functions, if $y=f(u_1,...,u_m)$ and $u_i=g_i(x_1,...,x_m)$ for $ i \in \{1,...,m\}$, then

$$
\frac{\part y}{\part x_i}=\sum_{j=1}^m\frac{\part y}{\part u_j}\cdot \frac{\part u_j}{\part x_i}
$$

- Example: 

  $h(x_1, x_2) = (ax_1+bx_2)^2x_1x_2$. $h$ can be expressed by defining

$$
y = f(u_1, u_2) = u_1^2 u_2 \\
u_1= g_1(x_1, x_2) = ax_1+bx_2 \\
u_2 = g_2(x_1, x_2) = x_1x_2 \ \ \ \ \ \ \ \ \
$$

​		Applying the chain rule gives
$$
\frac {\part h}{\part x_1} = \frac {\part f}{\part x_1} = \frac {\part f}{\part u_1} \cdot \frac {\part u_1}{\part x_1} + \frac {\part f}{\part u_2} \cdot \frac {\part u_2}{\part x_1} \\ \ \ \ \ \ \ \ \ \ \ 
= 2u_1u_2\cdot a + u_1^2\cdot x_2 \\ \ \ \ \ \ \ \ = u_1(2au_2+u_1x_2)\\ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ = (ax_1+bx_2)(2ax_1x_2+(ax_1+bx_2)x_2) \\ \ \ \ = (ax_1+bx_2)x_2(3ax_1+bx_2)
$$
​		Similarly, the chain rule gives
$$
\frac {\part h}{\part x_2} = \frac {\part f}{\part x_2} = \frac {\part f}{\part u_1} \cdot \frac {\part u_1}{\part x_2} + \frac {\part f}{\part u_2} \cdot \frac {\part u_2}{\part x_2} \\ \ \ \ \ \ \ \ \ \ \ 
= 2u_1u_2\cdot b + u_1^2\cdot x_1 \\ \ \ \ \ \ \ \ = u_1(2bu_2+u_1x_1)\\ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ = (ax_1+bx_2)(2bx_1x_2+(ax_1+bx_2)x_1) \\ \ \ \ = (ax_1+bx_2)x_1(3bx_2+ax_1)
$$


### 4.4 Gradient descent

#### 4.4.1 Directional derivative

Given a function $f:\mathbb R^m \rightarrow \mathbb R$ and a vector $v=(v_1,...,v_m),\Vert v\Vert=1$, the directional derivative of $f$ in $x=(x_1,...,x_m)$ along the vector $v$ is
$$
\nabla_vf(x)\triangleq \lim_{\alpha\rightarrow0}\frac {f(x+\alpha v)-f(x)}{\alpha} \\
= \lim_{\alpha\rightarrow0}\frac{f(x_1+\alpha v_1,...,x_m+\alpha v_M)-f(x_1,...,x_m)}{\alpha}
$$

#### 4.4.2 Computing directional derivative 

**Theorem**: If we know the gradient $\nabla f$, then we can compute the derivative in any direction $v$ given by
$$
\nabla_vf(x)=\nabla f(x)\cdot v
$$
**Proof**:

- Define the function $h(\alpha) = f(u_1,...,u_m):\mathbb R \rightarrow \mathbb R$, where $u_i = x_i + \alpha v_i$, $\forall i\in\{1,...,m\}$

$$
\nabla_vf(x) =\lim_{\alpha\rightarrow0}\frac{f(x+\alpha v)-f(x)}{\alpha} \\ \ \ \ \ \ \ \ \ \ \  
= \lim_{\alpha\rightarrow0}\frac{h(0+\alpha)-h(0)}{\alpha} \\= h'(0) \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ 
$$

- Using the chain rule, we have

$$
h'(\alpha) = \frac{dh}{d\alpha}=\sum_{i=1}^m\frac{\part f}{\part u_i}\cdot \frac{\part u_i}{\part \alpha} = \sum_{i=1}^{m}\frac {\part f}{\part u_i}\cdot v_i
$$

- Note that for $\alpha=0$, we have

$$
u_i=x_i+0\cdot v_i \rightarrow x_i
$$

- Using (49) (50) (51) above, we get

$$
\nabla_vf(x)=h'(0)=\sum_{i=1}^{m}\frac {\part f}{\part u_i}\cdot v_i = \nabla f(x)\cdot v
$$

#### 4.4.3 Steepest ascent 

The vector $v$ along which $f$ has steepest ascent is 
$$
\arg\max_{v,\Vert v\Vert=1} \nabla_vf(x)
\\ = \arg\max_{v,\Vert v\Vert=1} \nabla f(x)\cdot v
\\ = \arg\max_{v,\Vert v\Vert=1}\Vert \nabla f(x)\Vert \Vert v\Vert \cdot \cos\theta
\\ = \arg\max_{v,\Vert v\Vert=1}\Vert \nabla f(x)\Vert \cdot \cos\theta \ \ \ \ \ \
$$
**Theorem**: 

The vector $v$ which gives the steepest ascent is the vector that has angle $\theta=0$ to $\nabla f$, i.e. the vector which points in **the same direction** as $\nabla f$.

#### 4.4.4 Method of gradient descent

```pseudocode
Input: cost function J, learning rate e > 0

x <- some initial point in m-dimensional space
while termination condition not met {
		x <- x - e . ∆J(x)
}
```



| <img src="Neural_Computation.assets/Screenshot 2020-05-07 at 19.06.05.png" style="zoom:50%;" /> |
| :----------------------------------------------------------: |
|        **Fig 4.4** Steepest ascent of gradient points        |





## Lecture 5: Backpropagation

### 5.1 Feedforward neural networks

#### 5.1.1 Computation graph

We describe machine learning models using computation graphs where

- **Nodes** as variables (values, vectors, matrices)
- **Edges** as functional dependencies, i.e. edge from x to y indicates that y is a function of x.

#### 5.1.2 Structure and notations



| <img src="Neural_Computation.assets/Screenshot 2020-05-08 at 02.12.23.png" alt="Screenshot 2020-05-08 at 02.12.23" style="zoom:33%;" /> |
| :----------------------------------------------------------: |
| **Fig 5.1** A representation of multi-layer feedforward neural network |



A representation of feedforward neural network is noted above, where

- $L$:    the number of **layers** in the network, where layer 1 is the *input layer*, layer $L$ is the *output layer*.
- $m$:    the **width** of network, can vary between layers.
- $w_{jk}^l$:  the **weight** of connection from $k$-th unit in layer $l-1$ to $j$-th unit in layer $l$.
- $b_j^l$:    the **bias** of $j$-th unit in layer $l$.
- $z_j^l$:    the weighted **input** to unit $j$ in layer $l$.
- $a_j^l=\phi(z_j^l)$:    the **activation** of unit $j$ in layer $l$, where $\phi$ is an activation function.



#### 5.1.3 Training of feedforward neural networks

The parameters of the netowrk are the weights $w_{jk}^l$ and biases $b_j^l$.

**Method**: to apply gradient descent to optimise a weight $w$ or bias $b$ in a network, we apply the chain rule
$$
\frac{\part c}{\part w} = \frac{\part c}{\part v}\cdot \frac{\part v}{\part w }
$$


| <img src="Neural_Computation.assets/Screenshot 2020-05-08 at 02.46.54.png" alt="Screenshot 2020-05-08 at 02.46.54" style="zoom:50%;" /> |
| :----------------------------------------------------------: |
|          **Fig 5.2** Activation on a single neuron           |



The weighted input $z_j^l$ is calculated and then used to activate the neuron
$$
z_j^l = \sum_{k=1}^mw_{jk}^la_k^{l-1}+b_j^l,\ a_j^l = \phi(z_j^l)
$$
By the chain rule, we have
$$
\frac{\part c}{\part w_{jk}^l}=\frac{\part c}{\part z_j^l}\cdot \frac{\part z_j^l}{\part w_{jk}^l} = \frac{\part c}{\part z_j^l}\cdot a_k^{l-1}
$$

$$
\frac{\part c}{\part b_j^l} = \frac{\part c}{\part z_j^l} \cdot \frac{\part z_j^l}{\part b_j^l} = \frac{\part c}{\part z_j^l} \cdot 1
$$

The partial derivatives can be calculated if we know the **local gradient**
$$
\delta_j^l = \frac{\part c}{\part z_j^l}
$$

#### 5.1.4 Local gradient for output layer

Given activation $a_j^L = \phi(z_j^L)$, the local gradient for the output layer is 
$$
\delta_j^L = \frac{\part c}{\part z_j^L}
\\ = \frac{\part c}{\part a_j^L}\cdot \frac{\part a_j^L}{\part z_j^L}
\\ = \frac{\part c}{\part a_j^L}\cdot \phi'(z_j^L)
$$
where the partial derivative $\frac{\part c}{\part a_j^L}$ depends on the **cost function**.

In a regression problem of $m$ dimensions, one could define
$$
c(a_1^L, ..., a_m^L) = \frac12 \sum_{k=1}^m(y_k - a_k^L)^2
$$
where the partial derivative is
$$
\frac{\part c}{\part a_j^L}=a_j^L - y_j
$$

#### 5.1.5 Local gradient for hidden layers

By the definition of local gradient and chain rule,  we can get 
$$
\delta_j^l = \frac{\part c}{\part z_j^l}
\\ = \frac{\part c}{\part a_j^l} \cdot \frac{\part a_j^l}{\part z_j^l}
\\ = \Big(\sum_k \frac{\part c}{\part z_k^{l+1}}\cdot \frac{\part z_k^{l+1}}{\part a_j^l}\Big)\cdot \phi'(z_j^l)
\\ = \phi'(z_j^l)\sum_k\delta_k^{l+1}\cdot w_{kj}^{l+1} \ \ \ \ \ \ \ \ \ \ \ \ \ \ 
$$
which depends on local gradients of the next layer.

#### 5.1.6 Summary

For all weights and biases,
$$
\frac{\part c}{\part w_{jk}^l} = \delta_j^l\cdot a_k^{l-1}
$$

$$
\frac{\part c}{\part b_j^l} = \delta_j^l
$$

where the local gradient $\delta_j^l$ is defined as
$$
\delta_j^l = \cases {\phi'(z_j^L)\cdot \frac{\part c}{\part a_j^L} & output layer\\ \phi'(z_j^l)\cdot \sum\limits_k\delta_k^{l+1}\cdot w_{kj}^{l+1}& hidden layer}
$$


### 5.2 Backpropagation

#### 5.2.1 Matrix description

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

#### 5.2.2 Weighted inputs and activations

The weighted inputs can be represented for all nodes
$$
z_l = (z_1^l,...,z_m^l) 
\\ = (\sum_{k=1}^mw_{1k}^la_k^{l-1}+b_1^l,...,\sum_{k=1}^mw_{mk}^la_k^{l-1}+b_m^l)
\\ = w^la^{l-1} + b \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \
$$
while the activations for all nodes in a layer are
$$
a^l = (a_1^l,...,a_m^l)\ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ 
\\ = (\phi(z_1^l), ..., \phi(z_m^l)) 
\\ = \phi(z^l) \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \ \
$$

#### 5.2.3 Local gradients

Thus the local gradients can be represented as

- Output layer:

$$
\delta^L = (\delta_1^L,...,\delta_m^L)
\\ = (\frac {\part c}{\part a_1^L}\cdot \phi'(z_1^L), ..., \frac {\part c}{\part a_m^L}\cdot \phi'(z_m^L))
\\ = \nabla_{a^L}C\odot \phi'(z^L)
$$

- Hidden layer:

$$
\delta^L = (\delta_1^L,...,\delta_m^L)
\\= \Big(\phi'(z_1^l)\cdot \sum_k\delta_k^{l+1}\cdot w_{k1}^{l+1}, ..., \phi'(z_m^l)\cdot \sum_k\delta_k^{l+1}\cdot w_{km}^{l+1}\Big)
\\=\phi'(z^l)\odot (\sum_k(w^{l+1})^T_{1k}\delta_k^{l+1}, ..., \sum_k(w^{l+1})^T_{mk}\delta_k^{l+1})
\\= \phi'(z^l) \odot (w^{l+1})^T\delta^{l+1}
$$



#### 5.2.4 **Backpropagation** **algorithm**

**Input**: A training example$ (x,y) \in \mathbb R^m \times \mathbb R^{m'}.$

1. Set the activation in the input layer

$$
a^1 = x
$$

2. For each $l=2$ to $L$, feed forward

$$
z^l = w^la^{l-1}+b^l
\\ a^l = \phi(z^l) \ \ \ \ \ \ \ \ \ \ \
$$

3. Compute local gradient for output layer

$$
\delta^L = \nabla_{a^L}C \odot \phi'(z^L)
$$

4. **Backpropagate** the local gradients for hidden layers $l=L-1$ to $2$

$$
\delta^l = ((w^{l+1})^T\delta^{l+1}) \odot \phi'(z^l)
$$

5. Return the partial derivatives:

$$
\frac{\part c}{\part w_{jk}^l} = a_k^{l-1}\delta_j^l
\\ \frac{\part c}{\part b_j^l} = \delta_j^l \ \ \ \
$$



### 5.3 Training feedforward networks

#### 5.3.1 Gradient descent process

Assume $n$ training examples $(x^i, y^i),...,(x^n,y^n)$, with a cost function
$$
C = \frac 1n\sum_{i=1}^nC^i
$$
where $C^i$ is the cost on the i-th example. With MSE, we can define
$$
C^i = \frac12(y^i-a^L)
$$
where $a^L$ is the output of the network when $a^1=x^i$.

Backpropagation gives the *average* graident of the overall cost function
$$
\frac{\part c}{\part w^l} = \frac1n \sum_{i=1}^n\frac{\part c^i}{\part w^l}
\\ \frac{\part c}{\part b^l} = \frac1n\sum_{i=1}^n\frac{\part c^i}{\part b^l}
$$
Now we can use gradient descent to optimise weights and biases.

#### 5.3.2 Mini-batch gradient descent

However, computing the gradients is expensive when the number of training examples $n$ is large.

We can instead **approximate** the gradients
$$
\frac{\part c}{\part w^l} \approx \frac 1b \sum_{i=1}^b \frac{\part c^i}{\part w^l}
\\ \frac{\part c}{\part b^l} \approx \frac 1b \sum_{i=1}^b\frac{\part c^i}{\part b^l}
$$
using a random *mini-batch* of $b\leq n$ training examples.

- $1<b<n$ : mini-batch gradient descent
- $b=1$: stochastic gradient descent
- $b=n$: batch gradient descent

Common to use mini-batch with size $b\in(20,100)$.





## Lecture 6: Softmax

### 6.1 Probabilistic model for classification

The per-example cost function obtained in the last lecture using the maximum likelihood method
$$
C^i=\sum_{j=1}^m \frac12(y^i_j-a_j^i)^2
$$
made the assumption that the examples belong to a Gaussian distribution. This is acceptable for regression problems.

However, for **classification** problems with $m$ **discrete** class labels $1,...,m$, better have one output unit $p_j$ per class $j$, where $p_j$ is interpreted as the probability of class $j$. These units should therefor satisfy
$$
\forall j \in \{1,...,m\}, \ \ P_j \geq 0, \ \ \sum_{j=1}^m P_j =1
$$

### 6.2 Softmax

#### 6.2.1 Softmax layer

We replace the last layer by a *softmax* layer:
$$
P_j = \frac {e^{z_j^L}}{\sum_{k=1}^me^{z_k^L}}
$$


| <img src="Neural_Computation.assets/Screenshot 2020-05-11 at 03.45.10.png" alt="Screenshot 2020-05-11 at 03.45.10" style="zoom:50%;" /> |
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

#### 6.1.2 Cost function

We can then define a cost function using maximum likelihood principle
$$
C=-\log\mathcal L(w,b|((x^1,y^1), ...,(x^n,y^n)) = \frac1n\sum_{i=1}^nC^i
$$

$$
\text{where} \ C^i = -logP_{wb}(y^i|x^i)
\\ \ \ \ \ \ = -\log P_{y^i}
\\ \ \ \ \ \ \ \ \ \  =\log Q-z^L_{y^i}
$$

To apply gradient descent to minimise the cost function, we need to compute the gradients of $w, b$ by computing the **local gradient** for the softmax layer using backpropagation.



#### 6.1.3 Theorem

$$
\delta_j^L =\frac{\part C^i}{\part z_j^L} = P_j - \delta_{y^ij}
$$

where $\delta_{ab}$ represents the Kronecker delta function ($\delta_{ij} = [i==j]$)
$$
\delta_{ab} = \cases {1 & a=b\\ 0 & otherwise}
$$
**Proof**:
$$
\frac {\part C^i}{\part z_j^L} = -\frac {\part \log P(y^i|x^i)}{\part z_j}
\\ = - \frac {\part \log P_{y^i}}{\part z_j}, \  \ \text {substitute}\  P_{y^i}=\frac {e^{a_{y^i}}}
\\ = - \frac {\part }{\part z_j}(z_{y^i}-\log Q)
$$







## Lecture 11: Regularisation

### 11.1 Model capacity

- Informally, is the model's capacity to fit a wide variety of functions.

- In statistical learning theory, model capacity is quantified by **VC-dimension**, i.e. the largest training set for which the model can classify the labels arbitrary into two classes.

- By the **universal approximation theorem**, neural networks can have very high capacity.



### 11.2 Generalisation error

#### 11.2.1 Underfitting and overfitting

- Underfitting: too high training error.

- Overfitting: too large gap between training error and test error.



| <img src="/Users/kevinxu95/ACS/NC/Notes/Neural_Computation.assets/Screenshot 2020-05-23 at 14.39.46.png" style="zoom:75%;" /> |
| :----------------------------------------------------------: |
|    **Fig 11.1** Model capacity vs training and test error    |



#### 11.2.2 Model capacity vs error

- Training and test error behave differently.
- Training error often decreases with capacity.
- Test error can increase beyond a certain capacity.
- Capacity is **optimal** when model matches data generating process.



### 11.3 Regularisation techniques

#### 11.3.1 Data augmentation

Many data sets can be augmented via transformations.

For a data set of images:

- mirroring
- translation
- scaling
- rotation
- noise

#### 11.3.2 Early stopping

To apply regularisation, first split data into training, validation and test sets.

1. Train model on training set, evaluate with fixed intervals on validation set.
2. Stop training when validation error has significantly increased.
3. Return model parameters when validation loss was the lowest.



### 11.4 Dropout

#### 11.4.1 Parameter norm penalties

We replace cost function by
$$
\tilde C(\theta; x, y) = C(\theta;x,y) + \alpha \Omega(\theta)
$$
Where $\Omega$ is a **regulizer** i.e. a function which penalises complex models, and $\alpha$ is a hyperparameter controlling degree of regularisation.

##### $L^2$ parameter regularisation:

Assuming parameters $\theta=(w,b)$:
$$
\Omega(\theta) := \frac 12\Vert w\Vert_2
$$
This penalise large weights.

#### 11.4.2 Ensemble methods

Combining different models often reduces generalisation error.

**Idea:** train $k$ neural networks on $k$ subsets of the training data. Output the *average* of the networks.

**Disadvantages:**

1. Usually requires more training data.
2. k times increase in training time.
3. Only feasible for small $k$.

#### 11.4.3 Dropout in neural network

The underlying idea of using “dropout” is to **approximate** the ensemble methods on a large number of neural networks to reduce generalisation error.

- In each mini-batch, *deactivate* some randomly selected activation units.
- Each selection of units corresponds to a **subnetwork**. 
  - with $n$ input and hidden layer activation units, there are $2^n$ subnetworks.
- The subnetworks **share the weights**.

- No dropout when testing and using.

#### 11.4.4 Implementation of dropout

Replace each activation unit $a_j^l = \phi(z_j^l)$ in a hidden layer with a **dropout activation** unit:
$$
\tilde a_j^l = \frac 1{1-p} \cdot d_j^l\cdot \phi(z_j^l)
$$
where $d_j^l \sim \mathcal {Be}(1-p)$, i.e. $P(d_j^l=0)=p$.

Notice $\tilde a_j^l$ is a random variable with expectation
$$
\mathbb E[\tilde a_j^l] = [p\cdot\frac1{1-p}\cdot0+(1-p)\cdot\frac 1{1-p}\cdot1]\cdot\phi(z_j^l)
\\ = \phi(z_j^l) = a_j^l
$$
Hence, choosing the factor $\frac 1{1-p}$ normalises the expected activation identical to the activation without dropout.



| <img src="/Users/kevinxu95/ACS/NC/Notes/Neural_Computation.assets/Screenshot 2020-05-23 at 15.22.35.png" style="zoom:50%;" /> | <img src="/Users/kevinxu95/ACS/NC/Notes/Neural_Computation.assets/Screenshot 2020-05-23 at 15.23.11.png" style="zoom:50%;" /> |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
|            **Fig 11.2** Implementation of dropout            |                 Backpropagation with dropout                 |



#### 11.4.5 Backpropagation with dropout

Given local gradient $\delta_j^l$, partial derivatives are:
$$
\frac {\part c}{\part w_{jk}^l} = \frac {\part c}{\part z_j^l} \cdot \frac {\part z_j^l}{\part w_{jk}^l} = \delta_j^l \cdot \tilde a_k^{l-1}\\
\frac {\part c }{\part b_j^l} = \frac {\part c}{\part z_j^l} \cdot \frac {\part z_j^l}{\part b_j^l} = \delta_j^l
$$
Given the dropout activation unit, the local gradient is:
$$
\delta_j^l = \frac {\part c}{\part z_j^l}\\ = \frac {\part c}{\part \tilde a_j^l} \cdot \frac {\part \tilde a_j^l}{\part z_j^l} \\ = (\sum_{k=1}^m\frac {\part c}{\part z_k^{l+1}}\frac {\part z_k^{l+1}}{\part \tilde a_j^l})\cdot \frac 1 {1-p} \cdot d_j^l \cdot \phi'(z_j^l)
\\ = (\sum_{k=1}^m\delta_k^{l+1}\cdot w_{kj}^{l+1})\cdot \frac 1 {1-p}\cdot d_j^l \cdot \phi'(z_j^l)
$$
In matrix form:
$$
\delta^l = \frac 1{1-p}((w^{l+1})^T\delta^{l+1})\odot d^l\odot \phi'(z^l)
$$
