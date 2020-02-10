clear all;

initpath;

% Number of Principal Vectors to consider
Proj_Dim = 2;

% Load the data
% load data.no_price.norm.dat
load data.no_price.dat

Data = data;

% Compute PCA
[PC_Vals, PC_Vect] = pca(Data);

% Project to principal axes
Mean_Data = mean(Data);
Proj_Data = (Data - ones(size(Data,1),1) * Mean_Data) * PC_Vect(:, 1:Proj_Dim);


% Plot the 2-D projection
figure(1);

load labels.price;


N = size(labels,1);
for i=1:N
    
    if     labels(i) == 1
           plot(Proj_Data(i,1), Proj_Data(i,2), 'k*');
    elseif labels(i) == 2
           plot(Proj_Data(i,1), Proj_Data(i,2), 'bo');
    elseif labels(i) == 3
           plot(Proj_Data(i,1), Proj_Data(i,2), 'g+');
    elseif labels(i) == 4
           plot(Proj_Data(i,1), Proj_Data(i,2), 'rs');  
    end
    
    hold on;
end

title('2-D PCA projections');
hold off;
print boston.pca.eps -depsc;


% Plot the eigenvalue spectrum
figure(2);
plot(PC_Vals(1:13));
title('Eigenvalue spectrum');
hold off;
print boston.eigenvalues.eps -depsc;



%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Produce a cumulative sum plot
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

Sum_Eig = sum(PC_Vals);

% Normalize the eigenvalues
PC_Vals_Norm = PC_Vals/Sum_Eig;

% plot the cumulative sum
figure(3);
plot(cumsum(PC_Vals_Norm(1:13)));
hold on;
plot([0 12], [0.95 0.95],'--k');
title('Cumulative sum of eigenvalues');
hold off;
print boston.cumm_eig.eps -depsc;


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% First 2 principal components are needed 
% to explain 99% of the data
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
