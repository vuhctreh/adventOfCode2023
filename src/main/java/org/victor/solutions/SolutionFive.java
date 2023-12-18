package org.victor.solutions;

import java.util.*;
import java.util.stream.Collectors;

public class SolutionFive {
    public static void solve(List<String> input) {
        List<List<List<String>>> parsedAlmanac = parseInput(input);

        long lowestPos = Long.MAX_VALUE;

        List<String> seeds = parsedAlmanac.get(0).get(0);

        Deque<List<Long>> seedRanges = new LinkedList<>();

        // Create initial seed ranges
        for (int i = 0; i < seeds.size(); i += 2) {
            seedRanges.add(List.of(Long.parseLong(seeds.get(i)), Long.parseLong(seeds.get(i + 1))));
        }

        // For every map in almanac
        for(int i = 1; i < parsedAlmanac.size(); i++) {

            boolean intersecting;

            List<List<Long>> newMap = new ArrayList<>();

            while(!seedRanges.isEmpty()) {

                intersecting = false;

                // For every line in almanac map
                for(int j = 0; j < parsedAlmanac.get(i).size(); j++) {

                    if(intersecting) break;


                    List<Long> toCompute = seedRanges.poll();

                    long currStart = toCompute.get(0);
                    long currRange = toCompute.get(1);


                    List<String> computeAgainst = parsedAlmanac.get(i).get(j);


                    long destinationRangeStart = Long.parseLong(computeAgainst.get(0));
                    long sourceRangeStart = Long.parseLong(computeAgainst.get(1));
                    long sourceRange = Long.parseLong(computeAgainst.get(2));

                    long e = Math.max(currStart, sourceRangeStart);
                    long f = Math.min(currStart + currRange, sourceRangeStart + sourceRange);

                    // If intersection
                    if (e <= f) {
                        intersecting = true;

                        newMap.add(List.of(e + (destinationRangeStart - sourceRangeStart), f - e));

                        if(currStart < sourceRangeStart) {
                            newMap.add(List.of(currStart, sourceRangeStart - currStart - 1));
                        }

                        if(currStart + currRange > sourceRangeStart + sourceRange) {
                            newMap.add(List.of(sourceRangeStart + sourceRange + 1,
                                    (currStart + currRange) - (sourceRangeStart + sourceRange)));
                        }
                    } else {
                        seedRanges.addFirst(toCompute);
                    }
                }

                if(!intersecting) newMap.add(seedRanges.poll());
            }
            seedRanges.addAll(newMap);
        }

        while(!seedRanges.isEmpty()) lowestPos = Math.min(lowestPos, seedRanges.poll().get(0));

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

        for (int i = 1; i < input.size(); i++) {
            String s = input.get(i);

            if (s.isEmpty() || Character.isAlphabetic(s.charAt(0))) {
                if (!mapLine.isEmpty()) maps.add(mapLine);
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

