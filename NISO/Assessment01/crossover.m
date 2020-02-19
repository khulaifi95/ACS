function [child_1, child_2] = crossover(parent_1, parent_2)

point_a = randi(length(parent_1));
point_b = randi(length(parent_1));

child_1 = horzcat(parent_2(1:point_a-1), parent_1(point_a:point_b), parent_2(point_b+1:end));
child_2 = horzcat(parent_1(1:point_a-1), parent_2(point_a:point_b), parent_1(point_b+1:end));

end
