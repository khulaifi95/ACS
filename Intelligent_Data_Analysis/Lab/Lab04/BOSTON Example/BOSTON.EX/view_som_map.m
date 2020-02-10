clear all;

load som.winners;
load labels.price;


N = size(labels,1);
for i=1:N
    
    if     labels(i) == 1
           plot(som(i,1), som(i,2), 'k*');
    elseif labels(i) == 2
           plot(som(i,1), som(i,2), 'bo');
    elseif labels(i) == 3
           plot(som(i,1), som(i,2), 'g+');
    elseif labels(i) == 4
           plot(som(i,1), som(i,2), 'rs');  
    end
    
    hold on;
end

title('Map');
% legend('Low','Medium','High','Very High');
print boston.som_map.eps -depsc;

close all;
