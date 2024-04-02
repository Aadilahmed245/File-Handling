package com.example.FileHandling.Utility;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class DecryptPassword {
    public static void main(String[] args) throws IOException {
            Path path= Paths.get("src/main/java/com/example/FileHandling/content/EncryptedPassword.txt");

            String encryptedPassword = Files.readString(path);

            System.out.println(encryptedPassword);
            String strEncrypted[] = encryptedPassword.trim().split("\n");

            Stream.of(strEncrypted).map(str->str.trim())                        // trim is mandatory otherwise Base64.decode give illegal arg exception
                    .map(PasswordEncryptionDecryption::getDecryptedPassword)
                    .forEach(System.out::println);



    }
}
