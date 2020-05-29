

# Machine Learning



## Lecture 1: Regression



### 1. Linear regression

- Consider a problem with one one independent variable x and one dependent variable y.
- Dataset $\mathcal D = \{(x_0,y_0),..., (x_{N-1}, y_{N-1})\}=\{(x_i,y_i\}_{i=0}^{N-1}$.
- Model the relationship between x and y as a mathematical function $f(\mathbf w, x)$.
- Predict $y_i\approx f(\mathbf w, x_i)$ with unknown parameters $\mathbf w$.
- Measurements of *y* subject to **noise**: $y_i = f(\mathbf w, x_i)+\epsilon$.

**Goal:** find **w** that allows *f* to predict *y*.



### 2. Least squares loss

- We consider regression as an optimisation problem.
- Define a *loss* function that measures the difference between model and data.
- The objective is to find the value of **w** (denoted as **w***) that minimises the loss function:

$$
\mathbf w^*=\arg\min_w\mathcal L(\mathbf w)
$$

- Construct the residual error for each data point in $\mathcal D$:

$$
r_i(\mathbf w)=y_i-f(\mathbf w,x_i)
$$

- The **least squares error** (LSE) loss function is then defined as:

$$
\mathcal L_{LSE}(\mathbf w) = \sum_{i=0}^{N-1}r_i^2=\mathbf r^T\mathbf r
$$



### 3. Linear models

For simplicity, we first restrict to linear models:
$$
f(\mathbf w, x) = w_0\phi_0(x)+\cdots + w_{M-1}\phi_{M-1}(x)=\sum_{i=0}^{M-1}w_i\phi_i(x)
$$

- A *linear combination* of **basis functions** $\{\phi_i(x)\}_{i=0}^{M-1}$ weighted by the free parameters $\{w_i\}_{i=0}^{M-1}$.
- Common choice of basis is the polynomials $\{x^i\}_{i=0}^{M-1}$.
- In matrix form:

$$
\mathbf {f(w)=\Phi w}, \ \text{where}\ \phi_{ij} = \phi_j(x_i).
$$

- The residual can be written as:

$$
\mathbf {r=y-\Phi w}
$$

- The LSE loss becomes:

$$
\mathcal L_{LSE}(\mathbf w) = \mathbf {(y-\Phi w)}^T\mathbf {(y-\Phi w)}
$$

​		which has no upper bound but has a lower bound at 0.

- We find $\mathbf w = \mathbf w^*$ that minimises $\mathcal L_{LSE}(\mathbf w)$ by differentiating *w.r.t.* **w**:

$$
\frac{\part \mathcal L_{LSE}}{\part w_k} = \sum_l\frac{\part \mathcal L_{LSE}}{\part r_l}\cdot \frac{\part r_l}{\part w_k} \\ = -\sum_l2r_l\cdot \phi_{lk}
$$

- In matrix form:

$$
\frac{\part \mathcal L_{LSE}(\mathbf w)}{\part \mathbf w} =-2\Phi^T(\mathbf {y-\Phi w})
$$

​		where $\Phi$ is a column vector transposed in orientation.

- Set to zero to find the minimum:

$$
\mathbf\Phi^T\mathbf y-\mathbf\Phi^T\mathbf\Phi \mathbf w=0
$$

This result is known as the **normal equations** and is a set of simultaneous linear equations that we can solve for **w***.

