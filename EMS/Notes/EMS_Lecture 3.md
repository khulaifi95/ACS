# Evaluation Methods & Statistics



## Lecture 3: Inferential Statistics



### 1. Inferential statistics

While descriptive statistics summarises the data, to make inferences about the population from the data (**generalisation**), we use inferential statistics.

Inferential statistics requires:

1. An estimate of a population parameter
2. A test of an hypothesis

We want to know:

1. Whether a **statistic** (data value) is near a given parameter?
2. what is the **probability** that the parameter is within a **range**?
   - Condifence interval - *range*
   - Confidence level - *probability*



### 2. Point estimates

Several values can be used to estimate the parameters of a sample *n* of a population *N*.

1. **Population proportion**

The proportion of the tested samples in the total population.
$$
P = n/N
$$

2. **Mean** 

The Expected value is the average of all possible values of a variable.
$$
\mathbb E(x) = \frac 1n \sum_{i=1}^Nx_i
$$

3. **Variance**

The Variance is the spread of possible value of a variable.
$$
var(x) = \mathbb E [(x-\mu)^2] = \mathbb E[x^2]-(\mathbb E[x])^2
$$

4. **Standard deviation**

The standard deviation is the square root of variance.
$$
\sigma = \sqrt{var(x)}
$$



### 3. Standard score

We have the normal distribution with *pdf*:
$$
\mathcal N(x|\mu,\sigma^2) = \frac 1{\sqrt{2\pi\sigma^2}}e^{\frac{(x-\mu)^2}{2\sigma^2}}
$$

-  Standardized Normal Distribution assumes $\mu=0, \sigma=1$.

The **standard score** is the **number** of standard deviations by which the value of a raw score is above or below the mean value. (*z-table*)
$$
z = \frac {x-\mu}{\sigma}
$$
The standard score can be used to calculate the **Prediction interval**. It is an interval such that a future observation *x* will lie in with high probability.

- Upper bound: $\mu+z\sigma$
- Lower bound: $\mu-z\sigma$
- Interval of error: $z\sigma$



### 4. Sample variance

In practice, we often need to get the **sample variance**, because it is impossible to calculate the population mean given a limited number of samples.

- Simply dividing by *n* can **underestimate** population variance.

**Degree of freedom** *d.f.* is the number of data points *N* minus the number of parameters used in the calculation.

- When calculating variance, the degree of freedom is *N* subtracted by the number of parameter used i.e. 1, so we have:

$$
d.f. = N - 1
$$

Thus the sample variance is given by:
$$
S^2 = \frac {\sum(x-\bar x)^2}{N-1}
$$
where $N-1$ can be interpreted as the times of comparison for each row. 



### 5. Normality test

1. Histogram

We can plot the data in a histogram and visually check the *bell curve*.



2. Q-Q plot

We can plot **quantiles** of the ordered data points in the tested distribution to the quantiles of a normal distribution.

- Sort all data from experiment together.

- The closer the plot is to a straight line, the more likely $X\sim\mathcal N$.



3. Shapiro-Wilk test

It tests the null hypothesis $H_0$ that the sample is drawn from a normal distribution. The test statistic is:
$$
W = \frac {(\sum a_ix_{(i)})^2}{\sum(x_i-\bar x)^2}
$$
where $x_{(i)}$ is the i-th order statistic of the sample, $\bar x$ is the sample mean.

- Notice that the *denominator* is actually the **variance** of the sample data, while the *numerator* is in fact the **expected values** of ordered statistics sampled from a standard normal distribution. 
- The numerator can be analogy to the **slope** of the Q-Q plot, i.e. $\sigma^2$ when it is perfectly normally distributed. Then the *W* value should be 1.

- if the returned **p-value** is *less* than the chosen **alpha level**, then the null hypothesis is rejected and there is evidence that the data tested are not normally distributed. 
- $p> 0.05$ shows normality.



**Note:**

The Shapiro-Wilk test becomes very **sensitive** when the sample size is very large $\uparrow$. In that case, better use a Q-Q plot.



### 6. p-value

The p-value is defined as the **probability**, under null hypothesis $H_0$ about the unknown distribution $F$ of the random variable $X$, for the variate to be observed as a value **equal to or more extreme** than the value observed.

- Thus the p-value is given by:
  - $Pr(X\geq x|H)$ for right tail event.
  - $Pr(X\leq x|H)$ for left tail event.
  - $2\min\{Pr(X\leq x|H), Pr(X\geq x|H)\}$ for double tail event.

- The smaller $\downarrow$ the *p*-value, the higher $\uparrow$ the statistical significance.

- Small p-value tells that the hypothesis may **not** **adequately** **explain** the observation. 

- The null hypothesis is rejected once the p-value is less than or equal to a small, fixed predefined threshold $\alpha$, which is referred to as the **level of significance**.



### 7. Distribution transformation

If the data are not normally distributed, several tasks can be done to fix:

1. Check for outliers

- Calculate mean and standard deviation $\mu, \sigma$.
- Apply a **cut-off** of value outside $\mu ± 2\sigma$ interval.
- Criteria need to be defined prior to collecting data.



2. Remove outliers

Suppose many samples in the data are in the **tails** and lead to a **skewed** distribution. We can trim the tails and make the distribution normal.

- **Trimming**:

  Remove a fixed percentage (5%) of samples. Number of samples decreased.

- **Winsorization**:

  Trim but replace with these most extreme values left in each tail.



3. Transformation

- **Z score transform**:

  We can normalise each value to its z-score:

$$
x \rightarrow \frac {x-\mu}{\sigma}
$$

- **Log transform**:

  To manage **unequal variance** across levels of independent variable, we can transform the data throught $\log$.

- **Box-cox transform**:

  Transform non-normal data into a normal distribution by applying a **power transform**, $\lambda \in[-5,5]$, optimal selected.

- **Other approaches**:
  - Reconsider the measurement scale.
  - Apply non-parametric tests.



### 8. Sample size

We estimate the size of samples in a test by:
$$
n = \frac {2(za+z1-\beta)^2\cdot \sigma^2}{\Delta^2}
$$
where $\Delta$ is the estimated effect size, i.e. difference between conditions.

The value of $za, z1-\beta$ can be found in the lookup table.



| $za$     |      |      | $z1-\beta$ |        |        |        |        |
| -------- | ---- | ---- | ---------- | ------ | ------ | ------ | ------ |
| $\alpha$ | 0.05 | 0.01 | Power      | 80%    | 85%    | 90%    | 95%    |
| 2-sided  | 1.96 | 2.33 | $z1-\beta$ | 0.8416 | 1.0363 | 1.2816 | 1.6449 |

