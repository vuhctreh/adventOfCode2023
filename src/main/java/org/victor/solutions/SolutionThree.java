package org.victor.solutions;

import java.util.*;

public class SolutionThree {
    public static void solve(List<String> input) {
        int sum = 0;
        int gearRatioSum = 0;

        Map<String, Integer> possibleGears = new HashMap<>();

        // Encode position of star when checking for symbols.
        // Add to Map<String, Integer> where String = encoded pos and Integer = adjacent number.
        // When adjacent star is found, check in map and calculate sum if present.
        // If not, add to map.

        // For each string in input array
        for(int i = 0; i < input.size(); i++) {
            String s = input.get(i);
            // For each character in string
            for(int j = 0; j < s.length(); j++) {
                boolean symbolFound = false;

                Set<String> gears = new HashSet<>();

                StringBuilder numBuffer = new StringBuilder();

                // Check behind
                if(j > 0) {
                    boolean[] condArr = isSymbolAtPosition(input, i, j - 1);
                    symbolFound = condArr[0];

                    if(condArr[1]) {
                        gears.add("y" + i + "x" + (j-1));
                    }

                    // Check top left
                    if (i > 0 && !symbolFound) {
                        condArr = isSymbolAtPosition(input, i - 1, j - 1);
                        symbolFound = condArr[0];

                        if(condArr[1]) {
                            gears.add("y" + (i-1) + "x" + (j-1));
                        }
                    }

                    // Check bottom left
                    if(i < input.size() - 1 && !symbolFound) {
                        condArr = isSymbolAtPosition(input, i + 1, j - 1);
                        symbolFound = condArr[0];

                        if(condArr[1]) {
                            gears.add("y" + (i+1) + "x" + (j-1));
                        }
                    }
                }

                // If Character is a digit
                while(Character.isDigit(s.charAt(j))) {
                    numBuffer.append(s.charAt(j));

                    // Check above
                    if(i > 0 && !symbolFound) {
                        boolean[] condArr = isSymbolAtPosition(input, i -1, j);
                        symbolFound = condArr[0];

                        if(condArr[1]) {
                            gears.add("y" + (i - 1) + "x" + (j));
                        }
                    }
                    // Check below
                    if (i < input.size() - 1 && !symbolFound) {
                        boolean[] condArr = isSymbolAtPosition(input, i + 1, j);
                        symbolFound = condArr[0];

                        if(condArr[1]) {
                            gears.add("y" + (i+1) + "x" + (j));
                        }
                    }

                    j++;

                    if(j > s.length() - 1) break;
                }

                // Check to the right
                if(j < s.length() - 1 && !symbolFound) {
                    boolean[] condArr = isSymbolAtPosition(input, i, j);
                    symbolFound = condArr[0];

                    if(condArr[1]) {
                        gears.add("y" + (i) + "x" + (j));
                    }

                    // Check top right
                    if (i > 0 && !symbolFound) {
                        condArr = isSymbolAtPosition(input, i - 1, j);
                        symbolFound = condArr[0];

                        if(condArr[1]) {
                            gears.add("y" + (i-1) + "x" + (j));
                        }
                    }

                    // Check bottom right
                    if(i < input.size() - 1 && !symbolFound) {
                        condArr = isSymbolAtPosition(input, i + 1, j);
                        symbolFound = condArr[0];

                        if(condArr[1]) {
                            gears.add("y" + (i+1) + "x" + (j));
                        }
                    }
                }

                if(symbolFound && !numBuffer.isEmpty()) {
                    int num = Integer.parseInt(numBuffer.toString());

                    sum += num;

                    if(!gears.isEmpty()) {
                        for(String g: gears) {
                            if(possibleGears.containsKey(g)) {
                                gearRatioSum += possibleGears.get(g) * num;
                            } else {
                                possibleGears.put(g, num);
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Sum of part numbers: " + sum);
        System.out.println("Sum of gear ratios: " + gearRatioSum);
    }

    public static boolean[] isSymbolAndGear(Character c) {
        return new boolean[]{!Character.isDigit(c) && c != '.', c == '*'};
    }

    public static boolean[] isSymbolAtPosition(List<String> input, int positionY, int positionX) {
        return(isSymbolAndGear(input.get(positionY).charAt(positionX)));
    }
}
