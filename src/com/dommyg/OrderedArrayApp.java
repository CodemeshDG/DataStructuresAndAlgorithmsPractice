package com.dommyg;

/**
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

        array1.display();

        array1.delete(0);
        array1.delete(55);
        array1.delete(99);

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

        array2.display();

        OrderedArray array3 = array1.merge(array2);

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

    public long[] getArray() {
        return array;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    int size() {
        return totalItems;
    }

    int find(long searchKey) {
        int lowerBound = 0;
        int upperBound = totalItems - 1;
        int currentItem;

        while (true) {
            currentItem = (lowerBound + upperBound) / 2;
            if (array[currentItem] == searchKey) {
                return currentItem;
            } else {
                if (lowerBound > upperBound) {
                    return totalItems;
                } else {
                    if (array[currentItem] < searchKey) {
                        lowerBound = currentItem + 1;
                    } else {
                        upperBound = currentItem - 1;
                    }
                }
            }
        }
    }

    void insert(long value) {
        int lowerBound = 0;
        int upperBound = totalItems - 1;
        int currentItem;
        int destination = totalItems;

        while (true) {
            currentItem = (lowerBound + upperBound) / 2;
            if (lowerBound > upperBound) {
                break;
            }
            if (array[currentItem] > value) {
                destination = currentItem;
                upperBound = currentItem - 1;
            } else {
                destination = currentItem + 1;
                lowerBound = currentItem + 1;
            }
        }

        System.arraycopy(array, destination, array, destination + 1, totalItems - destination);
        array[destination] = value;
        totalItems++;
    }

    void delete(long value) {
        int j = find(value);
        if (j != totalItems) {
            System.arraycopy(array, j + 1, array, j, totalItems - j);
            totalItems--;
        }
    }

    void display() {
        for (int j = 0; j < totalItems; j++) {
            System.out.print(array[j] + " ");
        }
        System.out.println();
    }

    OrderedArray merge(OrderedArray arrayToMergeWith) {
        OrderedArray array1;
        OrderedArray array2;

        int array1Position = 0;
        int array2Position = 0;
        int mergeArrayPosition = 0;
        if (this.size() < arrayToMergeWith.size()) {
            array1 = this;
            array2 = arrayToMergeWith;
        } else {
            array1 = arrayToMergeWith;
            array2 = this;
        }

        int newSize = (array1.size() + array2.size());
        OrderedArray mergeArray = new OrderedArray(newSize);
        mergeArray.setTotalItems(newSize);

        while (array1.size() > array1Position && array2.size() > array2Position) {
            long array1Value = array1.getArray()[array1Position];
            long array2Value = array2.getArray()[array2Position];
            if (array1Value < array2Value) {
                mergeArray.getArray()[mergeArrayPosition] = array1Value;
                mergeArrayPosition++;
                array1Position++;
            } else {
                mergeArray.getArray()[mergeArrayPosition] = array2Value;
                mergeArrayPosition++;
                array2Position++;
            }
        }
        if (array1.size() > array1Position) {
            while (array1.size() > array1Position) {
                long array1Value = array1.getArray()[array1Position];
                mergeArray.getArray()[mergeArrayPosition] = array1Value;
                mergeArrayPosition++;
                array1Position++;
            }
        } else {
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