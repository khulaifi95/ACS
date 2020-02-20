function drawRoute(cities, bestRoutes, f)

shg
plot(cities(1,1) ,cities(2,1) ,'r*'); % plot the deport
plot(cities(1,2:end) ,cities(2,2:end) ,'o');

for ii=1:length(bestRoutes)
    route = [1 bestRoutes{1,ii} 1];
    route_lines = cities(:,route);
    temp_2 = line(route_lines(1,:),route_lines(2,:),'Marker','*');
    color = [rand, rand, rand];
    set(temp_2,'color',color);
end

distance_print = sprintf(...
     'The distance for %d cities is % 4.6f units'...
     ,length(cities),f);
title(distance_print,'fontweight','bold');
drawnow;
end

