package com.snhu.sslserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}

@RestController
class ServerController {

    // Returns SHA-256 checksum of your name string
    @RequestMapping("/hash")
    public String myHash() {
        String data = "Mo Witherspoon";
        String checkSum = "";

        try {
            // Create MessageDigest instance with SHA-256
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            // Compute the hash
            byte[] hashBytes = messageDigest.digest(data.getBytes());

            // Convert to hex format
            checkSum = bytesToHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // Return result in HTML format
        return "<p>Data: " + data + "<br>" +
               "Hash Algorithm: SHA-256<br>" +
               "Checksum Hash Value: " + checkSum + "</p>";
    }

    // Converts byte array to hexadecimal string
    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            int intVal = 0xff & b;
            if (intVal < 0x10) {
                hexString.append('0');
            }
            hexString.append(Integer.toHexString(intVal));
        }
        return hexString.toString();
    }
}
