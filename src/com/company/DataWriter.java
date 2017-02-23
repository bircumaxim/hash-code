package com.company;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Map;
import java.util.Set;

/**
 * Created by nicu on 2/23/17.
 */
public class DataWriter {
    public static void write(String filename, Map<Integer,Set<Integer>> output){
        try {
            PrintStream printStream = new PrintStream(filename);
            printStream.println(output.size());

            output.forEach((cacheServer,videos) -> printEntry(printStream,cacheServer,videos));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printEntry(PrintStream printStream,int cacheServer,Set<Integer> videos){
        printStream.print(cacheServer+" ");
        videos.forEach(it -> printStream.print(it+" "));
        printStream.println();
    }
}
