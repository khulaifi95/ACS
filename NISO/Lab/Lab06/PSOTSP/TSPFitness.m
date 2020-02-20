function [ total_distance ] = TSPFitness(x)
%UNTITLED Summary of this function goes here
%   Detailed explanation goes here
load('cities.mat')
num_cities = length(cities);

if nargin==0
    total_distance = num_cities;
else
    
% Decode the random key x into a route    
% Your implemetation here to get newroute

new_cities_coordinates = cities(:,newroute);

% Calclate the distance using distanceTSP function
total_distance = distanceTSP(new_cities_coordinates);

end

