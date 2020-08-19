#!/usr/bin/env python
# coding: utf-8

# ## Core

# In[3]:


import numpy as np
import scipy.signal

import torch
import torch.nn as nn
import tensorflow


# In[4]:


torch.cuda.is_available()


# In[5]:


def combined_shape(length, shape=None):
    if shape is None:
        return (length, )
    return (length, shape) if np. isscalar(shape) else (length, *shape)

def mlp(sizes, activation, output_activation=nn.Identity):
    layers = []
    for j in range(len(sizes)-1):
        act = activation if j < len(sizes)-2 else output_activation
        layers += [nn.Linear(sizes[j], sizes[j+1]), act()]
    return nn.Sequential(*layers)

def count_vars(module):
    return sum([np.prod(p.shape) for p in module.parameters()])


# In[6]:


class MLPActor(nn.Module):
    
    def __init__(self, obs_dim, act_dim, hidden_sizes, activation, act_limit):
        super().__init__()
        pi_sizes = [obs_dim] + list(hidden_sizes) + [act_dim]
        self.pi = mlp(pi_sizes, activation, nn.Tanh)
        self.act_limit = act_limit
        
    def forward(self, obs):
        # Return output from network scaled to action space limits.
        return self.act_limit * self.pi(obs)


# In[7]:


class MLPQFunction(nn.Module):
    
    def __init__(self, obs_dim, act_dim, hidden_sizes, activation):
        super().__init__()
        self.q = mlp([obs_dim + act_dim] + list(hidden_sizes) + [1], activation)
        
    def forward(self, obs, act):
        q = self.q(torch.cat([obs, act], dim=-1))
        return torch.squeeze(q, -1)  # ensure q in right shape


# In[8]:


class MLPActorCritic(nn.Module):
    
    def __init__(self, observation_space, action_space, hidden_sizes=(256, 256),
                activation=nn.ReLU):
        super().__init__()
        
        obs_dim = observation_space.shape[0]
        act_dim = action_space.shape[0]
        act_limit = action_space.high[0]
        
        # Build policy and value functions.
        self.pi = MLPActor(obs_dim, act_dim, hidden_sizes, activation, act_limit)
        self.q = MLPQFunction(obs_dim, act_dim, hidden_sizes, activation)
        
    def act(self, obs):
        with torch.no_grad():
            return self.pi(obs).numpy()
        


# ## Utilities

# In[9]:


import os
import os.path as osp
import time
from logx import EpochLogger


# ### EpochLogger
#     A variant of Logger tailored for tracking average values over epochs.
# 
#     Typical use case: there is some quantity which is calculated many times
#     throughout an epoch, and at the end of the epoch, you would like to 
#     report the average / std / min / max value of that quantity.
# 
#     With an EpochLogger, each time the quantity is calculated, you would
#     use 
# 
#     .. code-block:: python
# 
#         epoch_logger.store(NameOfQuantity=quantity_value)
# 
#     to load it into the EpochLogger's state. Then at the end of the epoch, you 
#     would use 
# 
#     .. code-block:: python
# 
#         epoch_logger.log_tabular(NameOfQuantity, **options)
# 
#     to record the desired values.

# In[11]:


DEFAULT_DATA_DIR = osp.join(osp.abspath(osp.dirname(os.getcwd())),'data')
FORCE_DATESTAMP = False


# In[13]:


def setup_logger_kwargs(exp_name, seed=None, data_dir=None, datestamp=False):
    datestamp = datestamp or FORCE_DATESTAMP

    # Make base path
    ymd_time = time.strftime("%Y-%m-%d_") if datestamp else ''
    relpath = ''.join([ymd_time, exp_name])
    
    if seed is not None:
        # Make a seed-specific subfolder in the experiment directory.
        if datestamp:
            hms_time = time.strftime("%Y-%m-%d_%H-%M-%S")
            subfolder = ''.join([hms_time, '-', exp_name, '_s', str(seed)])
        else:
            subfolder = ''.join([exp_name, '_s', str(seed)])
        relpath = osp.join(relpath, subfolder)
    
    data_dir = data_dir or DEFAULT_DATA_DIR
    logger_kwargs = dict(output_dir=osp.join(data_dir, relpath), 
                         exp_name=exp_name)
    return logger_kwargs


# ## DDPG

# In[9]:


from copy import deepcopy
import time

from torch.optim import Adam
import gym


# In[10]:


class ReplayBuffer:
    # FIFO experience replay buffer for DDPG agents.
    def __init__(self, obs_dim, act_dim, size):
        self.obs_buf = np.zeros(combined_shape(size, obs_dim), dtype=np.float32)
        self.obs2_buf = np.zeros(combined_shape(size, obs_dim), dtype=np.float32)
        self.act_buf = np.zeros(combined_shape(size, act_dim), dtype=np.float32)
        self.rew_buf = np.zeros(size, dtype=np.float32)
        self.ptr, self.size, self.max_size = 0, 0, size
        
    def store(self, obs, act, rew, next_obs, done):
        self.obs_buf[self.ptr] = obs
        self.obs2_buf[self.ptr] = next_obs
        self.act_buf[self.ptr] = act
        self.rew_buf[self.ptr] = rew
        self.dont_buf[self.ptr] = done
        self.ptr = (self.ptr+1) % self.max_size
        self.size = min(self.size+1, self.max_size)
        
    def sample_batch(self, batch_size=32):
        idxs = np.random.randint(0, self.size, size=batch_size)
        batch = dict(obs=self.obs_buf[idxs],
                    obs2=self.obs2_buf[idxs],
                    act=self.act_buf[idxs],
                    rew=self.rew_buf[idxs],
                    done=self.done_buf[idxs])
        return {key: torch.as_tensor(val, dtype=torch.float32) for key,val in batch.items()}
        


#     Deep Deterministic Policy Gradient (DDPG)
# 
# 
#     Args:
#         env_fn : A function which creates a copy of the environment.
#             The environment must satisfy the OpenAI Gym API.
# 
#         actor_critic: The constructor method for a PyTorch Module with an ``act`` 
#             method, a ``pi`` module, and a ``q`` module. The ``act`` method and
#             ``pi`` module should accept batches of observations as inputs,
#             and ``q`` should accept a batch of observations and a batch of 
#             actions as inputs. When called, these should return:
# 
#             ===========  ================  ======================================
#             Call         Output Shape      Description
#             ===========  ================  ======================================
#             ``act``      (batch, act_dim)  | Numpy array of actions for each 
#                                            | observation.
#             ``pi``       (batch, act_dim)  | Tensor containing actions from policy
#                                            | given observations.
#             ``q``        (batch,)          | Tensor containing the current estimate
#                                            | of Q* for the provided observations
#                                            | and actions. (Critical: make sure to
#                                            | flatten this!)
#             ===========  ================  ======================================
# 
#         ac_kwargs (dict): Any kwargs appropriate for the ActorCritic object 
#             you provided to DDPG.
# 
#         seed (int): Seed for random number generators.
# 
#         steps_per_epoch (int): Number of steps of interaction (state-action pairs) 
#             for the agent and the environment in each epoch.
# 
#         epochs (int): Number of epochs to run and train agent.
# 
#         replay_size (int): Maximum length of replay buffer.
# 
#         gamma (float): Discount factor. (Always between 0 and 1.)
# 
#         polyak (float): Interpolation factor in polyak averaging for target 
#             networks. Target networks are updated towards main networks 
#             according to:
# 
#             .. math:: \\theta_{\\text{targ}} \\leftarrow 
#                 \\rho \\theta_{\\text{targ}} + (1-\\rho) \\theta
# 
#             where :math:`\\rho` is polyak. (Always between 0 and 1, usually 
#             close to 1.)
# 
#         pi_lr (float): Learning rate for policy.
# 
#         q_lr (float): Learning rate for Q-networks.
# 
#         batch_size (int): Minibatch size for SGD.
# 
#         start_steps (int): Number of steps for uniform-random action selection,
#             before running real policy. Helps exploration.
# 
#         update_after (int): Number of env interactions to collect before
#             starting to do gradient descent updates. Ensures replay buffer
#             is full enough for useful updates.
# 
#         update_every (int): Number of env interactions that should elapse
#             between gradient descent updates. Note: Regardless of how long 
#             you wait between updates, the ratio of env steps to gradient steps 
#             is locked to 1.
# 
#         act_noise (float): Stddev for Gaussian exploration noise added to 
#             policy at training time. (At test time, no noise is added.)
# 
#         num_test_episodes (int): Number of episodes to test the deterministic
#             policy at the end of each epoch.
# 
#         max_ep_len (int): Maximum length of trajectory / episode / rollout.
# 
#         logger_kwargs (dict): Keyword args for EpochLogger.
# 
#         save_freq (int): How often (in terms of gap between epochs) to save
#             the current policy and value function.

# In[11]:


def ddpg(env_fn, actor_critic=MLPActorCritic, ac_kwargs=dict(), seed=0,
        steps_per_epoch=4000, epochs=100, replay_size=int(1e6), gamma=0.99,
        polyak=0.995, pi_lr=1e-3, q_lr=1e-3, batch_size=100, start_steps=10000,
        update_after=1000, update_every=50, act_noise=0.1, num_test_episodes=10,
        max_ep_len=1000, logger_kwargs=dict(), save_freq=1):
    
    logger = EpochLogger(**logger_kwargs)
    logger.save_config(locals())
    
    torch.manual_seed(seed)
    np.random.seed(seed)
    
    env, test_env = env_fn(), env_fn()
    obs_dim = env.observation_space.shape
    act_dim = env.action_space.shape[0]
    
    # Action limit for clamping
    act_limit = env.action_space.high[0]
    
    # Create actor-critic module and target networks
    ac = actor_critic(env.observation_space, env.action_space, **ac_kwargs)
    ac_targ = deepcopy(ac)
    
    # Freeze target networks w.r.t. optimisers
    for p in ac_targ.parameters():
        p.requires_grad = False
        
    # Experience buffer
    replay_buffer = ReplayBuffer(obs_dim=obs_dim, act_dim=act_dim, size=replay_size)
    
    # Count variables
    var_counts = tuple(count_vars(module) for module in [ac.pi, ac.q])
    logger.log('\n Number of parameters: \t pi: %d, \t q: %d\n'%var_counts)
    
    # Set up function for computing Q-loss
    def compute_loss_q(data):
        o, a, r, o2, d = data['obs'], data['act'], data['rew'], data['obs2'], data['done']
        
        q = ac.q(o ,a)
        
        # Bellman backup for Q function
        with torch.no_grad():
            q_pi_targ = ac_targ.q(o2, ac_targ.pi(o2))
            backup = r + gamma * (1 - d) * q_pi_targ
            
        # MSE loss against Bellman backup
        loss_q = ((q - backup)**2).mean()
        
        # Useful info for logging
        loss_info = dict(QVals=q.detach().numpy())
        
        return loss_q, loss_info
    
    # Set up function for computing pi loss
    def compute_loss_pi(data):
        o = data['obs']
        q_pi = ac.q(o, ac.pi(o))
        return -q_pi.mean()
    
    # Set up optimisers for policy and q-function
    pi_optimiser = Adam(ac.pi.parameters(), lr=pi_lr)
    q_optimiser = Adam(ac.q.parameters(), lr=q_lr)
    
    # Set up model saving
    logger.setup_pytorch_saver(ac)
    
    def update(data):
        # First run one gradient descent step for Q
        q_optimiser.zero_grad()
        loss_q, loss_info = compute_loss_q(data)
        loss_q.backward()
        q_optimiser.step()
        
        # Freeze Q-network
        for p in ac.q.parameters():
            p.requires_grad = False
            
        # Run one gradient descent step for pi
        pi_optimiser.zero_grad()
        loss_pi = compute_loss_pi(data)
        loss_pi.backward()
        pi_optimiser.step()
        
        # Unfreeze Q-network
        for p in ac.q.parameters():
            p.requires_grad = True
            
        # Logging
        logger.store(LossQ=loss_q.item(), LossPi=loss_pi.item(), **loss_info)
        
        # Update target networks by polyak averaging
        with torch.no_grad():
            for p, p_targ in zip(ac.parameters(), ac_targ.parameters()):
                # In-place operations
                p_targ.data.mul_(polyak)
                p_targ.data.add_((1 - polyak) * p.data)
                
    def get_action(o, noise_scale):
        a = ac.act(torch.as_tensor(o, dtype=torch.float32))
        a += noise_scale * np.random.randn(act_dim)
        return np.clip(a, -act_limit, act_limit)
    
    def test_agent():
        for j in range(num_test_episodes):
            o, d, ep_ret, ep_len = test_env.reset(), False, 0, 0
            while not (d or (ep_len == max_ep_len)):
                # Take deterministic actions at test time (noise_scale=0)
                o, r, d, _ = test_env.step(get_action(o, 0))
                ep_ret += r
                ep_len += 1
            logger.store(TestEpRet=ep_ret, TestEpLen=ep_len)
            
    # Prepare for interaction with envirnment
    total_steps = steps_per_epoch * epochs
    start_time = time.time()
    o, ep_ret, ep_len = env.reset(), 0, 0
    
    # Main loop:
    # Collect experience in env and update/log each epoch
    for t in range(total_steps):
        
        # Until start_steps have elapsed, randomly sample actions
        # from a uniform distribution for better exploration. Afterwards, 
        # use the learned policy (with some noise, via act_noise).
        if t > start_steps:
            a = get_action(o, act_noise)
        else:
            a = env.action_space.sample()
            
        # Step the env
        o2, r, d, _ = env.step(a)
        ep_ret += r
        ep_len += 1
        
        # Ignore 'done' signal if hits the time horizon
        d = False if en_len==max_ep_len else d
        
        # Store experience to replay buffer
        replay_buffer.store(o, a, r, o2, d)
        
        # Super critical, easy to overlook step: 
        # Make sure to update most recent observation!
        o = o2
        
        # End of trajectory handling
        if d or (en_len == max_ep_len):
            logger.store(EpRet=ep_ret, EpLen=ep_len)
            o, ep_ret, ep_len = env.reset(), 0, 0
            
        # Update handling
        if t >= update_after and t % update_every == 0:
            for _ in range(update_every):
                batch = replay_buffer.sample_batch(batch_size)
                update(data=batch)
                
        # End of epoch handling
        if (t+1) % steps_per_epoch == 0:
            epoch = (t+1) // steps_per_epoch
            
            # Save model
            if (epoch % save_freq == 0) or (epoch == epochs):
                logger.save_state({'env': env}, None)
                
            # Test the performance of the deterministic version agent
            test_agent()
            
            # Log info about apoch
            logger.log_tabular('Epoch', epoch)
            logger.log_tabular('EpRet', with_min_and_max=True)
            logger.log_tabular('TestEpRet', with_min_and_max=True)
            logger.log_tabular('EpLen', average_only=True)
            logger.log_tabular('TestEpLen', average_only=True)
            logger.log_tabular('TotalEnvInteracts', t)
            logger.log_tabular('QVals', with_min_and_max=True)
            logger.log_tabular('LossPi', average_only=True)
            logger.log_tabular('LossQ', average_only=True)
            logger.log_tabular('Time', time.time()-start_time)
            logger.dump_tabular()
                


# In[12]:


if __name__ == '__main__':
    import argparse
    parser = argparse.ArgumentParser()
    parser.add_argument('--env', type=str, default='HalfCheetah-v2')
    parser.add_argument('--hid', type=int, default=256)
    parser.add_argument('--l', type=int, default=2)
    parser.add_argument('--gamma', type=float, default=0.99)
    parser.add_argument('--seed', '-s', type=int, default=0)
    parser.add_argument('--epochs', type=int, default=50)
    parser.add_argument('--exp_name', type=str, default='ddpg')
    args = parser.parse_args()
    
    logger_kwargs = setup_logger_kwargs(args.exp_name, args.seed)
    
    ddpg(lambda : gym.make(args.env), actor_critic=MLPActorCritic,
         ac_kwargs=dict(hidden_sizes=[args.hid]*args.l),
         gamma=args.gamma, seed=args.seed, epochs=args.epochs,
         logger_kwargs=logger_kwargs)


# In[ ]:




