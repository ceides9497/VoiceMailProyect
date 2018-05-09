package ucb.voicemail.test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ucb.voicemail.main.Message;
import ucb.voicemail.repository.message.MessageQueue;

public class MessageQueueTest {
	
	MessageQueue messageQueueEmpty;
	MessageQueue messageQueueNotEmpy;
	Message mockedMessage;
	
	@Before
	public void setUp() {
		mockedMessage = mock(Message.class);
		messageQueueEmpty = new MessageQueue();
		messageQueueNotEmpy = new MessageQueue();
		
		messageQueueNotEmpy.add(mockedMessage);
		messageQueueNotEmpy.add(mockedMessage);
	}
	
	@Test
	public void deberiaRetornar0ConSize() {
		assertEquals(0, messageQueueEmpty.size());
	}
	
	@Test
	public void deberiaRetornarNumeroConSize() {
		assertEquals(2, messageQueueNotEmpy.size());
	}
	
	@Test
	public void deberiaRetornarMensajeQueSeraRemovido() {		
		assertEquals(mockedMessage, messageQueueNotEmpy.remove());
	}
	
	@Test
	public void deberiaGuardarUnNuevoMensajeConAdd() {
		assertEquals(mockedMessage, messageQueueNotEmpy.peek());
	}
	
	@Test
	public void deberiaRetornarNullConPeek() {
		assertNull(messageQueueEmpty.peek());
	}

}
