package com.example.FileHandling.BasicOperations;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Read_Specific_File {
    public static void main(String[] args) {
        Path path = Paths.get("src/main/java/com/example/FileHandling/content/science.txt");

        try
        {
            String fileContent= Files.readString(path);
            System.out.println(fileContent);
        }
        catch(IOException ex)
        {
            System.out.println("Error in Reading File!");
            ex.printStackTrace();
        }
    }
}
