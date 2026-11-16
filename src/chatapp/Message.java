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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author paulj
 */
public class Message {
    
    private static int totalMessagesSent = 0;
    
    private static String[] sentMessages = new String[100];
private static String[] disregardedMessages = new String[100];
private static String[] storedMessages = new String[100];

private static String[] messageHashes = new String[100];
private static String[] messageIDs = new String[100];
private static String[] storedMessageIDs = new String[100];
private static String[] storedMessageHashes = new String[100];

// Recipient details for each type of message
private static String[] sentRecipients = new String[100];
private static String[] disregardedRecipients = new String[100];
private static String[] storedRecipients = new String[100];

private static int sentCount = 0;
private static int disregardedCount = 0;
private static int storedCount = 0;
private static int hashCount = 0;
private static int idCount = 0;

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
        } 
        else {
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
    } 
    else if (choice == 2) {
        return "Press 0 to delete the message.";
    } 
    else if (choice == 3) {
        return "Message successfully stored.";
    } 
    else {
        return "Invalid option.";
    }
}
public void addMessageToArray(int choice) {

    if (choice == 1) {
        sentMessages[sentCount] = message;
        sentRecipients[sentCount] = recipient;
        sentCount++;

    } else if (choice == 2) {
        disregardedMessages[disregardedCount] = message;
        disregardedRecipients[disregardedCount] = recipient;
        disregardedCount++;

    } else if (choice == 3) {
        storedMessages[storedCount] = message;
        storedRecipients[storedCount] = recipient;
        storedMessageIDs[storedCount] = messageID;
        storedMessageHashes[storedCount] = messageHash;
        storedCount++;
    }

    messageHashes[hashCount] = messageHash;
    hashCount++;

    messageIDs[idCount] = messageID;
    idCount++;
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

  public static void displayArrayContents() {

    System.out.println("\n--- SENT MESSAGES ---");

    for (int i = 0; i < sentCount; i++) {
        System.out.println(sentMessages[i]);
    }

    System.out.println("\n--- DISREGARDED MESSAGES ---");

    for (int i = 0; i < disregardedCount; i++) {
        System.out.println(disregardedMessages[i]);
    }

    System.out.println("\n--- STORED MESSAGES ---");

    for (int i = 0; i < storedCount; i++) {
        System.out.println(storedMessages[i]);
    }

    System.out.println("\n--- MESSAGE HASHES ---");

    for (int i = 0; i < hashCount; i++) {
        System.out.println(messageHashes[i]);
    }

    System.out.println("\n--- MESSAGE IDs ---");

    for (int i = 0; i < idCount; i++) {
        System.out.println(messageIDs[i]);
    }
}  
  public static void loadStoredMessages() {

    storedCount = 0;

    try {
        FileReader reader = new FileReader("messages.json");

        JSONParser parser = new JSONParser();
        JSONArray messages = (JSONArray) parser.parse(reader);

        for (int i = 0; i < messages.size(); i++) {

            JSONObject savedMessage = (JSONObject) messages.get(i);

            storedMessages[storedCount] = (String) savedMessage.get("message");
            storedRecipients[storedCount] = (String) savedMessage.get("recipient");
            storedMessageIDs[storedCount] = (String) savedMessage.get("messageID");
            storedMessageHashes[storedCount] = (String) savedMessage.get("messageHash");

            storedCount++;
        }

        reader.close();

    } catch (Exception e) {
        System.out.println("Could not load stored messages.");
    }
}
  public static void displaySendersAndRecipients() {

    System.out.println("\n--- STORED MESSAGE DETAILS ---");

    for (int i = 0; i < storedCount; i++) {

        System.out.println("Sender: QuickChat User");
        System.out.println("Recipient: " + storedRecipients[i]);
        System.out.println();
    }
}
  public static void displayLongestMessage() {

    if (storedCount == 0) {
        System.out.println("No stored messages available.");
        return;
    }

    String longestMessage = storedMessages[0];

    for (int i = 1; i < storedCount; i++) {

        if (storedMessages[i].length() > longestMessage.length()) {
            longestMessage = storedMessages[i];
        }
    }

    System.out.println("\n--- LONGEST STORED MESSAGE ---");
    System.out.println(longestMessage);
}
  public static String getLongestStoredMessage() {
    if (storedCount == 0) {
        return "";
    }

    String longestMessage = storedMessages[0];

    for (int i = 1; i < storedCount; i++) {
        if (storedMessages[i].length() > longestMessage.length()) {
            longestMessage = storedMessages[i];
        }
    }

    return longestMessage;
}
  
  public static void searchMessageByID(String searchID) {

    boolean found = false;

    for (int i = 0; i < storedCount; i++) {

        if (storedMessageIDs[i].equals(searchID)) {

            System.out.println("\nMessage found!");
            System.out.println("Message ID: " + storedMessageIDs[i]);
            System.out.println("Recipient: " + storedRecipients[i]);
            System.out.println("Message: " + storedMessages[i]);

            found = true;
        }
    }

    if (!found) {
        System.out.println("Message ID not found.");
    }
}
  public static String getMessageByID(String searchID) {

    for (int i = 0; i < storedCount; i++) {

        if (storedMessageIDs[i].equals(searchID)) {

            return "Recipient: " + storedRecipients[i]
                    + "\nMessage: " + storedMessages[i];
        }
    }

    return "Message ID not found.";
}
  public static void searchMessagesByRecipient(String searchRecipient) {

    boolean found = false;

    for (int i = 0; i < storedCount; i++) {

        if (storedRecipients[i].equals(searchRecipient)) {

            System.out.println("\nMessage: " + storedMessages[i]);
            found = true;
        }
    }

    if (!found) {
        System.out.println("No messages found for this recipient.");
    }
}
  public static String getMessagesByRecipient(String searchRecipient) {

    String results = "";

    for (int i = 0; i < storedCount; i++) {

        if (storedRecipients[i].equals(searchRecipient)) {

            results = results + "Message: " + storedMessages[i] + "\n";
        }
    }

    if (results.equals("")) {
        return "No messages found for this recipient.";
    }

    return results.trim();
}
  public static String deleteMessageByHash(String searchHash) {

    for (int i = 0; i < storedCount; i++) {

        if (storedMessageHashes[i].equals(searchHash)) {

            String deletedMessage = storedMessages[i];

            for (int j = i; j < storedCount - 1; j++) {

                storedMessages[j] = storedMessages[j + 1];
                storedRecipients[j] = storedRecipients[j + 1];
                storedMessageHashes[j] = storedMessageHashes[j + 1];
                storedMessageIDs[j] = storedMessageIDs[j + 1];
            }

            storedCount--;

            saveStoredMessagesToFile();

            return "Message: '" + deletedMessage + "' successfully deleted.";
        }
    }

    return "Message hash not found.";
}
  public static void saveStoredMessagesToFile() {

    JSONArray messages = new JSONArray();

    for (int i = 0; i < storedCount; i++) {

        JSONObject savedMessage = new JSONObject();

        savedMessage.put("messageID", storedMessageIDs[i]);
        savedMessage.put("messageHash", storedMessageHashes[i]);
        savedMessage.put("recipient", storedRecipients[i]);
        savedMessage.put("message", storedMessages[i]);

        messages.add(savedMessage);
    }

    try {
        FileWriter writer = new FileWriter("messages.json");
        writer.write(messages.toJSONString());
        writer.close();

    } catch (IOException e) {
        System.out.println("Error updating messages.json.");
    }
}
  public static void displayFullReport() {

    System.out.println("\n--- FULL MESSAGE REPORT ---");

    if (storedCount == 0) {
        System.out.println("No stored messages available.");
        return;
    }

    for (int i = 0; i < storedCount; i++) {

        System.out.println("\nMessage " + (i + 1));
        System.out.println("Message ID: " + storedMessageIDs[i]);
        System.out.println("Message Hash: " + storedMessageHashes[i]);
        System.out.println("Recipient: " + storedRecipients[i]);
        System.out.println("Message: " + storedMessages[i]);
        System.out.println("----------------------------");
    }
}
  public static int getSentCount() {
    return sentCount;
}

public static String getSentMessage(int index) {
    return sentMessages[index];
}
public static String getFullReport() {

    String report = "";

    for (int i = 0; i < storedCount; i++) {

        report = report + "Message ID: " + storedMessageIDs[i] + "\n";
        report = report + "Message Hash: " + storedMessageHashes[i] + "\n";
        report = report + "Recipient: " + storedRecipients[i] + "\n";
        report = report + "Message: " + storedMessages[i] + "\n";
    }

    return report;
}
}
