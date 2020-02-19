function [best_distance, best_tour, ranked_distance, ranked_tour] = eval_pop(population, inputcities)

pop_distance = zeros(1, length(inputcities));
  
  for i = (1: rows(population))
    city_coordinates = inputcities(:, population(i,:));
    pop_distance(i) = distance(city_coordinates);
  end
  
[ranked_distance, idx] = sort(pop_distance, 'descend');
best_distance = ranked_distance(1);
best_tour = population(idx(1), :);
  
  for j = (1: rows(population))
    ranked_tour(i,:) = population(idx(i), :);
  end
  
end
