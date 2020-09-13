# Curling with Deep Reinforcement Learning



## Abstract .2k

The problem that this project aims to solve is one of the task completion for intelligent agents in a tabletop environment. In the context of a practical setting, the agent learns to play curling on the surface with an improved control policy when observing the environment. Works in the project is intended to explore the application of various theoretical approaches in the field of reinforcement learning. The method proposed here takes in the rendered observation of environment directly as pixel input, and learn through deep neural networks end-to-end with returned policies. To simulate physics and conduct the experiments, we uses PyBullet engine as an simulated environment with models of objects. Curling game play involves continuous control over the agent. The algorithm used for the task needs to update agent's deterministic policy for the given scenarios to improve convergence. During the training process, the agent is enabled to learn from past experiences of play to update current policies using a memory buffer. We propose a model with an *actor-critic* architecture, integrating the deep deterministic policy gradient method with a memory layer. We show that our model can be successfully applied on the curling game play task and other classic reinforcement learning tasks. Comparing with existing state-of-the-art methods, the result indicates the improvement in stability and generalisation due to the proposed modification.



## Introduction 1k

control

The problem of learning often involves the interaction with the environment. It is natural to consider both the agent and the environment where it locates when control problem is to be solved. Generally, we have three paradigms for the learning problem: supervised learning, unsupervised learning and reinforcement learning. In supervised learning, we judge the performance of learner's predictions by comparing it with given results of known samples, where the feedback is often in the form of an optimisation term, or a similarity ratio. Unsupervised learning requires no prior knowledge of the labels of data, but only exploit the internal relationship between the data. On the whole, for both approaches, the data collected for prediction tasks are assumed to be identically and independently distributed. However, when is faced with the control task, both paradigms cannot fit properly with sequential decision making.

Reinforcement learning can help 

Policy - action against state

Curling game play

The task of curling



