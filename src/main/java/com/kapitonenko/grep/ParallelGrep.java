package com.kapitonenko.grep;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kapiton on 28.02.16.
 */
public class ParallelGrep extends Thread{
    private String pathname;
    private String searchString;

    public void run() {
        BufferedReader br;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(pathname)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        String currentLine;
        int numberCurrentLine = 1;
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

    public ParallelGrep(String pathname, String searchString) {
        this.pathname = pathname;
        this.searchString = searchString;
    }

    public static void grep(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        String searchString = args[0];
        List<Callable<Object>> tasks = new ArrayList<>();
        for(int i = 1; i < args.length; i++)
            tasks.add(Executors.callable(new ParallelGrep(args[i], searchString)));
        try {
            service.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
