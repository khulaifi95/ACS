# Neural Computation



## Lecture 4: Gradient Descent



### 1. Functions of multiple variables

#### 1.1 Vectors

Vectors are *arrays* of numbers. We can consider a vector as a point in space, where each element $v_i$ giving the coordinate along the i-th axis.
$$
v=(v_1,...,v_m)\in \mathbb R^m
$$



#### 1.2 Norms

Norms assign *length* to vectors. The $L^p$-norm of a vector $v\in \mathbb R^m$ is
$$
\Vert v\Vert_p=(\sum|v_i|^p)^{1/p}
$$
Where $p\in \mathbb R$ and $p\geq 1$.

- $L^2$ is a special case known as Euclidean norm, denoted $\Vert v\Vert_2$.



#### 1.3 Operations

For all $a\in\mathbb R, u= (u_1,...,u_m)\in\mathbb R^m, v=(v_1, ..., v_m)\in \mathbb R^m$:

- scalar multiplication: $au = (au_1,...,au_m)$
- vector addition: $u+v = (u_1+v_1,...,u_m+v_m)$
- dot product: $u\cdot v=\sum_{i=1}^m u_iv_i$



#### 1.4 Theorem

Geometric interpretation of dot product states that if the angle between two vectors $u,v\in\mathbb R^m$ is $\theta$, then
$$
u\cdot v = \Vert u \Vert\cdot \Vert v\Vert \cdot \cos\theta
$$
The new length $\Vert u\Vert\cdot \cos\theta$ is exactly the projection of $u$ onto $v$.



| <img src="/Users/kevinxu95/ACS/NC/Notes/NC_Lecture 4.assets/Screenshot 2020-05-07 at 16.54.35.png" style="zoom:80%;" /> |
| :----------------------------------------------------------: |
|     **Fig 4.1** Geometric interpretation of dot product      |



### 2. Partial derivatives and the chain rule

#### 2.1 Partial derivative

The partial derivative of a function $f(x_1,...,x_m)$ in the direction of variable $x_i$ at the point $u=(u_1,...,u_m)$ is
$$
\frac{\part f}{\part x_i}(u_1,...,u_m) = \lim_{h\rightarrow0}\frac{f(u_1,...,u_i+ h, ..., u_m) - f(u_1,...,u_m)}{h}
$$
where intuitively all variables except $x_i$ are fixed as constraints.



#### 2.2 Geometric interpretation

As in **Fig 4.2**, for the function $f(x_1, x_2)$:

-  $\frac {\part f}{\part x_1}$ is the rate of change of $f$ along dimension $x_1$.

-  $\frac {\part f}{\part x_2}$ is the rate of change of $f$ along dimension $x_2$.



| <img src="/Users/kevinxu95/ACS/NC/Notes/NC_Lecture 4.assets/Screenshot 2020-05-07 at 17.11.02.png" style="zoom:50%;" /> |
| :----------------------------------------------------------: |
|        **Fig 4.2** Partial derivative in a 2-D space         |



### 3. Gradients

#### 3.1 Definition

The gradient of a function $f(x_1, ..., x_m)$ is 
$$
\nabla f \triangleq (\frac{\part f}{\part x_1},...,\frac{\part f}{\part x_m})
$$

- Example: $f(x_1, x_2)= 2x_1^2+x_2^2+3x_1x_2+4$

$$
\nabla f(x_1, x_2)=(\frac {\part f}{\part x_1}, \frac{\part f}{\part x_2}) 
=(4x_1+3x_2, 2x_2+3x_1)
$$



#### 3.2 Visualisation of the gradient

The gradient is a vector-valued function that maps vectors to vectors. i.e.
$$
\text {if} \ f: \mathbb R^m \rightarrow \mathbb R, \text{then} \ \nabla f: \mathbb R^m \rightarrow \mathbb R^m
$$


| <img src="/Users/kevinxu95/ACS/NC/Notes/NC_Lecture 4.assets/Screenshot 2020-05-07 at 17.22.18.png" style="zoom:50%;" /> |
| :----------------------------------------------------------: |
|      **Fig 4.3** Visualisation of gradient in 2-D space      |



#### 3.3 Special case of chain rule

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
\begin{align} \frac {\part h}{\part x_1} = \frac {\part f}{\part x_1} &= \frac {\part f}{\part u_1} \cdot \frac {\part u_1}{\part x_1} + \frac {\part f}{\part u_2} \cdot \frac {\part u_2}{\part x_1} \\&= 2u_1u_2\cdot a + u_1^2\cdot x_2 \\ &= u_1(2au_2+u_1x_2)\\ &= ax_1+bx_2)(2ax_1x_2+(ax_1+bx_2)x_2) \\ &= (ax_1+bx_2)x_2(3ax_1+bx_2) \end{align}
$$
​		Similarly, the chain rule gives
$$
\begin{align}\frac {\part h}{\part x_2} = \frac {\part f}{\part x_2} &= \frac {\part f}{\part u_1} \cdot \frac {\part u_1}{\part x_2} + \frac {\part f}{\part u_2} \cdot \frac {\part u_2}{\part x_2} \\ &= 2u_1u_2\cdot b + u_1^2\cdot x_1 \\ &= u_1(2bu_2+u_1x_1)\\&= (ax_1+bx_2)(2bx_1x_2+(ax_1+bx_2)x_1) \\ &= (ax_1+bx_2)x_1(3bx_2+ax_1) \end{align}
$$



### 4. Gradient descent

#### 4.1 Directional derivative

Given a function $f:\mathbb R^m \rightarrow \mathbb R$ and a vector $v=(v_1,...,v_m),\Vert v\Vert=1$, the directional derivative of $f$ in $x=(x_1,...,x_m)$ along the vector $v$ is
$$
\nabla_vf(x)\triangleq \lim_{\alpha\rightarrow0}\frac {f(x+\alpha v)-f(x)}{\alpha} \\= \lim_{\alpha\rightarrow0}\frac{f(x_1+\alpha v_1,...,x_m+\alpha v_M)-f(x_1,...,x_m)}{\alpha}
$$



#### 4.2 Computing directional derivative

**Theorem**: If we know the gradient $\nabla f$, then we can compute the derivative in any direction $v$ given by
$$
\nabla_vf(x)=\nabla f(x)\cdot v
$$
**Proof**:

- Define the function $h(\alpha) = f(u_1,...,u_m):\mathbb R \rightarrow \mathbb R$, where $u_i = x_i + \alpha v_i$, $\forall i\in\{1,...,m\}$

$$
\begin{align}\nabla_vf(x) &=\lim_{\alpha\rightarrow0}\frac{f(x+\alpha v)-f(x)}{\alpha} \\ &= \lim_{\alpha\rightarrow0}\frac{h(0+\alpha)-h(0)}{\alpha} \\&= h'(0) \end{align}
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



#### 4.3 Steepest ascent

The vector $v$ along which $f$ has steepest ascent is 
$$
\begin{align}\arg\max_{v,\Vert v\Vert=1} \nabla_vf(x) &= \arg\max_{v,\Vert v\Vert=1} \nabla f(x)\cdot v \\ &= \arg\max_{v,\Vert v\Vert=1}\Vert \nabla f(x)\Vert \Vert v\Vert \cdot \cos\theta \\ &= \arg\max_{v,\Vert v\Vert=1}\Vert \nabla f(x)\Vert \cdot \cos\theta \end{align}
$$
**Theorem**: 

The vector $v$ which gives the steepest ascent is the vector that has angle $\theta=0$ to $\nabla f$, i.e. the vector which points in **the same direction** as $\nabla f$.



#### 4.4 Method of gradient descent

```pseudocode
Input: cost function J, learning rate e > 0

x <- some initial point in m-dimensional space
while termination condition not met {
		x <- x - e . ∆J(x)
}
```



| <img src="/Users/kevinxu95/ACS/NC/Notes/NC_Lecture 4.assets/Screenshot 2020-05-07 at 19.06.05.png" style="zoom:50%;" /> |
| :----------------------------------------------------------: |
|        **Fig 4.4** Steepest ascent of gradient points        |

