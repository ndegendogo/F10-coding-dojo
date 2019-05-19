package com.company;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTestUsingLoop {

    static int maxPerimeter = 1000;
//    static int maxPerimeter = 10000;  // Beware! the 3-fold loop scales not so well for very large numbers

    @Test
    void canCreateTriangle() {
        Triangle t = new Triangle(3, 4, 5);
        assertTrue(t != null);
    }

    @Test
    void calcPerimeter() {
        Triangle t = new Triangle(3, 4, 5);
        int perimeter = t.perimeter();
        assertEquals(12, perimeter);
    }

    @Test
    void testOrthogonal() {
        Triangle t = new Triangle(3, 4, 5);
        assertTrue(t.isOrthogonal());
    }

    @Test
    void testNotOrthogonal() {
        Triangle t = new Triangle(2, 4, 5);
        assertFalse(t.isOrthogonal());
    }

    // insight: for every triangle, the following equations hold:
    // let a <= b <= c be the length of its sides
    // then 0 < a <= b <= c < p/2 where p = a + b + c

    @Test
    void countSolutions() {
        int count = 0;
        for (int p = 1; p <= maxPerimeter; p ++) {
            for (int a = 1; a < p / 2; a++) {
                for (int b = a + 1; b < p / 2; b++) {
                    // a + b + c = p, so c = p - (a + b)
                    int c = p - (a + b);
                    if (Triangle.isOrthogonal(a, b, c)) {
                        count ++;
                    }
                }
            }
        }
        System.out.println(count + " Solutions");
    }

    @Test
    void findSolutions() {
        List<Triangle> list = new ArrayList<Triangle>();
        for (int p = 1; p <= maxPerimeter; p ++) {
            for (int a = 1; a < p / 2; a++) {
                for (int b = a + 1; b < p / 2; b++) {
                    // a + b + c = p, so c = p - (a + b)
                    int c = p - (a + b);
                    if (Triangle.isOrthogonal(a, b, c)) {
                        list.add(new Triangle(a, b, c));
                    }
                }
            }
        }
        System.out.println(list.size() + " Solutions");
    }

    @Test
    void mapAnySolution() {
        Map<Integer, Triangle> map = new HashMap<Integer, Triangle>();
        int p = maxPerimeter;
        for (int a = 1; a < p / 2; a++) {
            for (int b = a + 1; b < p / 2; b++) {
                for (int c = b + 1; c < p/2; c++) {
                    if (Triangle.isOrthogonal(a, b, c)) {
                        Triangle triangle = new Triangle(a, b, c);
                        map.put(triangle.perimeter(), triangle);
                    }
                }
            }
        }
        System.out.println(map.size() + " Solutions");
    }

    @Test
    void countSolutionsInMap() {
        Map<Integer, Integer> solutionCount = new HashMap<>(maxPerimeter);
        int maxSide = maxPerimeter / 2;
        for (int a = 1; a <= maxSide; a++) {
            for (int b = a + 1; b <= maxSide; b++) {
                for (int c = b + 1; c <= maxSide; c++) {
                    int key = Triangle.calcPerimeter(a, b, c);
                    if (key > maxPerimeter) continue;
                    if (Triangle.isOrthogonal(a, b, c)) {
                        Integer value = solutionCount.get(key);
                        if (value == null) {
                            solutionCount.put(key, 1);
                        } else {
                            solutionCount.put(key, value + 1);
                        }
                    }
                }
            }
        }
/*
        System.out.println("Solutions exist for: ");
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
*/
        int max = 0;
        int perim = 0;
        for (Map.Entry<Integer, Integer> entry : solutionCount.entrySet()) {
            int count = entry.getValue();
            if (count > max) {
                max = count;
                perim = entry.getKey();
            }
        }
        System.out.println("Max number of solutions = " + max + " for perimeter " + perim);
    }

    @Test
    void storeOneSolutionInMap() {
        Map<Integer, Triangle> map = new HashMap<>(maxPerimeter);
        int p = maxPerimeter;
        int p2 = p / 2;
        for (int a = 1; a < p2; a++) {
            for (int b = a; b < p2; b++) {
                for (int c = b; c < p2; c++) {
                    int key = Triangle.calcPerimeter(a, b, c);
                    if (key <= p) {
                        if (Triangle.isOrthogonal(a, b, c)) {
                            Triangle triangle = new Triangle(a, b, c);
                            if (map.get(key) == null) {
                                map.put(key, triangle);
                            }
                        }
                    }
                }
            }
        }
        System.out.println("storeOneSolutionInMap: " + map.size() + " solutions");
        for (Map.Entry<Integer, Triangle> e : map.entrySet()) {
            Triangle triangle = e.getValue();
            System.out.println(e.getKey() + ": " + triangle.toString());
        }
    }

    @Test
    void storeAllSolutionsInMap() {
        Map<Integer, List<Triangle>> map = new HashMap<>(maxPerimeter);
        int p = maxPerimeter;
        int p2 = p / 2;
        for (int a = 1; a < p2; a++) {
            for (int b = a; b < p2; b++) {
                for (int c = b; c < p2; c++) {
                    int key = Triangle.calcPerimeter(a, b, c);
                    if (key <= p) {
                        if (Triangle.isOrthogonal(a, b, c)) {
                            Triangle triangle = new Triangle(a, b, c);
                            List<Triangle> solutions = map.get(key);
                            if (solutions == null) {
                                solutions = new ArrayList<Triangle>(1);
                            }
                            solutions.add(triangle);
                            map.put(key, solutions);
                        }
                    }
                }
            }
        }
        System.out.println("storeAllSolutionsInMap: " + map.size() + " solutions");
        for (Map.Entry<Integer, List<Triangle>> e : map.entrySet()) {
            List<Triangle> solutions = e.getValue();
            System.out.print(e.getKey() + ": ");
            for (Triangle t : solutions) {
                System.out.print(t.toString());
            }
            System.out.println();
        }
    }

}