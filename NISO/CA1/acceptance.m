function prob = acceptance(new, best, temp)
  if new < best
    prob = 1;
  else 
    prob = exp((best-new)/temp);
  endif
endfunction
