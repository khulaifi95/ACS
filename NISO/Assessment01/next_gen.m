function next_generation = next_gen(num_pop, children, elite_size)

next_generation = zeros(num_pop, columns(children));
next_generation(1:elite_size, :) = children(1:elite_size, :);

selected_children = children(randperm(length(children)), :);
next_generation(elite_size+1:end, :) = selected_children(1:num_pop-elite_size, :);

end