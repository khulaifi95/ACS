public class Bakery implements Lock {
    private boolean[] flag;
    private Label[] label;

    public Bakery (int n) {
        flag = new boolean[n];
        label = new Label[n];
        for (int i = 0; i < n; i++) {
            flag[i] = false; label[i] = 0;
        }
    }

    public void lock() {
        flag[i] = true;
        for (int j = 0; j < n; j++)
        label[i] = max(label[j]) + 1;
        while (flag[k] && label[i] > label[k] && i > k);
    }

    public void unlock() {
        flag[i] = false;
    }
}