package com.kapitonenko.grep;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by kapiton on 02.02.16.
 */
public class Grep {
    public static int[] hash;
    public static void bufferedSearch(String pathname, String searchString) {
        BufferedReader br;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(pathname)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        String currentLine;
        int numberCurrentLine = 1;
        hash = prefixFunction(searchString);
        try {
            while ((currentLine = br.readLine()) != null) {
                if(currentLine.contains(searchString))
                    System.out.println(" Path: " + pathname + " Number of string: " + numberCurrentLine
                                        + " String: " + currentLine);
                numberCurrentLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void streamSearch(String pathname, String searchString) {
            Stream<String> list;
            hash = prefixFunction(searchString);

            try (BufferedReader br = Files.newBufferedReader(Paths.get(pathname))) {
                list = br.lines();
                list.parallel().forEach(line -> {
                    if (line.contains(searchString))
                        System.out.println(" Path: " + pathname
                                            + " String: " + line);
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    private static boolean searchString(String string, String searchString) {
       //Boolean temp = string.contains(searchString);
       //return temp;
       return kmpMatcher(string, searchString);
    }
    private static int[] prefixFunction(String s) {
        int[] p = new int[s.length()];
        int k = 0;
        for (int i = 1; i < s.length(); i++) {
            while (k > 0 && s.charAt(k) != s.charAt(i))
                k = p[k - 1];
            if (s.charAt(k) == s.charAt(i))
                ++k;
            p[i] = k;
        }
        return p;
    }

    private static Boolean kmpMatcher(String s, String pattern) {
        int m = pattern.length();
        if (m == 0)
            return true;
        int[] p = hash;
        for (int i = 0, k = 0; i < s.length(); i++)
            for (; ; k = p[k - 1]) {
                if (pattern.charAt(k) == s.charAt(i)) {
                    if (++k == m)
                        return true;
                    break;
                }
                if (k == 0)
                    break;
            }
        return false;
    }
}
