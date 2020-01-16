clear all;

load data.no_price.dat;
Num_Data_Pnts = size(data,1);
Dim = size(data,2);

% Center the data
mu = mean(data);
data  = data - ones(Num_Data_Pnts,1) * mu;

% Co-ordinate-wise st. dev = 1.

for d=1:Dim
    data(:,d) = data(:,d)./std(data(:,d));
end
    
save data.no_price.norm.dat data -ascii;
   
    
