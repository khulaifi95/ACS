function d = distance(coordinates)

d = 0;
for n = 1 : length(coordinates)
    if n == length(coordinates)
        d = d + ceil(sqrt(sum((coordinates(:,n) - coordinates(:,1)).^2)/10));
    else    
        d = d + ceil(sqrt(sum((coordinates(:,n) - coordinates(:,n+1)).^2)/10));
    end
end
