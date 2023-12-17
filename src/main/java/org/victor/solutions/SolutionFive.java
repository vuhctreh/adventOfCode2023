package org.victor.solutions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SolutionFive {

    public static void solve(List<String> input) {
        List<List<List<String>>> parsedAlmanac = parseInput(input);

        long lowestPos = Long.MAX_VALUE;

        System.out.println(parsedAlmanac);

        // For every seed
        for(int i = 0; i < parsedAlmanac.get(0).get(0).size(); i ++) {
            long seed = Long.parseLong(parsedAlmanac.get(0).get(0).get(i));

            // For every almanac map
            for(int j = 1; j < parsedAlmanac.size(); j ++) {


                // For each line of almanac map
                for(int k = 0; k < parsedAlmanac.get(j).size(); k ++) {

                    // Parse Almanac ranges
                    long dRangeStart = Long.parseLong(parsedAlmanac.get(j).get(k).get(0));
                    long sRangeStart = Long.parseLong(parsedAlmanac.get(j).get(k).get(1));
                    long range = Long.parseLong(parsedAlmanac.get(j).get(k).get(2));

                    // Move to next iteration if seed is not in range
                    if(!(seed >= sRangeStart && seed < (sRangeStart + range))) continue;
                    System.out.println("sRange: " + sRangeStart);
                    System.out.println("seed: " +  seed);
                    System.out.println("destination: " + (dRangeStart + (seed - sRangeStart)));
                    seed = dRangeStart + (seed - sRangeStart);
                    break;
                }

            }
            lowestPos = Math.min(lowestPos, seed);
        }

        System.out.println(lowestPos);
    }

    public static List<List<List<String>>> parseInput(List<String> input) {
        List<List<List<String>>> maps = new ArrayList<>();

        List<List<String>> mapLine = new ArrayList<>();

        maps.add(new ArrayList<>(
                List.of(Arrays.stream(Arrays.stream(input.get(0).split(":")[1]
                        .split(" "))
                        .filter(s -> !s.isEmpty())
                        .toArray(String[]::new))
                        .toList()))
        );

        for(int i = 1; i < input.size(); i++) {
            String s = input.get(i);

            if (s.isEmpty() || Character.isAlphabetic(s.charAt(0))) {
                if(!mapLine.isEmpty()) maps.add(mapLine);
                mapLine = new ArrayList<>();
                continue;
            }

            mapLine.add(Arrays.stream(s.split(" "))
                    .filter(z -> !z.isEmpty())
                    .collect(Collectors.toList()));
        }

        maps.add(mapLine);

        return maps;
    }
}
