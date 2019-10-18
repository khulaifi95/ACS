public class Peterson implements Lock {
    private int victim;
    private boolean[] flag = new boolean[2];
    
    public void lock() {
        flag[i] = true;
        victim = i;
        while (flag[j] && victim == i) {};
    }

    public void unlock() {
        flag[i] = false;
    }
}

// Peterson's algorithm is deadlock free and starvation free(only 2 threads).