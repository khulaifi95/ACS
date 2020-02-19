function next_generation = next_gen(num_pop, parents, children, elite_size)

next_generation = zeros(rows(children), num_pop);
next_generation(:, 1:elite_size) = parents(:, 1:elite_size);

selected_children = children(:, randperm(columns(children)));
next_generation(:, elite_size+1:end) = selected_children(:, 1:num_pop-elite_size);

end