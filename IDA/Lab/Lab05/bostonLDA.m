% Load the raw data set
D = load('Prototask.data');
%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% This implementation of   %
% LDA on the Boston data   %
% Focusses on the "nox"    %
% parameter                %
%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Extract matrix containing all columns except nox
X = [D(:,1:4) D(:,6:14)];% Calculate the mean vector
m = mean(X,1);
v = var(X,1);
% Subtract the mean vector from all rows of D1
X=(X-m)./sqrt(v);
% X=(X-m);
% X = [D(:,1:4) D(:,6:14)];
N=size(X,1);
% Extract price column
P = D(:,5);
% histogram(P);
% Partition nox values
high = 0.7;
low = 0.4;
% Set labels
X1=[];
X2=[];
X3=[];
for i=1:1:N
    if P(i) <= low 
        L(i)=1;
        X1=[X1;X(i,:)];
    elseif P(i) >= high 
        L(i)=3;
        X3=[X3;X(i,:)];
    else
        L(i)=2;
        X2=[X2;X(i,:)];
    end
end
% Calculate inclass covariance matrices
C1=cov(X1);
C2=cov(X2);
C3=cov(X3);
CW=(C1+C2+C3)/3;
% Calculate between-class covariance matrix
XB=[mean(X1,1);mean(X2,1);mean(X3,1)];
CB=cov(XB);
C = CB*inv(CW);
% Apply LDA
% Apply eigenvalue decomposition
[U,D]=eig(C);
% Non-zero eigenvalues are numbers 1 and 2
x=1:1:13;
for i=1:1:13
    y(i)=D(i,i);
end
% plot(x,y);
% xlabel('eigenvalue index');
% ylabel('eigenvalue');
% xlabel('dimension');
% ylabel('eigenvalue');
e1 = U(:,1);
e2 = U(:,2);
% Project data onto eigenvectors
x1=X*e1;
x2=X*e2;
% Plot
hold on;
scatter(x1(L==1),x2(L==1),'r');
scatter(x1(L==2),x2(L==2),'g');
scatter(x1(L==3),x2(L==3),'b');
legend('low','medium','high');
hold off;