package com.example.FileHandling.Utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class EncryptPassword {
    public static void main(String[] args)  {
        List<String> list = List.of("adilq@748","ritu@956","nidhi@957");

        List<String> encryptedPassword=  list.stream()
                                            .map(PasswordEncryptionDecryption::getEncryptedPassword)
                                             .collect(Collectors.toList());

                                    // desired path where we want to save our encypted password file
        Path path = Paths.get("src/main/java/com/example/FileHandling/content/EncryptedPassword.txt");

        try {
            Files.write(path,encryptedPassword);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
