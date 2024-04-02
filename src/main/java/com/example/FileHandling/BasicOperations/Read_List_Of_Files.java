package com.example.FileHandling.BasicOperations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Read_List_Of_Files {
    public static void main(String[] args) {
        try {
            List<Path> pathList= Files.list(Paths.get("D:\\MY BIN\\Spring Workspace\\FileHandling\\src\\main\\java\\com\\example\\FileHandling\\content"))
                    .collect(Collectors.toList());

            pathList.forEach(path-> {
                try {
                    String fileData = Files.readString(path);
                    System.out.println(fileData);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            System.out.println("Exception in reading file");
            e.printStackTrace();
        }
    }
}
