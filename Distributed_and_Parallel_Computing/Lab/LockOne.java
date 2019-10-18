class LockOne implements Lock {
    private boolean[] flag = new boolean[2];
    
    public void lock() {
        int i = ThreadId.get();
        int j = 1 - i;
        flag[i] = true;
        while (flag[j]) {}
    }

    public void unlock() {
        int i = ThreadId.get();
        flag[i] = false;
    }
}

// LockOne fails deadlock-freedom – Concurrent execution can deadlock.