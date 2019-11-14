package com.dommyg.higharrayapp;

/**
 * Allows for practice with unsorted arrays. Practice problems are detailed below.
 *
 * 2.1 To the HighArray class, add a method called getMax() that returns the value of the highest key in the array or -1
 * if the array is empty. Add some code in main() to exercise this method. You can assume all the keys are positive numbers.
 *
 * 2.2 Modify the method in Programming Project 2.1 so that the item with the highest key is not only returned by the
 * method, but also removed from the array. Call the method removeMax().
 *
 * 2.3 The removeMax() method in Programming Project 2.2 suggests a way to sort the contents of an array by key value.
 * Implement a sorting scheme that does not require modifying the HighArray class, but only the code in main().
 * You'll need a second array, which will end up inversely sorted.
 *
 * 2.6 Write a noDups() method for the HighArray class. This method should remove all duplicates from the array. That is,
 * if three items with the key 17 appear in the array, noDups() should remove two of them. Don't worry about maintaining
 * the order of the items. One approach is to first compare every item with all the other items and overwrite any
 * duplicates with a null (or distinctive value that isn't used for real keys). Then remove all the nulls. Of course,
 * the array size will be reduced.
 */

public class HighArrayApp {

    public static void main(String[] args) {
        int maxSize = 100;
        HighArray array = new HighArray(maxSize);

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

        int searchKey = 35;
        if (array.find(searchKey)) {
            System.out.println("Found " + searchKey);
        } else {
            System.out.println("Can't find " + searchKey);
        }

        searchKey = 55;
        if (array.find(searchKey)) {
            System.out.println("Found " + searchKey);
        } else {
            System.out.println("Can't find " + searchKey);
        }

        array.deleteValue(0);
        array.deleteValue(55);
        array.deleteValue(99);

        // PRINT: 77 44 22 88 11 66 33
        array.display();

        // When complete, PRINT: 88 77 66 44 33 22 11
        HighArray arraySorted = new HighArray(maxSize);
        while (array.getTotalItems() != 0) {
            arraySorted.insert(array.removeMax());
            arraySorted.display();
        }

        HighArray arrayDuplicates = new HighArray(maxSize);

        arrayDuplicates.insert(14);
        arrayDuplicates.insert(76);
        arrayDuplicates.insert(43);
        arrayDuplicates.insert(10);
        arrayDuplicates.insert(95);
        arrayDuplicates.insert(76);
        arrayDuplicates.insert(14);
        arrayDuplicates.insert(10);
        arrayDuplicates.insert(43);
        arrayDuplicates.insert(76);
        arrayDuplicates.insert(22);
        arrayDuplicates.insert(10);
        arrayDuplicates.insert(43);
        arrayDuplicates.insert(22);
        arrayDuplicates.insert(22);
        arrayDuplicates.insert(5);

        // PRINT: 14 76 43 10 95 76 14 10 43 76 22 10 43 22 22 5
        arrayDuplicates.display();

        arrayDuplicates.noDups();

        // PRINT: 14 76 43 10 95 22 5
        arrayDuplicates.display();
    }
}

class HighArray {
    private long[] array;
    private int totalItems;

    HighArray(int max) {
        array = new long[max];
        totalItems = 0;
    }

    int getTotalItems() {
        return totalItems;
    }

    /**
     * Reports if a search key is found in the array.
     */
    boolean find(long searchKey) {
        int j;
        for (j = 0; j < totalItems; j++) {
            if (array[j] == searchKey) {
                return true;
            }
        }
        return false;
    }

    /**
     * Inserts a value at the totalItems position and increases totalItems by 1.
     */
    void insert(long value) {
        array[totalItems] = value;
        totalItems++;
    }

    /**
     * Searches for a value and deletes it if found.
     */
    void deleteValue(long value) {
        int j;
        for (j = 0; j < totalItems; j++) {
            if (value == array[j]) {
                break;
            }
        }
        if (j != totalItems) {
            deleteFromPosition(j);
        }
    }

    /**
     * Deletes the value from a given position by shifting down all subsequent values by one.
     * Decreases totalItems by one.
     */
    private void deleteFromPosition(int position) {
        System.arraycopy(array, position + 1, array, position, totalItems - position);
        totalItems--;
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
     * Finds, removes, and returns the largest key in the array.
     */
    long removeMax() {
        long max = -1;
        if (totalItems == 0) {
            return max;
        }
        for (int j = 0; j < totalItems; j++) {
            if (array[j] > max) {
                max = array[j];
            }
        }
        deleteValue(max);
        return max;
    }

    /**
     * Finds and removes duplicate keys in the array.
     */
    void noDups() {
        // After doing exercise 3.3 in InsertSortApp, I modified my approach, which I left commented out at top. Went from
        // 44 shifts to 3 with the test case in main(). This is nearly 15 times more efficient in this case.

//        for (int i = 0; totalItems < i; i++) {
//            for (int x = i + 1; x > totalItems; x++) {
//                if (array[i] == array[x]) {
//                    deleteFromPosition(x);
//                    x--;
//                }
//            }
//        }

        for (int i = 0; i < totalItems; i++) {
            // Loop to remove duplicates by marking them as -1.
            if (array[i] != -1) {
                for (int x = i + 1; x < totalItems; x++) {
                    // If current cell's value is not -1, check it against the subsequent cells.
                    if (array[x] != -1) {
                        if (array[x] == array[i]) {
                            // If checked cell's value is not -1 and matches current cell, change to -1.
                            array[x] = -1;
                        }
                    }
                }
            }
        }

        // Shift keeps track of how far back values need to be shifted.
        int shift = 0;

        for (int i = 0; i < totalItems; i++) {
            if (array[i] == -1) {
                // If checked value is -1, add one to shift.
                shift++;
            } else {
                if (shift > 0) {
                    // If checked value is not -1, shift it down if needed.
                    array[i - shift] = array[i];
                }
            }
        }
        // All duplicates removed. Update totalItems to reflect amount of duplicates removed.
        totalItems -= shift;
    }
}
