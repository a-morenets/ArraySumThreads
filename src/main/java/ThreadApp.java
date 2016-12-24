/**
 * Created by a-morenets (alexey.morenets@gmail.com) on 24.12.2016.
 */
public class ThreadApp {
    private int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private Runnable[] threads;

    public ThreadApp() {

    }

    private long sum(int[] arr) {
        Thread thread = new Thread(new ArrayThread(arr, 0, arr.length));
        return thread.getSum();
    }

    public static void main(String[] args) {
        ThreadApp app = new ThreadApp();
        System.out.println();
    }
}
