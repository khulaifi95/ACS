function [best_distance best_tour] = simulated_annealing(inputcities)
% randomsearch

% Hill climbing search algorithm
%The input arguments are
% inputcities         - The cordinates for n cities are represented as 2
%                       rows and n columns and is passed as an argument for
%                       greedysearch.


num_cities = length(inputcities);

% You can generate a random solution as the inital solution. 
% If you execute your algorithm several times, you have the hill climbing
% algorithm with random restart. 
best_tour = randperm(num_cities);
best_cities_coordinates = inputcities(:,best_tour);
best_distance = distance(best_cities_coordinates);

initial_temp = 0.01;
temp_index = 1;
stuck = false;
while (stuck==false)

    for i = 2 : num_cities-1
        for k = i+1 : num_cities - 1
            stuck = true;
            % Execute the swapping function
            new_tour = twoopt(best_tour, i, k);
            new_cities_coordinates = inputcities(:,new_tour);
            new_distance = distance(new_cities_coordinates);
            
            % Calculate the current temperature
            temp = temperature(temp_index+1, initial_temp);
            temp_index += 1;
            if acceptance(new_distance, best_distance, temp) > rand
                best_distance = new_distance;
                best_tour = new_tour;
                stuck = false;
                #plotcities(new_cities_coordinates);
            end
        end
    end


end
end



