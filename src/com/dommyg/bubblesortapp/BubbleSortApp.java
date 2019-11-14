package com.dommyg.bubblesortapp;

/**
 * Allows for practice with bubble sorting. Practice problems are detailed below.
 *
 * 3.1 In BubbleSortApp, the in index always goes from left to right, finding the largest item and carrying it toward
 * out on the right. Modify the bubbleSort() method so that it's bidirectional. This means the in index will first carry
 * the largest item from left to right as before, but when it reaches out, it will reverse and carry the smallest item
 * from right to left. You'll need two outer indexes, one on the right (the old out) and another on the left.
 *
 * 3.4 Another simple sort is the odd-even sort. The idea is to repeatedly make two passes through the array. On the
 * first pass you look at all the pairs of items, array[j] and array[j+1], where j is odd (j = 1, 3, 5, ...). If their
 * key values are out of order, you swap them. On the second pass you do the same for all the even values
 * (j = 2, 4, 6, ...). You do these two passes repeatedly until the array is sorted. Replace the bubbleSort() method
 * with an oddEvenSort() method. Make sure it works for varying amount of data. You'll need to figure out how many times
 * to do the two passes.
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

        // PRINT: 77 99 44 55 22 88 11 0 66 33
        array.display();

        array.bubbleSort();

        // PRINT: 0 11 22 33 44 55 66 77 88 99
        array.display();

        ArrayBubble array2 = new ArrayBubble(maxSize);

        array2.insert(77);
        array2.insert(99);
        array2.insert(44);
        array2.insert(55);
        array2.insert(22);
        array2.insert(88);
        array2.insert(11);
        array2.insert(0);
        array2.insert(66);
        array2.insert(33);

        // PRINT: 77 99 44 55 22 88 11 0 66 33
        array2.display();

        array2.oddEvenSort();

        // PRINT: 0 11 22 33 44 55 66 77 88 99
        array2.display();

        ArrayBubble array3 = new ArrayBubble(maxSize);

        array3.insert(67);
        array3.insert(23);
        array3.insert(11);
        array3.insert(74);
        array3.insert(158);
        array3.insert(462);
        array3.insert(3);
        array3.insert(84);
        array3.insert(35);
        array3.insert(78);
        array3.insert(45);
        array3.insert(99);
        array3.insert(8);
        array3.insert(29);
        array3.insert(75);
        array3.insert(55);
        array3.insert(249);
        array3.insert(98);
        array3.insert(63);
        array3.insert(21);
        array3.insert(0);
        array3.insert(69);
        array3.insert(74);
        array3.insert(100);
        array3.insert(95);
        array3.insert(43);
        array3.insert(24);
        array3.insert(28);
        array3.insert(17);
        array3.insert(2);
        array3.insert(9000);
        array3.insert(34);
        array3.insert(77);
        array3.insert(65);
        array3.insert(47);
        array3.insert(98);
        array3.insert(89);
        array3.insert(40);
        array3.insert(60);
        array3.insert(81);
        array3.insert(112);
        array3.insert(456);
        array3.insert(38);
        array3.insert(33);
        array3.insert(12);
        array3.insert(19);
        array3.insert(27);
        array3.insert(62);
        array3.insert(85);
        array3.insert(101);

        // PRINT: 67 23 11 74 158 462 3 84 35 78 45 99 8 29 75 55 249 98 63 21 0 69 74 100 95 43 24 28 17 2 9000 34
        // 77 65 47 98 89 40 60 81 112 456 38 33 12 19 27 62 85 101
        array3.display();

        array3.oddEvenSort();

        // PRINT: 0 2 3 8 11 12 17 19 21 23 24 27 28 29 33 34 35 38 40 43 45 47 55 60 62 63 65 67 69 74 74 75 77 78
        // 81 84 85 89 95 98 98 99 100 101 112 158 249 456 462 9000
        array3.display();
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

    void oddEvenSort() {
        boolean performedSwap = true;

        while (performedSwap) {
            performedSwap = false;
            for (int i = 0; i < totalItems - 1; i += 2) {
                // Even sort pass.
                if (array[i] > array[i + 1]) {
                    // Current value is larger than next; swap.
                    swap(i, i + 1);
                    performedSwap = true;
                }
            }
            for (int i = 1; i < totalItems - 1; i += 2) {
                // Odd sort pass.
                if (array[i] > array[i + 1]) {
                    // Current value is larger than next; swap.
                    swap(i, i + 1);
                    performedSwap = true;
                }
            }
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
