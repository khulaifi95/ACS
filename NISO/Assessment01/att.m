# Input the city dataset and validate the optimal distance
cities = transpose(dlmread('att48.tsp', '', [6, 1, 53, 3]));
optimalSolution = dlmread('att48.opt.tour', '', [5, 0, 52, 1]);
optimalInput = cities(:,optimalSolution);
shortest = distance(input)