package com.example.FileHandling.BasicOperations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Write_File {
    public static void main(String[] args) {
        Path path = Paths.get("D:\\MY BIN\\Spring Workspace\\FileHandling\\src\\main\\java\\com\\example\\FileHandling\\content\\file-txt.txt");
        List<String> list= List.of("Tiger","Elephant","Lion","Duck");

        try
        {
           Files.write(path,list);
        }
        catch(IOException ex)
        {
            System.out.println("Error in Writing File!");
            ex.printStackTrace();
        }
    }
}
