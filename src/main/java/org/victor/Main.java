package org.victor;

import java.io.*;
import java.util.*;
import org.victor.solutions.*;

public class Main {
    public static void main(String[] args) throws IOException {

        // Day One
        List<String> input = readInput("src/main/resources/dayOne/input.txt");
        SolutionOne.solve(input);

    }

    public static List<String> readInput(String fileName) throws IOException {
        List<String> arr = new ArrayList<>();

        File file = new File(fileName);

        System.out.println(file.getAbsolutePath());

        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while((line = br.readLine()) != null){
            arr.add(line);
        }

        return arr;
    }
}