% Author: Augusto Luis Ballardini
% Email: augusto.ballardini@disco.unimib.it
% Website: http://www.ira.disco.unimib.it/people/ballardini-augusto-luis/
% This library is distributed in the hope that it will be useful,
% but WITHOUT ANY WARRANTY; without even the implied warranty of
% MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
% Permission is granted to copy, distribute and/or modify this document
% under the terms of the GNU Free Documentation License, Version 1.3
% or any later version published by the Free Software Foundation;
% with no Invariant Sections, no Front-Cover Texts, and no Back-Cover Texts
% A copy of the license is included in the section entitled "GNU
% Free Documentation License".
% The following code is inspired by the following paper:
% Van Der Merwe, D. W.; Engelbrecht, AP., "Data clustering using particle
% swarm optimization," Evolutionary Computation, 2003. CEC '03. The 2003
% Congress on , vol.1, no., pp.215,220 Vol.1, 8-12 Dec. 2003
% doi: 10.1109/CEC.2003.1299577
% URL:
% http://ieeexplore.ieee.org/stamp/stamp.jsp?tp=&arnumber=1299577
clear;
close all;
rng('default') % For reproducibility
% INIT PARTICLE SWARM
centroids = 2; % == clusters here (aka centroids)
dimensions = 2; % how many dimensions in each centroid
particles = 1; % how many particles in the swarm, how many solutions
iterations = 50; % iterations of the optimization alg.
simtime=0.101; % simulation delay btw each iteration
dataset subset = 2; % for the IRIS dataset, change this value from 0 to 2
write video = false; % enable to grab the output picture and save a video
hybrid pso = true; % enable/disable hybrid pso
manual init = false; % enable/disable manual initialization
% (only for dimensions={2,3})
% GLOBAL PARAMETERS (the paper reports this values 0.72;1.49;1.49)
w = 0.72; %INERTIA
c1 = 1.49; %COGNITIVE
c2 = 1.49; %SOCIAL
% VIDEO GRAB STUFF...
if write video
writerObj = VideoWriter('PSO.avi');
writerObj.Quality=100;
open(writerObj);
end
% LOAD DEFAULT CLUSTER (IRIS DATASET); USE WITH CARE!
% Resize the dataset with current "dimensions" variable. the standard iris
% dataset in matlab is 150x4, in this tutorial we need 150x2 or 150x3 for
% visualization purposes
load fisheriris.mat
meas = meas(:,1+dataset subset:dimensions+dataset subset);
dataset size = size (meas);
% Execute k-means if enabled the hybrid pso approach. If enabled, the
% startint position of the pso algorithm will be initialized using the
% output of the standard matlab implementation of k-means.
if hybrid pso
fprintf('Running Matlab K-Means Version\n');
[idx,KMEANS CENTROIDS] = kmeans(meas, ...
centroids, ...
'dist', ...
'sqEuclidean', ...
'display', ...
'iter', ...
'start', ...
'uniform', ...
'onlinephase', ...
'off');
fprintf('\n');
end
% PLOT STUFF... HANDLERS AND COLORS.
% This lines pre-configures the variables that will be used to plot the
% data.
pc = []; txt = [];
cluster colors vector = rand(particles, 3);
% PLOT DATASET
% This block creates either a 2d or 3d plot according to the "dimensions"
% variable. Please note that for visualization purposes the only admissible
% values are 2 (two) or 3 (three).
fh=figure(1);
hold on;
if dimensions == 3
plot3(meas(:,1),meas(:,2),meas(:,3),'k*');
view(3);
elseif dimensions == 2
plot(meas(:,1),meas(:,2),'k*');
end
% PLOT STUFF .. SETTING UP AXIS IN THE FIGURE
% Reconfiguring the axis in the figure. Without this line the axis max/min
% values may change during runtime.
axis equal;
axis(reshape([min(meas)-2; max(meas)+2],1,[]));
hold off;
% SETTING UP PSO DATA STRUCTURES
% Here the variables needed in the pso clustering are pre-initialized.
% Please note that swarm vel, swarm pos and swarm best maintains the values
% for all the swarms (aka particles)
% 'c' =
% 'ranges' is used to scale the initial randomized values to something
% inside the range of the input data (just to not have useless values
% outside the valid range, i.e. the range of the data).
% 'swarm fitness' is initially set as infinite. this is the "value" that
% will become smaller and smaller (i.e. minimizating the fitness function)
swarm vel = rand(centroids,dimensions,particles)*0.1;
swarm pos = rand(centroids,dimensions,particles);
swarm best = zeros(centroids,dimensions);
c = zeros(dataset size(1),particles);
ranges = max(meas)-min(meas); % used to scale the values
swarm pos = swarm pos .* repmat(ranges,centroids,1,particles) + ...
repmat(min(meas),centroids,1,particles);
swarm fitness(1:particles)=Inf;
% KMEANS INIT
% Here, if the hybrid pso approach was selected, we replace the first
% swarm/solution to the result of the k-means algorithm. please note that
% even with this initialization the pso will somehow try to improve this
% guess, since the velocities of the swarm are still randomly set, meaning
% that the system is unstable at the very beginning.
if hybrid pso
swarm pos(:,:,1) = KMEANS CENTROIDS;
end
% MANUAL INITIALIZATION (only for dimension 2 and 3)
% For dimension 2 (two) we can add an user-initialization of the algorithm.
% This will eventually replace the k-means initialization, since here we
% replace again the first swarm/solution <notice the swarm pos(:,:,**1**)>
% In the case of dimensions==3, i put here a random value, you can change
% these meaningless numbers without any problem <[6 3 4; 5 3 1]>
if manual init
if dimensions == 3
% MANUAL INIT ONLY FOR THE FIRST PARTICLE (with 'random' numbers!)
swarm pos(:,:,1) = [6 3 4; 5 3 1];
elseif dimensions == 2
% KEYBOARD INIT ONLY FOR THE FIRST PARTICLE
swarm pos(:,:,1) = ginput(2);
end
end
% Here the real PSO-algorithm begins
for iteration=1:iterations
% CALCULATE EUCLIDEAN DISTANCES TO ALL CENTROIDS
% Here we evaluate the distance (default 2-norm) between each centroid
% inside each particle against all the values inside the input data
% vector (the 'meas' variable resized in the very beginning). Keep all
% the distances in the 'distances' variable.
distances=zeros(dataset size(1),centroids,particles);
for particle=1:particles
for centroid=1:centroids
distance=zeros(dataset size(1),1);
for data vector=1:dataset size(1)
%meas(data vector,:)
distance(data vector,1) = norm( ...
swarm pos(centroid,:,particle)-meas(data vector,:));
end
distances(:,centroid,particle) = distance;
end
end
% ASSIGN MEASURES with CLUSTERS
% using the 'min' Matlab function to find the "Smallest elements in
% array" we create an 150xn matrix where the first column represents
% the distances of each input value to neares current centroids, and
% the n-columns specifies to which cluster/centroid the distance
% refers to.
for particle=1:particles
[value, index] = min(distances(:,:,particle),[],2);
c(:,particle) = index;
end
% PLOT STUFF... CLEAR HANDLERS
% clean the figure before plotting again
delete(pc); delete(txt);
pc = []; txt = [];% PLOT STUFF...
% plotting again this step
hold on;
for particle=1:particles
for centroid=1:centroids
if any(c(:,particle) == centroid)
if dimensions == 3
pc = [pc plot3(swarm pos(centroid,1,particle), ...
swarm pos(centroid,2,particle), ...
swarm pos(centroid,3,particle),'*','color', ...
cluster colors vector(particle,:))];
elseif dimensions == 2
pc = [pc plot(swarm pos(centroid,1,particle), ...
swarm pos(centroid,2,particle),'*','color',...
cluster colors vector(particle,:))];
end
end
end
end
set(pc,{'MarkerSize'},{12})
set(gca,'LooseInset',get(gca,'TightInset'));
hold off;
% CALCULATE GLOBAL FITNESS and LOCAL FITNESS:=swarm fitness
% Here I evaluate the fitness of the algorithm, measured as the
% quantization error using the equation 8 of the original paper. It
% also calculates the global best and local best positions using
% equation 5. Please refer to the tutorial for explanation of this
% equation.
average fitness = zeros(particles,1);
for particle=1:particles
for centroid = 1 : centroids
if any(c(:,particle) == centroid)
local fitness = ...
mean(distances(c(:,particle)==centroid,centroid,particle));
average fitness(particle,1)=average fitness(particle,1)...
+ local fitness;
end
end
average fitness(particle,1) = average fitness(particle,1) / ...
centroids;
if (average fitness(particle,1) < swarm fitness(particle))
swarm fitness(particle) = average fitness(particle,1);
swarm best(:,:,particle) = swarm pos(:,:,particle); %LOCAL BEST
end %FITNESS
end
[global fitness, index] = min(swarm fitness); %GLOBAL BEST FITNESS
swarm overall pose = swarm pos(:,:,index); %GLOBAL BEST POSITION
% SOME INFO ON THE COMMAND WINDOW
% Here I print some info the the Matlab Command Window
fprintf('%3d. global fitness is %5.4f\n',iteration,global fitness);
pause(simtime);
% VIDEO GRAB STUFF...
% If the GRABBING option was selected, put the frame inside the video.
if write video
frame = getframe(fh);
writeVideo(writerObj,frame);
end
% SAMPLE r1 AND r2 FROM UNIFORM DISTRIBUTION [0..1]
% Equation 3 and 4 needs a random value, sampled from an uniform
% distribution. Here we go!
r1 = rand;
r2 = rand;
% UPDATE CLUSTER CENTROIDS
% Update the cluster centroids using equation 3 and 4. Here the
% cognitive and social contributions are calculated to update the
% velocity and position of each swar.
for particle=1:particles
inertia = w * swarm vel(:,:,particle);
cognitive = c1 * r1 * ...
(swarm best(:,:,particle)-swarm pos(:,:,particle));
social = c2 * r2 * (swarm overall pose-swarm pos(:,:,particle));
vel = inertia+cognitive+social;
% UPDATED PARTICLE ...
swarm pos(:,:,particle) = swarm pos(:,:,particle) + vel ; % .. POSE
swarm vel(:,:,particle) = vel; % .. VEL
end
end % end of the PSO algorithm
% PLOT THE ASSOCIATIONS WITH RESPECT TO THE CLUSTER
% At the very end, paint the original points using the same color for the
% elements within the same cluster.
hold on;
particle=index; %select the best particle (with best fitness)
cluster colors = ['m','g','y','b','r','c','g'];
for centroid=1:centroids
if any(c(:,particle) == centroid)
if dimensions == 3
plot3(meas(c(:,particle)==centroid,1),meas(c(:,particle)== ...
centroid,2),meas(c(:,particle)==centroid,3),'o','color',...
cluster colors(centroid));
elseif dimensions == 2
plot(meas(c(:,particle)==centroid,1), ...
meas(c(:,particle)==centroid,2),'o','color', ...
cluster colors(centroid));
end
end
end
hold off;
% VIDEO GRAB STUFF...
% Close the video file, if opened.
if write video
frame = getframe(fh);
writeVideo(writerObj,frame);
close(writerObj);
end
% SAY GOODBYE
fprintf('\nEnd, global fitness is %5.4f\n',global fitness);
