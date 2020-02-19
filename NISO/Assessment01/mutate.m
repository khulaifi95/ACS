function mutation = mutate(offspring, p)

for swapped = (1 : length(offspring))
  if (p > rand)
    swap_with = randi(length(offspring));
    
    city_1 = offspring(swapped);
    city_2 = offspring(swap_with);
    
    offspring(swapped) = city_2;
    offspring(swap_with) = city_1;
  end
end

mutation = offspring;
end
