package org.victor.solutions;

import java.util.*;

public class SolutionFour {
    public static void solve(List<String> input) {
        int sum = 0;

        int enumerate = 0;

        int[] cardSets = new int[input.size()];

        for(int n = 0; n < input.size(); n ++) {
            cardSets[n] ++;
        }

        int totalCards = 0;

        for(String s : input) {
            int matches = 0;

            List<List<String>> parsed = parseLine(s);

            Set<String> winners = new HashSet<>(parsed.get(0));

            for(String scratched : parsed.get(1)) {
                if(winners.contains(scratched)) {
                    matches++;
                }
            }

            if(matches > 0) {
                for(int i = enumerate + 1; i <= enumerate + matches; i++) {
                    cardSets[i] += cardSets[enumerate];
                }

                sum ++;

                for(int i = 0; i < matches - 1; i ++) {
                    sum += (int) Math.pow(2, i);
                }

            }

            totalCards += (cardSets[enumerate]);

            enumerate ++;
        }

        System.out.println(sum);
        System.out.println(totalCards);
    }

    public static List<List<String>> parseLine(String line) {
        List<List<String>> parsed = new ArrayList<>();

        // Remove "Game N:" text
        line = line.split(":")[1];

        String[] split = line.split("\\|");
        String[] winning = split[0].substring(1).split(" ");
        String[] scratched = split[1].substring(1).split(" ");

        List<String> temp1 = new ArrayList<>();
        List<String> temp2 = new ArrayList<>();

        for(String s: winning) {
            if(!Objects.equals(s, "")) temp1.add(s);
        }

        parsed.add(temp1);

        for(String s: scratched) {
            if(!Objects.equals(s, "")) temp2.add(s);
        }

        parsed.add(temp2);

        return parsed;
    }
}