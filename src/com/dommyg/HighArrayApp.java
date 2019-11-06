package com.dommyg;

public class HighArrayApp {

    public static void main(String[] args) {
        int maxSize = 100;
        HighArray array;
        array = new HighArray(maxSize);

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

        array.delete(0);
        array.delete(55);
        array.delete(99);

        array.display();

        HighArray arraySorted = new HighArray(maxSize);
        while (array.getTotalItems() != 0) {
            arraySorted.insert(array.removeMax());
            arraySorted.display();
        }

    }
}

class HighArray {
    private long[] array;
    private int totalItems;

    HighArray(int max) {
        array = new long[max];
        totalItems = 0;
    }

    public int getTotalItems() {
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

    void delete(long value) {
        int j;
        for (j = 0; j < totalItems; j++) {
            if (value == array[j]) {
                break;
            }
        }
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
        delete(max);
        return max;
    }
}
