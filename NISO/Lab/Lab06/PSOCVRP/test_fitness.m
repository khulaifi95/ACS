
close all
clear all
[cities, demands, capacity] = ReadInData('A-n32-k5.vrp')

num_vehicles = 5; 
Num_separators = num_vehicles - 1;
num_customers = length(cities)-1; % Exclude the deport

NDim = num_customers + Num_separators;

r1 = [21 31 19 17 13 7 26]+1
r2 = [12 1 16 30]+1
r3 = [27 24]+1
r4 = [29 18 8 9 22 15 10 25 5 20]+1
r5 = [14 28 11 4 23 3 2 6]+1;

I = [r1 33 r2 34 r3 35 r4 36 r5]
[temp idx] = sort(I)
A = sort(rand(1,NDim))
tempA = sort(rand(1,NDim))
A = tempA(idx)

[f total_distance G Routes] = CVRPFitness(A, cities, demands, capacity)