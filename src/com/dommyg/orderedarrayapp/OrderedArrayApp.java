package com.dommyg.orderedarrayapp;

/**
 * Allows for practice with binary searching in ordered arrays. Practice problems are detailed below.
 *
 * 2.4 Modify the OrderedArrayApp so that the insert() and delete() routines, as well as find(), use a binary search.
 *
 * 2.5 Add a merge() method to the OrderedArray class so that you can merge two ordered source arrays into an ordered
 * destination array. Write code in main() that inserts some random numbers into the two source arrays, invokes merge(),
 * and displays the contents of the resulting destination array. The source arrays may hold different numbers of items.
 * In your algorithm you will need to compare the keys of the source arrays, picking the smallest one to copy to the
 * destination. You'll also need to handle the situation when one source array exhausts its contents before the other.
 */

public class OrderedArrayApp {

    public static void main(String[] args) {
        int maxSize = 100;
        OrderedArray array1 = new OrderedArray(maxSize);

        array1.insert(77);
        array1.insert(99);
        array1.insert(44);
        array1.insert(55);
        array1.insert(22);
        array1.insert(88);
        array1.insert(11);
        array1.insert(0);
        array1.insert(66);
        array1.insert(33);

        int searchKey = 55;
        if (array1.find(searchKey) != array1.size())
            System.out.println("Found " + searchKey);
        else
            System.out.println("Can't find " + searchKey);

        searchKey = 45;
        if (array1.find(searchKey) != array1.size())
            System.out.println("Found " + searchKey);
        else
            System.out.println("Can't find " + searchKey);

        // Displays 0 11 22 33 44 55 66 77 88 99
        array1.display();

        array1.delete(0);
        array1.delete(55);
        array1.delete(99);

        // Displays 11 22 33 44 66 77 88
        array1.display();

        OrderedArray array2 = new OrderedArray(maxSize);

        array2.insert(65);
        array2.insert(43);
        array2.insert(87);
        array2.insert(23);
        array2.insert(9);
        array2.insert(38);
        array2.insert(71);
        array2.insert(5);
        array2.insert(48);
        array2.insert(64);

        // Displays 5 9 23 38 43 48 64 65 71 87
        array2.display();

        OrderedArray array3 = array1.merge(array2);

        // Displays 5 9 11 22 23 33 38 43 44 48 64 65 66 71 77 87 88
        array3.display();

    }
}

class OrderedArray {
    private long[] array;
    private int totalItems;

    OrderedArray(int max) {
        array = new long[max];
        totalItems = 0;
    }

    private long[] getArray() {
        return array;
    }

    private void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    int size() {
        return totalItems;
    }

    /**
     * Finds the array index of a search value. Returns totalItems value if it is unable to find.
     */
    int find(long searchKey) {
        // Utilizes binary search.
        int lowerBound = 0;
        int upperBound = totalItems - 1;
        int currentItem;

        while (true) {
            // Find middle cell of array range.
            currentItem = (lowerBound + upperBound) / 2;
            if (array[currentItem] == searchKey) {
                return currentItem;
            } else {
                if (lowerBound > upperBound) {
                    // All of array searched with no result.
                    return totalItems;
                } else {
                    if (array[currentItem] < searchKey) {
                        // searchKey is larger than currentItem cell value;
                        // lowerBound is now cell after currentItem cell.
                        lowerBound = currentItem + 1;
                    } else {
                        // searchKey is smaller than currentItem cell value;
                        // upperBound is now cell before currentItem cell.
                        upperBound = currentItem - 1;
                    }
                }
            }
        }
    }

    /**
     * Inserts a value into the array at the appropriate location to keep it ordered.
     */
    void insert(long value) {
        // Utilizes binary search.
        int lowerBound = 0;
        int upperBound = totalItems - 1;
        int currentItem;
        int destination = totalItems;

        while (true) {
            // Find middle cell of array range.
            currentItem = (lowerBound + upperBound) / 2;
            if (lowerBound > upperBound) {
                // Range has depleted. Move on to insert at current position.
                break;
            }
            if (array[currentItem] > value) {
                // Value is smaller than the one at currentItem location;
                // upperBound is now cell before currentItem cell.
                // Mark current location as this cell.
                // If range is depleted, value will be inserted here.
                destination = currentItem;
                upperBound = currentItem - 1;
            } else {
                // Value is larger than the one at currentItem location;
                // lowerBound is now cell after currentItem cell.
                // Mark current location as next cell in array.
                // If range is depleted, value will be inserted there.
                destination = currentItem + 1;
                lowerBound = currentItem + 1;
            }
        }

        // Insert value into array and shift all subsequent items up. Increase totalItems.
        System.arraycopy(array, destination, array, destination + 1, totalItems - destination);
        array[destination] = value;
        totalItems++;
    }

    /**
     * Searches for a value and deletes it if found.
     */
    void delete(long value) {
        int j = find(value);
        if (j != totalItems) {
            // Deletes the value from a given position by shifting down all proceeding values by one.
            // Decreases totalItems by one.
            System.arraycopy(array, j + 1, array, j, totalItems - j);
            totalItems--;
        }
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
     * Merges the target array with another array into a third array. Returns the merged third array with values in
     * ascending order. This allows for the original arrays to remain unmodified.
     */
    OrderedArray merge(OrderedArray arrayToMergeWith) {
        OrderedArray array1;
        OrderedArray array2;

        int array1Position = 0;
        int array2Position = 0;
        int mergeArrayPosition = 0;
        // Setting the larger and smaller array based on totalItems value.
        if (this.size() < arrayToMergeWith.size()) {
            array1 = this;
            array2 = arrayToMergeWith;
        } else {
            array1 = arrayToMergeWith;
            array2 = this;
        }

        // Creating the array where the values will be merged.
        // The size of the array will be the combined size of both arrays.
        int newSize = (array1.size() + array2.size());
        OrderedArray mergeArray = new OrderedArray(newSize);
        mergeArray.setTotalItems(newSize);

        // While both arrays still have uncopied items.
        while (array1.size() > array1Position && array2.size() > array2Position) {
            long array1Value = array1.getArray()[array1Position];
            long array2Value = array2.getArray()[array2Position];
            if (array1Value > array2Value) {
                // array1's current position value is larger.
                // Insert array2's value into the merged array.
                mergeArray.getArray()[mergeArrayPosition] = array2Value;
                mergeArrayPosition++;
                array2Position++;
            } else {
                // array2's current position value is larger.
                // Insert array1's value into the merged array.
                mergeArray.getArray()[mergeArrayPosition] = array1Value;
                mergeArrayPosition++;
                array1Position++;
            }
        }
        // Determine which array still has values left to copy.
        if (array1.size() > array1Position) {
            // Copy remaining values from array1.
            while (array1.size() > array1Position) {
                long array1Value = array1.getArray()[array1Position];
                mergeArray.getArray()[mergeArrayPosition] = array1Value;
                mergeArrayPosition++;
                array1Position++;
            }
        } else {
            // Copy remaining values from array2.
            while (array2.size() > array2Position) {
                long array2Value = array2.getArray()[array2Position];
                mergeArray.getArray()[mergeArrayPosition] = array2Value;
                mergeArrayPosition++;
                array2Position++;
            }
        }

        return mergeArray;
    }
}