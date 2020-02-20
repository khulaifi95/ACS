function [newdistance, newBestRoute] = CVRP_two_opt(cities, bestRoutes)
% randomsearch

% Hill climbing search algorithm
%The input arguments are
% inputcities         - The cordinates for n cities are represented as 2
%                       rows and n columns and is passed as an argument for
%                       greedysearch.

inputcities = cities;
newdistance = 0;

for jj = 1:length(bestRoutes)
    best_route = [1 bestRoutes{1,jj} 1];
    
    best_cities_coordinates = inputcities(:,best_route);
    best_distance = distance(best_cities_coordinates);  
    num_cities = length(best_route);
    
    % test_city = ['a', 'b', 'e', 'd', 'c', 'f', 'g'];
    % newroute = twooptSwap(test_city, 3, 5)
    
    stuck = false;
    while (stuck==false)
        for i = 2 : num_cities-1
            for k = i+1 : num_cities - 1
                stuck = true;
                % Execute the swapping function
                newroute = twooptSwap(best_route, i, k);
                new_cities_coordinates = inputcities(:,newroute);
                new_distance = distance(new_cities_coordinates);
                if new_distance < best_distance
                    best_distance = new_distance;
                    best_route = newroute;
                    stuck = false;
                    % plotcities(new_cities_coordinates);
                    % Since it is a simple hill climbing algorihtm,
                    % accept the first better solution and then terminate
                    % the search of other immediate neighbours
                    break;
                end
            end
        end
        
        
    end
    
    newBestRoute{1,jj} = best_route;
    newdistance = newdistance + best_distance;
end
end

%% Swap two cities
function new_route = twooptSwap(route, i, k)
temp = route(i);
route(i) = route(k);
route(k) = temp;
new_route = route;
end

