import java.util.Random;

/**
 * Created by a-morenets (alexey.morenets@gmail.com) on 24.12.2016.
 */
public class ThreadApp {

    public static final int ARRAY_SIZE = 40_000_000;

    public ThreadApp() {
        process();
    }

    private int[] fillArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        shuffle(arr);
        return arr;
    }

    private void shuffle(int[] arr) {
        Random rnd = new Random();

        for (int anArr : arr) {
            int i1 = rnd.nextInt(arr.length);
            int i2 = rnd.nextInt(arr.length);
            swap(arr, i1, i2);
        }
    }

    private void swap(int[] arr, int i1, int i2) {
        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }

    private long linearSum(int[] arr) {
        long sum = 0;
        for (int item : arr) {
            sum += item;
        }
        return sum;
    }

    private long sum(int[] arr, int numParts) {
        ArrayThread[] arrThreads = new ArrayThread[numParts];

        for (int i = 0; i < numParts; i++) {
            arrThreads[i] = new ArrayThread(arr, i * arr.length / numParts, arr.length / numParts);
            Thread thread = new Thread(arrThreads[i]);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long sum = 0;
        for (ArrayThread thrd : arrThreads ) {
            sum += thrd.getSum();
        }

        return sum;
    }

    private void process() {
        System.out.println("Creating array...");
        int[] arr = fillArray(ARRAY_SIZE);
        System.out.println("Linear sum...");
        System.out.println("LinearSum = " + linearSum(arr));
        System.out.println("Thread sum...");
        System.out.println("ThreadSum = " + sum(arr, 4));

    }

    public static void main(String[] args) {
        ThreadApp app = new ThreadApp();
    }
}
