package com.dommyg.recursionapp;

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

        array[0] = 11;
        array[1] = 8;
        array[2] = 7;
        array[3] = 6;
        array[4] = 5;

        // PRINT: Knapsack is empty. Putting in item #1 weighing 11.
        //Currently 9 weight remains free in knapsack.
        //Item # 2 weighing 8 fits!
        //Currently 1 weight remains free in knapsack.
        //Item # 3 weighing 7 is too large.
        //Currently 1 weight remains free in knapsack.
        //Item # 4 weighing 6 is too large.
        //Currently 1 weight remains free in knapsack.
        //Item # 5 weighing 5 is too large.
        //No more items to try. Must remove an item and try a new combination.
        //Trying again from the start.
        //Knapsack is empty. Putting in item #2 weighing 8.
        //Currently 12 weight remains free in knapsack.
        //Item # 3 weighing 7 fits!
        //Currently 5 weight remains free in knapsack.
        //Item # 4 weighing 6 is too large.
        //Currently 5 weight remains free in knapsack.
        //Item # 5 weighing 5 fits! Target weight reached!
//        knapsackSolver.solve(20, array);

        knapsackSolver.configureKnapsack2(20, array, 0);

        System.out.println();

        array[0] = 15;
        array[1] = 2;
        array[2] = 8;
        array[3] = 1;
        array[4] = 6;

        // PRINT: Knapsack is empty. Putting in item #1 weighing 15.
        //Currently 7 weight remains free in knapsack.
        //Item # 2 weighing 2 fits!
        //Currently 5 weight remains free in knapsack.
        //Item # 3 weighing 8 is too large.
        //Currently 5 weight remains free in knapsack.
        //Item # 4 weighing 1 fits!
        //Currently 4 weight remains free in knapsack.
        //Item # 5 weighing 6 is too large.
        //No more items to try. Must remove an item and try a new combination.
        //Currently 5 weight remains free in knapsack.
        //Item # 5 weighing 6 is too large.
        //No more items to try. Must remove an item and try a new combination.
        //Trying again from the start.
        //Knapsack is empty. Putting in item #2 weighing 2.
        //Currently 20 weight remains free in knapsack.
        //Item # 3 weighing 8 fits!
        //Currently 12 weight remains free in knapsack.
        //Item # 4 weighing 1 fits!
        //Currently 11 weight remains free in knapsack.
        //Item # 5 weighing 6 fits!
        //No more items to try. Must remove an item and try a new combination.
        //No more items to try. Must remove an item and try a new combination.
        //Currently 12 weight remains free in knapsack.
        //Item # 5 weighing 6 fits!
        //No more items to try. Must remove an item and try a new combination.
        //No more items to try. Must remove an item and try a new combination.
        //Trying again from the start.
        //Knapsack is empty. Putting in item #3 weighing 8.
        //Currently 14 weight remains free in knapsack.
        //Item # 4 weighing 1 fits!
        //Currently 13 weight remains free in knapsack.
        //Item # 5 weighing 6 fits!
        //No more items to try. Must remove an item and try a new combination.
        //No more items to try. Must remove an item and try a new combination.
        //Trying again from the start.
        //Knapsack is empty. Putting in item #4 weighing 1.
        //Currently 21 weight remains free in knapsack.
        //Item # 5 weighing 6 fits!
        //No more items to try. Must remove an item and try a new combination.
        //Trying again from the start.
        //Could not reach target weight with these items.
//        knapsackSolver.solve(22, array);

//        int[] players = new int[5];
//
//        players[0] = 1;
//        players[1] = 2;
//        players[2] = 3;
//        players[3] = 4;
//        players[4] = 5;
//
//        TeamSorter teamSorter = new TeamSorter();
//
//        teamSorter.showTeams(players, 3);
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

    void solve(int targetWeight, int[] items) {
        for (int i = 0; i < items.length - 1; i++) {
            System.out.println("Knapsack is empty. Putting in item #" + (i + 1) + " weighing " + items[i] + ".");
            if (configureKnapsack(targetWeight - items[i], items, i + 1)) {
                return;
            } else {
                System.out.println("Trying again from the start.");
            }
        }
        System.out.println("Could not reach target weight with these items.");
    }

    private boolean configureKnapsack(int targetWeight, int[] items, int index) {
        System.out.println("Currently " + targetWeight + " weight remains free in knapsack.");
        targetWeight -= items[index];

        if (targetWeight == 0) {
            System.out.println("Item # " + (index + 1) + " weighing " + items[index] + " fits! Target weight reached!");
            return true;
        }
        if (targetWeight < 0) {
            System.out.println("Item # " + (index + 1) + " weighing " + items[index] + " is too large.");
            return false;
        }
        System.out.println("Item # " + (index + 1) + " weighing " + items[index] + " fits!");

        for (int i = index; i < items.length - 1; i++) {
            if (configureKnapsack(targetWeight, items, i + 1)) {
                return true;
            }
        }
        System.out.println("No more items to try. Must remove an item and try a new combination.");
        return false;
    }

    boolean configureKnapsack2(int targetWeight, int[] items, int index) {
//        System.out.println("Knapsack is empty. Putting in item #" + (index + 1) + " weighing " + items[index] + ".");

        for (int i = index; i < items.length - 1; i++) {
            System.out.println("Putting in item #" + (index + 1) + " weighing " + items[index] + ".");
            targetWeight -= items[i];
            System.out.println("Currently " + targetWeight + " weight remains free in knapsack.");

            if (targetWeight == 0) {
                System.out.println("Item # " + (index + 1) + " weighing " + items[index] + " fits! Target weight reached!");
                return true;
            }
            if (targetWeight < 0) {
                System.out.println("Item # " + (index + 1) + " weighing " + items[index] + " is too large.");
                return false;
            }
            System.out.println("Item # " + (index + 1) + " weighing " + items[index] + " fits!");

            if (configureKnapsack(targetWeight, items, i + 1)) {
                return true;
            }
        }
        return false;
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
