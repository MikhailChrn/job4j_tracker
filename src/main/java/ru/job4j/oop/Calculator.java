package ru.job4j.oop;

public class Calculator {

    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public int multiply(int a) {
        return x * a;
    }

    public static int minus(int u) {
        return x - u;
    }

    public int divide(int n) {
        return n / x;
    }

    public int sumAllOperation(int z) {
        return sum(z) + multiply(z) + minus(z) + divide(z);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println("Демонстрация метода SUM: " + sum(5));
        System.out.println("Демонстрация метода MULTIPLY : " + calculator.multiply(5));
        System.out.println("Демонстрация метода MINUS: " + minus(5));
        System.out.println("Демонстрация метода DEVIDE : " + calculator.divide(5));
        System.out.println("Демонстрация метода sumAllOperation: " + calculator.sumAllOperation(5));
    }
}
