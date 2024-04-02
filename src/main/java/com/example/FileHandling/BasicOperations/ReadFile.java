package com.example.FileHandling.BasicOperations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFile {
    public static void main(String[] args) throws IOException {
        Files.lines(Paths.get("src/main/java/com/example/FileHandling/content/file-txt.txt"))
                .forEach(System.out::println);

    }
}
