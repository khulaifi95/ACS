function [ranked_tour, ranked_distance] = eval_pop(population, inputcities)

pop_distance = zeros(1, columns(population));
city_coordinates = [];
  
  for i = (1: columns(population))
    city_coordinates = inputcities(:, population(:,i));
    pop_distance(i) = distance(city_coordinates);
  end
  
[ranked_distance, idx] = sort(pop_distance, 'descend');
  
  for j = (1: columns(population))
    ranked_tour(:, j) = population(:, idx(j));
  end
  
end
