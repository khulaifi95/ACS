# RL Project



## Week 3



> This memo serves as a reflection on the first half of the final project on reinforcement learning.



The goal of this project is, as proposed initially, to study deep reinforcement larning methods with application. Reinforcement learning with deep neural network solves sequential decision problems with an end-to-end scheme, taking inputs directly without hand-crafted features, returning 



In 2015, The Atari game playing paper by Mnih proposed the first deep RL framework - DQN, integrating convolutional layers with traditional Q-learning algorithm. It operates directly on images of past frames on the screen to generate feature representations of the current state. Then the action-value function is updated online 



1. Action value function

To solve sequential decision problems we can learn estimates for the optimal value of each action, defined as the expected sum of future rewards when taking that action and following the optimal policy thereafter.

Under a given policy π, the true value of an action *a* in a state *s* is
$$
Q_\pi(s,a)=\mathbb E[R_1+\gamma R_2+...|S_0=s,A_0=a,\pi]
$$


1. Off-policy Q-learning
2. Deep Q-network
3. Target Q-network
4. Double DQN
5. Prioritized experience replay
6. Dueling architechture

