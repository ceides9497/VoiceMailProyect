import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

public class MessageQueueTest {
	MessageQueue messages;
	Message mockedMessage;
	
	@Before
	public void init(){
		mockedMessage = mock(Message.class);
		messages = new MessageQueue();
	}
	
	@Test
	public void deberiaRetornar0ConSize() {
		assertEquals(0, messages.size());
	}
	
	@Test
	public void deberiaRetornarNumeroConSize() {
		messages.add(mockedMessage);
		messages.add(mockedMessage);
		assertEquals(2, messages.size());
	}
	
	@Test
	public void deberiaRetornarMesajequeSeRemovera() {
		messages.add(mockedMessage);
		messages.add(mockedMessage);
		
		assertEquals(mockedMessage, messages.remove());
	}
	
	@Test
	public void deberiaGuardarUnNuevoMensajeConAdd() {
		messages.add(mockedMessage);
		
		assertEquals(mockedMessage, messages.peek());
	}
	
	@Test
	public void deberiaRetornarNullConPeek() {
		assertNull(messages.peek());
	}

}
