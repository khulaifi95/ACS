function population = init_pop(pop_size, num_cities)

population = zeros(pop_size, num_cities);

for i = (1:pop_size)
  population(i,:) = randperm(num_cities);
end
end
