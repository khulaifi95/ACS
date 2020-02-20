function [fbestval,bestparticle] = PSOPC(fname, NDim, MaxIter, PopSize)

%% Real coded GA for Matlab
% Copyright (C) 2014-2016 Shan He, the University of Birmingham
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
c1 = .6;       % PSO parameter C1
c2 = .6;       % PSO parameter C2
c3 = 0.6;
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
vmax = ones(PopSize, NDim);

%% We need to set the maximum V
for i=1:NDim
    vmax(:, i)=(UpperBound(:,i)-LowerBound(:,i))/2;
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
[fsortval, sortindex] = sort(fvalue);
while(flag == 0) & (iteration < MaxIter)
    iteration = iteration +1;
    w = startwaight - iteration*waightstep;
    gbest = X(index, :);
    for i=1:PopSize
        
        R1 = rand(1, NDim);
        R2 = rand(1, NDim);
        R3 = rand(1, NDim);
        %
        
        randindex = randi(PopSize);
        rparticle = X(randindex,:);

        V(i,:) = w*V(i,:) + c1*R1.*(pbest(i,:)-X(i,:)) + c2*R2.*(gbest-X(i,:)) + c3*R3.*(rparticle-X(i,:));
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


