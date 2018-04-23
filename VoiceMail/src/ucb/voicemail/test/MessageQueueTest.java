package ucb.voicemail.test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ucb.voicemail.main.Message;
import ucb.voicemail.main.MessageQueue;

public class MessageQueueTest {
	MessageQueue messagesQueueEmpty;
	Message mockedMessage;
	
	@Before
	public void init(){
		mockedMessage = mock(Message.class);
		messagesQueueEmpty = new MessageQueue();
	}
	
	@Test
	public void deberiaRetornar0ConSize() {
		assertEquals(0, messagesQueueEmpty.size());
	}
	
	@Test
	public void deberiaRetornarNumeroConSize() {
		messagesQueueEmpty.add(mockedMessage);
		messagesQueueEmpty.add(mockedMessage);
		assertEquals(2, messagesQueueEmpty.size());
	}
	
	@Test
	public void deberiaRetornarMensajeQueSeraRemovido() {
		messagesQueueEmpty.add(mockedMessage);
		messagesQueueEmpty.add(mockedMessage);
		
		assertEquals(mockedMessage, messagesQueueEmpty.remove());
	}
	
	@Test
	public void deberiaGuardarUnNuevoMensajeConAdd() {
		messagesQueueEmpty.add(mockedMessage);
		
		assertEquals(mockedMessage, messagesQueueEmpty.peek());
	}
	
	@Test
	public void deberiaRetornarNullConPeek() {
		assertNull(messagesQueueEmpty.peek());
	}

}
