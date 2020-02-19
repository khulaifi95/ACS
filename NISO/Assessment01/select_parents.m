function parents = select_parents(ranked_tour, ranked_distance, pop_size)

parents = zeros(1, columns(ranked_tour));

sum = sum(ranked_distance);
cum_sum = cumsum(ranked_distance);
cum_pct = 100 * cum_sum / sum;

  for (i = (1 : pop_size))
    if (cum_pct(i) > rand)
        parents(end+1,:) = ranked_tour(i);
    end
  end    
    
end
