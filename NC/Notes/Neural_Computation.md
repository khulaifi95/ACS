# Neural Computation

---



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
w = \frac {\sum\limits_{i=1}^nx^iy^i}{\sum\limits_{i=1}^n(wx^i)^2} \\
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
\\= \arg\max_\theta \log\mathcal L(\theta)
\\= \arg\max_\theta \log\prod_{i=1}^np_{model}(y^i|x^i;\theta)
\\= \arg\max_\theta \sum_{i=1}^n\log p_{model}(y^i|x^i;\theta)
\\= \arg\min_\theta \frac1n\sum-\log p_{model}(y^i|x^i;\theta)
\\= \arg\min_\theta \mathbb E_{(x,y)\sim \mathcal {\hat D}}-\log p_{model} (y|x;\theta)
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
\frac{\part f}{\part x_i}(u_1,...,u_m) = \lim_{n\rightarrow0}\frac{f(u_1,...,u_i+ h, ..., u_m) - f(u_1,...,u_m)}{h}
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



