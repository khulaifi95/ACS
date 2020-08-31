# Pattern Recognition and Machine Learning



## Chapter 1: Introduction



### 1. Probability theory

The **sum rule** of probability:
$$
p(X) = \sum_Yp(X,Y)
$$
The **product rule** of probability:
$$
p(X,Y) = p(Y|X)p(X)
$$
From (2) and the symmetry property $p(X,Y)=p(Y,X)$, we have the **Bayes' theorem**:
$$
p(Y|X) = \frac{p(X|Y)p(Y)}{p(X)}
$$
Using (1), the denominator in (3) can be expressed in terms of the quantities in the numerator:
$$
p(X) = \sum_Yp(X|Y)p(Y)
$$
where $p(Y)$ is the **prior**, $p(Y|X)$ is the **posterior**.

$X$ and $Y$ are said to be **independent** when the joint distribution factorises into the product of two marginals i.e.
$$
p(X,Y) = p(X)p(Y)
$$


#### 1.1 Probability densities

The probability that $x$ will lie in an interval $(a,b)$ is given by:
$$
p(x\in (a,b)) = \int_a^bp(x)dx
$$
The probability density $p(x)$ must satisfy two conditions, i.e.:
$$
\begin{align} p(x) &\geq 0 \\ \int_{-\infty}^{\infty}p(x)dx &= 1 \end{align}
$$
The probability that $x$ lies in the interval $(-\infty, z)$ is given by the *cumulative distribution function* (**CDF**):
$$
P(z) = \int_{-\infty}^zp(x)dx
$$
which satisfies $P'(x) = p(x)$. 

For several continuous variables $x_1, \cdots, x_D$ denoted by the vector $\mathbf x$, we can define a joint probability density:
$$
p(\mathbf x)=p(x_1,\cdots, x_D)
$$
The multivariate probability density must satisfy:
$$
\begin{align} p(\mathbf x) &\geq 0 \\ \int p(\mathbf x) d\mathbf x &= 1 \end{align}
$$
If $x$ is a *discrete* variable, then $p(x)$ is sometimes call a *probability mass function* (**PMF**).



#### 1.2 Expectations and covariances

The **expectation** of $f(x)$ is the weighted average value of function $f(x)$ under a probability distribution $p(x)$:
$$
\mathbb E[f] = \sum_xp(x)f(x)
$$
For continuous variables, expectations are expressed in terms of an **integration**:
$$
\mathbb E[f] = \int p(x)f(x) dx
$$
If we are given a finite number $N$ of points drawn from the distribution, the expectation can be **approximated** as:
$$
\mathbb E[f] \approxeq \frac 1N \sum_{n=1}^Nf(x_n)
$$
which becomes exact in the limit $N \rightarrow \infty$.

A **conditional expectation** *w.r.t.* a conditional distribution can be denoted as:
$$
\mathbb E_x[f|y] = \sum_xp(x|y)f(x)
$$
The **variance** of $f(x)$ provides a measure of how much variability in $f(x)$ around the mean value $\mathbb E[f(x)]$:
$$
var[f] = \mathbb E[(f(x) - \mathbb E[f(x)])^2]
$$
The variance of the variable $x$ itself is given by:
$$
var[x] = \mathbb E[x^2] - \mathbb E[x]^2
$$
For two random variables $x$ and $y$, the **covariance** expresses the extent to which $x$ and $y$ vary together:
$$
\begin{align} cov[x,y] &= \mathbb E_{x,y}[{x-\mathbb E[x]}{y-\mathbb E[y]}] \\&=\mathbb E_{x,y}[xy] - \mathbb E[x]\mathbb E[y] \end{align}
$$


#### 1.3 Bayesian probabilities

In some circumstances, we would like to be able to quantify the expression of **uncertainty** and make precise revisions of uncertainty in the light of new evidence.

When making inferences about parameters $\mathbf w$, the effect of the observed data $\mathcal D = \{t_1,\cdots, t_N\}$ is expressed through the conditional probability $p(\mathcal D|\mathbf w)$ by Bayes' theorem in the form:
$$
p(\mathbf w|\mathcal D) = \frac{p(\mathcal D|\mathbf w)p(\mathbf w)}{p(\mathcal D)}
$$
where $p(\mathcal D|\mathbf w)$ is the **likelihood function**, expressing how probable the data set is given the parameter vector.

The denominator can be expressed in terms of the prior distribution and the likelihood function:
$$
p(\mathcal D) = \int p(\mathcal D|\mathbf w)p(\mathbf w)d\mathbf w
$$
Bayes' theorem can be stated in words as following:
$$
\text{posterior} \propto \text{likelihood} \times\text{prior}
$$
From the Bayesian viewpoint there is only a single data set $\mathcal D$, and the **uncertainty** in the parameters $\mathbf w$.



#### 1.5 Gaussian distribution

For the case of a single real-valued variable $x$, the Gaussian distribution is defined by:
$$
\mathcal N(x|\mu, \sigma^2) = \frac1{\sqrt{2\pi\sigma^2}}exp\Big\{-\frac1{2\sigma^2}(x-\mu)^2\Big\}
$$

- $\mu$: mean
- $\sigma^2$: variance
- $\sigma$: standard deviation
- $\beta=1/\sigma^2$: precision

Since the data set $\mathbf x$ is *i.i.d*, we can therefore write the probability of the data set given mean and variance:
$$
p(\mathbf x|\mu, \sigma^2)=\prod_{n=1}^N\mathcal N(x_n|\mu, \sigma^2)
$$
which is viewed as a function of $\mu, \sigma^2$, and as the *likelihood function* for the Gaussian.

[^i.i.d]: Data points that are drawn independently from the same distribution are said to be *independent and identically distributed*, which is often abbreviated to i.i.d.

The **log likelihood function** for Gaussian can be written in the form:
$$
\ln p(\mathbf x|\mu, \sigma^2) = -\frac1{2\sigma^2}\sum_{n=1}^N(x_n-\mu)^2 - \frac N2 \ln\sigma^2-\frac N2\ln(2\pi)
$$
Maximising *w.r.t*. $\mu$, we have the maximum likelihood solution for the mean, which is the **sample mean**:
$$
\mu_{MLE} = \frac 1N\sum_{n=1}^Nx_n
$$
Maximising *w.r.t.* $\sigma^2$, we have the maximum likelihood solution for the variance, which is the **sample variance**:
$$
\sigma^2_{MLE} = \frac 1N\sum_{n=1}^N(x_n-\mu_{MLE})^2
$$
The maximum likelihood approach systematically **underestimates** the variance of the distribution in practice.

This is an example of a phenomenon called **bias** and is related to the problem of over-fitting.

The following estimate for the variance parameter is unbiased:
$$
\tilde \sigma^2 = \frac{N}{N-1}\sigma^2_{ML} = \frac{1}{N-1}\sum_{n=1}^N(x_n - \mu_{ML})^2
$$
The bias of the maximum likelihood solution becomes *less significant* as the number N of data points increases.



| <img src="Lecture 1_Intro.assets/Screenshot from 2020-08-29 23-20-47.png" style="zoom:80%;" /> |
| :----------------------------------------------------------: |
|        **Fig 1.1** How bias arises in MLE of variance        |



#### 1.5 Bayesian curve fitting

A Bayesian treatment simply corresponds to a consistent application of the sum and product rules of probability, which allow the predictive distribution to be written in the form:
$$
p(t|x, \mathbf x, \mathbf t) = \int p(t|x,\mathbf w)p(\mathbf w|\mathbf x, \mathbf t)d\mathbf w
$$
while $p(t|x,\mathbf w)$ is given by:
$$
p(t|x,\mathbf w, \beta) = \mathcal N(t|y(x,\mathbf w), \beta^{-1})
$$
And the posterior distribution $p(\mathbf w|\mathbf x,\mathbf t)$ can be evaluated analytically:
$$
p(\mathbf w|\mathbf x, \mathbf t,\alpha,\beta)\propto p(\mathbf {t|x, w}, \beta)p(\mathbf w|\alpha)
$$
The integration can also be performed analytically:
$$
p(t|x,\mathbf x, \mathbf t) = \mathcal N(t|m(x), s^(x))
$$
where the mean and variance are given by:
$$
m(x) = \beta\phi(x)^T\mathbf S\sum_{n=1}^N\phi(x_n)t_n \\ s^2(x) = \beta^{-1}+\phi(x)^T\mathbf S\phi(x)
$$
Here the matrix $\mathbf S$ is given by:
$$
\mathbf S^{-1} = \alpha\mathbf I + \beta\sum_{n=1}^N\phi(x_n)\phi(x)^T
$$


### 2. Model selection



### 3. The curse of dimensionality



### 4. Decision theory



### 5. Information theory



