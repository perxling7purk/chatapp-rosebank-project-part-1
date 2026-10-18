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

}
    

