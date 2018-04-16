package com.voicemail.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.voicemail.Class.Mailbox;
import com.voicemail.Class.Message;

class MailBoxTest {
	private Mailbox mailBoxEmpty;
	private Mailbox mailBoxWithCurrentMessageInNewMessagesQueue;
	private Mailbox mailBoxWithCurrentMessageInKeptMessagesQueue;

	@BeforeEach
	public void setUp() throws Exception {
		mailBoxEmpty = new Mailbox("passcode", "greeting");
		mailBoxWithCurrentMessageInNewMessagesQueue = new Mailbox("passcode", "greeting");
		mailBoxWithCurrentMessageInKeptMessagesQueue = new Mailbox("passcode", "greeting");
		
		mailBoxWithCurrentMessageInNewMessagesQueue.addMessage(new Message("current message"));
		mailBoxWithCurrentMessageInKeptMessagesQueue.addMessage(new Message("current message"));
		
		mailBoxWithCurrentMessageInKeptMessagesQueue.saveCurrentMessage();
	}

	@Test
	public void testCheckPasscodeWithCorrectPasscode() {
		assertTrue(mailBoxEmpty.checkPasscode("passcode"));
	}
	
	@Test
	public void testCheckPasscodeWithIncorrectPasscode() {
		assertFalse(mailBoxEmpty.checkPasscode("incorrect"));
	}
	
	@Test
	public void testGetGreeting() {
		assertEquals("greeting", mailBoxEmpty.getGreeting());
	}
	
	@Test
	public void testSetGreeting() {
		mailBoxEmpty.setGreeting("greeting2");
		
		assertEquals("greeting2", mailBoxEmpty.getGreeting());
	}
	
	@Test
	public void testSetPasscode() {
		mailBoxEmpty.setPasscode("passcode2");
		
		assertTrue(mailBoxEmpty.checkPasscode("passcode2"));
	}
	
	@Test
	public void testGetCurrentMessageWithNewMessagesQueue() {
		Message currentMessage = mailBoxWithCurrentMessageInNewMessagesQueue.getCurrentMessage();
		
		assertEquals("current message", currentMessage.getText());
	}
	
	@Test
	public void testRemoveCurrentMessageWithNewMessagesQueue() {
		Message currentMessage = mailBoxWithCurrentMessageInNewMessagesQueue.removeCurrentMessage();
		
		assertEquals("current message", currentMessage.getText());
	}
	
	@Test
	public void testGetCurrentMessageWithKeptMessagesQueue() {
		Message currentMessage = mailBoxWithCurrentMessageInKeptMessagesQueue.getCurrentMessage();
		
		assertEquals("current message", currentMessage.getText());
	}
	
	@Test
	public void testRemoveCurrentMessageWithKeptMessagesQueue() {
		Message currentMessage = mailBoxWithCurrentMessageInKeptMessagesQueue.removeCurrentMessage();
		
		assertEquals("current message", currentMessage.getText());
	}
	
	@Test
	public void testGetCurrentMessageWithEmptyQueues() {
		assertNull(mailBoxEmpty.getCurrentMessage());
	}
	
	@Test
	public void testRemoveCurrentMessageWithEmptyQueues() {
		assertNull(mailBoxEmpty.removeCurrentMessage());
	}
	
	@Test
	public void testSaveCurrentMessageWithEmptyQueues() {
		Message message1 = mailBoxEmpty.getCurrentMessage();
		
		mailBoxEmpty.saveCurrentMessage();
		
		Message message2 = mailBoxEmpty.getCurrentMessage();
		
		assertEquals(message1, message2);
	}
}
