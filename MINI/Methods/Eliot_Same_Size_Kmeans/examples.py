# example.py

# Test algorithms
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.cluster import KMeans
from sklearn.datasets import load_digits
from SSKmeans import same_size_kmeans

# %matplotlib notebook # for notebooks

# Create dictionary for colors (plots)
color_dict = {0:'red', 1:'blue', 2: 'green', 3:'cyan', 4:'magenta', 5:'yellow', 6:'black', 7:'darkgreen', 8:'darkblue', 9:'blueviolet'}

# - SAME SIZE K-MEANS
# Define a some coordinates x1 and x2

# data = pd.read_csv("/home/kevinxu95/ACS/MINI/SPCA_order.csv")

data = pd.DataFrame({'x1': [1., 1., 1.5, 1.5, 2., 2, 3, 3, 3, 5, 5, 6, 6, 6, 6.5, 7, 8, 8, 8, 8, 4, 2, 1, 3, 2, 3, 5, 6, 3, 1, 5, 6, 7],
                     'x2': [1., 4., 1.5, 8., 1.5, 8, 2, 3, 4, 5, 6, 2, 3, 4, 5, 6.5, 3, 4, 5, 6, 6, 7, 2, 3, 5, 1, 5, 6, 6, 2, 1, 3, 4]})

#  Perform a Same Size K-means (3 clusters, no flexibility for size)
ssk_df = same_size_kmeans(data, k=3, size_flexibility=0, max_iter=10)

# Plot results
ssk_df['color'] = ssk_df.cluster_id.map(color_dict)

plt.scatter(ssk_df['x1'], ssk_df['x2'], color = ssk_df['color'])
plt.title('Clustering with Same Size K-means algorithm')
plt.show()
#

# - CLASSIC K-MEANS
# Define the same coordinates x1 and x2
kmeansdata = pd.DataFrame({'x1': [1., 1., 1.5, 1.5, 2., 2, 3, 3, 3, 5, 5, 6, 6, 6, 6.5, 7, 8, 8, 8, 8],
                           'x2': [1., 4., 1.5, 8., 1.5, 8, 2, 3, 4, 5, 6, 2, 3, 4, 5, 6.5, 3, 4, 5, 6]})

#  Perform a Classic K-means (3 clusters, no flexibility for size)
kmeans = KMeans(n_clusters=3).fit(kmeansdata)
kmeansdata['cluster_id'] = list(kmeans.labels_)

# Plot results
kmeansdata['color'] = kmeansdata.cluster_id.map(color_dict)

plt.scatter(kmeansdata['x1'], kmeansdata['x2'], color=kmeansdata['color'])
plt.title('Clustering with classic K-means algorithm')
plt.show()

#
