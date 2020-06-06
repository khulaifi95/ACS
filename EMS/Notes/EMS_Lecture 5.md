# Evaluation Methods & Statistics



## Lecture 5: Difference in Distribution



### 1. Testing for differences in means

If the sample **mean difference** is greater than we expect, then:

1. We could have collected samples that are not typical of the population.

2. The two samples could be from different populations.

We can compare the means to decide which explanation is most plausible.



### 2. Student's t-distribution

A t-distribution is defined as a measure of **central tendency** divided by a measure of **spread**.

Let $X_1,...,X_n$ be independently and identically drawn (*i.i.d.*) from the distribution $\mathcal N(\mu, \sigma^2)$, i.e., this is a sample of size *n* from a normally distributed population with expected mean value $\mu$ and variance $\sigma^2$.

The sample mean is given by:
$$
\bar X = \frac 1n \sum_{i=1}^nX_i
$$
The sample variance is given by:
$$
S^2=\frac 1{n-1}\sum_{i=1}^n(X_i-\bar X)^2
$$
The resulting *t-value* is defined as:
$$
t = \frac {\bar X - \mu}{S/\sqrt{n}}
$$
which follows the t-distribution with *n-1* degrees of freedom.



### 3. Student's t-test

Two-sample t-tests for a difference in mean involve two **types**:

1. Paired samples

   - Repeated measures of one group.
- Each participant completes **all** conditions.
   
2. Independent samples

   - Measure of two seperate groups.

   - Different participants are allocated to **each** condition.

We make two **assumptions** under the t-test:

- The differences between sample mean, $D$, follow a normal distribution.

- The population variance is the same as the sample variance.

The t-statistic for the **paired** samples test is:
$$
t = \frac {(\sum D)/N}{\sqrt{\frac{\sum D^2-(\sum D)^2/N}{N(N-1)}}}
$$
The t-statistic for the **independent** samples test is:
$$
t = \frac {\bar X_1-\bar X2}{\sqrt{\frac{S_1^2}{N_1}+\frac{S_2^2}{N_2}}}
$$
where $D$ is the difference between trials, $N$ is the number of samples.

- Use t-table to check the significance of t-statistics. (accept when $p>0.05$)
- Report the result as: $t(N) = t, \ p<0.05$ if rejecting null hypothesis.



### 4. Power and effect size

Power $1-\beta$ is the **probability** that the study produces **significant** **difference** between groups when a difference actually exists. 

- Related to Type II error.
- Correctly rejects the false null hypothesis.
- Relies on sample size and **effect size**.

In testing the differences between means, the effect size $\theta$ of a population is:
$$
\theta = \frac{\mu_1-\mu_2}{\sigma}
$$
where $\mu$ are the means of two populations, and $\sigma$ is the standard deviation.



We use **Cohen's d** to formulae the **effect size** when the population is unknown.

- For a **paired** t-test:

$$
d = \frac {\bar X_1 -\bar X_2}{\sqrt{S_1^2+S_2^2-2rS_1S_2}}
$$

​		where $r$ is the correlation coefficient between groups.

- For an **independent** t-test:

$$
d = \frac {\bar X_1-\bar X_2}{\text{pooled variance}}
$$

​		where the pooled variance for two equal sized samples is: $\sqrt{\frac{S_1^2+S_2^2}{2}}$.



### 5. Odds ratio

An odds ratio indicates the **strength of effect** for one condition relative to another.

We calculate the probability of a result given condition and contrast this with the probability of a contrasting result.

In a two-event example, the experiment results are in a $2\times2$ matrix:

|      | Miss | Attend |
| ---- | ---- | ------ |
| Fail | 17   | 5      |
| Pass | 6    | 32     |

The odds ratio is given by $(17\times 32)/(6\times5)=18.13$.



### 6. Non-parametric test

If the data is non-parametric, we can test the difference between groups by:

- Friedman test:
  - Rank rows together and consider the values of ranks by columns.
  - Detect differences in treatments across multiple test attempts.
  - Alternative to **independent** t-test.
- Wilcoxon signed-rank test:
  - Whether population mean ranks differ.
  - Alternative to **paired** t-test.