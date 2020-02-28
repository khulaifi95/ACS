%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%  Simple demonstration of the k-means clustering algorithm with k=6 %%
%%  Martin Russell, 8/2/2018                                          %%
%%                                                                    %%
%%  ADDITIONAL CODE NEEDED TO SOLVE MATLAB EXERCISE FROM WEEK 15 IN   %%
%%  UPPER CASE
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%
% Read in the data
X=load('lab2Data2.txt');
% The data is 5 dimensional, choose just the first 2 dimensions
X=X(:,1:2);
% t is the index for the number of separate runs of k-means clustering with
% different initial centroids
numExamples=20;
for t=1:1:numExamples
    % Choose a random set of initial centroid values
    C=(rand(6,2)-0.5)*20;
    % Find the number of data N points in X (use 'size')
    N=size(X,1);
    % Find the dimension F of the vectors
    F=size(X,2);
    % Find the number of centroids K (in fact, in this example this is set
    % to 6)
    K=size(C,1);
    % Clear all graphics
    clf;
    % Plot the data as a 2-dimensional scatter plot
    scatter(X(:,1),X(:,2));
    % 'hold on' keeps the current figure so that subsequent plots add to it
    % ratrher than replace it
    hold on;
    % Set the min and max values of the axes (rather than basing them on
    % the data)
    axis([-15 15 -15 15]);
    % Add the initial centroids to the scatter plot ((in black)
    scatter(C(:,1),C(:,2),'k','o','filled');
    hold on;
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    % THIS BIT IS REQUIRED FOR SOLUTION TO WEEK 15 EXERCISE             %
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    % EPSILON SPECIFIES HOW CLOSE SUCCESSIVE DISTORTIONS NEED TO BE TO STOP THE PROCESS
    epsilon=0.01;
    % INITIALISE THE DISTORTION VALUES (SO THERE IS SOMETHING TO COMPARE AT
    % THE START OF THE FIRST ITERATION
    OldDist=1000;
    NewDist=0;
    % i is the index for the number of iterations of k-means clustering
    i=0;
    %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    while (abs(NewDist - OldDist) > epsilon)
        % NEW
        % INCREMENT ITERATION COUNTER i
        i=i+1;
        OldDist=NewDist;
        % INITIALISE THE ACCUMULATOR TO MEASURE DISTORTION
        NewDist=0;
        % Calculate the distance matrix
        for k=1:1:K
            Y=X;
            % Subtract C(k,:) from each row of Y
            Y=Y-C(k,:);
            % Square the result
            Y=Y.^2;
            % Sum contributions from each dimension and take square root
            if k==1
                D=sqrt(sum(Y,2));
            else
                D=[D sqrt(sum(Y,2))];
            end
        end
        % End of calculating the distance matrix
        % Create space to accumulate data values to resestimate centroids
        C1=[0 0; 0 0; 0 0;0 0;0 0;0 0];
        % Create space to count how many data points correspond to each
        % centroid
        M=[0 0 0 0 0 0];
        % Accumulate data to reestimate centroids
        % For each centroid...
        for k=1:1:K
            % For each data point...
            for n=1:1:N
                % Is the nth data point closest to the kth centroid?              % point?
                if D(n,k)==min(D(n,:))
                    % If so, add nth data point to accumulator for kth
                    % centroid and...
                    C1(k,:)=C1(k,:)+X(n,:);
                    % ...increment the corresponding counter
                    M(k)=M(k)+1;
                    % ... AND FINALLY INCREMENT THE DISTORTION
                    NewDist=NewDist+D(n,k);
                end
            end
        end
        % End of accumulation of statistics
        % Now reestimate the centroids
        for k=1:1:K
            if M(k)>0
                C1(k,:)=C1(k,:)/M(k);
            end
        end
        % Add the new centroids to the plot (in green)
        pause(0.01);
        scatter(C1(:,1),C1(:,2),'g','o','filled');
        hold on;
        % PRINT OUT THE ITERATION NUMBER AND THE DISTORTION (NOT NEEDED FOR
        % THE EXERCISE BUT IT GIVES CONFIDENCE THAT THE PROGRAM IS WORKING
        % (WHY?)
        fprintf('iteration %d, distortion = %f\n',i,NewDist);
        % The new centroids become the old centroids ready to statr again
        for d=1:1:F
            C(:,d)=C1(:,d);
        end
    end
    % reestimation process is complete (maximum number of iterations
    % reached)
    % Add the final centroids to the plot as large red dots
    scatter(C1(:,1),C1(:,2),80,'r','o','filled');
    % End of experiment number t
    % Wait 4 seconds and start again
    pause(4);
    % hold on;
    % clf;
end

