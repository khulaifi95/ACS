function [sa_mean, sa_std, ts_mean, ts_std, sp_mean, sp_std] = test(run, iteration)
  
cities = transpose(dlmread('att48.tsp', '', [6, 1, 53, 3]));
sa_mean = zeros(p);
sa_std = zeros(q);
  
  for p = 1:run
    sa = zeros(q);
    ts = zeros(q);
    sp = zeros(q);
    for q = 1:iteration
      sa(q) = simulated_annealing(cities);
      ts(q) = tabu_search(cities);
      sp(q) = simple_hill_climbing_two_opt(cities);
    end
    sa_mean(p) = mean(sa);
    sa_std(p) = std(sa);
    ts_mean(p) = mean(ts);
    ts_std(p) = std(ts);
    sp_mean(p) = mean(sp);
    sp_std(p) = std(sp);
  end
end