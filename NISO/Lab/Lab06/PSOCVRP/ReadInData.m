function [cities, demands, capacity] = ReadInData(fname)

% Open file
fid = fopen(fname,'rt');


costs =[];
dim = 0;
x = [];
y = [];
demands = [];
tline = fgets(fid);
while ischar(tline) 
    if findstr((tline),'DIMENSION')
        [name dim] = strread(tline, '%s %d', 'delimiter', ':');
        disp(tline)
    end    
    if findstr((tline),'CAPACITY')
        [name capacity] = strread(tline, '%s %d', 'delimiter', ':');
        disp(tline)
    end
    if findstr((tline),'NODE_COORD_SECTION')
        for i=1:dim
            tline = fgets(fid);
            [temp temp_x temp_y] = strread(tline, '%d %d %d');
            x = [x temp_x];
            y = [y temp_y];
        end
        cities = [x; y];    
    end 
    
    if findstr((tline),'DEMAND_SECTION')
        for i=1:dim
            tline = fgets(fid);
            [temp temp_demand] = strread(tline, '%d %d');
            demands = [demands temp_demand];
        end   
    end  
    
    
    tline = fgets(fid);
end

fclose(fid);

end
