function [fbestval,bestparticle] = PSO(fname, NDim, MaxIter, PopSize)

%% Real coded GA for Matlab
% Copyright (C) 2014-2016 Shan He, the University of Birmingham
% Input Arguments:
%   fname       - the name of the evaluation .m function
%   NDim        - dimension of the evalation function
%   MaxIter     - maximum iteration
%   PopSize     - number of particles

%% Parameters
flag=0;
iteration = 0;
c1 = .6;       % PSO parameter C1
c2 = .6;       % PSO parameter C2
w=0.8;          % Inertia weigth
% decrease the inertia
startwaight = 0.8;
endwaight = 0.4;
waightstep = (startwaight-endwaight)/MaxIter;


%% Lower/Upper bounds
Bound=eval(fname);
% Defined lower bound and upper bound.
LowerBound = zeros(PopSize, NDim);
UpperBound = zeros(PopSize, NDim);
for i=1:PopSize
    LowerBound(i,:)=Bound(:,1);
    UpperBound(i,:)=Bound(:,2);
end


X =  rand(PopSize, NDim).*(UpperBound-LowerBound) + LowerBound;     % Initialize swarm X


%% We need to set the maximum V
vmax = ones(PopSize, NDim);
for i=1:NDim
    vmax(:, i)=(UpperBound(:,i)-LowerBound(:,i))/2;
end
V = vmax.*rand(PopSize, NDim);     % Initialize V

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
[fsortval, sortindex] = sort(fvalue);
while(flag == 0) & (iteration < MaxIter)
    iteration = iteration +1;
    % Save current particle for visualisation
    previous_X = X;
    w = startwaight - iteration*waightstep;
    gbest = X(index, :);
    for i=1:PopSize
        
        R1 = rand(1, NDim);
        R2 = rand(1, NDim);


        V(i,:) = w*V(i,:) + c1*R1.*(pbest(i,:)-X(i,:)) + c2*R2.*(gbest-X(i,:)); 
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
    disp(['Best fitenss = ', num2str(fbestval)]);
    popvisualisation(fname, X, previous_X, iteration, MaxIter)
end

bestparticle = X(index, :)


