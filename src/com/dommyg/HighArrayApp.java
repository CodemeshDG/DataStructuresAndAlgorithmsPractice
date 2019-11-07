package com.dommyg;

/**
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

        array.display();

        int searchKey = 35;
        if (array.find(searchKey)) {
            System.out.println("Found " + searchKey);
        } else {
            System.out.println("Can't find " + searchKey);
        }

        array.deleteValue(0);
        array.deleteValue(55);
        array.deleteValue(99);

        array.display();

        HighArray arraySorted = new HighArray(maxSize);
        while (array.getTotalItems() != 0) {
            arraySorted.insert(array.removeMax());
            arraySorted.display();
        }

        HighArray arrayDuplicates = new HighArray(maxSize);

        arrayDuplicates.insert(14);
        arrayDuplicates.insert(76);
        arrayDuplicates.insert(43);
        arrayDuplicates.insert(95);
        arrayDuplicates.insert(76);
        arrayDuplicates.insert(22);
        arrayDuplicates.insert(10);
        arrayDuplicates.insert(5);
        arrayDuplicates.insert(43);
        arrayDuplicates.insert(14);
        arrayDuplicates.insert(76);
        arrayDuplicates.insert(43);

        arrayDuplicates.display();

        arrayDuplicates.noDups();

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

    boolean find(long searchKey) {
        int j;
        for (j = 0; j < totalItems; j++) {
            if (array[j] == searchKey) {
                break;
            }
        }
        return j == totalItems;
    }

    void insert(long value) {
        array[totalItems] = value;
        totalItems++;
    }

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

    void deleteFromPosition(int position) {
        System.arraycopy(array, position + 1, array, position, totalItems - position);
        totalItems--;
    }

    void display() {
        for (int j = 0; j < totalItems; j++) {
            System.out.print(array[j] + " ");
        }
        System.out.println();
    }

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

    void noDups() {
        for (int i = 0; totalItems > i; i++) {
            for (int x = i +1; totalItems > x; x++) {
                if (array[i] == array[x]) {
                    deleteFromPosition(x);
                    x--;
                }
            }
        }
    }
}
