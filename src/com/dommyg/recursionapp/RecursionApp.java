package com.dommyg.recursionapp;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Allows for practice with recursion. Practice problems are detailed below.
 * <p>
 * 6.1 Supposed you buy a budget-priced pocket PC and discover that the chip inside can't do multiplication, only
 * addition. You program your way out of this quandary by writing a recursive method, mult(), that performs
 * multiplication of x and y by adding x to itself y times. Its arguments are x and y and its returning value is the
 * product of x and y. Write such a method and a main() program to call it. Does the addition take place when the
 * method calls itself or when it returns?
 * <p>
 * 6.3 Implement the recursive approach to raising a number to a power, as described in the "Raising a Number to a
 * Power" section near the end of this chapter. Write the recursive power() function and a main() routine to test it.
 * <p>
 * 6.4 Write a program that solved the knapsack problem for an arbitrary knapsack capacity and series of weights.
 * Assume the weights are stored in an array. Hint: The arguments to the recursive knapsack() function are the target
 * weight and the array index where the remaining items start.
 * <p>
 * 6.5 Implements a recursive approach to showing all the teams that can be created from a group (n things taken k at
 * a time). Write the recursive showTeams() method and a main() method to prompt the user for the group size and the
 * team size to provide arguments for showTeam(), which then displays all the possible combinations.
 */
public class RecursionApp {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        // PRINT: 30
        System.out.println(calculator.mult(5, 6));
        // PRINT: 100
        System.out.println(calculator.mult(10, 10));
        // PRINT: 5850
        System.out.println(calculator.mult(65, 90));

        // PRINT: 64
        System.out.println(calculator.power(8, 2));
        // PRINT: 512
        System.out.println(calculator.power(8, 3));
        // PRINT: 32768
        System.out.println(calculator.power(2, 15));

        KnapsackSolver knapsackSolver = new KnapsackSolver();

        int[] array = new int[5];

        array[0] = 6;
        array[1] = 3;
        array[2] = 2;
        array[3] = 8;
        array[4] = 4;

        knapsackSolver.solve(12, array);

        System.out.println();

        int[] array2 = new int[6];

        array2[0] = 14;
        array2[1] = 2;
        array2[2] = 85;
        array2[3] = 22;
        array2[4] = 61;
        array2[5] = 9;

        knapsackSolver.solve(203, array2);
    }

}

/**
 * Provides basic mathematical functions through method recursion.
 */
class Calculator {
    /**
     * Multiplies two values and returns the result.
     */
    long mult(long x, long y) {
        if (y == 1) {
            return x;
        } else {
            return (x + mult(x, y - 1));
        }
    }

    /**
     * Raises one value by the power of another and returns the result.
     *
     * @param x The value of the base.
     * @param y The value of the exponent.
     */
    long power(long x, long y) {
        if (y == 1) {
            return x;
        } else {
            return (x * power(x, y - 1));
        }
    }
}

/**
 * Provides a solve method which reports if an array of values contains a combination which matches a target value
 * (based on the knapsack problem). Will print out the first combination it finds. Any instance of this class can be
 * reused to solve with multiple arrays.
 */
class KnapsackSolver {
    private ArrayList<Integer> knapsack;
    private boolean isFull;

    /**
     * Prints out if a combination of items weights can be found which will equal a target weight.
     *
     * @param targetWeight The weight which the item combinations will be measured against.
     * @param items        The array of item weights.
     */
    void solve(int targetWeight, int[] items) {
        // Setting the knapsack to a new ArrayList and the isFull boolean to false within this method allows for a
        // single instance of the KnapsackSolver object to be reused with any amount of arrays.
        knapsack = new ArrayList<>();
        isFull = false;

        solve(targetWeight, items, 0, 0);

        if (!isFull) {
            // All recursive calls returned and the knapsack is not full.
            System.out.println("These items cannot be used to reach the target weight.");
        }
    }

    /**
     * Recursively configures the knapsack with values from the items array until it finds an appropriate combination
     * or runs out of items. Will print out the successful combination of items it discovers.
     */
    private void solve(int targetWeight, int[] items, int itemIndex, int knapsackIndex) {
        while (true) {
            System.out.print("The knapsack currently has " + knapsackIndex + " items in it. ");

            // Take an item from the collection.
            int currentItem = items[itemIndex];
            System.out.println("Trying to reach " + targetWeight + " weight.");
            if (currentItem == targetWeight) {
                // Put in the current item. The current item matches the target weight. The knapsack is now full.
                System.out.println("Adding item #" + (itemIndex + 1) + " weighing " + items[itemIndex] + ".");
                knapsack.add(knapsackIndex, currentItem);
                System.out.println("Target weight reached with items weighing " + knapsack.toString() + ".");
                isFull = true;
                return;

            } else if (currentItem > targetWeight) {
                // Put in the current item. It is too large.
                System.out.println("Adding item #" + (itemIndex + 1) + " weighing " + items[itemIndex] + ".");
                knapsack.add(knapsackIndex, currentItem);
                targetWeight -= currentItem;
                System.out.print("Too heavy! ");

            } else if (itemIndex < items.length - 1) {
                // The items fits and there are more items which can be placed in the knapsack. Put in the current item,
                // and call the function again on the next item for the next knapsack space.
                System.out.println("Adding item #" + (itemIndex + 1) + " weighing " + items[itemIndex] + ".");
                knapsack.add(knapsackIndex, currentItem);
                targetWeight -= currentItem;
                solve(targetWeight, items, itemIndex + 1, knapsackIndex + 1);
                if (isFull) {
                    // The recursive call returned and the knapsack is full.
                    return;
                }

            } else {
                // The items fits but there are no more items to add.
                System.out.println("No more items to add...");
                return;
            }

            // Either the recursive call returned and the knapsack is not full (meaning the item inserted on THIS call
            // will not create an appropriate combination with any previous items inserted) or the item inserted on this
            // call was too large. Both possible instances require us to remove the item inserted on this call.
            System.out.println("Removing item weighing " + knapsack.get(knapsackIndex) + ".");
            knapsack.remove(knapsackIndex);

            if (itemIndex >= items.length - 1) {
                // There are no more items to add.
                System.out.println("No more items to add...");
                return;
            }

            // There are more items to add. Adjust the target weight to reflect the current item being removed, and try
            // the next item for the next loop.
            targetWeight += currentItem;
            itemIndex++;

        }
    }
}

class TeamSorter {

//    void showTeams(int[] players, int teamSize) {
//        System.out.println(calculateTeams());
//    }
//
//    private int[] calculateTeams(int[] players, int[] combination, int teamSize, int playersIndex, int combinationIndex) {
//        // Take first element in array
//        //
//    }
}
