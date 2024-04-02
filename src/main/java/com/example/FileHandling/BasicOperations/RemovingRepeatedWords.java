package com.example.FileHandling.BasicOperations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class RemovingRepeatedWords {
    public static void main(String[] args) throws IOException {
        Files.lines(Paths.get("src/main/java/com/example/FileHandling/content/demo.txt"))
                .map(str->str.split(" "))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::println);
    }
}
