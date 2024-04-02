package com.example.FileHandling.Utility;
import java.util.Base64;
import java.util.stream.Stream;

public class PasswordEncryptionDecryption {
    public static String getEncryptedPassword(String pwd)
    {
        Base64.Encoder encoder= Base64.getEncoder();
        String encrypted=  encoder.encodeToString(pwd.getBytes());
        return encrypted;
    }

    public static String getDecryptedPassword(String pwd)
    {
        Base64.Decoder decoder= Base64.getDecoder();
        byte []decrypted= decoder.decode(pwd);
        return new String(decrypted);
    }

    public static void main(String[] args) {
        String str= "YWRpbHFANzQ4\n" +
                "cml0dUA5NTY=\n" +
                "bmlkaGlAOTU3\n";
        String strA[]= str.split("\n");
        Stream.of(strA)
                .map(PasswordEncryptionDecryption::getDecryptedPassword)
                .forEach(System.out::println);
    }
}
