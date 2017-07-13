/**
 * Represents one thread implementing Runnable interface
 * Created by Oleksii on 04.07.2017.
 */
public class ArrayRunnable implements Runnable {
    private Thread thread;
    private ArrayBox arrayBox;
    private int from;
    private int to;
    private long localSum;

    public ArrayRunnable(ArrayBox arrayBox, int from, int to) {
        this.arrayBox = arrayBox;
        this.from = from;
        this.to = to;
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void run() {
        for (int i = from; i < to; i++) {
            localSum += arrayBox.get(i);
        }
    }

    public Thread getThread() {
        return thread;
    }

    public long getLocalSum() {
        return localSum;
    }
}
