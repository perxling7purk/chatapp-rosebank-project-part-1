/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatapp;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.io.FileReader;
/**
 *
 * @author paulj
 */
public class Message {
    
    private static int totalMessagesSent = 0;
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;

    public Message(String messageID, int messageNumber,
                   String recipient, String message) {

        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.message = message;
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public String checkRecipientCell() {

        if (recipient.matches("^\\+27[0-9]{9}$")) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }
    

public String createMessageHash() {
    String firstTwoDigits = messageID.substring(0, 2);

    String[] words = message.trim().split("\\s+");

    String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
    String lastWord = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "");

    messageHash = (firstTwoDigits + ":" + messageNumber + ":"
            + firstWord + lastWord).toUpperCase();

    return messageHash;
}


public String SentMessage(int choice) {

    if (choice == 1) {
    totalMessagesSent++;
    return "Message successfully sent.";
    } else if (choice == 2) {
        return "Press 0 to delete the message.";
    } else if (choice == 3) {
        return "Message successfully stored.";
    } else {
        return "Invalid option.";
    }
}

public String printMessages() {

    return "Message ID: " + messageID
            + "\nMessage Hash: " + messageHash
            + "\nRecipient: " + recipient
            + "\nMessage: " + message;
}
public static int returnTotalMessages() {
    return totalMessagesSent;
}

public static String generateMessageID() {

    Random random = new Random();

    long number = 1000000000L
            + random.nextInt(900000000);

    return String.valueOf(number);
}

public String checkMessageLength() {

    if (message.length() < 250) {
        return "Message ready to send.";
    } else {
        int extraCharacters = message.length() - 250;

        return "Message exceeds 250 characters by "
                + extraCharacters
                + "; please reduce the size.";
    }
}


public String storeMessage() {
    ArrayList<String> messages = new ArrayList<>();

    try {
        // Read messages already stored in the file
        BufferedReader reader = new BufferedReader(
                new FileReader("messages.json"));

        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line);
        }

        reader.close();

        String json = content.toString().trim();

        // Extract existing message objects
        if (json.startsWith("[") && json.endsWith("]")) {
            json = json.substring(1, json.length() - 1).trim();

            if (!json.isEmpty()) {
                messages.add(json);
            }
        }

    } catch (java.io.FileNotFoundException e) {
        // The file does not exist yet, so start a new list
    } catch (IOException e) {
        return "Error reading stored messages.";
    }

    // Prepare the new message
    String safeMessage = message
            .replace("\\", "\\\\")
            .replace("\"", "\\\"");

    String newMessage = "{"
            + "\"messageID\":\"" + messageID + "\","
            + "\"messageHash\":\"" + messageHash + "\","
            + "\"recipient\":\"" + recipient + "\","
            + "\"message\":\"" + safeMessage + "\""
            + "}";

    // Write the messages as a JSON array
    try {
        FileWriter writer = new FileWriter("messages.json");

        writer.write("[\n");

        for (String oldMessage : messages) {
            writer.write(oldMessage + ",\n");
        }

        writer.write(newMessage + "\n]");
        writer.close();

        return "Message successfully stored.";

    } catch (IOException e) {
        return "Error storing message.";
    }
}

    
}
