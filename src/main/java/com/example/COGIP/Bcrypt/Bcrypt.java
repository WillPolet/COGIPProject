package com.example.COGIP.Bcrypt;

import org.mindrot.jbcrypt.BCrypt;

public class Bcrypt {
    public static void main(String[] args) {
        // Password to be hashed
        String password = "mySecurePassword";

        // Hash the password
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        // Print the hashed password
        System.out.println("Hashed Password: " + hashedPassword);

        // You can also verify a password against its hashed version
        String candidatePassword = "mySecurePassword";
        if (BCrypt.checkpw(candidatePassword, hashedPassword)) {
            System.out.println("Password matches!");
        } else {
            System.out.println("Password does not match.");
        }
    }
}
