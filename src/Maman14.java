import javax.sound.midi.Soundbank;
import java.util.*;

/**
 * @author Alon Ohana
 */
public class Maman14 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("""
                Hello. In the following program you are required to enter a number.
                After that, an array will be generate with random numbers between 1-100,
                After that you will get the statistics regarding the number of comparisons that the algorithm had made,
                 and the number of the assignments the algorithm had made.

                Please enter a number.""");

        int arraySize = in.nextInt();
        int[] givenArray = new int[arraySize];
        int[] placeHolderArray; //Some of the algorithms

        Random integerRandom = new Random();
        for (int i = 0; i < givenArray.length; i++) {
            givenArray[i] = integerRandom.nextInt(100); //generates numbers between 1 to 100 as requested.
        }
        System.out.println("Original algorithm from Maman 11 for array with " + arraySize + " size");
        placeHolderArray = givenArray;
        int D = D(placeHolderArray);
        System.out.println("////////////");

        System.out.println("Insertion sort for array with " + arraySize + " size");
        placeHolderArray = givenArray;
        insertionUniqueCheck(placeHolderArray);
        System.out.println("////////////");

        System.out.println("Heap sort for array with " + arraySize + " size");
        placeHolderArray = givenArray;
        heapSortUnique(placeHolderArray);
        System.out.println("////////////");

        System.out.println("C array from Count sort variant for array with " + arraySize + " size");
        placeHolderArray = givenArray;
        countingUniqueCheck(placeHolderArray);
        System.out.println("////////////");

        System.out.println("Hash table implementation for array with " + arraySize + " size");
        placeHolderArray = givenArray;
        HashTable table = new HashTable(placeHolderArray);
        table.statistics();
    }

    /**
     * Using an insertion sort in order to check the amount of the unique numbers in a given array.
     * The method gets an array and posts the following:
     * 1. The number of unique numbers
     * 2. The number of comparisons
     * 3. The number of assignments
     * <p>
     * Time complexity: O(n^2)
     * Space complexity: O(1)
     * n - Array's Length
     *
     * @param array - given array
     */
    public static void insertionUniqueCheck(int[] array) {
        int assignments = 0;
        int comparisons = 0;
        int uniqueCount = 1;
        int key;
        int j;
        for (int i = 1; i < array.length; i++) {
            key = array[i];
            j = i - 1;
            assignments += 2;
            while (j > 0 && array[j] != key) {
                j--;
            }
            if (j == 0) { //Instead of swapping - checks if array[j] was already there.
                if (array[j] != key) {
                    uniqueCount++;
                }
                comparisons++;
            }
            comparisons++;
        }
        System.out.println("The number of comparisons: " + comparisons +
                "\nThe number of assignments: " + assignments +
                "\nThe amount of unique numbers: " + uniqueCount);
    }

    /**
     * By Heap Sort (because it is easier to implement with Priority Queue from the utils of Java)
     * The method gets an array and posts the following:
     * 1. The number of unique numbers
     * 2. The number of comparisons
     * 3. The number of assignments
     * <p>
     * Time complexity: O(nlogn)
     * Space complexity: O(n)
     * n - Array Size
     *
     * @param array - Given array
     */
    public static void heapSortUnique(int[] array) {
        int assignments = 0;
        int comparisons = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int j : array) {  //Build a heap - takes O(nlogn) time with O(n) space
            assignments++;
            minHeap.add(j);
        }
        int currentOne;
        int lastOne;
        int counter = 1;
        lastOne = Integer.parseInt(minHeap.peek().toString());
        String s;
        while (minHeap.peek() != null) { //O(n)
            currentOne = Integer.parseInt(minHeap.peek().toString());
            if (currentOne != lastOne) { //Since the PQ is MinHeap, all we need to do is to check that the last two items were NOT the same.
                counter++;
            }
            comparisons++;
            lastOne = Integer.parseInt(minHeap.peek().toString());
            assignments += 2;
            minHeap.poll();
        }
        System.out.println("The number of comparisons: " + comparisons +
                "\nThe number of assignments: " + assignments +
                "\nThe amount of unique numbers: " + counter);
    }

    /**
     * C Array of Counting Sort (just as maman13)
     * The method gets an array and posts the following:
     * 1. The number of unique numbers
     * 2. The number of comparisons
     * 3. The number of assignments
     * <p>
     * Time complexity: O(n+m)
     * Space complexity: O(m)
     * m - Maximum number in a given array.
     *
     * @param array - Given array
     */
    public static void countingUniqueCheck(int[] array) { //Same method as in the Maman13
        int assignments = 0;
        int comparisons = 0;
        int arrayMax = Integer.MIN_VALUE;
        int arrayMin = Integer.MAX_VALUE;
        int[] cArray;
        int diff;
        int sum = 0;
        for (int k : array) {
            if (k > arrayMax) {
                arrayMax = k;
                assignments++;
            }
            if (k < arrayMin) {
                arrayMin = k;
                assignments++;
            }
            comparisons += 2;
        }
        cArray = new int[Math.abs(arrayMax - arrayMin) + 1]; //Gives the C Array a length of the range of the numbers.
        Arrays.fill(cArray, 0); //init the CArray.
        assignments += cArray.length;
        diff = arrayMin; //Checks out in a case that the array doesnt have the number 1.
        for (int j : array) { //Counts
            if (cArray[j - diff] == 0) {
                cArray[j - diff] = 1;
                assignments++;
            }
            comparisons++;
        }
        for (int j : cArray) { //sums up all of the non-duplicate
            sum += j;
            assignments++;
        }
        System.out.println("The number of comparisons: " + comparisons +
                "\nThe number of assignments: " + assignments +
                "\nThe amount of unique numbers: " + sum);
    }

    /**
     * Original method from Maman11. The method gets an array and checks the amount of duplicates it has.
     * The given algorithm has the same method names as in the Maman11, that is for the purpose of consistency.
     *
     * @param a - given Array
     * @return the amount of duplicates in a given array.
     */
    public static int D(int[] a) {
        int assignments = 0;
        int comparisons = 0;
        int U_Size = 1;
        for (int i = 1; i < a.length; i++) { //
            boolean U = true;
            for (int j = 0; j < U_Size; j++) {
                if (a[i] == a[j]) {
                    U = false;
                    j = U_Size;
                    assignments += 2;
                }
                comparisons++;
            }
            if (U) {
                a[U_Size] = a[i];
                U_Size++;
                assignments++;
            }
            comparisons++;
        }
        System.out.println("The number of comparisons: " + comparisons +
                "\nThe number of assignments: " + assignments +
                "\nThe amount of unique numbers: " + U_Size);
        return U_Size;
    }
}
