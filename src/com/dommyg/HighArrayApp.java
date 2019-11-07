package com.dommyg;

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
