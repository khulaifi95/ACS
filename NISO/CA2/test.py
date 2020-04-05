height = [0,1,0,2,1,0,1,3,2,1,2,1]
i = 0

while i < len(height) - 1:
    if height[i] == 0:
        i += 1
        break

print(i)