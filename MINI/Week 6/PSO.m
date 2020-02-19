1 % Author: Augusto Luis Ballardini
2 % Email: augusto.ballardini@disco.unimib.it
3 % Website: http://www.ira.disco.unimib.it/people/ballardini-augusto-luis/
4
5 % This library is distributed in the hope that it will be useful,
6 % but WITHOUT ANY WARRANTY; without even the implied warranty of
7 % MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
8 % Permission is granted to copy, distribute and/or modify this document
9 % under the terms of the GNU Free Documentation License, Version 1.3
10 % or any later version published by the Free Software Foundation;
11 % with no Invariant Sections, no Front-Cover Texts, and no Back-Cover Texts
12 % A copy of the license is included in the section entitled "GNU
13 % Free Documentation License".
14
15 % The following code is inspired by the following paper:
16 % Van Der Merwe, D. W.; Engelbrecht, AP., "Data clustering using particle
17 % swarm optimization," Evolutionary Computation, 2003. CEC '03. The 2003
18 % Congress on , vol.1, no., pp.215,220 Vol.1, 8-12 Dec. 2003
19 % doi: 10.1109/CEC.2003.1299577
20 % URL:
21 % http://ieeexplore.ieee.org/stamp/stamp.jsp?tp=&arnumber=1299577
22
23 clear;
24 close all;
25
26 rng('default') % For reproducibility
27
28 % INIT PARTICLE SWARM
29 centroids = 2; % == clusters here (aka centroids)
30 dimensions = 2; % how many dimensions in each centroid
31 particles = 1; % how many particles in the swarm, how many solutions
32 iterations = 50; % iterations of the optimization alg.
33 simtime=0.101; % simulation delay btw each iteration
34 dataset subset = 2; % for the IRIS dataset, change this value from 0 to 2
35 write video = false; % enable to grab the output picture and save a video
36 hybrid pso = true; % enable/disable hybrid pso
37 manual init = false; % enable/disable manual initialization
38 % (only for dimensions={2,3})
39
40 % GLOBAL PARAMETERS (the paper reports this values 0.72;1.49;1.49)
41 w = 0.72; %INERTIA
42 c1 = 1.49; %COGNITIVE
43 c2 = 1.49; %SOCIAL
44
45 % VIDEO GRAB STUFF...
46 if write video
47 writerObj = VideoWriter('PSO.avi');
48 writerObj.Quality=100;
49 open(writerObj);
50 end
51
52 % LOAD DEFAULT CLUSTER (IRIS DATASET); USE WITH CARE!
53 % Resize the dataset with current "dimensions" variable. the standard iris
54 % dataset in matlab is 150x4, in this tutorial we need 150x2 or 150x3 for
55 % visualization purposes
56 load fisheriris.mat
57 meas = meas(:,1+dataset subset:dimensions+dataset subset);
58 dataset size = size (meas);
59
60 % Execute k-means if enabled the hybrid pso approach. If enabled, the
8
61 % startint position of the pso algorithm will be initialized using the
62 % output of the standard matlab implementation of k-means.
63 if hybrid pso
64 fprintf('Running Matlab K-Means Version\n');
65 [idx,KMEANS CENTROIDS] = kmeans(meas, ...
66 centroids, ...
67 'dist', ...
68 'sqEuclidean', ...
69 'display', ...
70 'iter', ...
71 'start', ...
72 'uniform', ...
73 'onlinephase', ...
74 'off');
75 fprintf('\n');
76 end
77
78 % PLOT STUFF... HANDLERS AND COLORS.
79 % This lines pre-configures the variables that will be used to plot the
80 % data.
81 pc = []; txt = [];
82 cluster colors vector = rand(particles, 3);
83
84 % PLOT DATASET
85 % This block creates either a 2d or 3d plot according to the "dimensions"
86 % variable. Please note that for visualization purposes the only admissible
87 % values are 2 (two) or 3 (three).
88 fh=figure(1);
89 hold on;
90 if dimensions == 3
91 plot3(meas(:,1),meas(:,2),meas(:,3),'k*');
92 view(3);
93 elseif dimensions == 2
94 plot(meas(:,1),meas(:,2),'k*');
95 end
96
97 % PLOT STUFF .. SETTING UP AXIS IN THE FIGURE
98 % Reconfiguring the axis in the figure. Without this line the axis max/min
99 % values may change during runtime.
100 axis equal;
101 axis(reshape([min(meas)-2; max(meas)+2],1,[]));
102 hold off;
103
104 % SETTING UP PSO DATA STRUCTURES
105 % Here the variables needed in the pso clustering are pre-initialized.
106 % Please note that swarm vel, swarm pos and swarm best maintains the values
107 % for all the swarms (aka particles)
108 % 'c' =
109 % 'ranges' is used to scale the initial randomized values to something
110 % inside the range of the input data (just to not have useless values
111 % outside the valid range, i.e. the range of the data).
112 % 'swarm fitness' is initially set as infinite. this is the "value" that
113 % will become smaller and smaller (i.e. minimizating the fitness function)
114 swarm vel = rand(centroids,dimensions,particles)*0.1;
115 swarm pos = rand(centroids,dimensions,particles);
116 swarm best = zeros(centroids,dimensions);
117 c = zeros(dataset size(1),particles);
118 ranges = max(meas)-min(meas); % used to scale the values
119 swarm pos = swarm pos .* repmat(ranges,centroids,1,particles) + ...
120 repmat(min(meas),centroids,1,particles);
121 swarm fitness(1:particles)=Inf;
122
9
123 % KMEANS INIT
124 % Here, if the hybrid pso approach was selected, we replace the first
125 % swarm/solution to the result of the k-means algorithm. please note that
126 % even with this initialization the pso will somehow try to improve this
127 % guess, since the velocities of the swarm are still randomly set, meaning
128 % that the system is unstable at the very beginning.
129 if hybrid pso
130 swarm pos(:,:,1) = KMEANS CENTROIDS;
131 end
132
133 % MANUAL INITIALIZATION (only for dimension 2 and 3)
134 % For dimension 2 (two) we can add an user-initialization of the algorithm.
135 % This will eventually replace the k-means initialization, since here we
136 % replace again the first swarm/solution <notice the swarm pos(:,:,**1**)>
137 % In the case of dimensions==3, i put here a random value, you can change
138 % these meaningless numbers without any problem <[6 3 4; 5 3 1]>
139 if manual init
140 if dimensions == 3
141 % MANUAL INIT ONLY FOR THE FIRST PARTICLE (with 'random' numbers!)
142 swarm pos(:,:,1) = [6 3 4; 5 3 1];
143 elseif dimensions == 2
144 % KEYBOARD INIT ONLY FOR THE FIRST PARTICLE
145 swarm pos(:,:,1) = ginput(2);
146 end
147 end
148
149 % Here the real PSO-algorithm begins
150 for iteration=1:iterations
151
152 % CALCULATE EUCLIDEAN DISTANCES TO ALL CENTROIDS
153 % Here we evaluate the distance (default 2-norm) between each centroid
154 % inside each particle against all the values inside the input data
155 % vector (the 'meas' variable resized in the very beginning). Keep all
156 % the distances in the 'distances' variable.
157 distances=zeros(dataset size(1),centroids,particles);
158 for particle=1:particles
159 for centroid=1:centroids
160 distance=zeros(dataset size(1),1);
161 for data vector=1:dataset size(1)
162 %meas(data vector,:)
163 distance(data vector,1) = norm( ...
164 swarm pos(centroid,:,particle)-meas(data vector,:));
165 end
166 distances(:,centroid,particle) = distance;
167 end
168 end
169
170 % ASSIGN MEASURES with CLUSTERS
171 % using the 'min' Matlab function to find the "Smallest elements in
172 % array" we create an 150xn matrix where the first column represents
173 % the distances of each input value to neares current centroids, and
174 % the n-columns specifies to which cluster/centroid the distance
175 % refers to.
176 for particle=1:particles
177 [value, index] = min(distances(:,:,particle),[],2);
178 c(:,particle) = index;
179 end
180
181 % PLOT STUFF... CLEAR HANDLERS
182 % clean the figure before plotting again
183 delete(pc); delete(txt);
184 pc = []; txt = [];
10
185
186 % PLOT STUFF...
187 % plotting again this step
188 hold on;
189 for particle=1:particles
190 for centroid=1:centroids
191 if any(c(:,particle) == centroid)
192 if dimensions == 3
193 pc = [pc plot3(swarm pos(centroid,1,particle), ...
194 swarm pos(centroid,2,particle), ...
195 swarm pos(centroid,3,particle),'*','color', ...
196 cluster colors vector(particle,:))];
197 elseif dimensions == 2
198 pc = [pc plot(swarm pos(centroid,1,particle), ...
199 swarm pos(centroid,2,particle),'*','color',...
200 cluster colors vector(particle,:))];
201 end
202 end
203 end
204 end
205 set(pc,{'MarkerSize'},{12})
206 set(gca,'LooseInset',get(gca,'TightInset'));
207 hold off;
208
209 % CALCULATE GLOBAL FITNESS and LOCAL FITNESS:=swarm fitness
210 % Here I evaluate the fitness of the algorithm, measured as the
211 % quantization error using the equation 8 of the original paper. It
212 % also calculates the global best and local best positions using
213 % equation 5. Please refer to the tutorial for explanation of this
214 % equation.
215 average fitness = zeros(particles,1);
216 for particle=1:particles
217 for centroid = 1 : centroids
218 if any(c(:,particle) == centroid)
219 local fitness = ...
220 mean(distances(c(:,particle)==centroid,centroid,particle));
221 average fitness(particle,1)=average fitness(particle,1)...
222 + local fitness;
223 end
224 end
225 average fitness(particle,1) = average fitness(particle,1) / ...
226 centroids;
227 if (average fitness(particle,1) < swarm fitness(particle))
228 swarm fitness(particle) = average fitness(particle,1);
229 swarm best(:,:,particle) = swarm pos(:,:,particle); %LOCAL BEST
230 end %FITNESS
231 end
232 [global fitness, index] = min(swarm fitness); %GLOBAL BEST FITNESS
233 swarm overall pose = swarm pos(:,:,index); %GLOBAL BEST POSITION
234
235 % SOME INFO ON THE COMMAND WINDOW
236 % Here I print some info the the Matlab Command Window
237 fprintf('%3d. global fitness is %5.4f\n',iteration,global fitness);
238 pause(simtime);
239
240 % VIDEO GRAB STUFF...
241 % If the GRABBING option was selected, put the frame inside the video.
242 if write video
243 frame = getframe(fh);
244 writeVideo(writerObj,frame);
245 end
246
11
247 % SAMPLE r1 AND r2 FROM UNIFORM DISTRIBUTION [0..1]
248 % Equation 3 and 4 needs a random value, sampled from an uniform
249 % distribution. Here we go!
250 r1 = rand;
251 r2 = rand;
252
253 % UPDATE CLUSTER CENTROIDS
254 % Update the cluster centroids using equation 3 and 4. Here the
255 % cognitive and social contributions are calculated to update the
256 % velocity and position of each swar.
257 for particle=1:particles
258 inertia = w * swarm vel(:,:,particle);
259 cognitive = c1 * r1 * ...
260 (swarm best(:,:,particle)-swarm pos(:,:,particle));
261 social = c2 * r2 * (swarm overall pose-swarm pos(:,:,particle));
262 vel = inertia+cognitive+social;
263
264 % UPDATED PARTICLE ...
265 swarm pos(:,:,particle) = swarm pos(:,:,particle) + vel ; % .. POSE
266 swarm vel(:,:,particle) = vel; % .. VEL
267 end
268
269 end % end of the PSO algorithm
270
271 % PLOT THE ASSOCIATIONS WITH RESPECT TO THE CLUSTER
272 % At the very end, paint the original points using the same color for the
273 % elements within the same cluster.
274 hold on;
275 particle=index; %select the best particle (with best fitness)
276 cluster colors = ['m','g','y','b','r','c','g'];
277 for centroid=1:centroids
278 if any(c(:,particle) == centroid)
279 if dimensions == 3
280 plot3(meas(c(:,particle)==centroid,1),meas(c(:,particle)== ...
281 centroid,2),meas(c(:,particle)==centroid,3),'o','color',...
282 cluster colors(centroid));
283 elseif dimensions == 2
284 plot(meas(c(:,particle)==centroid,1), ...
285 meas(c(:,particle)==centroid,2),'o','color', ...
286 cluster colors(centroid));
287 end
288 end
289 end
290 hold off;
291
292 % VIDEO GRAB STUFF...
293 % Close the video file, if opened.
294 if write video
295 frame = getframe(fh);
296 writeVideo(writerObj,frame);
297 close(writerObj);
298 end
299
300 % SAY GOODBYE
301 fprintf('\nEnd, global fitness is %5.4f\n',global fitness);
