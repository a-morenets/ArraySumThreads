/**
 * Created by a-morenets (alexey.morenets@gmail.com) on 24.12.2016.
 */
public class ArrayThread implements Runnable {
    private int[] arr;
    private int from;
    private int size;
    private long sum;

    public ArrayThread(int[] arr, int from, int size) {
        this.arr = arr;
        this.from = from;
        this.size = size;
    }

    public long getSum() {
        return sum;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    public void run() {
        for (int i = from; i < from + size; i++) {
            sum += arr[i];
        }
    }
}
