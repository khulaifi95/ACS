public class LockTwo implements Lock {
    private int victim;
    
    public void lock() {
        victim = i;
        while (victim == i) {};
    }

    public void unlock() {}
}

// LockTwo is not deadlock free – Sequential execution deadlocks, 
// if one thread never tries to get the lock.