function [fbestval,bestparticle] = PSO_TSP(MaxIter, PopSize)

%% PSO with random key enconding scheme to solve TSP problems
% Copyright (C) 2014-2019 Shan He, the University of Birmingham
% Input Arguments:
%   fname       - the name of the evaluation .m function
%   NDim        - dimension of the evalation function
%   MaxIter     - maximum iteration
%   PopSize     - number of particles

close all;
ploton=0;
figure


%% Parameters
flag=0;
iteration = 0;
c1 = 1;       % PSO parameter C1
c2 = 1;       % PSO parameter C2
w=0.8;          % Inertia weigth
% decrease the inertia
startwaight = 0.8;
endwaight = 0.5;
waightstep = (startwaight-endwaight)/MaxIter;

fname = 'TSPFitness'
%% Lower/Upper bounds
NDim = eval(fname)
% Defined lower bound and upper bound.
LowerBound = zeros(PopSize, NDim);
UpperBound = ones(PopSize, NDim);



X =  rand(PopSize, NDim).*(UpperBound-LowerBound) + LowerBound;     % Initialize swarm X
vmax = ones(PopSize, NDim);

%% We need to set the maximum V
for i=1:NDim
    vmax(:, i)=(UpperBound(:,i)-LowerBound(:,i))/10;
end
V = vmax.*rand(PopSize, NDim);      % Initialize V

%% Evaluate the particles
exexutefunction=strcat(fname,'(X(i,:))');
% Evaluate initial X
for i = 1:PopSize,
    fvalue(i) = eval(exexutefunction);
end

pbest = X;   % Initializing Best positions matrix
fpbest = fvalue;      % Initializing the corresponding function values
% Finding best particle in initial X
[fbestval,index] = min(fvalue);    % Find the globe best
while(flag == 0) & (iteration < MaxIter)
    iteration = iteration +1;
    w = startwaight - iteration*waightstep;
    gbest = X(index, :);
    for i=1:PopSize
        
        r1 = rand(1, NDim);
        r2 = rand(1, NDim);

        V(i,:) = w*V(i,:) + c1*r1.*(pbest(i,:)-X(i,:)) + c2*r2.*(gbest-X(i,:));
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
    
    % Updating index
    [fbestval, index] = min(fvalue);
    [fsortval, sortindex] = sort(fvalue);
    
    % plot best fitness
    %hold on;
    Best(iteration) =fbestval;
    semilogy(Best,'r--');xlabel('generation'); ylabel('f(x)');
    text(0.5,0.95,['Best = ', num2str(Best(iteration))],'Units','normalized');
    drawnow;
end

bestparticle = X(index, :)


[fbestval bestparticle] = simple_hill_climbing_two_opt(bestparticle)
