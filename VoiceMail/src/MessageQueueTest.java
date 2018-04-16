package com.voicemail.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.voicemail.Class.Message;
import com.voicemail.Class.MessageQueue;

class MessageQueueTest {
	private MessageQueue messageQueueEmpty;
	private MessageQueue messageQueueNotEmpty;

	@BeforeEach
	public void setUp() throws Exception {
		messageQueueEmpty = new MessageQueue();
		messageQueueNotEmpty = new MessageQueue();
		
		messageQueueNotEmpty.add(new Message("message 1"));
		messageQueueNotEmpty.add(new Message("message 2"));
		messageQueueNotEmpty.add(new Message("message 3"));
	}

	@Test
	public void testThrowExceptionWhenRemovingAMessageFromEmptyQueue() {
		assertThrows(IndexOutOfBoundsException.class, () -> { messageQueueEmpty.remove(); });
	}
	
	@Test
	public void testAddMessageToEmptyQueue() {
		messageQueueEmpty.add(new Message("message Test"));
		
		Message messageRemoved = messageQueueEmpty.remove();
		
		assertEquals("message Test", messageRemoved.getText());
	}
	
	@Test
	public void testMessageRemovedFromQueueNotEmpty() {
		Message messageRemoved = messageQueueNotEmpty.remove();
		
		assertEquals("message 1", messageRemoved.getText());
	}
	
	@Test
	public void testAddMessageToNotEmptyQueue() {
		messageQueueNotEmpty.add(new Message("message 4"));
		
		Message messageRemoved = messageQueueNotEmpty.remove();
		
		assertEquals("message 1", messageRemoved.getText());
	}
	
	@Test
	public void testSizeOfEmptyQueue() {
		assertEquals(0, messageQueueEmpty.size());
	}
	
	@Test
	public void testSizeOfNotEmptyQueue() {
		assertEquals(3, messageQueueNotEmpty.size());
	}
	
	@Test
	public void testPeekOfEmptyQueue() {
		Message message = messageQueueEmpty.peek();
		
		assertNull(message);
	}
	
	@Test
	public void testPeekOfNotEmptyQueue() {
		Message message = messageQueueNotEmpty.peek();
		
		assertEquals("message 1", message.getText());
	}
}
