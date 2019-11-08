package com.dommyg.bubblesortapp;

/**
 * Allows for practice with bubble sorting. Practice problems are detailed below.
 *
 * 3.1 In BubbleSortApp, the in index always goes from left to right, finding the largest item and carrying it toward
 * out on the right. Modify the bubbleSort() method so that it's bidirectional. This means the in index will first carry
 * the largest item from left to right as before, but when it reaches out, it will reverse and carry the smallest item
 * from right to left. You'll need two outer indexes, one on the right (the old out) and another on the left.
 */

public class BubbleSortApp {

    public static void main(String[] args) {
        int maxSize = 100;
        ArrayBubble array = new ArrayBubble(maxSize);

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

        // Displays 77 99 44 55 22 88 11 0 66 33
        array.display();

        array.bubbleSort();

        // Displays 0 11 22 33 44 55 66 77 88 99
        array.display();
    }
}

class ArrayBubble {
    private long[] array;
    private int totalItems;

    ArrayBubble(int max) {
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
     * Sorts the array values in ascending order.
     */
    void bubbleSort() {
        int upOut;
        int downOut = 0;
        int in;

        // This bubble sort starts ascending the array, carrying the higher values up to the top. It then descends and
        // carries the lower values down to the bottom. As it sweeps the array, the range becomes smaller and smaller
        // toward the middle.
        for (upOut = totalItems - 1; upOut > 1; upOut--) {
            // Ascending the array.
            for (in = 0; in < upOut; in++) {
                if (array[in] > array[in + 1]) {
                    swap(in, in + 1);
                }
            }
            // Descending the array.
            for (in = upOut; in > downOut; in--) {
                if (array[in] < array[in - 1]) {
                    swap(in, in - 1);
                }
            }
            // Increase the bottom boundary by one. The upper boundary is decreased by one as part of the loop.
            downOut += 1;
        }
    }

    /**
     * Swaps two values within the array.
     */
    private void swap(int one, int two) {
        long temp = array[one];
        array[one] = array[two];
        array[two] = temp;
    }
}
