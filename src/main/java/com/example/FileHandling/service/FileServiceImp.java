package com.example.FileHandling.service;

import com.example.FileHandling.Utility.DecryptFileUsingCypher;
import com.example.FileHandling.Utility.EncryptFileUsingCypher;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

import static com.example.FileHandling.Utility.EncryptFileUsingCypher.convertStringToSecretKeyto;
import static com.example.FileHandling.Utility.EncryptFileUsingCypher.key;

@Service
public class FileServiceImp implements IFile {

    @Override
    public ResponseEntity<String> uploadFile(MultipartFile file) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file to upload.", HttpStatus.BAD_REQUEST);
        }
        List<String> allowedExtensions = Arrays.asList("pdf", "doc", "docx", "xls", "xlsx");
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        if (!allowedExtensions.contains(fileExtension.toLowerCase())) {
            return new ResponseEntity<>("Only PDF, Word, or Excel files are allowed.", HttpStatus.BAD_REQUEST);
        }
        long fileSizeInBytes = file.getSize();
        System.out.println(fileSizeInBytes);
        double fileSizeInMB = fileSizeInBytes / (double)(1024 * 1024); // Convert bytes to MB
        System.out.println(fileSizeInMB);
        if (fileSizeInMB > 10) {
            return  ResponseEntity.status( HttpStatus.BAD_REQUEST).body("File size exceed to 10MB");
        }
        String uploadDir= "D:/filepoc";
        try {
            // Get the file bytes
            byte[] fileBytes = file.getBytes();


            byte []encryptedFile= EncryptFileUsingCypher.encryptFile(fileBytes, EncryptFileUsingCypher.convertStringToSecretKeyto(key));
            // Create file path
            String filePath = uploadDir + File.separator + file.getOriginalFilename();
            Path path = Paths.get(filePath);

            // Write the file to the upload directory
            Files.write(path, encryptedFile);

            return new ResponseEntity<>("File uploaded successfully.", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to upload file.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ByteArrayResource> downloadFile(String fileName) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        String uploadDir= "D:/filepoc";
        String filePath = uploadDir + File.separator + fileName;
        File file = new File(filePath);

        if (!file.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        try {
           // Read the file into a byte array
            byte[] fileContent = Files.readAllBytes(file.toPath());
//            SecretKey secretKey= EncryptFileUsingCypher.secretKey;
            byte[]decryptedFile= DecryptFileUsingCypher.decryptFile(fileContent,convertStringToSecretKeyto(key));
            ByteArrayResource resource = new ByteArrayResource(decryptedFile);
            String[] fileNameParts = fileName.split("\\.");
            String originalExtension = fileNameParts[fileNameParts.length - 1];

            // Set up headers to include the original file name and extension
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName);
            headers.add("File-Name", fileName); // Optional - add original file name as header
            headers.add("File-Extension", originalExtension); // Optional - add original extension as header

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }





}
