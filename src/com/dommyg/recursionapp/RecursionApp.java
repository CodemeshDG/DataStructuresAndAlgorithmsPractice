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

class KnapsackSolver {
    private ArrayList<Integer> knapsack;
    private boolean isFull;

    void solve(int targetWeight, int[] items) {
        knapsack = new ArrayList<>();
        isFull = false;

        solve(targetWeight, items, 0,0);
    }

    private void solve(int targetWeight, int[] items, int itemIndex, int knapsackIndex) {
        while(true) {
            int currentItem = items[itemIndex];
            System.out.println("Trying to reach " + targetWeight + " weight.");
            if (currentItem == targetWeight) {
                System.out.println("Adding item #" + (itemIndex + 1) + " weighing " + items[itemIndex] + ".");
                knapsack.add(knapsackIndex, currentItem);
                System.out.println("Target weight reached with items weighing " + knapsack.toString() + ".");
                isFull = true;
                return;
            } else if (currentItem > targetWeight) {
                System.out.println("Adding item #" + (itemIndex + 1) + " weighing " + items[itemIndex] + ".");
                System.out.println("Too heavy! Removing this item.");
                itemIndex++;
            } else if (currentItem < targetWeight && itemIndex < items.length - 1) {
                System.out.println("Adding item #" + (itemIndex + 1) + " weighing " + items[itemIndex] + ".");
                knapsack.add(knapsackIndex, currentItem);
                targetWeight -= currentItem;
                itemIndex++;
                knapsackIndex++;
                solve(targetWeight, items, itemIndex, knapsackIndex);
            } else {
                System.out.println("No more items to add...");
                return;
            }
            itemIndex++;
            knapsackIndex--;
            if (isFull || itemIndex < items.length - 1) {
                return;
            }
            System.out.println("Taking out item...");
            knapsack.remove(knapsackIndex);
        }
    }

//    private void solve(int targetWeight, int[] items, int itemIndex) {
//        while(true) {
//            System.out.println("Trying to reach " + targetWeight + " weight.");
//            knapsack.add(knapsackIndex, items[itemIndex]);
//            targetWeight -= items[itemIndex];
//            System.out.println("Added item #" + (itemIndex + 1) + " weighing " + items[itemIndex] + ".");
//            if (targetWeight == 0) {
//                System.out.println("Target weight reached with items weighing " + knapsack.toString() + ".");
//                isFull = true;
//                return;
//            }
//            if (targetWeight < 0) {
//                System.out.println("Too heavy! Removing this item.");
//                knapsack.remove(knapsackIndex);
//                knapsackIndex--;
//            } else if (itemIndex == items.length - 1) {
//                System.out.println("There are no more items. Removing item weighing " + knapsack.get(knapsackIndex) + ".");
//                knapsack.remove(knapsackIndex);
//                knapsackIndex--;
//                return;
//            }
//
//            itemIndex++;
//            knapsackIndex++;
//            solve(targetWeight, items, itemIndex);
//
//            targetWeight += items[itemIndex - 1];
//            itemIndex++;
//
//            if(isFull || itemIndex == items.length) {
//                return;
//            }
//
//        }
//    }

//    void solve(int targetWeight, int[] items) {
//        for (int i = 0; i < items.length - 1; i++) {
//            System.out.println("Knapsack is empty. Putting in item #" + (i + 1) + " weighing " + items[i] + ".");
//            if (configureKnapsack(targetWeight - items[i], items, i + 1)) {
//                return;
//            } else {
//                System.out.println("Trying again from the start.");
//            }
//        }
//        System.out.println("Could not reach target weight with these items.");
//    }
//
//    private boolean configureKnapsack(int targetWeight, int[] items, int index) {
//        System.out.println("Currently " + targetWeight + " weight remains free in knapsack.");
//        targetWeight -= items[index];
//
//        if (targetWeight == 0) {
//            System.out.println("Item # " + (index + 1) + " weighing " + items[index] + " fits! Target weight reached!");
//            return true;
//        }
//        if (targetWeight < 0) {
//            System.out.println("Item # " + (index + 1) + " weighing " + items[index] + " is too large.");
//            return false;
//        }
//        System.out.println("Item # " + (index + 1) + " weighing " + items[index] + " fits!");
//
//        for (int i = index; i < items.length - 1; i++) {
//            if (configureKnapsack(targetWeight, items, i + 1)) {
//                return true;
//            }
//        }
//        System.out.println("No more items to try. Must remove an item and try a new combination.");
//        return false;
//    }
//
//    boolean configureKnapsack2(int targetWeight, int[] items, int index) {
////        System.out.println("Knapsack is empty. Putting in item #" + (index + 1) + " weighing " + items[index] + ".");
//
//        for (int i = index; i < items.length - 1; i++) {
//            System.out.println("Putting in item #" + (index + 1) + " weighing " + items[index] + ".");
//            targetWeight -= items[i];
//            System.out.println("Currently " + targetWeight + " weight remains free in knapsack.");
//
//            if (targetWeight == 0) {
//                System.out.println("Item # " + (index + 1) + " weighing " + items[index] + " fits! Target weight reached!");
//                return true;
//            }
//            if (targetWeight < 0) {
//                System.out.println("Item # " + (index + 1) + " weighing " + items[index] + " is too large.");
//                return false;
//            }
//            System.out.println("Item # " + (index + 1) + " weighing " + items[index] + " fits!");
//
//            if (configureKnapsack(targetWeight, items, i + 1)) {
//                return true;
//            }
//        }
//        return false;
//    }
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
