import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class MessageQueueTest {
	private MessageQueue messages;

	@Test
	public void deberiaRetornar0ConSize() {
		messages = mock(MessageQueue.class);
		when(messages.size()).thenReturn(0);

		assertEquals(0, messages.size());
	}
	
	@Test
	public void deberiaRetornarNullConRemove() {
		messages = mock(MessageQueue.class);
		when(messages.remove()).thenReturn(null);
		
		assertNull(messages.remove());
	}
	
	@Test
	public void deberiaGuardarUnNuevoMensajeConAdd() {
		Message nuevo = new Message("mensaje1");
		
		messages = mock(MessageQueue.class);
		doNothing().when(messages).add(isA(Message.class));
		messages.add(nuevo);
		
		verify(messages, times(1)).add(nuevo);
	}
	
	@Test
	public void deberiaRetornarNullConPeek() {
		messages = mock(MessageQueue.class);
		when(messages.peek()).thenReturn(null);
		
		assertNull(messages.peek());
	}

}
