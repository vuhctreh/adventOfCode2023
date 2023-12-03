package org.victor.solutions;

import java.util.List;

public class SolutionThree {

    // When digit encountered
    // -> while digit
    // -> append to String
    // -> check for symbol around it
    // -> If symbol found
    //     -> mark symbol found = true
    // add to sum if symbol found = true
    public static void solve(List<String> input) {
        int sum = 0;

        // For each string in input array
        for(int i = 0; i < input.size(); i++) {
            String s = input.get(i);
            // For each character in string
            for(int j = 0; j < s.length(); j++) {
                boolean symbolFound = false;

                StringBuilder numBuffer = new StringBuilder();

                // Check behind
                if(j > 0) {
                    symbolFound = isSymbolAtPosition(input, i, j - 1);
                    // Check top left
                    if (i > 0 && !symbolFound) {
                        symbolFound = isSymbolAtPosition(input, i - 1, j - 1);
                    }

                    // Check bottom left
                    if(i < input.size() - 1 && !symbolFound) {
                        symbolFound = isSymbolAtPosition(input, i + 1, j - 1);
                    }
                }

                // If Character is a digit
                while(Character.isDigit(s.charAt(j))) {
                    numBuffer.append(s.charAt(j));

                    // Check above
                    if(i > 0 && !symbolFound) {
                        symbolFound = isSymbolAtPosition(input, i - 1, j);
                    }
                    // Check below
                    if (i < input.size() - 1 && !symbolFound) {
                        symbolFound = isSymbolAtPosition(input, i + 1, j);
                    }
                    j++;

                    if(j > s.length() - 1) break;
                }

                System.out.println("Current number: " + numBuffer);

                // Check to the right
                if(j < s.length() - 1 && !symbolFound) {
                    System.out.println("Checking right: y = " + (i) + " x = " + (j));
                    System.out.println("Character: " + input.get(i).charAt(j));
                    symbolFound = isSymbolAtPosition(input, i, j);

                    // Check top right
                    if (i > 0 && !symbolFound) {
                        System.out.println("Checking top right: y = " + (i - 1) + " x = " + (j));
                        System.out.println("Character: " + input.get(i - 1).charAt(j));
                        symbolFound = isSymbolAtPosition(input, i - 1, j);
                    }

                    // Check bottom right
                    if(i < input.size() - 1 && !symbolFound) {
                        System.out.println("Checking bottom right: y = " + (i + 1) + " x = " + (j));
                        System.out.println("Character: " + input.get(i + 1).charAt(j));
                        symbolFound = isSymbolAtPosition(input, i + 1, j);
                    }
                }

                System.out.println("Symbol found: " + symbolFound);

                if(symbolFound && !numBuffer.isEmpty()) {
                    System.out.println("Number added: " + numBuffer);
                    sum += Integer.parseInt(numBuffer.toString());
                }
            }
        }

        System.out.println(sum);
    }

    public static boolean isSymbol(Character c) {
        return(!Character.isDigit(c) && c!= '.');
    }

    public static boolean isSymbolAtPosition(List<String> input, int positionY, int positionX) {
        return(isSymbol(input.get(positionY).charAt(positionX)));
    }
}
