public class LockTwo implements Lock {
    private int victim;

    public void lock() {
        victim = i;
        while (victim == i) {};
    }

    public void unlock() {}
}

// LockTwo: Mutual exclusion, sequential deadlock, concurrent non-deadlock.