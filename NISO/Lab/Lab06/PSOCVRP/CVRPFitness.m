function [f total_distance G Routes] = CVRPFitness(x, cities, demands, capacity)
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here

% First get the number of customers
num_customers = length(cities)-1;


if nargin==0
    f = num_cities;
else
    [temp1 route] = sort(x);

	% We need to exclude the depart (node 1 in our Matlab code)
    route = route + 1;
    
	% Initialise total cost and degree of violations 
	total_distance = 0;
    G = 0;
	% Get the indices of the separators
    Separators = find(route>length(cities));
	% Get the number of the vechiles
    num_vehicles = length(Separators)+1; 
	% Initilise the routes. Here we use a cell array.
    Routes = cell(1, num_vehicles);
	% Decode the sorted indices (route) into separate routes:
  % We need to use a for-loop to separate the sorted indices into
	% num_vehicles routes
    for ii=1:length(Separators)+1
        if ii==1
            routenew = route(1:Separators(ii)-1);
        else   
            if ii == length(Separators)+1
                routenew = route(Separators(ii-1)+1:end);
            else
                routenew = route(Separators(ii-1)+1:Separators(ii)-1);
            end
        end
		% For each route, we need to calcuate the total commodities demanded by the customers of the route
        route_demands = sum(demands(routenew));
		% Use the pentaly method, e.g., the penalty function to calcualte the violations of capacity constraints. 
        G = G + max(route_demands - capacity, 0);
        if ~isempty(routenew)
            routenew = [1 routenew 1];
            Routes{1, ii} = routenew;
            new_cities_coordinates = cities(:,routenew);
            total_distance = total_distance + distance(new_cities_coordinates);
        end
    end
    f = total_distance + 100*G;
end

