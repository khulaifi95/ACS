function new_temp = temperature(k, initial)
  new_temp = 0.95^k*initial;
endfunction



### T_(k+1)= α T_k [Dosso,Oldenburg, 1991]
### T_(k+1)= T_0/log⁡(k+1) [Geman and Geman]
### T_k= e^(-αk).T_0
### T_k=(T_1/T_0 ) ^k ,(T_1/T_(0 ) )=0.9 [Kirptrick, 1983]