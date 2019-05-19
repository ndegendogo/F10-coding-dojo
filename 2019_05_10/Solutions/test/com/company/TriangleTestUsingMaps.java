package com.company;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class TriangleTestUsingMaps {

    private final int maxPerimeter = 10000;

    @Test
    void countSolutionsOnly() {
        int maxLengthOfSide = maxPerimeter / 2;
        Map<Integer, Integer> mapOfSquares = new HashMap<>(maxLengthOfSide);
        for (int c = 1; c <= maxLengthOfSide; c ++) {
            mapOfSquares.put(c * c, c);
        }
        Map<Integer, Integer> solutionCounts = new HashMap<>();
        for( int a = 1; a <= maxLengthOfSide; a ++) {
            for (int b = a; b <= maxLengthOfSide; b ++) {
                Integer c = mapOfSquares.get(a*a + b*b);
                if (c == null) continue;    // no solution
                Integer perimeter = a + b + c;
                if (perimeter > maxPerimeter) continue; // beyond our scope
                Integer count = solutionCounts.get(perimeter);
                if (count == null) {
                    solutionCounts.put(perimeter, 1);
                } else {
                    solutionCounts.put(perimeter, count + 1);
                }
            }
        }
        int max = 0;
        int perim = 0;
        for (Map.Entry<Integer, Integer> entry : solutionCounts.entrySet()) {
            int count = entry.getValue();
            if (count > max) {
                max = count;
                perim = entry.getKey();
            }
        }
        System.out.println("Max number of solutions = " + max + " for perimeter " + perim);
    }

}
