/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package chatapp;

import java.util.Scanner;

/**
 *
 * @author paulj
 */
public class ChatApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
          Scanner lolchat = new Scanner(System.in);

        // Registration
        System.out.println("=== REGISTRATION ===");

        System.out.print("Enter your first name: ");
        String firstName = lolchat.nextLine();

        System.out.print("Enter your last name: ");
        String lastName = lolchat.nextLine();

        System.out.print("Enter your username: ");
        String username = lolchat.nextLine();

        System.out.print("Enter your password: ");
        String password = lolchat.nextLine();

        System.out.print("Enter your South African cell phone number: ");
        String cellPhone = lolchat.nextLine();

        // Create the user
        RegLogin user = new RegLogin(
                username,
                password,
                cellPhone,
                firstName,
                lastName
        );

        // Check registration
        System.out.println();
        System.out.println(user.registerUser());

        // Login
        System.out.println();
        System.out.println("=== LOGIN ===");

        System.out.print("Enter your username: ");
        String loginUsername = lolchat.nextLine();

        System.out.print("Enter your password: ");
        String loginPassword = lolchat.nextLine();

        // Check login details
        boolean loginStatus = user.loginUser(
                loginUsername,
                loginPassword
        );

        // Display login result
        System.out.println();
        System.out.println(user.returnLoginStatus(loginStatus));
    
    
}
}