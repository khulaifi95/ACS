function child = crossover(parent_1, parent_2, p)

if (p > rand)
  child = zeros(rows(parent_1),1);
  
  point_a = randi(rows(parent_1)-2);
  point_b = randi([point_a+1,rows(parent_1)]);

  child(point_a:point_b) = parent_1(point_a:point_b);
  parent_2(point_a:point_b) = [];
  child([1:(point_a-1), (point_b+1):end]) = flip(parent_2);
else 
  child = parent_1;
end

end
