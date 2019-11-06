package com.dommyg;

public class OrderedArray {

    public static void main(String[] args) {
        int maxSize = 100;
        OrdArray array;
        array = new OrdArray(maxSize);

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

        int searchKey = 55;
        if (array.find(searchKey) != array.size())
            System.out.println("Found " + searchKey);
        else
            System.out.println("Can't find " + searchKey);

        array.display();

        array.delete(0);
        array.delete(55);
        array.delete(99);

        array.display();
    }
}

class OrdArray {
    private long[] array;
    private int totalItems;

    OrdArray(int max) {
        array = new long[max];
        totalItems = 0;
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
        int destination;

        while (true) {
            currentItem = (lowerBound + upperBound) / 2;
            if (lowerBound > upperBound) {
                break;
            }
            if (array[currentItem] > value) {
                destination = currentItem;
            }
        }

        int j;
        for (j = 0; j < totalItems; j++) {
            if (array[j] > value) {
                break;
            }
        }
        System.arraycopy(array, j, array, j + 1, totalItems - j);
        array[j] = value;
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
}