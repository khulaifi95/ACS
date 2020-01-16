clear all;

initpath;

% Load the data
load data.no_price.norm.dat
% load data.no_price.dat

A1 = 3;
A2 = 6;

% Project to the selected axes
Proj_Data = [data(:,A1), data(:,A2)];


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

title(['2-D coordinate projections (',num2str(A1),',',num2str(A2),')']);
eval(['print boston.coord.proj.',num2str(A1),'.',num2str(A2),'.eps -depsc;']);
hold off;


