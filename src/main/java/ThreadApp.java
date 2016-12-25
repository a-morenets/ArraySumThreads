import java.util.Arrays;
import java.util.Random;

/**
 * Evaluating sum of array elements in several threads
 * Created by a-morenets (alexey.morenets@gmail.com) on 24.12.2016.
 */
public class ThreadApp {

    /* Array size */
    public static final int ARRAY_SIZE = 40_000_000;

    /* Number of threads */
    public static final int NUM_THREADS = 16;

    /**
     * Constructor
     */
    public ThreadApp() {
        process();
    }

    /**
     * Creates an array and fills it with unique numbers
     * @param size size of new array
     * @return shuffled array filled with unique numbers
     */
    private int[] createArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        return arr;
    }

    /**
     * In-place shuffle array elements by replacing each element with randomly selected one
     * @param arr given array
     */
    private void shuffle(int[] arr) {
        Random rnd = new Random();

        for (int anArr : arr) {
            int i1 = rnd.nextInt(arr.length);
            int i2 = rnd.nextInt(arr.length);
            swap(arr, i1, i2);
        }
    }

    /**
     * Swap two array elements
     * @param arr given array
     * @param i1  index of 1st element
     * @param i2  index of 2nd element
     */
    private void swap(int[] arr, int i1, int i2) {
        int tmp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = tmp;
    }

    /**
     * Evaluates sum of all array elements adding all elements in one loop sequentially
     * @param arr given array
     * @return sum of all array elements
     */
    private long linearSum(int[] arr) {
        long sum = 0;
        for (int a : arr) {
            sum += a;
        }
        return sum;
    }

    /**
     * Evaluates partial sums of all array elements in several threads
     * @param arr        given array
     * @param numThreads number of threads
     * @return sum of all array elements
     */
    private long sum(int[] arr, int numThreads) {
        ArrayThread[] arrayThreads = new ArrayThread[numThreads];
        Thread[] threads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            int from = i * arr.length / numThreads;
            int to = (i + 1) * arr.length / numThreads;

            arrayThreads[i] = new ArrayThread(arr, from, to);
            threads[i] = new Thread(arrayThreads[i]);
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        return ArrayThread.sum;
        return Arrays.stream(arrayThreads).mapToLong(ArrayThread::getSum).sum();
    }

    /**
     * Runner
     */
    private void process() {
        System.out.println("Creating array...");
        int[] arr = createArray(ARRAY_SIZE);
//        System.out.println("Shuffling array...");
//        shuffle(arr);
        System.out.println("Linear sum...");
        System.out.println("LinearSum = " + linearSum(arr));
        System.out.println("Thread sum...");
        System.out.println("ThreadSum = " + sum(arr, NUM_THREADS));

    }

    /**
     * Main function
     */
    public static void main(String[] args) {
        new ThreadApp();
    }
}
