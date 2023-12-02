package org.victor.solutions;

import java.util.*;

public class SolutionOne {
    public static void solve(List<String> input) {
        int sum = 0;

        Map<Character, String> map = Map.of(
                '1', "one",
                '2', "two",
                '3', "three",
                '4', "four",
                '5', "five",
                '6', "six",
                '7', "seven",
                '8', "eight",
                '9', "nine",
                '0', "zero"
        );

        for (String s : input) {

            for(char c: map.keySet()) {
                String temp = map.get(c);
                s = s.replaceAll(temp, "" + temp.charAt(0) + c + temp.charAt(temp.length() - 1));
            }

            StringBuilder str = new StringBuilder();
            Character prev = null;
            for (int j = 0; j < s.length(); j++) {
                if (map.containsKey(s.charAt(j))) {
                    if (str.isEmpty()) str.append(s.charAt(j));
                    prev = s.charAt(j);
                }
            }
            if(prev!= null) str.append(prev);
            if(!str.isEmpty()) sum += Integer.parseInt(str.toString());
        }
        System.out.println(sum);
    }
}