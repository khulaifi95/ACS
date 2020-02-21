function [fbestval,newBestRoute] = PSO_CVRP(fname, MaxIter, PopSize)

%% Standard PSO for solving Capacitated Vehicle Routing problem
% Copyright (C) 2014-2019 Shan He, the University of Birmingham
% Input Arguments:
%   fname       - the name of the evaluation .m function
%   MaxIter     - maximum iteration
%   PopSize     - number of particles

close all;
ploton=0;

[cities, demands, capacity] = ReadInData(fname)

% We need to obtain the dimensionality of each solution NDim:
num_vehicles = 5; 
Num_separators = num_vehicles - 1; % Used to separate a solution into num_vehicles routes
num_customers = length(cities)-1; % Exclude the deport
NDim = num_customers + Num_separators;
%% Parameters
flag=0;
iteration = 0;
c1 = 0.6;       % PSO parameter C1
c2 = 0.6;       % PSO parameter C2
% decrease the inertia
startwaight = 0.8;
endwaight = 0.5;
waightstep = (startwaight-endwaight)/MaxIter;


%% Lower/Upper bounds
% Defined lower bound and upper bound.
LowerBound = zeros(PopSize, NDim);
UpperBound = ones(PopSize, NDim);

% Initialize swarm X
X =  rand(PopSize, NDim).*(UpperBound-LowerBound) + LowerBound;     
vmax = ones(PopSize, NDim);

%% We need to set the maximum V
for i=1:NDim
    vmax(:, i)=(UpperBound(:,i)-LowerBound(:,i))/10;
end
V = vmax.*rand(PopSize, NDim);      % Initialize V

%% Evaluate the particles
exexutefunction=strcat('CVRPFitness','(X(i,:), cities, demands, capacity)');
% Evaluate initial X
for i = 1:PopSize,
    fvalue(i) = eval(exexutefunction);
end

pbest = X;   % Initializing Best positions matrix
fpbest = fvalue;      % Initializing the corresponding function values
% Finding best particle in initial X
[fbestval,index] = min(fvalue);    % Find the globe best
gbest = X(index, :);

while(flag == 0) & (iteration < MaxIter)
    iteration = iteration +1;
    w = startwaight - iteration*waightstep;
    for i=1:PopSize       
        R1 = rand(1, NDim);
        R2 = rand(1, NDim);
        % Equations 1-2 in the Week 10 slides
        V(i,:) = w*V(i,:) + c1*R1.*(pbest(i,:)-X(i,:)) + c2*R2.*(gbest-X(i,:));
        if V(i,:) > vmax(i,:)
            V(i,:) = vmax(i,:)
        end
        % Update the swarm particle
        X(i,:) = X(i,:) + V(i,:);
        
        
        % Prevent particles from flying outside search space
        OutFlag = X(i,:)<=LowerBound(i,:) | X(i,:)>=UpperBound(i,:);
        X(i,:) = X(i,:) - OutFlag.*V(i,:);
        
        
        % Evaluate the new swarm       
        fvalue(i) = eval(exexutefunction);
        
        % Updating the pbest for each particle
        if fvalue(i)<=fpbest(i)
            fpbest(i) = fvalue(i);
            pbest(i,:) = X(i,:);
        end
    end
    
    % Updating the best particle 
    [fbestval, index] = min(fvalue);
    gbest = X(index, :);
    % Evaluate the best particle   
    [f total_distance G Routes] = CVRPFitness(gbest, cities, demands, capacity);
    % Plot the best route found so far
    drawRoute(cities,Routes, f);

end

[fbestval newBestRoute] = CVRP_two_opt(cities, Routes)
figure(2)
drawRoute(cities, newBestRoute, fbestval);

