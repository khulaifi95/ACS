class Filter implements Lock {
    private int[] level;    // level[i] for thread i
    private int[] victim;   // victim[L] for level L

    public Filter(int n) {
        level = new int[n];
        victim = new int[n];
        for (int i = 1; i < n; i++) {
            level[i] = 0;
        }
    }

    public void lock() {
        for (int L =1; L < n; L++) {
            level[i] = L;
            victim[L] = i;
            for (int k =1; k < n; k++)
                while ((level[k] >= L) && (victim[L] == i)) {};
        }
    }

    public void unlock() {
        level[i] = 0;
    }
}

// Filter: Mutual exclusion, no deadlock, starvation free, Fairness.