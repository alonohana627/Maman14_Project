import java.util.Arrays;

/**@author Alon Ohana
 * Implementation of VERY basic hash table including a hash function.
 * This hash table acts as a SET. Therefore, there is no need to worry about collisions.
 * Time Complexity: O(n)
 * Space Complexity: O(n+m) where m is the size of the table.
 */
public class HashTable {
    private int assignments = 3;
    private int comparisons = 0;
    private int counter = 0;
    private final int size; //max-min+1
    private int[] table; //sized: max-min+1
    private final int[] arrayHolder;
    private static int min, max;

    public HashTable(int[] array){
        this.arrayHolder = array;
        findMaxAndMin(arrayHolder);
        size = max-min+1; //small optimization for the size of the Hash Table.
        // While we can set it to 100 as required, we set it to the MINIMUM size it needs.
        assignments++;
        initTable();
    }

    /**
     * The given method prints the comparisons, assignments, and unique counter.
     * Time Complexity: O(n)
     */
    public void statistics(){
        for(int i = min; i<=max; i++){
            if(isExist(i)){
                counter++;
            }
            comparisons++;
        }
        System.out.println("The number of comparisons: "+comparisons+
                "\nThe number of assignments: "+assignments+
                "\nThe amount of unique numbers: "+counter);
    }

    /**
     * The following method checks out if a given key is exist in our Hash Table/set.
     * @param key - wanted value
     * @return - is the given key exists.
     */
    public boolean isExist(int key){
        return table[hashFunction(key)]==key;
    }

    /**
     * Adds new key into the Hash Table/Set.
     * Since the Hash Table acts as a set, we do not need to solve any collisions - once it is different than 0 - we count it.
     * @param key - the key to add.
     */
    public void assign(int key){
        table[hashFunction(key)]=key;
    }

    /**
     * The Hash Function. The reason it works - the key goes to the cell
     * @param key - key to "hash"
     * @return - the hashed value
     */
    public int hashFunction(int key){
        return key-min; //not so smart yet simple and working "Hash Function". There is no need to use any fancy Hash Function here.
    }

    //Private methods that initialize the values of the Hash Table

    /**
     * The method gets the array and assigns the min value and max value.
     * Time Complexity: O(n)
     * @param array - given array
     */
    private void findMaxAndMin(int[] array){
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int j : array) {
            if (j < min) {
                min = j;
                assignments++;
            }
            if (j > max) {
                max = j;
                assignments++;
            }
            comparisons += 2;
        }
        HashTable.min =min;
        HashTable.max =max;
        assignments+=2;
    }

    /**
     * Initialize the Hash Table. At first in gets the size, after that it assigns all of the values to 0,
     * Time Complexity: O(n)
     * and after that - it changes from zero to the wanted value
     */
    private void initTable(){
        table = new int[size];
        Arrays.fill(table, 0);
        for (int j : arrayHolder) {
            assign(j);
        }
        assignments+=1+table.length+arrayHolder.length;
    }
}