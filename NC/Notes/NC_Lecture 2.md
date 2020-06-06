# Neural Computation



## Lecture 2. Linear Regression



### 1. Linear regression models

#### 1.1 Experience E

The dataset consists of $n$ data points
$$
(x^1,y^1),...,(x^n,y^n) \in \mathbb R^D \times \mathbb R
$$
where $x^i \in \mathbb R^d$ is the **input** for the i-th data point as a feature vector with $d$ elements, $y^i \in \mathbb R$ is the **output** for the i-th data point.



#### 1.2 Task T

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

- $x\in\mathbb R^d$ is the input vector  - **features**.
- $w\in\mathbb R^d$ is a **weight** vector - **parameters**.
- $b\in\mathbb R$ is a **bias** parameter.
- $f(x)\in \mathbb R$ is the predicted **output**.



#### 1.3 Performance measure P

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

- ignore the sign of the errors.
- Penalise large errors more.



### 2. Optimisation

#### 2.1 Unconstrained minimisation

Given a loss function $f$, an element $x\in\mathbb R^d$ is 

- a global minimum of $f$ if

$$
\forall m \in \mathbb R^d, \ \ f(x) \leq f(m)
$$

- a local minimum of $f$ if

$$
\exists \ \epsilon>0 \ \ \forall \ m \in \mathbb R^d, \forall i\in [1,d]\ |x_i-m_i|<\epsilon,\ \ f(x)\leq f(m).
$$



| <img src="/Users/kevinxu95/ACS/NC/Notes/NC_Lecture 2.assets/Screenshot 2020-05-07 at 16.54.02.png" style="zoom:50%;" /> |
| :----------------------------------------------------------: |
|        **Fig 2.1**: Local minimum and global minimum         |



#### 2.2 Theorem

For any continuous function $f:\mathbb R \rightarrow \mathbb R$, if $x$ is a local optimum, then 
$$
f'(x) = 0
$$



### 3. Derivatives

#### 3.1 Definition

The first derivative of a function $f:\mathbb R \rightarrow \mathbb R$ is 
$$
f'(x) = \lim_{\Delta x\rightarrow0}\frac {f(x+\Delta x)-f(x)} {\Delta x}
$$



#### 3.2 Leibniz's notation

If $y=f(x)$, then
$$
\frac {dy}{dx} = f'(x)
$$



#### 3.3 Differentiation rules

- $(cf(x))'=cf'(x)$
- $(x^k)' = kx^{k-1}$
- $(f(x) + g(x))' = f'(x) + g'(x)$
- *Chain rule* - $(f(g(x)))' = f'(g(x))\cdot g'(x)$



#### 3.4 Chain rule in Leibniz's notation

Assume that $z=h(x)$, $y=g(z)=g(h(x))$, then
$$
\frac {dy}{dx} = \frac {dy}{dz}.\frac{dz}{dx}
$$



#### 3.5 Example: chain rule

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



### 4. Approach I: Ordinary Least Square

Optimise $J$ by directly solving $J'(w) = 0$:
$$
\begin{align} J(w) &= \frac {1}{2n} \sum\limits_{i=1}^n(y^i - wx^i)^2 \\ \text{Let}\ J'(w) &= \frac 1n \sum_{i=1}(wx^i-y^i)x^i =0\\ w\sum\limits_{i=1}^n(wx^i)^2&= \sum\limits_{i=1}^nx^iy^i \end{align}
$$

$$
w = \frac {\sum\limits_{i=1}^nx^iy^i}{\sum\limits_{i=1}^n(x^i)^2} \\
$$

- Only one solution to $J'(w) = 0$, hence **globally** optimal.



### 5. Approach II: Gradient Descent

However, it is often difficult or impossible to solve $J'(w) =0$ for non-linear models with many parameters such so in neural networks.

**Idea**: Start with an initial guess $w$, while $J'(w)\neq0$, move $w$ ***slightly*** in the ***right direction***.



#### 5.1 Attempt 1 (Failed)

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



#### 5.2 Attempt 2 (Gradient)

```pseudocode
w <- initial weight
repeat
		w <- w - eJ`(w)		
		# if J`(w)< 0, w increase
		# if J`(w)> 0, w decrease
```

- $\epsilon$ : **learning rate** - hyperparameter 

