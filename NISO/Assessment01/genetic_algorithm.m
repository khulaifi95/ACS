function [best_distance best_tour] = genetic_algorithm(inputcities, iter)

num_cities = length(inputcities);

pop_size = 100;
max_gens = 100;
p_mutation = 1.0/num_cities;
elite_size = 20;

population = init_pop(pop_size, num_cities);
[best_distance, best_tour, ranked_distance, ranked_tour] = eval_pop(population, inputcities);

num_iteration = iter;
count = 0;

while (count < num_iteration)
  parents = select_parents(ranked_tour, ranked_distance, pop_size);
  children = zeros(1, num_cities);
  for i = (1:rows(parents))
    for j = (1:rows(parents))
        parent_1 = parents(i, :);
        parent_2 = parents(j, :);
        [child_1, child_2] = crossover(parent_1, parent_2);
        children(end+1, :) = mutate(child_1, p_mutation);
        children(end+1, :) = mutate(child_2, p_mutation);
      end  
    end  
  end
  
  children(1,:) = [];
  population = next_gen(pop_size, children, elite_size);
   
[best_distance, best_tour] = eval_pop(population, pop_size, inputcities); 
end