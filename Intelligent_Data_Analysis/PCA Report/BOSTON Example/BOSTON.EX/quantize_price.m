
load price.dat;

hist(price,25);
xlabel('prices');
title('Boston data - histogram of prices');
hold on;
T1 = 16;
T2 = 26;
T3 = 37;

plot([T1,T1], [0,70], 'r--');
plot([T2,T2], [0,70], 'r--');
plot([T3,T3], [0,70], 'r--');

%print prices.hist.eps -depsc;



N_Data = size(price,1);
Labels = ones(N_Data,1);

for i=1:N_Data
    
    if     price(i) < T1
           Labels(i) = 1;
    elseif price(i) < T2
           Labels(i) = 2;
    elseif price(i) < T3
           Labels(i) = 3;
    else   
           Labels(i) = 4;	   
    end
end

save labels.price Labels -ascii;
    	 
