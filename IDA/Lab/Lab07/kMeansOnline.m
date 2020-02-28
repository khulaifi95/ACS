%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% SOM code for "Intelligent Data Analysis %
% Martin Russell                          %
% School of Computer Science              %
% University of Birmingham                %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%
% Red data from file
file='C:\Users\Martin\Dropbox\MATLAB\SOM\csvdata';
X=load(file);
s=size(X);
% Calculate number of data points
NX=s(1);
%
% Use columns 2 and 3 as data (column 1 is class label)
Y=X(:,2:3);
%
% Choose initial values for centroids
NC=5;
scale=1;
C=rand(NC,2)-0.5;
%
% Plot the data and centroids
hold on;
scatter(Y(1:300,1),Y(1:300,2),'r');
scatter(Y(301:600,1),Y(301:600,2),'g');
scatter(Y(601:900,1),Y(601:900,2),'b');
scatter(C(:,1),C(:,2),'y','filled');
% plot(C(:,1),C(:,2),'k');
axis([-2 2 -2 2]);
hold off;
%
% SOM learning algorithm
% Initial learning rate lr;
lr=1.0;
eta=1.0;
% Time constant%
%tau=10000.0;
tau=1000;
% Time
time=0;
% initial neighbourhood width
initsigma=5.0;
while eta>0.0001
% for i=1:1:300
%     time=time+1;
%     % Update learning rate
%     eta=lr*exp(-time/tau);
%     % Update sigma
%     sigma=initsigma*exp(-i/tau);
    % Calculate distances between centriods and data points
    % Uses squared Euclidean distance
    dist=zeros(NX,NC);
    for c=1:1:NC
        Z=Y-C(c,:);
        dist(:,c)=sum(Z.*Z,2);
    end
    % Calculate closest centroid for each data point
    for n=1:1:NX
        time=time+1;
        % Update learning rate
        eta=lr*exp(-time/tau);
        % Update sigma
        sigma=initsigma*exp(-time/tau);
        % Process nth sample
        [m,am]=min(dist(n,:));
        %
        C(am,:)=C(am,:)+eta*(Y(n,:)-C(am,:));
%         % Now cycle through the centroids and update then
%         for c=1:1:NC    
%             % Update neighbourhood link strength
%             nbhd=exp(-1*abs(c-am)/sigma);
%             % Increment cth centroid
%             C(c,:)=C(c,:)+nbhd*eta*(Y(n,:)-C(c,:));
%         end
    end
    % Plot the data and centroids
end
%if mod(i,100)==0
    hold on;
    scatter(Y(1:300,1),Y(1:300,2),'r');
    scatter(Y(301:600,1),Y(301:600,2),'g');
    scatter(Y(601:900,1),Y(601:900,2),'b');
    scatter(C(:,1),C(:,2),'k','filled');
    %plot(C(:,1),C(:,2),'k');
    axis([-2 2 -2 2]);
%end
