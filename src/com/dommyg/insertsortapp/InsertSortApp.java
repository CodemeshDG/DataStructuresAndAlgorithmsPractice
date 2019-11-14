package com.dommyg.insertsortapp;

/**
 * Allows for practice with insert sorting. Practice problems are detailed below.
 *
 * 3.2 Add a method called median() to the ArrayInsert class. This method should return the median value in the array.
 * (Recall that in a group of numbers half are larger than the median and half are smaller.) Do it the easy way.
 *
 * 3.3 Add a method called noDups() that removes duplicates from a previously sorted array without disrupting the order.
 * (You can use the insertionSort() method to sort the data, or you can simply use main() to insert the data in sorted
 * order.) One can imagine schemes in which all the items from the place where a duplicate was discovered to the end of
 * the array would be shifted down one space every time a duplicate was discovered, but this would lead to slow O(N^2)
 * time, at least when there were a lot of duplicates. In your algorithm, make sure no item is moved more than once, no
 * matter how many duplicates there are. This will give you an algorithm with O(N) time.
 *
 * 3.5 Modify the insertionSort() method so it counts the number of copies and the number of comparisons it makes during
 * a sort and displays the totals. To count comparisons, you'll need to break up the double condition in the inner while
 * loop. Use this program to measure the number of copies and comparisons for different amounts of inversely sorted data.
 * Do the results verify O(N^2) efficiency? Do the same for almost-sorted data (only a few items out of place). What can
 * you deduce about the efficiency of this algorithm for almost-sorted data?
 */

public class InsertSortApp {

    public static void main(String[] args) {
        int maxSize = 100;
        ArrayInsert array = new ArrayInsert(maxSize);

        array.insert(77);
        array.insert(99);
        array.insert(44);
        array.insert(55);
        array.insert(22);
        array.insert(88);
        array.insert(11);
        array.insert(0);
        array.insert(66);
        array.insert(33);

        // PRINT: 77 99 44 55 22 88 11 0 66 33
        array.display();

        array.insertionSort();

        // PRINT: 0 11 22 33 44 55 66 77 88 99
        array.display();

        System.out.println("Median is " + array.median());

        array.insert(44);
        array.insert(44);
        array.insert(88);
        array.insert(88);
        array.insert(88);
        array.insert(66);

        // PRINT: 0 11 22 33 44 55 66 77 88 99 44 44 88 88 88 66
        array.display();

        array.insertionSort();

        // PRINT: 0 11 22 33 44 44 44 55 66 66 77 88 88 88 88 99
        array.display();

        array.noDups();

        // PRINT: 0 11 22 33 44 55 66 77 88 99
        array.display();

    }
}

class ArrayInsert {
    private long[] array;
    private int totalItems;

    ArrayInsert(int max) {
        array = new long[max];
        totalItems = 0;
    }

    /**
     * Inserts a value at the totalItems position and increases totalItems by 1.
     */
    void insert(long value) {
        array[totalItems] = value;
        totalItems++;
    }

    /**
     * Prints out each element in the array up to the totalItems amount.
     */
    void display() {
        for (int j = 0; j < totalItems; j++) {
            System.out.print(array[j] + " ");
        }
        System.out.println();
    }

    /**
     * Sorts the array's values in ascending order.
     */
    void insertionSort() {
        // Utilizes insertion sort.
        int in;
        int out;

        for (out = 1; out < totalItems; out++) {
            // Copy the current out position value into a temp variable.
            // Set in value to out value position.
            long temp = array[out];
            in = out;
            while (in > 0 && array[in - 1] >= temp) {
                // Cycle down the array, shifting the values up,
                // until a value is encountered which is equal to or more than the temp value.
                array[in] = array[in - 1];
                --in;
            }
            // Copy temp into in position.
            array[in] = temp;
        }
    }

    /**
     * Returns the median of the values in the array.
     */
    long median() {
        if (totalItems % 2 == 0) {
            return (array[totalItems / 2] + array[(totalItems / 2) + 1]) / 2;
        } else {
            return array[totalItems / 2];
        }
    }

    /**
     * Removes duplicate values in the array.
     */
    void noDups() {
        // Shift keeps track of how far back values need to be shifted.
        int shift = -1;
        long compareValue = array[0];
        for (int i = 0; i < totalItems - 1; i++) {
            // Compares current cell with next cell.
            System.out.println("Compare value is " + compareValue + ". Currently checking array [" + (i + 1) + "]");
            if (compareValue == array[i + 1]) {
                // Next cell is a duplicate. Add one to shift value.
                shift++;
                System.out.println("Value is " + array[i+1] + ". It is a duplicate! Adding one to shift. Shift is now " + shift);
            } else {
                // Next cell is not a duplicate.
                compareValue = array[i + 1];
                System.out.println("Value is " + array[i+1] + ". It is not a duplicate. CompareValue is now " + compareValue);
                if (shift >= 0) {
                    // At least one duplicate has been removed thus far;
                    // shift value back.
                    System.out.println("Shifting back " + (shift + 1) + " spaces");
                    array[i - shift] = array[i + 1];
                    System.out.print("Array is now ");
                    display();
                }
            }
            System.out.println();
        }
        // All duplicates removed. Update totalItems to reflect amount of duplicates removed.
        totalItems -= shift + 1;
    }
}

