# Neural Computation



## Lecture 3: Maximum Likelihood



### 1. Probabilistic models

Different from a deterministic model, a probabilistic model can account for **variance** in the data. For example:
$$
F(x) = wx+N
$$
where $N\sim \mathcal N(0,\sigma^2)$ is a *noise term* which is normally distributed.

Here, the function $F(x)$ is a **random variable** which can be described by a *conditional density* $P(y|x,w)$.



### 2. [Concepts](/Users/kevinxu95/Selfstudy/Machine Learning/2 Probability.md)

#### 2.1 Random variables

A random variable takes a value depending on a random phenomenon.



#### 2.2 Density function

The density function of a continuous random variable $x$ is a function $p: \mathbb R \rightarrow \mathbb R$, such that:
$$
\int_a^bp(x)dx=Pr(a\leq x\leq b)
$$



#### 2.3 Normal distribution

The *pdf* is defined by the mean value and standard deviation
$$
\mathcal N(x|\mu, \sigma^2) = \frac1{\sqrt{2\pi\sigma^2}} \exp(-\frac{(x-u^2)}{2\sigma^2})
$$



#### 2.4 Expectation

The expected value of the distribution of a random variable is
$$
\mathbb E_{x\sim p}x \triangleq \int_{-\infty}^\infty xp(x)dx
$$

**Example**:
$$
\mathbb E_{x\sim\mathcal N(\mu,\sigma^2)}x = \mu
$$



#### 2.5 Joint distributions and independence

- The joint density function of $n$ random variables $x^i, ...,x^n$ is a function $p: \mathbb R^n \rightarrow \mathbb R$ such that:

$$
\int_Dp(x^1,...,x^n)\ dx^1,...,dx^n=Pr((x^1, ...,x^n)\in D)
$$

​		for any n-dimensional domain D.

- If $x^1,...x^n$ are n **independent** random variables with density functions $p^1,..., p^n$ and joint density $p$, then 

$$
p(x^1,...,x^n) = \prod\limits_{i=1}^nP^i(x^i)
$$



#### 2.6 Empirical distribution

Given $n$ independent samples from an unknown distribution $\mathcal D$, we can construct an *approximation* of $\mathcal D$ by uniformly sampling from the set $\{x^1,...,x^n\}$. The *pdf* of the empirical distribution of $\mathcal D$ is defined as
$$
p_n(x) \triangleq \frac 1n \sum\limits_{i=1}^n\delta(x^i-x)
$$
where $\delta$ is the **Dirac delta**, i.e. $\delta(x)=0$ for $x\neq0$ or 1 for $x=0$. Note:
$$
\mathbb E_{x\sim p_n}f(x) = \frac 1n\sum\limits_{i=1}^nf(x^i)
$$



#### 3. Maximum likelihood

#### 3.1 Learning task

Instead of deterministically predicting an output, we train a probabilistic model represented by a **conditional density function**
$$
p_{model}(y|x;\theta)
$$
where $\theta$ is the parameter of model.

- We need to choose from a family of probabilistic models to find the parameter $\theta$ which is appropriate for the data. 



#### 3.2 Likelihood function

The likelihood function of given independent training dataset $(x^1,y^1),...,(x^i,y^i)$ and a probabilistic model $p_{model}$ with parameter $\theta$ is defined as:
$$
\mathcal L(\theta; (x^1,y^1),...,(x^i,y^i)) \triangleq \prod_{i=1}^np_{model}(y^i|x^i;\theta)
$$
where $\mathcal L$ is the **likelihood** that the observation comes from the model.



#### 3.3 Maximum likelihood estimate

**Goal**: Given training data and a family of models indexed by parameter $\theta$, which of the models are most likely to have produced the data?
$$
\Theta_{MLE}\triangleq \arg\max_\theta \mathcal L(\theta;  (x^1,y^1),...,(x^i,y^i)) \\ = \arg\max_\theta\prod_{i=1}^np_{model}(y^i|x^i;\theta)
$$



#### 3.4 Log-likelihood

For numerical and analytical reasons, a convenient reformulation is
$$
\begin{align}\Theta_{MLE} &= \arg\max_\theta\mathcal L(\theta) \\&= \arg\max_\theta \log\mathcal L(\theta) \\&= \arg\max_\theta \log\prod_{i=1}^np_{model}(y^i|x^i;\theta) \\&= \arg\max_\theta \sum_{i=1}^n\log p_{model}(y^i|x^i;\theta) \\&= \arg\min_\theta \frac1n\sum-\log p_{model}(y^i|x^i;\theta) \\&= \arg\min_\theta \mathbb E_{(x,y)\sim \mathcal D^n}-\log p_{model} (y|x;\theta)\end{align}
$$



#### 3.5 Learning via log-likelihood

Neural network models are often trained by **minimising the negative log-likelihood** of the model given the training data, the cost function
$$
J(\theta) = \mathbb E_{(x,y)\sim \mathcal D^n}-\log p_{model}(y|x;\theta)
$$
where $\mathcal D$ is the empirical distribution of data.



#### 3.6 Example: Linear regression

Predicting from the model $F(x)=wx+N$, where $N\sim \mathcal N(0,\sigma^2)$, hence:
$$
F(x) \sim \mathcal N(wx,\sigma^2)
$$
i.e., a model with conditional density function
$$
p_{model}(y|x;w) = \frac1{\sqrt{2\pi\sigma^2}}\exp(-\frac{(y-xw)^2}{2\sigma^2})
$$
The parameter $w$ which maximises the likelihood is given by
$$
\begin{align} w_{MLE} &= \arg\min_w\sum_{i=1}^n-\log p_{model}(y^i|x^i;w) \\ &= \arg\min_w\sum_{i=1}^n[-\log(\frac1{\sqrt{2\pi\sigma^2}})+\frac{(y^i-x^iw)^2}{2\sigma^2} ]\\ &= \arg\min_w\frac1n\sum_{i=1}^n\frac12(y^i-x^iw)^2 \end{align}
$$

- Identical to the MSE solution.

