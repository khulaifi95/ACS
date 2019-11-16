import numpy as np
q=np.zeros
M=np.cos
E=np.sin
j=np.round
g=np.pi
N=np.angle
B=np.abs
a=np.arctan
import math
import matplotlib.pyplot as plt
from random import shuffle
from pylatexenc.latex2text import LatexNodes2Text
from qiskit import Aer
r=Aer.get_backend
from qiskit import QuantumRegister,ClassicalRegister,QuantumCircuit,execute
from qiskit.visualization import plot_bloch_vector
def bloch_vec(qc):
 backend=r('statevector_simulator')
 ket=execute(qc,backend).result().get_statevector()
 if ket[0]!=0:
  theta=2*a(B(ket[1]/ket[0]))
  phi=N(ket[1]/ket[0])
 else:
  theta=g
  phi=0
 bloch_vector=j([E(theta)*M(phi),E(theta)*E(phi),M(theta)],5)
 return bloch_vector
def check_answer(output,sol):
 if(output==sol).all():
  return True
 else:
  return False
def check(ex,sol,hint=''):
 output=ex
 if(type(sol)==QuantumCircuit):
  if output==sol:
   print("\033[1;30;42m Correct! Well done! \n")
   score=1
  else:
   print("\033[1;30;41m Incorrect, try again! \n")
   score=0
  return score
 if(type(sol)==dict):
  compare_dict=q(len(sol))
  m=0
  for key in sol:
   if key in ex:
    if abs(sol[key]-ex[key])<=100:
     compare_dict[m]=True
   m+=1
  if compare_dict.all()==True:
   print("\033[1;30;42m Correct! Well done! \n")
   score=1
  else:
   print("\033[1;30;41m Incorrect, try again! \n")
   score=0
  return score
 check=q(len(sol))
 for i in range(len(sol)):
  check[i]=check_answer(output[i],sol[i])
 if check.all()==True:
  print("\033[1;30;42m Correct! Well done! \n")
  score=1
 else:
  print("\033[1;30;41m Incorrect, try again! \n")
  score=0
  print(hint)
 return score
def run_and_check(ex,run_sol):
 sol,nr=run_sol
 score[nr-1]=check(ex,sol)
 totalscore=int(sum(score))
 print('\033[1;30;47m Your score: ',totalscore,"/8")
def qc_check(circ,sol):
 if type(sol)is QuantumCircuit:
  if circ==sol:
   print("state preparation correct")
  else:
   print("state preparation wrong")
def intro_sol1():
 qc=QuantumCircuit(1)
 qc.x(0)
 bloch_vector=bloch_vec(qc)
 return bloch_vector,1
def intro_sol2():
 qc=QuantumCircuit(1)
 qc.h(0)
 bloch_vector=bloch_vec(qc)
 return bloch_vector,2
def intro_sol3():
 qc=QuantumCircuit(1)
 qc.x(0)
 qc.h(0)
 bloch_vector=bloch_vec(qc)
 return bloch_vector,3
def intro_sol4():
 qc=QuantumCircuit(1)
 qc.h(0)
 qc.sdg(0)
 bloch_vector=bloch_vec(qc)
 return bloch_vector,4
def intro_sol5():
 qc=QuantumCircuit(2,2)
 qc.h(0)
 qc.cx(0,1)
 qc.measure(0,0)
 qc.measure(1,1)
 backend=r('qasm_simulator')
 counts=execute(qc,backend,shots=1000).result().get_counts()
 return counts,5
def intro_sol6():
 qc=QuantumCircuit(2,2)
 qc.h(0)
 qc.cx(0,1)
 qc.z(1)
 qc.x(1)
 backend=r('statevector_simulator')
 vec=execute(qc,backend).result().get_statevector()
 return vec,6
def intro_sol7():
 qc=QuantumCircuit(2,2)
 qc.rx(g/3,1)
 qc.x(0)
 qc.measure(0,0)
 qc.measure(1,1)
 backend=r('qasm_simulator')
 result=execute(qc,backend,shots=1000).result()
 counts=result.get_counts()
 return counts,7
def intro_sol8():
 qc=QuantumCircuit(3)
 qc.h(0)
 qc.cx(0,1)
 qc.cx(0,2)
 backend=r('statevector_simulator')
 vec=execute(qc,backend).result().get_statevector()
 return vec,8
def check_mcanswer(answer,answers):
 if answers[answer_dict[answer]][1]==0:
  print("\033[1;30;42m Correct! Well done!")
  score=1
 else:
  print("\033[1;30;41m Incorrect, try again!")
  score=0
 return score
def ltx(latexstring):
 return LatexNodes2Text().latex_to_text(latexstring)
def create_question(answernr):
 answers=[[ltx(answernr[0]),0],[ltx(answernr[1]),1],[ltx(answernr[2]),2],[ltx(answernr[3]),3],[ltx(answernr[4]),4]]
 shuffle(answers)
 print(' a:',answers[0][0],'\n b:',answers[1][0],'\n c:',answers[2][0],'\n d:',answers[3][0],'\n e:',answers[4][0])
 return answers
def question_and_check(answernr):
 all_answers=create_question(answernr)
 answer=input()
 nr=answernr[5]
 score[nr-1]=check_mcanswer(answer,all_answers)
 totalscore=int(sum(score))
 print('\033[1;30;47m Your score: ',totalscore,"/10")
def combine_alg_and_appl(answernr,algos):
 checks=q(5)
 all_answers=create_question(answernr)
 for i in range(5):
  print(algos[i])
  answer=input()
  if all_answers[answer_dict[answer]][1]==i:
   print("Correct!")
   checks[i]=1
  else:
   print("Incorrect!")
   checks[i]=0
 nr=answernr[5]
 score[nr-1]=checks.all()
 if checks.all()==True:
  print("\033[1;30;42m All answers matched correctly! Well done!")
 else:
  print("\033[1;30;41m Not all answers matched correctly, try again!")
 totalscore=int(sum(score))
 print('\033[1;30;47m Your score: ',totalscore,"/10")
global score
score=q(10)
answer_dict={'a':0,'b':1,'c':2,'d':3,'e':4}
answers1=[r'$2^10$',r'$20$',r'$10$',r'$10^2$',r'$10^10$',9]
app=[r'integer factorization',r'database search',r'approximation of measurement probabilities',r'find the ground state energy of a molecule',r'combinatorial optimization',10]
algos=[r"Shor's algorithm",r"Grover's algorithm",r'amplitude estimation',r'Variational Quantum Eigensolver',r'QAOA']
# Created by pyminifier (https://github.com/liftoff/pyminifier)

