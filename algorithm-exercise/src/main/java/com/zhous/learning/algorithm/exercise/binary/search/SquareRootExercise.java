package com.zhous.learning.algorithm.exercise.binary.search;

/**
 * 求平方根
 *
 * @author Zhou Changjian
 * @version 1.0
 * @since 2021/7/28
 */
public class SquareRootExercise {

    public static void main(String[] args) {
        System.out.println(squareRoot(0.5f, 0.000001));
        System.out.println(Math.sqrt(0.5f));
    }

    public static double squareRoot(double value, double precision) {
        double low = 0f;
        double high = value;
        if (value < 1) {
            low = value;
            high = 1;
        }
        double mid = low + ((high - low) / 2);
        double square = mid * mid;
        while (Math.abs(square - value) > precision) {
            if (square > value) {
                high = mid;
            } else {
                low = mid;
            }
            mid = low + ((high - low) / 2);
            square = mid * mid;
        }
        return mid;
    }

}