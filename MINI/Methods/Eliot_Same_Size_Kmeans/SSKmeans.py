# SSKmeans.py

# - Import packages - #
import numpy as np
import pandas as pd
import math
import numbers
from collections import Counter

# - Functions - #


# - Initialization part
def cluster_size(df, k, flexibility=0):
    """
    Compute the number of items allowed in each cluster. Allow a flexibility to create almost same size clusters.

    Args:
        df: pandas DataFrame, shape=(n_samples, n_features).
        k: int, The number of clusters to form as well as the number of centroids to generate.
        flexibility: int | float (default 0), flexibility allowed for the size of clusters.

            float numbers under 1 represent the percentage of variation allowed for cluster's size.
            Example: 0.2 for ±20% variation per cluster.

            integer numbers over 1 represent the number of observation variation allowed for cluster's size.
            Example: 5 for ±5 items per cluster.

            0 represents a fixed sized without any variation allowed.

    Returns:
        n_max: int, maximum size of a cluster. Needed in initialization part. (Unused)
        n_min: int, minimum size of a cluster. Needed in initialization part.
        n_min_flex: int, minimum size of a cluster (including the flexibility). Needed in iteration part.
        n_max_flex: int, maximum size of a cluster (including the flexibility). Needed in iteration part.

    To do:
        Conditions for floats over 1 and integers over cluster size and k over df.shape

    """

    # Get the total number of items if the DataFrame
    n_obs = df.shape[0]

    # Get the estimated size of clusters (total number of items / number of clusters)
    if (n_obs % k == 0) and (flexibility == 0):
        # Case of equal sized clusters
        n_min = n_min_flex = n_obs/k
        n_max = n_max_flex = n_obs/k
        
    else:
        # Case of unequal sized clusters
        n_min = math.floor(n_obs/k)
        n_max = math.ceil(n_obs/k)
        
        if flexibility >= 1:
            n_min_flex = n_min - flexibility
            n_max_flex = n_max + flexibility
        else:
            n_min_flex = n_min - math.floor(flexibility * math.floor(n_obs/k))
            n_max_flex = n_max + math.floor(flexibility * math.floor(n_obs/k))

    return int(n_max), int(n_min), int(n_max_flex), int(n_min_flex)


# - Core
def check_random_state(seed):
    """
    Turn seed into a np.random.RandomState instance

    Args:
        seed: None | int | instance of RandomState
            If seed is None, return the RandomState singleton used by np.random.
            If seed is an int, return a new RandomState instance seeded with seed.
            If seed is already a RandomState instance, return it.
            Otherwise raise ValueError.

    Returns:
        RandomState instance

    """

    if seed is None or seed is np.random:
        return np.random.mtrand._rand
    if isinstance(seed, numbers.Integral):
        return np.random.RandomState(seed)
    if isinstance(seed, np.random.RandomState):
        return seed
    raise ValueError('%r cannot be used to seed a numpy.random.RandomState instance' % seed)


# - Initialization part
def get_random_centers(df, k, seed=None):
    """
    Initialise random centers for k clusters based on item's features.

    Args:
        df: pandas DataFrame, shape=(n_samples, n_features). Only features used to compute centroids.
        k: int, The number of clusters to form as well as the number of centroids to generate.
        seed: int | RandomState instance | None (default), Determines random number generation for
            centroid initialization. Use an int to make the randomness deterministic.

    Returns:
        dict_cluster_center: dict, cluster's id (as key) and an array of features as the centroid of clusters (as value)
        centers: array-like, shape (n_clusters, n_features) with features of the centroid of clusters

    To do:
        check columns types (numeric only)
    """

    # Get a correct value for the seed
    random_state = check_random_state(seed)

    # Get k random centers
    centers = df.sample(k, random_state=random_state).values

    # Store centers in a dictionary, key is the id of the cluster
    dict_cluster_center = {}

    for k_ in range(k):
        dict_cluster_center[k_] = centers[k_]

    return dict_cluster_center


# - Core
def get_distance(x, y, distance='euclidean'):
    """
        Compute the distance between 2 elements (array-like).

    Args:
        x: array-like, input array.
        y: array-like, input array.
        distance: str (default is 'euclidean'), type of distance to compute.

    Returns:
        float, the distance between vectors x and y.

    To do:
        Add other measures
        Raise errors for non-numeric array

    """

    if distance == 'euclidean':
        return np.linalg.norm(x - y)
    else:
        # Add other distances later
        return np.linalg.norm(x - y)


# - Initialization part
def cluster_initialization(df, k_centers, n_min):
    """
    Find and assign closest points to each cluster as an initialization process.

    Args:
        df: pandas DataFrame, shape=(n_samples, n_features). Only features used to compute centroids.
        k_centers: dict, cluster's id (as key) and an array of features as the centroid of clusters (as value)
        n_min: int, minimum number of item per cluster

    Returns:
        dict_assigned_items2cluster: dict, item index (as key) and cluster assignation (as value)

    To do:
        Find a better assignment for last items

    """

    dict_assigned_items2cluster = {}

    # Iter over all centroids
    for k_id, k_center in k_centers.items():
        # Compute distances to the centroid
        distances = df.apply(lambda x: get_distance(x.values, k_center), axis=1)  # default is euclidean

        # Get up to n_min closest items
        closest_idx = distances.nsmallest(n=n_min, keep='first').index

        # Assign in a dictionary each item index (as key) its cluster id (as value)
        for idx in closest_idx:
            dict_assigned_items2cluster[idx] = k_id

        # Remove already assigned item
        df = df[~df.index.isin(closest_idx)]
    
    # For last items (if total number of items is not divisible by the number of clusters)
    if df.shape[0] != 0:
        # Find a cluster for last item. Temporary option: random dispatch
        for i in range(df.shape[0]):
            dict_assigned_items2cluster[df.index[i]] = list(k_centers.keys())[i]

    return dict_assigned_items2cluster


# - Iteration part
def compute_cluster_center(df, col_cluster='cluster_id', cols_centroid=None):
    """
    Compute the position of the centroid of each cluster.

    Args:
        df: pandas DataFrame, shape=(n_samples, n_features).
            Only features used to compute centroids and 'current assignment clusters' column.
            Can use it raw and specify columns with the 2 other arguments.
        col_cluster: str (default 'cluster_id'), name of column containing 'current assignment clusters'.
        cols_centroid: None | list (default None), list of columns containing features to use to compute centroids.

    Returns:
        dict_cluster_center: dict, cluster's id (as key) and an array of features as the centroid of clusters (as value)

    To do: Raise errors for list type or values in it
    """

    dict_cluster_center = {}

    # Filter DataFrame with features columns (and cluster_id one), if needed. Use a copy to not modify input list.
    cols_to_keep = cols_centroid.copy()

    if (cols_to_keep is not None) and (type(cols_to_keep) == list):
        cols_to_keep.append(col_cluster)
        df = df[cols_to_keep]

    # Group by the cluster id column, get mean for features columns
    grouped_df = df.groupby(col_cluster).mean()

    # Store centers in a dictionary, key is the id of the cluster
    for i in grouped_df.index:

        dict_cluster_center[int(i)] = grouped_df.iloc[int(i)].values
        
    return dict_cluster_center


# - Iteration part
def get_distance2centers(df, k_centers, cols_centroid=None):
    """
    Compute the distance between all items and clusters.

    Args:
        df: pandas DataFrame, shape=(n_samples, n_features). Only features used to compute centroids.
        k_centers: dict, cluster's id (as key) and an array of features as the centroid of clusters (as value)
        cols_centroid: None | list (default None), list of columns containing features to use to compute centroids.

    Returns:
        dist_to_centers_dict: nested dict,
            First level: items (as key) and a dict of distance to clusters
            Second level: clusters id (as key) and the distance (between 1st level key and 2nd level key) (as value).

    To do:
        Consistency with args of the previous function 'compute_cluster_center'
        Check if another format could be better than a nested dict

    """

    # Filter DataFrame with features columns, if needed
    if (cols_centroid is not None) and (type(cols_centroid) == list):

        df = df[cols_centroid]
    # Initialize 1st level dictionary
    dist_to_centers_dict = {}

    # Iter over all item
    for index, row in df.iterrows():
        # Iter (dict comprehension) over all clusters and compute each distance to centroids.
        # Create the 2nd level dictionary
        distance2k = {k_id: get_distance(row.values, k_center, 'euclidean') for k_id, k_center in k_centers.items()}

        # Complete the 1st level dictionary with item (as key) and the sub-dictionary previously computed
        dist_to_centers_dict[index] = distance2k
        
    return dist_to_centers_dict


# - Iteration part
def get_current_assignation(df, col_cluster='cluster_id'):
    """
    Create a dictionary to assign item to their cluster_id based on a DataFrame
    Args:
        df: pandas DataFrame, shape=(n_samples, n_features).
        col_cluster: str (default 'cluster_id'), name of column containing 'current assignment clusters'.

    Returns:
        dict, item index (as key) and cluster assignation (as value)

    """

    return pd.Series(df[col_cluster].values.astype(int), index=df.index.values.astype(int)).to_dict()


# - Iteration part
def delta_with_current_cluster(dict_dist_to_centers, dict_current_centers):
    """
    Compute the difference between the distance to current assignation and the distance to other centers

    Args:
        dict_dist_to_centers: nested dict, items (as main-key) and a dict (as main-value) of
            clusters id (as sub-key) and the distance (between 1st level key and 2nd level key) (as sub-value).

        dict_current_centers: dict, item index (as key) and cluster assignation (as value)

    Returns:
        list_all_other_differences: list,
            contain all combination tuples (item, cluster, distance difference with current assignation)
        dict_candidates_to_switch: nested dict,
            First level: items (as key) and a dict of gain to a cluster
            Second level: clusters id (as key) and the distance gain (compare to current item assignation) (as value).

    To do:
        Find less heavy way of thinking

    """
    dict_candidates_to_swap = {}
    all_other_differences = []

    for item, dict_distances_cluster in dict_dist_to_centers.items():  # key = item_id value: sub dict of possibilities
        current_cluster = dict_current_centers[item]  # get the current cluster of the item
        dict_gain_to_new_cluster = {}
        all_distances = []

        for cl_id, _ in dict_distances_cluster.items():  # value = cluster_id
            if cl_id != current_cluster:
                # For all other cluster, compute the difference between the distance of current assignation and an other
                distance_difference = dict_distances_cluster[current_cluster] - dict_distances_cluster[cl_id]

                # Keep track of all potential gain/loss to each cluster
                all_distances.append((cl_id, distance_difference))

                # Keep gain of distance as item to swap to improve overall clustering
                if distance_difference > 0:  # a positive difference means the item is closest to another centroid
                    dict_gain_to_new_cluster[cl_id] = distance_difference

        # Reshape sublist (cluster_id,  distance) by adding item in each tuple to get (item, cluster_id, distance)
        all_distances = [(item, x[0], x[1]) for x in all_distances]

        # Add it to overall list, will have to be flattened
        all_other_differences.append(all_distances)
        
        # From the item to swap (improving the overall clustering),
        # keep only non-empty ones into a new dictionary with item as kayΩ
        if bool(dict_gain_to_new_cluster):
            dict_candidates_to_swap[item] = dict_gain_to_new_cluster

    # Flatten the list of list containing all tuples (item, cluster_id, distance)
    list_all_other_differences = [item for sublist in all_other_differences for item in sublist]
        
    return list_all_other_differences, dict_candidates_to_swap  # dict(index: dict(cluster: gain))


# - Iteration part
def move_items(dict_candidates_to_swap, current_clusters, n_min, n_max):
    """
    Move items from one cluster to another if there is a benefit and if the size constraint allows it.

    Args:
        dict_candidates_to_swap: nested dict, item index (as key) and a dict (as value) of
            clusters id (as sub-key) and the distance gain (as sub-value).
        current_clusters:  dict, item index (as key) and cluster assignation (as value)
        n_min: int, minimum size of a cluster (including the flexibility)
        n_max: int, maximum size of a cluster (including the flexibility)

    Returns:
        dict_candidates_to_swap: nested dict, input dictionary imputed from moved without swap items
        current_assign: dict, input dictionary updated with new assignment with moved items
        candidates_key_remove: list, list of items moved to a new cluster

    """
    candidates_key_remove = []

    # Loop over items that could improve the clustering
    for item, swap_cluster in dict_candidates_to_swap.items():

        # Get the current size of clusters. current_assign will be updated in the loop
        dict_cl_count = Counter(list(current_clusters.values()))

        # Get current cluster of the item
        item_current_cluster = current_clusters[item]

        # Check if the candidate can be moved
        if dict_cl_count[item_current_cluster] > n_min:

            # Transform sub-dict to a sorted list of tuples
            tuple_cl_dist = list(swap_cluster.items())
            sorted_tuple_cl_dist = sorted(tuple_cl_dist, key=lambda x: x[1], reverse=True)

            for i in range(len(sorted_tuple_cl_dist)):
                if dict_cl_count[sorted_tuple_cl_dist[i][0]] < n_max:
                    # Update current assignment
                    current_clusters[item] = sorted_tuple_cl_dist[i][0]

                    # Update candidates list
                    candidates_key_remove.append(item)

                    # Break the loop, item has been moved
                    break

    # Update list of potential swaps by removing already moved items
    for x in candidates_key_remove:
        dict_candidates_to_swap.pop(x, None)

    return dict_candidates_to_swap, current_clusters, candidates_key_remove


# - Iteration part
def swap_items(deltas, dict_candidates_to_swap, current_clusters, treated_items):
    """
    Swap items between 2 clusters if there is a global benefit. There is a benefit if the distance gain by the first
    item is bigger than the distance lost by the second. If there is a mutual gain, it's even better.

    Args:
        deltas: list, contain all combination tuples (item, cluster, distance difference with current assignation)
        dict_candidates_to_swap: nested dict, item index (as key) and a dict (as value) of
            clusters id (as sub-key) and the distance gain (as sub-value).
        current_clusters: dict, item index (as key) and cluster assignation (as value)
        treated_items: list, list of item already treated in the move function

    Returns:
        swapped_items: dict, item index (as key) and new cluster assignation (as value)

    To do:
        Optimize this mess

    """

    swapped_items = current_clusters

    # Loop over items that could improve the clustering
    for item, dict_swap_cluster in dict_candidates_to_swap.items():

        # Check if item hasn't been treated before
        if item not in treated_items:
            # Transform sub-dict to a sorted list of tuples
            tuple_cluster_dist = list(dict_swap_cluster.items())
            sorted_tuple_cl_dist = sorted(tuple_cluster_dist, key=lambda x: x[1], reverse=True)

            # Loop over possible cluster (for swap)
            for i in range(len(sorted_tuple_cl_dist)):

                # Get loop elements: Potential cluster & distance to it
                destination_cluster = sorted_tuple_cl_dist[i][0]
                dist_improve = sorted_tuple_cl_dist[i][1]

                # Get item current cluster
                item_current_cluster = current_clusters[item]

                # Get list of items of the destination cluster
                items_in_destination = [k for k, v in current_clusters.items() if v == destination_cluster]

                # Find potential items from list of deltas for not already treated items in destination cluster
                # having a distance to the current cluster of item to swap
                deltas_cluster = [x for x in deltas if x[0] in items_in_destination and x[1] == item_current_cluster and x[0] not in treated_items]

                # Sort according to distance to find the best candidate for the swap
                deltas_cluster = sorted(deltas_cluster, key=lambda x: x[2], reverse=True)

                # Get distance for the swapped candidate
                d = deltas_cluster[0][2]

                # If there is a benefit pure or if cost on one side is bellow benefit on the other side
                if ((d < 0) and (abs(d) < dist_improve)) or (d > 0):  # distance of 1st elt (which is min)
                    swapped_items[item] = destination_cluster  # update assignation of the item
                    swapped_items[deltas_cluster[0][0]] = item_current_cluster  # update assignation of the swapped

                    # Add item to the list of item already treated
                    treated_items.append(item)
                    treated_items.append(deltas_cluster[0][0])

                    break  # break the loop, item already swapped

    # At the end of the loop, all untouched item have to remain in their cluster
    keys_remaining = [x for x in list(current_clusters.keys()) if x not in treated_items]  # get untouched items

    for x in keys_remaining:
        swapped_items[x] = current_clusters[x]

    return swapped_items


def optimize_items_assignment(dict_candidates_to_swap, current_clusters, deltas, n_min, n_max):
    """
    Function running 'move_items' function and then 'swap_items' function to get a better global assignation

    Args:
        dict_candidates_to_swap: nested dict, item index (as key) and a dict (as value) of
            clusters id (as sub-key) and the distance gain (as sub-value).
        current_clusters: dict, item index (as key) and cluster assignation (as value)
        deltas: list, contain all combination tuples (item, cluster, distance difference with current assignation)
        n_min: int, minimum size of a cluster (including the flexibility)
        n_max: int, maximum size of a cluster (including the flexibility)

    Returns:
        new_clusters: dict, item index (as key) and new cluster assignation (as value)

    """

    # Step one: move candidates (if possible) to improve global clustering
    dict_candidates_to_swap, current_clusters, treated_items = move_items(dict_candidates_to_swap,
                                                                          current_clusters, n_min, n_max)

    # Step two: swap candidates to improve global clustering
    new_clusters = swap_items(deltas, dict_candidates_to_swap, current_clusters, treated_items)

    return new_clusters


# - Core
def same_size_kmeans(df, k, size_flexibility=0, max_iter=100, seed=None):
    """

    Args:
        df: pandas DataFrame of shape (n_samples, n_features). Must contain only columns used for the clustering.
            Important note: index of the DataFrame will be overwrite.
        k: int, The number of clusters to form as well as the number of centroids to generate.
        size_flexibility: int | float (default 0), flexibility allowed for the size of clusters.

            float numbers under 1 represent the percentage of variation allowed for cluster's size.
            Example: 0.2 for ±20% variation per cluster.

            integer numbers over 1 represent the number of observation variation allowed for cluster's size.
            Example: 5 for ±5 items per cluster.

            0 represents a fixed sized without any variation allowed.

        max_iter: int (default 100), Number of loop performed to optimize the same_size_kmeans algorithm
        seed: int | RandomState instance | None (default), Determines random number generation for
            centroid initialization. Use an int to make the randomness deterministic.

    Returns:
        df: pandas DataFrame of shape (n_samples, n_features) with initial features and a 'cluster_id' column.
            Important note: index of the DataFrame is reset.

    To do:
        Cut loop if there is no more change or an optimization criterion is no more improved

    """
    # - Transform DataFrame et get main information
    # Get features columns
    df_cols = list(df.columns)

    # Reset index to be sure about the uniqueness
    df = df.reset_index()

    # - Initialization part
    # Get cluster sizes
    n_max, n_min, n_max_flex, n_min_flex = cluster_size(df=df, k=k, flexibility=size_flexibility)

    # Get random centroids for clusters
    dict_cl_centers = get_random_centers(df=df, k=k, seed=seed)

    # Assign points to closest cluster
    dict_assign = cluster_initialization(df=df, k_centers=dict_cl_centers, n_min=n_min)

    # Map clusters to initial DataFrame
    df['cluster_id'] = df.index.map(dict_assign)

    # - Iteration part
    for i in range(max_iter):

        # Compute new centroids for clusters
        dict_new_cl_centers = compute_cluster_center(df=df, col_cluster='cluster_id', cols_centroid=df_cols)

        # Compute distance between items and all clusters centroids
        dict_distances = get_distance2centers(df=df, k_centers=dict_new_cl_centers, cols_centroid=df_cols)

        # Get the current cluster's belonging of items
        # Later_check ??
        dict_current_cl = get_current_assignation(df=df, col_cluster='cluster_id')

        # Compute delta distance for all items between their current assignation and all other clusters
        deltas, dict_positive_swap_deltas = delta_with_current_cluster(dict_dist_to_centers=dict_distances,
                                                                       dict_current_centers=dict_current_cl)

        dict_new_cl_assign = optimize_items_assignment(dict_candidates_to_swap=dict_positive_swap_deltas,
                                                       current_clusters=dict_current_cl, deltas=deltas,
                                                       n_min=n_min_flex, n_max=n_max_flex)

    df['cluster_id'] = df.index.map(dict_new_cl_assign)

    return df



