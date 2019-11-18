package com.dommyg.recursionapp;

/**
 *  Allows for practice with recursion. Practice problems are detailed below.
 *
 *  6.1 Supposed you buy a budget-priced pocket PC and discover that the chip inside can't do multiplication, only
 *  addition. You program your way out of this quandary by writing a recursive method, mult(), that performs
 *  multiplication of x and y by adding x to itself y times. Its arguments are x and y and its returning value is the
 *  product of x and y. Write such a method and a main() program to call it. Does the addition take place when the
 *  method calls itself or when it returns?
 *
 *  6.3 Implement the recursive approach to raising a number to a power, as described in the "Raising a Number to a
 *  Power" section near the end of this chapter. Write the recursive power() function and a main() routine to test it.
 *
 *  6.4 Write a program that solved the knapsack problem for an arbitrary knapsack capacity and series of weights.
 *  Assume the weights are stored in an array. Hint: The arguments to the recursive knapsack() function are the target
 *  weight and the array index where the remaining items start.
 *
 *  6.5 Implements a recursive approach to showing all the teams that can be created from a group (n things taken k at
 *  a time). Write the recursive showTeams() method and a main() method to prompt the user for the group size and the
 *  team size to provide arguments for showTeam(), which then displays all the possible combinations.
 */
public class RecursionApp {

    public static void main(String[] args) {
        // PRINT: 30
        System.out.println(mult(5,6));
        // PRINT: 100
        System.out.println(mult(10,10));
        // PRINT: 5850
        System.out.println(mult(65, 90));

        // PRINT: 64
        System.out.println(power(8, 2));
        // PRINT: 512
        System.out.println(power(8,3));
        // PRINT: 32768
        System.out.println(power(2, 15));
    }

    /**
     * Multiplies two values and returns the result.
     */
    private static long mult(long x, long y) {
        if (y == 1) {
            return x;
        } else {
            return (x + mult(x, y - 1));
        }
    }

    private static long power(long x, long y) {
        if (y == 1) {
            return x;
        } else {
            return (x * power(x, y - 1));
        }
    }
}
