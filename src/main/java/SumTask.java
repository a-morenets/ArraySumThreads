/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Arrays;

/**
 * @author Admin
 */
class SumCalculator extends Thread {
    private int arr[];
    static volatile long res;
    private int begin;
    private int end;

    SumCalculator(int[] arr, int begin, int end) {
        this.arr = arr;
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
        for (int i = begin; i < end; i++) {
            synchronized (SumCalculator.class) {
                res += arr[i];
            }
        }
    }
}

public class SumTask {

    public static void main(String args[]) throws InterruptedException {
        int arr[] = new int[40_000_000];
        Arrays.fill(arr, 1);
        int threadNumber = 256;
        SumCalculator[] sc = new SumCalculator[threadNumber];
        for (int i = 0; i < threadNumber; i++) {
            sc[i] = new SumCalculator(arr, arr.length / threadNumber * i,
                    arr.length / threadNumber * (i + 1));
            sc[i].start();
        }

        for (SumCalculator th : sc) {
            th.join();
        }

        System.out.println(SumCalculator.res); // total
    }

}
