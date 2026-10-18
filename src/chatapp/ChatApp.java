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

        
        if (loginStatus) {

            System.out.println();
            System.out.println("Welcome to QuickChat.");

            System.out.print("How many messages would you like to enter? ");
            int numberOfMessages = lolchat.nextInt();
            lolchat.nextLine();

            int messagesEntered = 0;

            while (true) {

                System.out.println();
                System.out.println("1) Send Messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Choose an option: ");

                int option = lolchat.nextInt();
                lolchat.nextLine();

                if (option == 1) {

                    if (messagesEntered >= numberOfMessages) {
                        System.out.println("You have reached your message limit.");
                    } else {

                        System.out.print("Enter recipient cellphone number: ");
                        String recipient = lolchat.nextLine();

                        System.out.print("Enter your message: ");
                        String messageText = lolchat.nextLine();

                        String messageID = Message.generateMessageID();

                        Message message = new Message(
                                messageID,
                                messagesEntered,
                                recipient,
                                messageText
                        );

                        if (!message.checkMessageID()) {
                            System.out.println("Invalid message ID.");
                        } else if (!message.checkRecipientCell().equals(
                                "Cell phone number successfully captured.")) {
                            System.out.println(message.checkRecipientCell());
                        } else if (!message.checkMessageLength().equals(
                                "Message ready to send.")) {
                            System.out.println(message.checkMessageLength());
                        } else {

                            System.out.println(message.createMessageHash());

                            System.out.println("1) Send Message");
                            System.out.println("2) Disregard Message");
                            System.out.println("3) Store Message");
                            System.out.print("Choose an option: ");

                            int choice = lolchat.nextInt();
                            lolchat.nextLine();

                            if (choice == 3) {
                                System.out.println(message.storeMessage());
                            } else {
                                System.out.println(message.SentMessage(choice));
                            }

                            if (choice == 1) {
                                System.out.println(message.printMessages());
                            }

                            messagesEntered++;
                        }
                    }

                } else if (option == 2) {

                    System.out.println("Coming Soon.");

                } else if (option == 3) {

                    System.out.println("Total messages sent: "
                            + Message.returnTotalMessages());
                    System.out.println("Goodbye!");
                    break;

                } else {

                    System.out.println("Invalid option. Please try again.");
                }
            }

        } else {

            System.out.println(
                    user.returnLoginStatus(loginStatus)
            );
        }

    }
}
