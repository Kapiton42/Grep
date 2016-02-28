package com.kapitonenko.grep;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by kapiton on 10.02.16.
 */
public class TestFileCreater {
    public static void createFile(String pathname) {
        try(FileWriter writer = new FileWriter(pathname, false))
        {
            for(int i = 0; i < 10000000; i++) {
                String text = "aaaabaaaabaaaabaaaabaaaabaaaabaaaabaaaabaaaab" +
                        "aaaabaaaabaaaabaaaabaaaabaaaaabaaaabaaaabaaaab" +
                        "aaaabaaaabaaaabaaaabaaaabaaaabaaaabaaaabaaaab" +
                        "aaaabaaaabaaaabaaaabaaaabaaaaabaaaabaaaabaaaab" +
                         "aaaabaaaabaaaabaaaabaaaabaaaabaaaabaaaabaaaab" +
                        "aaaabaaaabaaaabaaaabaaaabaaaaabaaaabaaaabaaaab";
                writer.write(text);
                writer.append('\n');
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
