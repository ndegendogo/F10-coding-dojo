package com.company;

public class Triangle {
    private final int a;
    private final int b;
    private final int c;

    Triangle(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    static int calcPerimeter(int a, int b, int c) {
        return a + b + c;
    }

    int perimeter() {
        int a = this.a;
        int b = this.b;
        int c = this.c;
        int perim = calcPerimeter(a, b, c);
        return perim;
    }

    static boolean isOrthogonal(int a, int b, int c) {
        return a * a + b * b == c * c;
    }

    boolean isOrthogonal() {
        return Triangle.isOrthogonal(a, b, c);
    }

    public String toString() {
        return "( " + a + ", " + b + " ," + c + " )";
    }
    public static void main(String[] args) {

    }
}
