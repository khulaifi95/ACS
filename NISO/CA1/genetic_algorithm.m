% Run first_import before this!

function [best_distance best_tour] = genetic_algorithm(inputcities, iter)

num_cities = length(inputcities);

pop_size = 100; %100
p_crossover = 0.98;
p_mutation = 2.0/num_cities; % 1.0/
elite_size = 20; % 20

population = init_pop(pop_size, num_cities);
[ranked_tour, ranked_distance] = eval_pop(population, inputcities);

max_gens = iter;
count = 0;

while (count < max_gens)
  parents = select_parents(ranked_tour, ranked_distance, pop_size); 
  children = []; 
  selected = 0;
  while (selected < pop_size)
    parent_1 = parents(:, randi(columns(parents)));
    parent_2 = parents(:, randi(columns(parents)));
    child = crossover(parent_1, parent_2, p_crossover);
    child = mutate(child, p_mutation);
    children(:, end+1) = child;
    selected += 1;
  end  
  population = next_gen(pop_size, parents, children, elite_size, inputcities);
  count += 1;

end

[ranked_tour, ranked_distance] = eval_pop(population, inputcities);
best_distance = ranked_distance(1);
best_tour = ranked_tour(1);
end
