function [best_distance best_tour] = simulated_annealing(inputcities, iter)

num_cities = length(inputcities);

best_tour = randperm(num_cities);
best_cities_coordinates = inputcities(:,best_tour);
best_distance = distance(best_cities_coordinates);

initial_temp = 10000; # 1e2, 1e3, 1e4, 1e5, 3000, 6000

num_iteration = iter; # 3000, 100
count = 0;

while (count < num_iteration)
  i = randi([1, num_cities-1]);
  k = randi([i+1, num_cities]);
  new_tour = twoopt(best_tour, i, k);
  new_cities_coordinates = inputcities(:,new_tour);
  new_distance = distance(new_cities_coordinates);
            
  temp = temperature(count+1, initial_temp);
  if acceptance(new_distance, best_distance, temp) > rand # 0.5
    best_distance = new_distance;
    best_tour = new_tour;
    
    #plotcities(new_cities_coordinates);
  end
  count += 1;
end
end


