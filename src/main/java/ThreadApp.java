/**
 * Evaluating sum of array elements in several threads
 * Created by a-morenets (alexey.morenets@gmail.com) on 24.12.2016.
 */
public class ThreadApp {
    private static final int ARRAY_SIZE = 1_234_567;
    private static final int NUM_THREADS = 11;

    /**
     * Evaluates partial sums of all array elements in several threads
     * @param arrayBox   given array
     * @param numThreads number of threads
     * @return sum of all array elements
     */
    private long threadSum(ArrayBox arrayBox, int numThreads) {
        ArrayRunnable[] threads = new ArrayRunnable[numThreads];

        int from = 0;
        int to = 0;
        for (int i = 0; i < numThreads - 1; i++) {
            from = arrayBox.size() / numThreads * i;
            to = arrayBox.size() / numThreads * (i + 1);
            threads[i] = new ArrayRunnable(arrayBox, from, to);
        }
        threads[numThreads - 1] = new ArrayRunnable(arrayBox, to, arrayBox.size());

        for (ArrayRunnable thread : threads) {
            try {
                thread.getThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long totalSum = 0;
        for (ArrayRunnable thread : threads) {
            totalSum += thread.getLocalSum();
        }
        return totalSum;
    }

    /**
     * Runner
     */
    private void process() {
        System.out.println("Creating array...");
        ArrayBox arrayBox = new ArrayBox(ARRAY_SIZE);
        arrayBox.fillArray();
        System.out.println("Shuffling array...");
        arrayBox.shuffle();
//        arrayBox.printArray();
        System.out.println("Linear sum...");
        System.out.println("LinearSum = " + arrayBox.linearSum());
        System.out.println("Thread sum...");
        System.out.println("ThreadSum = " + threadSum(arrayBox, NUM_THREADS));
    }

    /**
     * Main function
     */
    public static void main(String[] args) {
        new ThreadApp().process();
    }
}
