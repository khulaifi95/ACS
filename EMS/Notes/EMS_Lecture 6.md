# Evaluation Methods & Statistics



## Lecture 6: Relationships in Distribution



### 1. Correlation

Correlation gives a measure of the degree to which two variables are related.

- **No fitted line** through data points.
- Coefficient *r* indicates how much one variable **changes** when the other does.
- The score is symmetric.

**Covariance** is a measure of how two variables $(x,y)$ vary together, defined as:
$$
cov(x,y)=\frac {\sum(x_i-\bar x)(y_i-\bar y)}{N-1}
$$
To acquire a measure of correlation without accounting for variance, we need to *standardise* the covariance against standard deviation.

The **Pearson's Product-moment correlation** is thus given as:
$$
r=\frac {N\sum xy - \sum x\sum y}{\sqrt{[N\sum x^2-(\sum x)^2][N\sum y^2 - (\sum y)^2]}}
$$
where *N* is the number of pairs.

Pearson's *r* makes **assumptions** that:

1. Continuous variables only, i.e. interval or ratio data.
2. Parametric tests, i.e. normally distribued.
3. Indicates the strength of linear relationship between variables.



### 2. Regression

Regression defines the best **line relation** between a *predictor* and an *outcome*.

- The statistic is denoted as $R^2$.
- We assume there is a relationship.
- We assume the relations are linear.

For a straight line model:
$$
Y_i = \beta_1x_i+\beta_0+\epsilon
$$
where $Y_i$ is the outcome, $\beta_0$ is the intercept, $\beta_1$ is the slope of the regression line, $\epsilon$ is a mean-zero noise term.



### 3. Estimate the coefficients

In practice, $\beta_0$ and $\beta_1$ are unknown. So we must use data to estimate the coefficients.

We want to find the parameters such that the resulting model is the **closest** be the data points. We minimise the least squares criterion.

- Let $\hat y_i = \hat\beta_0+\hat\beta_1x_i$ be the prediction for $Y$ based on the i-th value of $X$.
- Then $e_i = y_i - \hat y_i$ represents the **residual** i.e. difference between the i-th observed value and i-th response value.
- We define the *residual sum of squares* RSS as:

$$
RSS = e_1^2+e_2^2+...+e_n^2
$$

- Thus the goal is to minimise the RSS in the form:

$$
\min RSS = (y_1-\hat\beta_0-\hat\beta_1x_1)^2+...+(y_n-\hat\beta_0- \hat\beta_1x_n)^2
$$

- Calculate the derivative of RSS and set to zero, we have:

$$
\begin{align}
\min\hat\beta_1 &= \frac{\sum_{i=1}^n(x_i-\bar x)(y_i-\bar y)}{\sum_{i=1}^n(x_i-\bar x)^2} \\ \min\hat\beta_0 &= \bar y-\hat\beta_1\bar x
\end{align}
$$

​		where $\bar x_i, \bar y_i$ are the sample means.



### 4. Accuracy of estimates

When estimating the coefficients on the basis of a set of obervations proportion to the total population, the model might be biased. 

An **unbiased** estimator does not *systematically* over- or -under-estimated the true parameter. By **averaging** the estimates obtained over a huge number of data sets, we can achieve the coefficients very close to the true value.

- We define the **standard error** of an estimate of mean by:

$$
Var(\hat\mu) = SE(\hat\mu)^2=\frac{\sigma^2}{n}
$$

The standard error tells the average amount that the estimator $\hat\mu$ differs from the   real mean. The more observations $n\uparrow$ we have, the smaller standard error of $\hat \mu\downarrow$.

- Similarly, the standard errors of $\hat\beta_0, \hat\beta_1$ is given by:

$$
SE(\hat\beta_0)^2=\sigma^2[\frac 1n+\frac{\bar x^2}{\sum_{i=1}^n(x_i-\bar x)^2}], \\ SE(\hat \beta_1)^2=\frac {\sigma^2}{\sum_{i=1}^n(x_i-\bar x)^2}
$$

​		where $\sigma^2=Var(\epsilon)$ is the variance in the noise term.

- $\sigma$ is unknown, but can be estimated as the *residual standard error* RSE:

$$
RSE = \sqrt{RSS/(n-2)}
$$



### 5. Relationship test

The assumption that there is a relationship between the predictor and outcome must be tested before the assessment.

- With standard error, the **confidence interval** for the coefficients can be represented as:

$$
\hat\beta_0±2\cdot SE(\hat\beta_0), \hat\beta_1±2\cdot SE(\hat\beta_1).
$$

- To reject the null hypothesis that there's no relationship between $X$ and $Y$, we compute a *t-statistic* given by:

$$
t=\frac{\hat\beta_1-0}{SE(\hat\beta_1)}
$$

​		which measures the number of standard deviations from 0.

- We expect it follows a t-distribution with $n-2$ degrees of freedom. We can compute **p-value** as the probability of observing any number larger or equal to $|t|$ in absolute value.
  - $p<0.05$, reject null hypothesis.



### 6. Assess the accuracy

The quality of a linear regression fit can be assessed by RSE and the $R^2$ statistic.

The *residual standard erro*r RSE is an estimate of the standard deviation of the noise term $\epsilon$. It is given by:
$$
RSE=\sqrt{\frac{1}{n-2}RSS}=\sqrt{\frac1{n-2}\sum_{i=1}^n(y_i-\hat y_i)^2}
$$

- It represents the average amount that the data will deviate from the true regression line.
- It is a measure of the *lack of fit of the model*.

The $R^2$ statistic is the **proportion** of variance **explained** in the data, in formula:
$$
R^2=\frac{TSS-RSS}{TSS}=1-\frac{RSS}{TSS}
$$
where $TSS=\sum(y_i-\bar y)^2$ is the *total sum of squares*, $RSS=\sum(y_i-\hat y_i)^2$.

- TSS is the amount of variability **inherent** in the data before regression.
- RSS is the amount of variability left **unexpalined** after regression.
- $TSS-RSS$ hence is the amount of variability that is explained.

Thus, the $R^2$ statistic is a measure of the proportion of variability in $Y$ that **can be explained** using $X$.



### 7. F-ratio

We need to determine whether there is a relationship between the response and predictors. We test the null hypothesis that:
$$
H_0:\beta_1=\beta_2=...=\beta_p=0
$$
while the alternative is $H_a$ that $\exist \ \beta_j$ for $j\in[1,p]$ that is non-zero.

The hypothesis test is performed by computing the **F-statistic**:
$$
F = \frac{(TSS-RSS)/p}{RSS/(n-p-1)}
$$
where $TSS=\sum(y_i-\bar y)^2, \ RSS=\sum(y_i-\hat y_i)^2$, *p* is the number of predictors.

Practically, the F-statistic compares the variation in the data **explained** by the model against the variation of date left not explained by the model.

- When there is no relationship, the F-ratio is expected to be close to 1.
- Otherwise, F-ratio is greater than 1.



### 8. ANOVA

ANOVA is an omnibus analysis of variance that checks whether the explained variance by the model is significantly greater than the unexplained in the data.

- We hypothesise that our experimental conditions will be sufficient to separate the sample data into distributions.
- We report whether there is a **main effect** for data variance.
- We calculate the F-ratio to test the variance.

$$
F = \frac{\sum_{i=1}^K n_i(y_i-\bar y)^2/(K-1)}{\sum_{i=1}^K\sum_{j=1}^{n_i}(y_i-\hat y_i)^2/(N-K)}
$$

where $K$ is the number of groups, $n_i$ is the number of observations in the i-th group, $N$ is the overall sample size.

- $F=t^2$ when only 2 groups for a one-way ANOVA.

The power in ANOVA, aka **correlation ratio**, is a measure between the statistical dispersion within individual categories and the dispersion across the whole population or sample.

We represent the power as the ratio as:
$$
\eta^2=\frac{\sigma_{\bar y}^2}{\sigma_y^2}
$$
where $\sigma_{\bar y}$ is the variance of the category means, $\sigma_y^2$  is the variance of all samples.



### 9. F critical value

We define the value that F needs to be in order for our experiment to report an effect by looking up the **F critical value** according to the degrees of freedom.

- Between group *d.f.*: $K-1$

- Within group *d.f.*: $N-K$
- Total *d.f.*: $N-1$

We use the number of *d.f.* to check for the critical value of F.

- If $F>F_{crit}$, we reject the null hypothesis.

We report the result as shown in example:
$$
F(\text{between d.f.}=2,\text{within d.f.}=18) = 3.79, p<0.05
$$
This concludes there is **main efffect** in the data.



### 10. Post-hoc tests

**Assumptions** for independent one-way ANOVA:

1. Independent sources.
2. Interval or ratio.
3. Normally distributed.
4. Equality of variance.

**Assumptions** for repeated measures ANOVA:

1. Interval or ratio.
2. Normally distributed.
3. **Sphericity** tested.
   - The degree of interaction between any pair of levels in the independent variable.
   - There will be different variances between the levels.

To validate the assumptions, we can do:

- Tukey test: 
  - Comparison of means.
  - Control Type I error.
- Bonferroni correction:
  - Adjust the $\alpha$ p-value by the number of tests.
  - Control Type I error.

- Shapiro-Wilk test:
  - Test for normality.

- Mauchly's test of sphericity:
  - $H_0$: variances of differences are equal.
  - Expect $p>0.05$.
  - If not, apply correction.



|           | Post-hoc                                              | ANOVA                                               | Test                                  |
| --------- | ----------------------------------------------------- | --------------------------------------------------- | ------------------------------------- |
|           | Between                                               | Repeated measures                                   | Interaction                           |
| >2 levels | Independent t-test/ Tukey test /Bonferroni correction | Dependent t-test/ Tukey test /Bonferroni correction | SPLIT one IV column                   |
| =2 levels | Interpret means                                       | Interpret means                                     | Independent t-test / Dependent t-test |

