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
 * team size to provide arguments for showTeams(), which then displays all the possible combinations.
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

        // PRINT: The knapsack currently has 0 items in it. Trying to reach 12 weight.
        //Adding item #1 weighing 6.
        //The knapsack currently has 1 items in it. Trying to reach 6 weight.
        //Adding item #2 weighing 3.
        //The knapsack currently has 2 items in it. Trying to reach 3 weight.
        //Adding item #3 weighing 2.
        //The knapsack currently has 3 items in it. Trying to reach 1 weight.
        //Adding item #4 weighing 8.
        //Too heavy! Removing item weighing 8.
        //The knapsack currently has 3 items in it. Trying to reach 1 weight.
        //Adding item #5 weighing 4.
        //Too heavy! Removing item weighing 4.
        //No more items to add...
        //Removing item weighing 2.
        //The knapsack currently has 2 items in it. Trying to reach 3 weight.
        //Adding item #4 weighing 8.
        //Too heavy! Removing item weighing 8.
        //The knapsack currently has 2 items in it. Trying to reach 3 weight.
        //Adding item #5 weighing 4.
        //Too heavy! Removing item weighing 4.
        //No more items to add...
        //Removing item weighing 3.
        //The knapsack currently has 1 items in it. Trying to reach 6 weight.
        //Adding item #3 weighing 2.
        //The knapsack currently has 2 items in it. Trying to reach 4 weight.
        //Adding item #4 weighing 8.
        //Too heavy! Removing item weighing 8.
        //The knapsack currently has 2 items in it. Trying to reach 4 weight.
        //Adding item #5 weighing 4.
        //Target weight reached with items weighing [6, 2, 4].
        knapsackSolver.solve(12, array);

        System.out.println();

        int[] array2 = new int[6];

        array2[0] = 14;
        array2[1] = 2;
        array2[2] = 85;
        array2[3] = 22;
        array2[4] = 61;
        array2[5] = 9;

        // PRINT: The knapsack currently has 0 items in it. Trying to reach 203 weight.
        //Adding item #1 weighing 14.
        //The knapsack currently has 1 items in it. Trying to reach 189 weight.
        //Adding item #2 weighing 2.
        //The knapsack currently has 2 items in it. Trying to reach 187 weight.
        //Adding item #3 weighing 85.
        //The knapsack currently has 3 items in it. Trying to reach 102 weight.
        //Adding item #4 weighing 22.
        //The knapsack currently has 4 items in it. Trying to reach 80 weight.
        //Adding item #5 weighing 61.
        //The knapsack currently has 5 items in it. Trying to reach 19 weight.
        //No more items to add...
        //Removing item weighing 61.
        //The knapsack currently has 4 items in it. Trying to reach 80 weight.
        //No more items to add...
        //Removing item weighing 22.
        //The knapsack currently has 3 items in it. Trying to reach 102 weight.
        //Adding item #5 weighing 61.
        //The knapsack currently has 4 items in it. Trying to reach 41 weight.
        //No more items to add...
        //Removing item weighing 61.
        //The knapsack currently has 3 items in it. Trying to reach 102 weight.
        //No more items to add...
        //Removing item weighing 85.
        //The knapsack currently has 2 items in it. Trying to reach 187 weight.
        //Adding item #4 weighing 22.
        //The knapsack currently has 3 items in it. Trying to reach 165 weight.
        //Adding item #5 weighing 61.
        //The knapsack currently has 4 items in it. Trying to reach 104 weight.
        //No more items to add...
        //Removing item weighing 61.
        //The knapsack currently has 3 items in it. Trying to reach 165 weight.
        //No more items to add...
        //Removing item weighing 22.
        //The knapsack currently has 2 items in it. Trying to reach 187 weight.
        //Adding item #5 weighing 61.
        //The knapsack currently has 3 items in it. Trying to reach 126 weight.
        //No more items to add...
        //Removing item weighing 61.
        //The knapsack currently has 2 items in it. Trying to reach 187 weight.
        //No more items to add...
        //Removing item weighing 2.
        //The knapsack currently has 1 items in it. Trying to reach 189 weight.
        //Adding item #3 weighing 85.
        //The knapsack currently has 2 items in it. Trying to reach 104 weight.
        //Adding item #4 weighing 22.
        //The knapsack currently has 3 items in it. Trying to reach 82 weight.
        //Adding item #5 weighing 61.
        //The knapsack currently has 4 items in it. Trying to reach 21 weight.
        //No more items to add...
        //Removing item weighing 61.
        //The knapsack currently has 3 items in it. Trying to reach 82 weight.
        //No more items to add...
        //Removing item weighing 22.
        //The knapsack currently has 2 items in it. Trying to reach 104 weight.
        //Adding item #5 weighing 61.
        //The knapsack currently has 3 items in it. Trying to reach 43 weight.
        //No more items to add...
        //Removing item weighing 61.
        //The knapsack currently has 2 items in it. Trying to reach 104 weight.
        //No more items to add...
        //Removing item weighing 85.
        //The knapsack currently has 1 items in it. Trying to reach 189 weight.
        //Adding item #4 weighing 22.
        //The knapsack currently has 2 items in it. Trying to reach 167 weight.
        //Adding item #5 weighing 61.
        //The knapsack currently has 3 items in it. Trying to reach 106 weight.
        //No more items to add...
        //Removing item weighing 61.
        //The knapsack currently has 2 items in it. Trying to reach 167 weight.
        //No more items to add...
        //Removing item weighing 22.
        //The knapsack currently has 1 items in it. Trying to reach 189 weight.
        //Adding item #5 weighing 61.
        //The knapsack currently has 2 items in it. Trying to reach 128 weight.
        //No more items to add...
        //Removing item weighing 61.
        //The knapsack currently has 1 items in it. Trying to reach 189 weight.
        //No more items to add...
        //Removing item weighing 14.
        //The knapsack currently has 0 items in it. Trying to reach 203 weight.
        //Adding item #2 weighing 2.
        //The knapsack currently has 1 items in it. Trying to reach 201 weight.
        //Adding item #3 weighing 85.
        //The knapsack currently has 2 items in it. Trying to reach 116 weight.
        //Adding item #4 weighing 22.
        //The knapsack currently has 3 items in it. Trying to reach 94 weight.
        //Adding item #5 weighing 61.
        //The knapsack currently has 4 items in it. Trying to reach 33 weight.
        //No more items to add...
        //Removing item weighing 61.
        //The knapsack currently has 3 items in it. Trying to reach 94 weight.
        //No more items to add...
        //Removing item weighing 22.
        //The knapsack currently has 2 items in it. Trying to reach 116 weight.
        //Adding item #5 weighing 61.
        //The knapsack currently has 3 items in it. Trying to reach 55 weight.
        //No more items to add...
        //Removing item weighing 61.
        //The knapsack currently has 2 items in it. Trying to reach 116 weight.
        //No more items to add...
        //Removing item weighing 85.
        //The knapsack currently has 1 items in it. Trying to reach 201 weight.
        //Adding item #4 weighing 22.
        //The knapsack currently has 2 items in it. Trying to reach 179 weight.
        //Adding item #5 weighing 61.
        //The knapsack currently has 3 items in it. Trying to reach 118 weight.
        //No more items to add...
        //Removing item weighing 61.
        //The knapsack currently has 2 items in it. Trying to reach 179 weight.
        //No more items to add...
        //Removing item weighing 22.
        //The knapsack currently has 1 items in it. Trying to reach 201 weight.
        //Adding item #5 weighing 61.
        //The knapsack currently has 2 items in it. Trying to reach 140 weight.
        //No more items to add...
        //Removing item weighing 61.
        //The knapsack currently has 1 items in it. Trying to reach 201 weight.
        //No more items to add...
        //Removing item weighing 2.
        //The knapsack currently has 0 items in it. Trying to reach 203 weight.
        //Adding item #3 weighing 85.
        //The knapsack currently has 1 items in it. Trying to reach 118 weight.
        //Adding item #4 weighing 22.
        //The knapsack currently has 2 items in it. Trying to reach 96 weight.
        //Adding item #5 weighing 61.
        //The knapsack currently has 3 items in it. Trying to reach 35 weight.
        //No more items to add...
        //Removing item weighing 61.
        //The knapsack currently has 2 items in it. Trying to reach 96 weight.
        //No more items to add...
        //Removing item weighing 22.
        //The knapsack currently has 1 items in it. Trying to reach 118 weight.
        //Adding item #5 weighing 61.
        //The knapsack currently has 2 items in it. Trying to reach 57 weight.
        //No more items to add...
        //Removing item weighing 61.
        //The knapsack currently has 1 items in it. Trying to reach 118 weight.
        //No more items to add...
        //Removing item weighing 85.
        //The knapsack currently has 0 items in it. Trying to reach 203 weight.
        //Adding item #4 weighing 22.
        //The knapsack currently has 1 items in it. Trying to reach 181 weight.
        //Adding item #5 weighing 61.
        //The knapsack currently has 2 items in it. Trying to reach 120 weight.
        //No more items to add...
        //Removing item weighing 61.
        //The knapsack currently has 1 items in it. Trying to reach 181 weight.
        //No more items to add...
        //Removing item weighing 22.
        //The knapsack currently has 0 items in it. Trying to reach 203 weight.
        //Adding item #5 weighing 61.
        //The knapsack currently has 1 items in it. Trying to reach 142 weight.
        //No more items to add...
        //Removing item weighing 61.
        //The knapsack currently has 0 items in it. Trying to reach 203 weight.
        //No more items to add...
        //These items cannot be used to reach the target weight.
        knapsackSolver.solve(203, array2);

        System.out.println();

        int[] teamArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        TeamSorter teamSorter = new TeamSorter();

        // PRINT: [1, 2, 3]
        //[1, 2, 4]
        //[1, 2, 5]
        //[1, 2, 6]
        //[1, 2, 7]
        //[1, 2, 8]
        //[1, 2, 9]
        //[1, 2, 10]
        //[1, 3, 4]
        //[1, 3, 5]
        //[1, 3, 6]
        //[1, 3, 7]
        //[1, 3, 8]
        //[1, 3, 9]
        //[1, 3, 10]
        //[1, 4, 5]
        //[1, 4, 6]
        //[1, 4, 7]
        //[1, 4, 8]
        //[1, 4, 9]
        //[1, 4, 10]
        //[1, 5, 6]
        //[1, 5, 7]
        //[1, 5, 8]
        //[1, 5, 9]
        //[1, 5, 10]
        //[1, 6, 7]
        //[1, 6, 8]
        //[1, 6, 9]
        //[1, 6, 10]
        //[1, 7, 8]
        //[1, 7, 9]
        //[1, 7, 10]
        //[1, 8, 9]
        //[1, 8, 10]
        //[1, 9, 10]
        //[2, 3, 4]
        //[2, 3, 5]
        //[2, 3, 6]
        //[2, 3, 7]
        //[2, 3, 8]
        //[2, 3, 9]
        //[2, 3, 10]
        //[2, 4, 5]
        //[2, 4, 6]
        //[2, 4, 7]
        //[2, 4, 8]
        //[2, 4, 9]
        //[2, 4, 10]
        //[2, 5, 6]
        //[2, 5, 7]
        //[2, 5, 8]
        //[2, 5, 9]
        //[2, 5, 10]
        //[2, 6, 7]
        //[2, 6, 8]
        //[2, 6, 9]
        //[2, 6, 10]
        //[2, 7, 8]
        //[2, 7, 9]
        //[2, 7, 10]
        //[2, 8, 9]
        //[2, 8, 10]
        //[2, 9, 10]
        //[3, 4, 5]
        //[3, 4, 6]
        //[3, 4, 7]
        //[3, 4, 8]
        //[3, 4, 9]
        //[3, 4, 10]
        //[3, 5, 6]
        //[3, 5, 7]
        //[3, 5, 8]
        //[3, 5, 9]
        //[3, 5, 10]
        //[3, 6, 7]
        //[3, 6, 8]
        //[3, 6, 9]
        //[3, 6, 10]
        //[3, 7, 8]
        //[3, 7, 9]
        //[3, 7, 10]
        //[3, 8, 9]
        //[3, 8, 10]
        //[3, 9, 10]
        //[4, 5, 6]
        //[4, 5, 7]
        //[4, 5, 8]
        //[4, 5, 9]
        //[4, 5, 10]
        //[4, 6, 7]
        //[4, 6, 8]
        //[4, 6, 9]
        //[4, 6, 10]
        //[4, 7, 8]
        //[4, 7, 9]
        //[4, 7, 10]
        //[4, 8, 9]
        //[4, 8, 10]
        //[4, 9, 10]
        //[5, 6, 7]
        //[5, 6, 8]
        //[5, 6, 9]
        //[5, 6, 10]
        //[5, 7, 8]
        //[5, 7, 9]
        //[5, 7, 10]
        //[5, 8, 9]
        //[5, 8, 10]
        //[5, 9, 10]
        //[6, 7, 8]
        //[6, 7, 9]
        //[6, 7, 10]
        //[6, 8, 9]
        //[6, 8, 10]
        //[6, 9, 10]
        //[7, 8, 9]
        //[7, 8, 10]
        //[7, 9, 10]
        //[8, 9, 10]
        //There are 120 combinations.
        teamSorter.showTeams(teamArray, 3);

        System.out.println();

        int[] teamArray2 = {4, 6, 8, 11, 14, 15, 16, 19, 23, 24, 26, 28, 30, 31, 32, 33, 34, 35, 37, 38, 40};

        // PRINT: (A ton of combinations which overflow the run screen)
        // There are 352716 combinations.
        teamSorter.showTeams(teamArray2, 10);
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
     *
     * @param targetWeight  The current weight remaining before reaching the target weight provided by the user.
     * @param items         The array of item weights provided by the user.
     * @param itemIndex     The current item to be used in the knapsack.
     * @param knapsackIndex The current item number in the knapsack being modified.
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

/**
 * Provides a showTeams method which, given an array of player numbers, will print out all combinations of a desired
 * team size and the total combination amount. Any instance of this class can be reused with multiple arrays.
 */
class TeamSorter {
    long total;

    /**
     * Prints out all combinations of player numbers (not considering order) and the total combination amount.
     *
     * @param players  The array of player numbers.
     * @param teamSize The desired size of a team.
     */
    void showTeams(int[] players, int teamSize) {
        if (players.length < teamSize) {
            // The team size is larger than the amount of players.
            System.out.println("Cannot make a team of size " + teamSize + " with only " + players.length + " players.");
            return;
        }

        // Set the total amount of combos to zero (to allow reuse of any TeamSorter instance).
        total = 0;
        int playersIndexEndPoint = (players.length - teamSize);

        calculateTeams(players, new int[teamSize], teamSize, playersIndexEndPoint, 0, 0);

        System.out.println("There are " + total + " combinations.");
    }

    /**
     * Prints out possible player combinations and increases the total counter each time a combination is printed.
     *
     * @param players              The array of player numbers provided by the user.
     * @param combination          The array which holds possible combinations.
     * @param teamSize             The size of the team as chosen by the user.
     * @param playersIndexEndPoint The index position in the player array where the final combination can be made by
     *                             taking it and all other indexes after it.
     * @param playersIndex         The current player to be used in the combination.
     * @param combinationIndex     The current position of the combination array.
     */
    private void calculateTeams(int[] players, int[] combination, int teamSize, int playersIndexEndPoint, int playersIndex, int combinationIndex) {
        while (true) {
            // Add a player to the combination.
            combination[combinationIndex] = players[playersIndex];

            if (combinationIndex + 1 == teamSize) {
                // A combination is complete. Increase the total combination count.
                System.out.println(Arrays.toString(combination));
                total++;
            } else {
                // Combination is incomplete.
                calculateTeams(players, combination, teamSize, playersIndexEndPoint + 1, playersIndex + 1, combinationIndex + 1);
            }

            // Move to next player in array.
            playersIndex++;

            if (playersIndex > playersIndexEndPoint) {
                // The index of this next player is beyond the index end point. A combination cannot be made with the
                // remaining players.
                return;
            }
        }
    }
}
