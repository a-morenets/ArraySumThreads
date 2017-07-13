import java.util.Random;

/**
 * Created by Oleksii on 03.07.2017.
 */
public class ArrayBox {
    private int[] array;

    public ArrayBox(int arraySize) {
        this.array = new int[arraySize];
    }

    /**
     * Fills array with unique numbers
     */
    public void fillArray() {
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
    }

    public int size() {
        return array.length;
    }

    /**
     * In-place shuffle array elements by replacing each element with randomly selected one
     */
    public void shuffle() {
        Random rnd = new Random();

        for (int element : array) {
            int randomIndex = rnd.nextInt(array.length);
            swap(array, element, randomIndex);
        }
    }

    /**
     * Swap two array elements
     * @param array given array
     * @param index1  index of 1st element
     * @param index2  index of 2nd element
     */
    private static void swap(int[] array, int index1, int index2) {
        int tmp = array[index1];
        array[index1] = array[index2];
        array[index2] = tmp;
    }

    public int get(int index) {
        return array[index];
    }

    /**
     * Evaluates sum of all array elements adding all elements in one loop sequentially
     * @return sum of all array elements
     */
    public long linearSum() {
        long sum = 0;
        for (int element : array) {
            sum += element;
        }
        return sum;
    }

}
