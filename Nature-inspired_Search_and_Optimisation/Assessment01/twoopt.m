%% Swap two cities
function newroute = twoopt(route, i, k)
% Step 1: take $route[1]$ to $route[i-1]$ and add them in order to
% $newroute$ 
newroute = route(:, 1:i-1);

% Step 2: take $route[i]$ to $route[k]$ and add them in reverse order to
% $newroute$ 
reversed = fliplr(route(:, i:k));
newroute = cat(newroute, reversed);

% Step 3: take $route[k+1]$ to end and add them in order to new $newroute$
newroute = cat(newroute, route(:, k+1:end));


end