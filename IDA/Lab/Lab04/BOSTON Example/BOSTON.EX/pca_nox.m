clear all;

initpath;

# Load the data
load Prototask.data;
A = Prototask;

# Seperate dataset into original data and labels
L = A(:, 5);
X = sortrows(A, 5);
X(:, 5) = [];

# Label the nox with Low, Medium and High
H = hist(L, 10);
l1 = sum(H(1:3));
l2 = l1 + sum(H(4:6));
l3 = l2 + sum(H(7:10));



# Normalise the columns
m = mean(X,1);
v = var(X,1);
X = (X-m)./sqrt(v);

# Calculate the covariance matrix 
N = rows(X);
C = X'*X/(N-1);

# Apply eigenvalue decomposition
[U,D] = eig(C);

# Analyse eigenvalues of each column
E = max(D, [], 1);
[e,idx] = sort(E,'descend');

# Return the first two biggest eigenvalues
d1 = idx(1);
d2 = idx(2);

# Identify the two principal components
e1 = U(:, d1);
e2 = U(:, d2);

# Project the data points onto the plane
x1 = X*e1;
x2 = X*e2;

# Plot the data
title('2-D PCA projections');
scatter(x1(1:l1, :), x2(1:l1, :), 'r');
hold on;
scatter(x1(l1+1:l2, :), x2(l1+1:l2, :), 'g');
hold on;
scatter(x1(l2+1:l3, :), x2(l2+1:l3, :), 'b');
