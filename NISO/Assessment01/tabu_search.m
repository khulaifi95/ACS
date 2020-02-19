function [best_distance best_tour] = tabu_search(inputcities, iter)
  
num_cities = length(inputcities);

best_tour = randperm(num_cities);
best_cities_coordinates = inputcities(:,best_tour);
best_distance = distance(best_cities_coordinates);

num_tabu = 15; # 30, 
tabu_list = zeros(num_tabu, 2);

num_iteration = iter; # 3000
count = 0;

while (count < num_iteration)
  i = randi([1, num_cities-1]);
  k = randi([i+1, num_cities]);
  new_tour = twoopt(best_tour, i, k);
  
  if (ismember([i, k], tabu_list,'rows') == false)
    new_cities_coordinates = inputcities(:,new_tour);
    new_distance = distance(new_cities_coordinates);

    if (new_distance < best_distance)
      best_distance = new_distance;
      best_tour = new_tour;
      tabu_list(mod(count, num_tabu)+1, :) = [i, k];
      #plotcities(new_cities_coordinates);
    end
  end
  count += 1;
end
end