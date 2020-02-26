function parents = select_parents(ranked_tour, ranked_distance, pop_size)

parents = [];

while (columns(parents) < pop_size)
  i = randi(pop_size,1);
  j = randi(pop_size,1);

  if (ranked_distance(:, i) < ranked_distance(:, j))
    parents(:, end+1) = ranked_tour(:, i);
  else
    parents(:, end+1) = ranked_tour(:, j);  
  end
end
end
