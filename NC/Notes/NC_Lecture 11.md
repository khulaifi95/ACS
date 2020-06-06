# Neural Computation



## Lecture 11: Regularisation



### 1. Model capacity

- Informally, is the model's capacity to fit a wide variety of functions.

- In statistical learning theory, model capacity is quantified by **VC-dimension**, i.e. the largest training set for which the model can classify the labels arbitrary into two classes.

- By the **universal approximation theorem**, neural networks can have very high capacity.



### 2. Generalisation error

#### 2.1 Underfitting and overfitting

- Underfitting: too high training error.

- Overfitting: too large gap between training error and test error.



| <img src="/Users/kevinxu95/ACS/NC/Notes/Neural_Computation.assets/Screenshot 2020-05-23 at 14.39.46.png" style="zoom:80%;" /> |
| :----------------------------------------------------------: |
|    **Fig 11.1** Model capacity vs training and test error    |



#### 2.2 Model capacity vs error

- Training and test error behave differently.
- Training error often decreases with capacity.
- Test error can increase beyond a certain capacity.
- Capacity is **optimal** when model matches data generating process.



### 3. Regularisation techniques

#### 3.1 Data augmentation

Many data sets can be augmented via transformations.

For a data set of images:

- mirroring
- translation
- scaling
- rotation
- noise



#### 3.2 Early stopping

To apply regularisation, first split data into training, validation and test sets.

1. Train model on training set, evaluate with fixed intervals on validation set.
2. Stop training when validation error has significantly increased.
3. Return model parameters when validation loss was the lowest.



### 4. Dropout

#### 4.1 Parameter norm penalties

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



#### 4.2 Ensemble methods

Combining different models often reduces generalisation error.

**Idea:** train $k$ neural networks on $k$ subsets of the training data. Output the *average* of the networks.

**Disadvantages:**

1. Usually requires more training data.
2. k times increase in training time.
3. Only feasible for small $k$.



#### 4.3 Dropout in neural network

The underlying idea of using “dropout” is to **approximate** the ensemble methods on a large number of neural networks to reduce generalisation error.

- In each mini-batch, *deactivate* some randomly selected activation units.
- Each selection of units corresponds to a **subnetwork**. 
  - with $n$ input and hidden layer activation units, there are $2^n$ subnetworks.
- The subnetworks **share the weights**.

- No dropout when testing and using.



#### 4.4 Implementation of dropout

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



#### 4.5 Backpropagation with dropout

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

