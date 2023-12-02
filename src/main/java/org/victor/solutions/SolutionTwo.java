package org.victor.solutions;

import java.util.*;

public class SolutionTwo {
    public static void solve(List<String> input) {
        int sum = 0;

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

            for (List<List<String>> game : games) {
                for(List<String> roll : game) {
                    if(map.get(roll.get(1)) < Integer.parseInt(roll.get(0))) {
                        possible = 0;
                        break;
                    }
                }
            }

            if(possible == 1) {
                sum += i + 1;
            }
        }
        System.out.println(sum);
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
            for(int j = 0; j < split.length; j++) {
                // For each character in string
                for(int k = 0; k < split[j].length(); k++) {
                    if(!Character.isDigit(split[j].charAt(k))) {
                        list.add(List.of(split[j].substring(0, k), split[j].substring(k)));
                        break;
                    }
                }
            }
            arr.add(list);
        }
        return arr;
    }
}
