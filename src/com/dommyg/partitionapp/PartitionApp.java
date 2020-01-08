package com.dommyg.partitionapp;

/**
 * Allows for practice with partitioning an array. Practice problems are detailed below.
 * <p>
 * 7.1 Modify the ArrayPartition so that the partitionIt() method always uses the highest-index (right) element as the
 * pivot, rather than an arbitrary number. Make sure your routine will work for arrays of three or fewer elements. To do
 * so, you may need a few extra statements.
 * <p>
 * 7.3 In Exercise 3.2 (InsertSortApp), we suggested that you could find the median of a set of data by sorting the data
 * and picking the middle element. You might think using quicksort and picking the middle element would be the fastest
 * way to find the median, but there's an even faster way. It uses the partition algorithm to find the median without
 * completely sorting the data.
 * <p>
 * To see how this works, imagine that you partition the data, and, by chance, the pivot happens to end up at the middle
 * element. You're done! All the items to the right of the pivot are larger (or equal), and all the items to the left
 * are smaller (or equal), so if the pivot falls in the exact center of the array, then it's the median. The pivot won't
 * end up in the center very often, but we can fix that by repartitioning the partition that contains the middle element.
 * <p>
 * Suppose your array has seven elements numbered from 0 to 6. The middle is element 3. If you partition this array and
 * the pivot ends up at 4, then you need to partition again from 0 to 4 (the partition that contains 3), not 5 to 6. If
 * the pivot ends up at 2, you need to partition from 2 to 6, not 0 to 1. You continue partition the appropriate
 * partitions recursively, always checking if the pivot falls on the middle element. Eventually, it will, and you're
 * done. Because you need fewer partitions than in quicksort, this algorithm is faster.
 * <p>
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

        // PRINT (EXAMPLE): A=52 191 103 169 197 164 55 88 120 59 46 147 52 93 75 112
        array.display();

        int size = array.size();

        int partitionIndex = array.partitionIt(0, size - 1);

        // PRINT (EXAMPLE): Pivot is 112, partition is at index 9
        System.out.println(", partition is at index " + partitionIndex);
        // PRINT (EXAMPLE): A=52 75 103 93 52 46 55 88 59 112 164 147 197 169 191 120
        array.display();

        maxSize = 5;
        ArrayPartition array2 = new ArrayPartition(maxSize);

        array2.insert(11);
        array2.insert(1);
        array2.insert(8);
        array2.insert(5);
        array2.insert(4);

        size = array2.size();

        // PRINT: A=11 1 8 5 4
        array2.display();

        int medianNumber = array2.findMedian(0, size - 1);

        // PRINT: Pivot is 4, partition is at index 1. Median index is 2. Trying again.
        // Pivot is 11, partition is at index 4. Median index is 2. Trying again.
        // Pivot is 5, median number is 5
        System.out.println(", median number is " + medianNumber);
        //PRINT: A=1 4 5 8 11
        array2.display();

        maxSize = 13;
        ArrayPartition array3 = new ArrayPartition(maxSize);

        for (int j = 0; j < maxSize; j++) {
            long n = (int) (java.lang.Math.random() * 199);
            array3.insert(n);
        }

        size = array3.size();

        // PRINT (EXAMPLE): A=34 55 66 80 30 52 101 109 129 42 25 64 71
        array3.display();

        // PRINT (EXAMPLE): Pivot is 71, partition is at index 8. Median index is 6. Trying again.
        // Pivot is 42, partition is at index 3. Median index is 6. Trying again.
        // Pivot is 64, median number is 64
        medianNumber = array3.findMedian(0, size - 1);
        System.out.println(", median number is " + medianNumber);
        // PRINT (EXAMPLE): A=34 25 30 42 55 52 64 66 71 109 101 80 129
        array3.display();
    }
}


class ArrayPartition {
    private long[] theArray;
    private int totalItems;

    public ArrayPartition(int max) {
        theArray = new long[max];
        totalItems = 0;
    }

    /**
     * Inserts a value at the totalItems position and increases totalItems by 1.
     */
    public void insert(long value) {
        theArray[totalItems] = value;
        totalItems++;
    }

    /**
     * Returns the total number of items in the array.
     */
    public int size() {
        return totalItems;
    }

    /**
     * Prints out all elements in the array.
     */
    public void display() {
        System.out.print("A=");
        for (int j = 0; j < totalItems; j++)
            System.out.print(theArray[j] + " ");
        System.out.println();
    }

    /**
     * Partitions the array using the right boundary as a pivot and returns the index position of the pivot after
     * partitioning.
     *
     * @param left  Left index boundary of the portion of the array to be partitioned.
     * @param right Right index boundary of the portion of the array to be partitioned.
     */
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
                int rightPtr = right;
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
                swap(leftPtr, right);
                return leftPtr;
        }
    }

    /**
     * Returns the median number in an array using recursive partitioning.
     *
     * @param left  Left index boundary of the portion of the array to be examined for median number.
     * @param right Right index boundary of the portion of the array to be examined for median number.
     */
    public int findMedian(int left, int right) {
        // The index where the median number would appear is established.
        int medianIndex = (right - left) / 2;
        return findMedian(left, right, medianIndex);
    }

    /**
     * Returns the median number in an array using recursive partitioning.
     *
     * @param left        Left index boundary of the portion of the array to be examined for median number.
     * @param right       Right index boundary of the portion of the array to be examined for median number.
     * @param medianIndex The middle index location where the median number would appear.
     */
    private int findMedian(int left, int right, int medianIndex) {
        // The array is partitioned and returns the location of the pivot index.
        int pivotIndex = partitionIt(left, right);

        if (pivotIndex == medianIndex) {
            // The pivot index matches the median index; return the number from that index.
            return (int) theArray[medianIndex];
        } else if (pivotIndex > medianIndex) {
            // The median index is lower than the pivot index; the area to be partitioned now is from the left boundary
            // to the index right before the pivot index.
            System.out.println(", partition is at index " + pivotIndex + ". Median index is " + medianIndex + ". Trying again.");
            return findMedian(left, pivotIndex - 1, medianIndex);
        } else {
            // The median index is higher than the pivot index; the area to be partitioned now is from the right boundary
            // to the index right after the pivot index.
            System.out.println(", partition is at index " + pivotIndex + ". Median index is " + medianIndex + ". Trying again.");
            return findMedian(pivotIndex + 1, right, medianIndex);
        }
    }

    /**
     * Swaps two index positions in the array.
     */
    public void swap(int index1, int index2) {
        long temp;
        temp = theArray[index1];
        theArray[index1] = theArray[index2];
        theArray[index2] = temp;
    }
}
