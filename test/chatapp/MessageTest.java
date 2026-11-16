/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 *
 * @author paulj
 */
public class MessageTest {
    @Test
    public void testCheckMessageID() {
        Message message = new Message(
                "1234567890", 0,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertTrue(message.checkMessageID());
    }

    @Test
    public void testCheckRecipientCellCorrect() {
        Message message = new Message(
                "1234567890", 0,
                "+27718693002",
                "Hello"
        );

        assertEquals(
                "Cell phone number successfully captured.",
                message.checkRecipientCell()
        );
    }

    @Test
    public void testCheckRecipientCellIncorrect() {
        Message message = new Message(
                "1234567890", 0,
                "08575975889",
                "Hello"
        );

        assertEquals(
                "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.",
                message.checkRecipientCell()
        );
    }

    @Test
    public void testCheckMessageLength() {
        Message message = new Message(
                "1234567890", 0,
                "+27718693002",
                "Hi Mike"
        );

        assertEquals(
                "Message ready to send.",
                message.checkMessageLength()
        );
    }
    
    @Test
    public void testCreateMessageHash() {
        Message message = new Message(
                "0012345678", 0,
                "+27718693002",
                "Hi Mike, can you join us for dinner tonight?"
        );

        assertEquals(
                "00:0:HITONIGHT",
                message.createMessageHash()
        );
    }

    @Test
    public void testSendMessage() {
        Message message = new Message(
                "1234567890", 0,
                "+27718693002",
                "Hello"
        );

        assertEquals(
                "Message successfully sent.",
                message.SentMessage(1)
        );
    }

    @Test
    public void testDisregardMessage() {
        Message message = new Message(
                "1234567890", 0,
                "+27718693002",
                "Hello"
        );

        assertEquals(
                "Press 0 to delete the message.",
                message.SentMessage(2)
        );
    }

    @Test
    public void testStoreMessage() {
        Message message = new Message(
                "1234567890", 0,
                "+27718693002",
                "Hello"
        );

        message.createMessageHash();

        assertEquals(
                "Message successfully stored.",
                message.storeMessage()
        );
    }
@Test
public void testSentMessagesArray() {

    Message message1 = new Message(
            "1111111111", 0,
            "+27834557896",
            "Did you get the cake?"
    );

    Message message2 = new Message(
            "2222222222", 1,
            "+27838884567",
            "It is dinner time!"
    );

    message1.addMessageToArray(1);
    message2.addMessageToArray(1);

    assertEquals("Did you get the cake?", Message.getSentMessage(0));
    assertEquals("It is dinner time!", Message.getSentMessage(1));
}

@Test
public void testLongestStoredMessage() {

    Message message = new Message(
            "1111111111", 0,
            "+27838884567",
            "Where are you? You are late! I have asked you to be on time."
    );

    message.createMessageHash();
    message.addMessageToArray(3);

    assertEquals(
            "Where are you? You are late! I have asked you to be on time.",
            Message.getLongestStoredMessage()
    );
}
@Test
public void testSearchMessageByID() {

    Message message = new Message(
            "3333333333", 0,
            "+27834557896",
            "Did you get the cake?"
    );

    message.createMessageHash();
    message.addMessageToArray(3);

    assertEquals(
            "Recipient: +27834557896\nMessage: Did you get the cake?",
            Message.getMessageByID("3333333333")
    );
}
@Test
public void testSearchMessagesByRecipient() {

    Message message1 = new Message(
            "4444444444", 0,
            "+27838884567",
            "Where are you?"
    );

    Message message2 = new Message(
            "5555555555", 1,
            "+27838884567",
            "I am waiting for you."
    );

    message1.createMessageHash();
    message2.createMessageHash();

    message1.addMessageToArray(3);
    message2.addMessageToArray(3);

    assertEquals(
            "Message: Where are you?\nMessage: I am waiting for you.",
            Message.getMessagesByRecipient("+27838884567")
    );
}
@Test
public void testDeleteMessageByHash() {

    Message message = new Message(
            "6666666666", 0,
            "+27838884567",
            "Where are you? You are late!"
    );

    String hash = message.createMessageHash();

    message.addMessageToArray(3);

    assertEquals(
            "Message: 'Where are you? You are late!' successfully deleted.",
            Message.deleteMessageByHash(hash)
    );
}
@Test
public void testFullMessageReport() {

    String oldReport = Message.getFullReport();

    Message message = new Message(
            "7777777777", 0,
            "+27838884567",
            "Hello report"
    );

    message.createMessageHash();
    message.addMessageToArray(3);

    String expectedReport = oldReport
            + "Message ID: 7777777777\n"
            + "Message Hash: 77:0:HELLOREPORT\n"
            + "Recipient: +27838884567\n"
            + "Message: Hello report\n";

    assertEquals(
            expectedReport,
            Message.getFullReport()
    );
}
}