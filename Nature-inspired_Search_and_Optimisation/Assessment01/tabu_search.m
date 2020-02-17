function [best_distance best_tour] = tabu_search(inputcities)
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
num_tabu = 100;
tabu_list = zeros(1, num_cities);
tabu_list(1,:) = best_tour;

stuck = false;
while (stuck==false)
    for i = 2 : num_cities-1
        for k = i+1 : num_cities - 1
            stuck = true;
            % Execute the swapping function
            new_tour = twoopt(best_tour, i, k);
            if (ismember(new_tour, tabu_list,'rows') == false)
              new_cities_coordinates = inputcities(:,new_tour);
              new_distance = distance(new_cities_coordinates);

              if new_distance < best_distance
                  best_distance = new_distance;
                  best_tour = new_tour;
                  if length(tabu_list) < num_tabu
                    tabu_list(end+1,:) = best_tour;
                  else
                    tabu_list(1,:) = best_tour;
                  end
                  stuck = false;
                  %plotcities(new_cities_coordinates);
                  %break;
              end
            end
        end
    end


end
end