
close all
clear all
[cities, demands, capacity] = ReadInData('A-n32-k5.vrp')


plot(cities(1,1) ,cities(2,1) ,'r*'); % plot the deport
hold on
plot(cities(1,2:end) ,cities(2,2:end) ,'o');

Routes = zeros(5, 31);


r1 = [21 31 19 17 13 7 26]+1
r2 = [12 1 16 30]+1
r3 = [27 24]+1
r4 = [29 18 8 9 22 15 10 25 5 20]+1
r5 = [14 28 11 4 23 3 2 6]+1;

Routes = {r1, r2, r3, r4, r5}

total_distance = 0;
for ii = 1:5
    routenew = [1 Routes{1,ii} 1]
    new_cities_coordinates = cities(:,routenew);
    total_distance = total_distance + distance(new_cities_coordinates);
end

figure(2)
drawRoute(cities, Routes, total_distance)
