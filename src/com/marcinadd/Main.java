package com.marcinadd;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("zadanie.txt");
        Scanner scanner = new Scanner(file);
        TreeMap<String, List<Integer>> treeMap = new TreeMap<>();

        int lineCounter = 1;
        while (scanner.hasNext()) {
            processLine(scanner.nextLine(),lineCounter,treeMap);
            lineCounter++;
        }
        printResults(treeMap);
    }

    private static void processLine(String line, int lineCounter, TreeMap<String, List<Integer>> treeMap){
        Pattern pattern = Pattern.compile("[a-zA-Za-żA-Ż0-9]+");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String word = matcher.group();
            if (!treeMap.containsKey(word)) {
                List<Integer> occurrences = new ArrayList<>();
                occurrences.add(lineCounter);
                treeMap.put(word, occurrences);
            } else {
                List<Integer> occurrences = treeMap.get(word);
                occurrences.add(lineCounter);
                treeMap.replace(word, occurrences);
            }
        }
    }

    private static void printResults(TreeMap<String, List<Integer>> results){
        for (String word : results.keySet()
        ) {
            List<Integer> occurrences = results.get(word);
            System.out.println(word + " - " + occurrences.size() + " - pozycje -> " + occurrences);
        }
    }
}
