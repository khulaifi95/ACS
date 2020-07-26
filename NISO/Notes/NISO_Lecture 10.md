# Nature-inspired Search & Optimisation



## Lecture 10 : Swarm Intelligence and Particle Swarm Optimisation



### 1. Swarm and swarm intelligence

#### 1.1 Swarm intelligence

Swarm intelligence is the collective behaviour of decentralised self-organised natural/artificial systems.

- Self-organised system: An initially disorderd system, but **global phenomena** such as order or coordination will arise out of the **local interactions** between its components. (**Emergence**)
- A swarm intelligence system typically consists of simple agents interacting with each other and their environment following simple rules.



#### 1.2 Why animals evolved swarm?

Animals evolve swarm as outcomes of natural selection.

Some possible explanations to the evolution of swarm behaviour:

- Better defence, e.g. early warning, confuse predator.
- Better foraging efficiency.
- Saving energy.

We can learn from animal swarm for designing computer systems and algorithms.



#### 1.3 Swarm in computer science

In computer science, Swarm Intelligence is a **multi-agent system** that has **self-organised behaviour** that shows some **intelligence**.

- Swarm intelligence algorithms:
  - Ant colony optimisation: Solve combinatorial optimisation problems, inspired by ants' collective behaviour.
  - Particle swarm optimisation.
- Swarm robotics: Design coordination of multirobot systems which consist of large numbers of mostly simple physical robots.
  - Aims to promote **scalability** going beyond distributed system, e.g. control a pack of robots.
  - Swarmanoid: Humanoid robotic swarms.



### 2. BOID model

BOID was invented in 1986 by Craig Reynolds to **simulate coordinated animal motion**.

- Inspired by bird flocks and fish schools.
- Each individual of the model is called a boid.
- Used in many films.



| <img src="NISO_Lecture 10.assets/Screenshot 2020-07-26 at 14.30.32.png" style="zoom:50%;" /> | <img src="NISO_Lecture 10.assets/Screenshot 2020-07-26 at 14.29.43.png" style="zoom:50%;" /> | <img src="NISO_Lecture 10.assets/Screenshot 2020-07-26 at 14.29.54.png" style="zoom:50%;" /> |
| :----------------------------------------------------------: | :----------------------------------------------------------: | :----------------------------------------------------------: |
|                        **Separation**                        |                        **Alignment**                         |                         **Cohesion**                         |
|                                                              |                 **Fig 10.1** BOID behaviours                 |                                                              |



### 3. Particle swarm optimisation

#### 3.1 Particle swarm optimiser(PSO)

Particle swarm optimiser was invented by Kennedy and Eberhart in 1995.

- Inspired by BIOD.
- Simple rules for searching global optima.
- Primarily for real-valued optimisation problems.
- Simple but sometimes better than EA.



#### 3.2 Details of PSO

PSO can be seen as a swarm of particles flying in the search space to find the optimal solution.

- Each particle is a solution to the problem, represented by position $X^t_i$ in the search space.
- Each particle also has a velocity $V^{t+1}_i$, used to update its position.

The variation operator consists of only two equations:
$$
V^{t+1}_i = \omega V^t_i+c_1r_1(P_i-X^t_i)+c_2r_2(P_g-X^t_i) \\ X^{t+1}_i=X^t_i+V^{t+1}_i
$$
where:

- $P_i$ is the best previous position of the *i*-th particle.
- $P_g$ is the global best position of the swarm.
- $\omega\in(0,1]$ is the inertia weight.
- $c_1,c_2$ are learning rates/factors.
- $r_1,r_2\in(0,1)$ are random numbers.



#### 3.3 PSO algorithm

**Generic Particle Swarm Optimisation Algorithm**:

> **Initiation**: Initial population $X^0$ of $M$ particles. 
>
> $P^0=X^0$.
>
> terminationflag = false, *t* = 0.
>
> Evaluate the fitness of each particle in $X^0$.
>
> **While** (terminationflag != true)
>
> ​		Select the best particle $P_g$ from $X^t$ based on their fitness.
>
> ​		**For** $i=1\ \text{to} \ M$ do
>
> ​				**If** $X^t_i$ is better than $P_i$ **then**
>
> ​						$P_i = X^t_i$;  // update $P_i$
>
> ​				Calculate $V^{t+1}_i=\omega V^t_i+c_1r_1(P_i-X^t_i)+c_2r_2(P_g-X^t_i)$;
>
> ​				Update $X^{t+1}_i=X^t_i+V^{t+1}_i$;
>
> ​		**End for**
>
> ​		Evaluate the fitness of each particle in $X^{t+1}$.
>
> ​		$t=t+1$
>
> ​		**If** termination criterion met **then**
>
> ​				terminationflag = true;
>
> **Output** $P_g$.



#### 3.4 PSO update illustration

For the *i*-th particle $X_i$, its new search direction is determined by:

- Its current direction $V^k_i$.
- The best position visited in its autobiographical memory $P_i$.
- The publicised knowledge i.e. the current best solution $P_g$ found by the population.



| <img src="NISO_Lecture 10.assets/Screenshot 2020-07-26 at 14.56.41.png" style="zoom:67%;" /> |
| :----------------------------------------------------------: |
|     **Fig 10.2** Fiding particles' next search direction     |



#### 3.5 Conclusion

PSO is a *plug-and-play* optimisation algorithm with only 3 parameters that are not very sensitive.

