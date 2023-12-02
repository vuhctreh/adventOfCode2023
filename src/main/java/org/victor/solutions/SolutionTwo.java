package org.victor.solutions;

import java.util.*;

public class SolutionTwo {
    public static void solve(List<String> input) {
        int sum = 0;
        int setSum = 0;

        Map<String, Integer> map = new HashMap<>(
                Map.of(
                        "red", 12,
                        "green", 13,
                        "blue", 14
                )
        );

        for (int i = 0; i < input.size(); i++) {
            String s = input.get(i);

            // List[0][0][0] => First game played, first roll, number rolled
            // List[0][0][0] => First game played, first roll, colour rolled
            List<List<List<String>>> games = parsed(s);

            int possible = 1;

            Map<String, Integer> minSet = new HashMap<>(
                    Map.of(
                            "red", 0,
                            "green", 0,
                            "blue", 0
                    )
            );

            for (List<List<String>> game : games) {
                for(List<String> roll : game) {
                    int rollNo = Integer.parseInt(roll.get(0));

                    int min = Math.max(minSet.get(roll.get(1)), rollNo);

                    minSet.put(roll.get(1), min);

                    if(map.get(roll.get(1)) < rollNo) {
                        possible = 0;
                    }
                }
            }

            if(possible == 1) {
                sum += i + 1;
            }

            setSum += minSet.get("red") * minSet.get("green") * minSet.get("blue");
        }
        System.out.println("Sum of possible games: " + sum);
        System.out.println("Sum cube set power: " + setSum);
    }

    public static List<List<List<String>>> parsed(String s) {
        List<List<List<String>>> arr = new ArrayList<>();
        s = s.split(":")[1];
        s = s.replaceAll(" ", "");

        String[] parts = s.split(";");

        for(String i: parts) {
            String[] split = i.split(",");
            StringBuilder str = new StringBuilder();

            List<List<String>> list = new ArrayList<>();

            // For each string in split array
            for (String string : split) {
                // For each character in string
                for (int k = 0; k < string.length(); k++) {
                    if (!Character.isDigit(string.charAt(k))) {
                        list.add(List.of(string.substring(0, k), string.substring(k)));
                        break;
                    }
                }
            }
            arr.add(list);
        }
        return arr;
    }
}
