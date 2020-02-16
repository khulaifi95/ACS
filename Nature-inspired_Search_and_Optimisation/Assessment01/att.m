# Input the city dataset and validate the optimal distance
cities = transpose(dlmread('att48.tsp', '', [6, 1, 53, 3]));
optimalSolution = dlmread('att48.opt.tour', '', [5, 0, 52, 1]);
input = cities(:,optimalSolution);
shortest = distance(input)


# Pseudo-Euclidean distance
function d = distance(inputcities)

d = 0;
for n = 1 : length(inputcities)
    if n == length(inputcities)
        d = d + ceil(sqrt(sum((inputcities(:,n) - inputcities(:,1)).^2)/10));
    else    
        d = d + ceil(sqrt(sum((inputcities(:,n) - inputcities(:,n+1)).^2)/10));
    end
end

