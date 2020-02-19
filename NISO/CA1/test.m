function [sa_mean, sa_std, ts_mean, ts_std, ga_mean, ga_std] = test(run, iteration)
  
cities = transpose(dlmread('att48.tsp', '', [6, 1, 53, 3]));

sa = zeros(run);
ts = zeros(run);
#ga = zeros(run);

[sa_mean, sa_std, ts_mean, ts_std] = deal(0, 0, 0, 0);

  for p = 1:run
    sa(p) = simulated_annealing(cities, iteration);
    ts(p) = tabu_search(cities, iteration);
    #ga(p) = genetic_algorithm(cities, iteration);
  end
  
  sa_mean = mean(sa)(:, 1);
  sa_std = std(sa)(:, 1);
  ts_mean = mean(ts)(:, 1);
  ts_std = std(ts)(:, 1);
  #ga_mean = mean(ga)(:, 1);
  #ga_std = std(ga)(:, 1);

end