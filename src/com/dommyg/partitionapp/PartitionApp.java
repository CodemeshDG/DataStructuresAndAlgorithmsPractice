package com.dommyg.partitionapp;

/**
 * Allows for practice with partitioning an array. Practice problems are detailed below.
 *
 * 7.1 Modify the ArrayPartition so that the partitionIt() method always uses the highest-index (right) element as the
 * pivot, rather than an arbitrary number. Make sure your routine will work for arrays of three or fewer elements. To do
 * so, you may need a few extra statements.
 *
 * 7.3 In Exercise 3.2 (InsertSortApp), we suggested that you could find the median of a set of data by sorting the data
 * and picking the middle element. You might think using quicksort and picking the middle element would be the fastest
 * way to find the median, but there's an even faster way. It uses the partition algorithm to find the median without
 * completely sorting the data.
 *
 * To see how this works, imagine that you partition the data, and, by chance, the pivot happens to end up at the middle
 * element. You're done! All the items to the right of the pivot are larger (or equal), and all the items to the left
 * are smaller (or equal), so if the pivot falls in the exact center of the array, then it's the median. The pivot won't
 * end up in the center very often, but we can fix that by repartitioning the partition that contains the middle element.
 *
 * Suppose your array has seven elements numbered from 0 to 6. The middle is element 3. If you partition this array and
 * the pivot ends up at 4, then you need to partition again from 0 to 4 (the partition that contains 3), not 5 to 6. If
 * the pivot ends up at 2, you need to partition from 2 to 6, not 0 to 1. You continue partition the appropriate
 * partitions recursively, always checking if the pivot falls on the middle element. Eventually, it will, and you're
 * done. Because you need fewer partitions than in quicksort, this algorithm is faster.
 *
 * Extend Programming Project 7.1 to find the median of an array. You'll make recursive calls somewhat like those in
 * quicksort, but they will only partition each subarray, not completely sort it. The process stops when the median is
 * found, not when the array is sorted.
 */
public class PartitionApp {
    public static void main(String[] args) {
        int maxSize = 16;
        ArrayPartition array = new ArrayPartition(maxSize);

        for (int j = 0; j < maxSize; j++) {
            long n = (int) (java.lang.Math.random() * 199);
            array.insert(n);
        }

        array.display();

        int size = array.size();

        int partDex = array.partitionIt(0, size - 1);

        System.out.println(", Partition is at index " + partDex);
        array.display();
    }
}


class ArrayPartition {
    private long[] theArray;
    private int totalItems;

    public ArrayPartition(int max) {
        theArray = new long[max];
        totalItems = 0;
    }

    public void insert(long value) {
        theArray[totalItems] = value;
        totalItems++;
    }

    public int size() {
        return totalItems;
    }

    public void display() {
        System.out.print("A=");
        for (int j = 0; j < totalItems; j++)
            System.out.print(theArray[j] + " ");
        System.out.println();
    }

    public int partitionIt(int left, int right) {
        long pivot = theArray[right];
        System.out.print("Pivot is " + pivot);

        switch (right - left) {
            case 0:
                // Partition is of one index position; return position.
                return right;

            case 1:
                // Partition is of two index positions.
                if (theArray[left] > theArray[right]) {
                    // Left position is larger; swap and return left index position.
                    swap(right, left);
                    return left;
                }
                // Return right index position.
                return right;

            case 2:
                // Partition is of three index positions.
                if (theArray[right] > theArray[left]) {
                    if (theArray[right] > theArray[right - 1]) {
                        // Right position is largest; return it.
                        return right;
                    }
                    // Middle position is largest; swap with right position and return middle index position.
                    swap(right, right - 1);
                    return right - 1;
                }
                if (theArray[left] > theArray[left + 1]) {
                    // Left position is largest; return it.
                    swap(right, left);
                    return left;
                }
                // Middle position is largest; return it.
                swap(right, right - 1);
                return left + 1;

            default:
                // Partition is more than three index positions; use a left and right pointer and return the left
                // pointer's final index position.
                int leftPtr = left - 1;
                int rightPtr = right + 1;
                while (true) {
                    while (leftPtr < right && theArray[++leftPtr] < pivot)
                        ;  // (nop)

                    while (rightPtr > left && theArray[--rightPtr] > pivot)
                        ;  // (nop)

                    if (leftPtr >= rightPtr) {
                        break;
                    } else {
                        swap(leftPtr, rightPtr);
                    }
                }
                return leftPtr;
        }
    }

    public void swap(int index1, int index2) {
        long temp;
        temp = theArray[index1];
        theArray[index1] = theArray[index2];
        theArray[index2] = temp;
    }
}
