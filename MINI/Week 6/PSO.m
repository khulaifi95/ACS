clear;
close all;
rand("seed", 42); % For reproducibility
% INIT PARTICLE SWARM
centroids = 2; % == clusters here (aka centroids)
dimensions = 2; % how many dimensions in each centroid
particles = 100; % how many particles in the swarm, how many solutions
iterations = 50; % iterations of the optimization alg.
simtime=0.101; % simulation delay btw each iteration
dataset_subset = 2; % for the IRIS dataset, change this value from 0 to 2
write_video = false; % enable to grab the output picture and save a video
hybrid_pso = true; % enable/disable hybrid_pso
manual_init = false; % enable/disable manual_initialization
% (only for dimensions={2,3})
% GLOBAL PARAMETERS (the paper reports this values 0.72;1.49;1.49)
w = 0.72; %INERTIA
c1 = 1.49; %COGNITIVE
c2 = 1.49; %SOCIAL
% VIDEO GRAB STUFF...
if write_video
writerObj = VideoWriter('PSO.avi');
writerObj.Quality=100;
open(writerObj);
end
% LOAD DEFAULT CLUSTER (IRIS DATASET); USE WITH CARE!
% Resize the dataset with current "dimensions" variable. the standard iris
% dataset in matlab is 150x4, in this tutorial we need 150x2 or 150x3 for
% visualization purposes
load fisheriris.mat
meas = meas(:,1+dataset_subset:dimensions+dataset_subset);
dataset_size = size (meas);
% Execute k-means if enabled the hybrid_pso approach. If enabled, the
% startint position of the pso algorithm will be initialized using the
% output of the standard matlab implementation of k-means.
if hybrid_pso
fprintf('Running Matlab K-Means Version\n');
[idx,KMEANS_CENTROIDS] = kmeans(meas, centroids,...
'DISTANCE', 'sqeuclidean', 'START', 'uniform');
fprintf('\n');
end
% PLOT STUFF... HANDLERS AND COLORS.
% This lines pre-configures the variables that will be used to plot the
% data.
pc = []; txt = [];
cluster_colors_vector = rand(particles, 3);
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
% Please note that swarm_vel, swarm_pos and swarm_best maintains the values
% for all the swarms (aka particles)
% 'c' =
% 'ranges' is used to scale the initial randomized values to something
% inside the range of the input data (just to not have useless values
% outside the valid range, i.e. the range of the data).
% 'swarm_fitness' is initially set as infinite. this is the "value" that
% will become smaller and smaller (i.e. minimizating the fitness function)
swarm_vel = rand(centroids,dimensions,particles)*0.1;
swarm_pos = rand(centroids,dimensions,particles);
swarm_best = zeros(centroids,dimensions);
c = zeros(dataset_size(1),particles);
ranges = max(meas)-min(meas); % used to scale the values
swarm_pos = swarm_pos .* repmat(ranges,centroids,1,particles) + ...
repmat(min(meas),centroids,1,particles);
swarm_fitness(1:particles)=Inf;
% KMEANS INIT
% Here, if the hybrid_pso approach was selected, we replace the first
% swarm/solution to the result of the k-means algorithm. please note that
% even with this initialization the pso will somehow try to improve this
% guess, since the velocities of the swarm are still randomly set, meaning
% that the system is unstable at the very beginning.
if hybrid_pso
swarm_pos(:,:,1) = KMEANS_CENTROIDS;
end
% manual_initIALIZATION (only for dimension 2 and 3)
% For dimension 2 (two) we can add an user-initialization of the algorithm.
% This will eventually replace the k-means initialization, since here we
% replace again the first swarm/solution <notice the swarm_pos(:,:,**1**)>
% In the case of dimensions==3, i put here a random value, you can change
% these meaningless numbers without any problem <[6 3 4; 5 3 1]>
if manual_init
if dimensions == 3
% manual_init ONLY FOR THE FIRST PARTICLE (with 'random' numbers!)
swarm_pos(:,:,1) = [6 3 4; 5 3 1];
elseif dimensions == 2
% KEYBOARD INIT ONLY FOR THE FIRST PARTICLE
swarm_pos(:,:,1) = ginput(2);
end
end
% Here the real PSO-algorithm begins
for iteration=1:iterations
% CALCULATE EUCLIDEAN DISTANCES TO ALL CENTROIDS
% Here we evaluate the distance (default 2-norm) between each centroid
% inside each particle against all the values inside the input data
% vector (the 'meas' variable resized in the very beginning). Keep all
% the distances in the 'distances' variable.
distances=zeros(dataset_size(1),centroids,particles);
for particle=1:particles
for centroid=1:centroids
distance=zeros(dataset_size(1),1);
for data_vector=1:dataset_size(1)
%meas(data_vector,:)
distance(data_vector,1) = norm( ...
swarm_pos(centroid,:,particle)-meas(data_vector,:));
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
pc = [pc plot3(swarm_pos(centroid,1,particle), ...
swarm_pos(centroid,2,particle), ...
swarm_pos(centroid,3,particle),'*','color', ...
cluster_colors_vector(particle,:))];
elseif dimensions == 2
pc = [pc plot(swarm_pos(centroid,1,particle), ...
swarm_pos(centroid,2,particle),'*','color',...
cluster_colors_vector(particle,:))];
end
end
end
end
set(pc,{'MarkerSize'},{12})
set(gca,'LooseInset',get(gca,'TightInset'));
hold off;
% CALCULATE global_fitness and local_fitness:=swarm_fitness
% Here I evaluate the fitness of the algorithm, measured as the
% quantization error using the equation 8 of the original paper. It
% also calculates the global best and local best positions using
% equation 5. Please refer to the tutorial for explanation of this
% equation.
average_fitness = zeros(particles,1);
for particle=1:particles
for centroid = 1 : centroids
if any(c(:,particle) == centroid)
local_fitness = ...
mean(distances(c(:,particle)==centroid,centroid,particle));
average_fitness(particle,1)=average_fitness(particle,1)...
+ local_fitness;
end
end
average_fitness(particle,1) = average_fitness(particle,1) / ...
centroids;
if (average_fitness(particle,1) < swarm_fitness(particle))
swarm_fitness(particle) = average_fitness(particle,1);
swarm_best(:,:,particle) = swarm_pos(:,:,particle); %LOCAL BEST
end %FITNESS
end
[global_fitness, index] = min(swarm_fitness); %GLOBAL BEST FITNESS
swarm_overall_pose = swarm_pos(:,:,index); %GLOBAL BEST POSITION
% SOME INFO ON THE COMMAND WINDOW
% Here I print some info the the Matlab Command Window
fprintf('%3d. global_fitness is %5.4f\n',iteration,global_fitness);
pause(simtime);
% VIDEO GRAB STUFF...
% If the GRABBING option was selected, put the frame inside the video.
if write_video
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
inertia = w * swarm_vel(:,:,particle);
cognitive = c1 * r1 * ...
(swarm_best(:,:,particle)-swarm_pos(:,:,particle));
social = c2 * r2 * (swarm_overall_pose-swarm_pos(:,:,particle));
vel = inertia+cognitive+social;
% UPDATED PARTICLE ...
swarm_pos(:,:,particle) = swarm_pos(:,:,particle) + vel ; % .. POSE
swarm_vel(:,:,particle) = vel; % .. VEL
end
end % end of the PSO algorithm
% PLOT THE ASSOCIATIONS WITH RESPECT TO THE CLUSTER
% At the very end, paint the original points using the same color for the
% elements within the same cluster.
hold on;
particle=index; %select the best particle (with best fitness)
cluster_colors = ['m','g','y','b','r','c','g'];
for centroid=1:centroids
if any(c(:,particle) == centroid)
if dimensions == 3
plot3(meas(c(:,particle)==centroid,1),meas(c(:,particle)== ...
centroid,2),meas(c(:,particle)==centroid,3),'o','color',...
cluster_colors(centroid));
elseif dimensions == 2
plot(meas(c(:,particle)==centroid,1), ...
meas(c(:,particle)==centroid,2),'o','color', ...
cluster_colors(centroid));
end
end
end
hold off;
% VIDEO GRAB STUFF...
% Close the video file, if opened.
if write_video
frame = getframe(fh);
writeVideo(writerObj,frame);
close(writerObj);
end
% SAY GOODBYE
fprintf('\nEnd, global_fitness is %5.4f\n',global_fitness);
