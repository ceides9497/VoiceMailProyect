import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class MailboxTest {

	Mailbox mailbox;
	String passcode;
	String greeting;
	Message mockedMessage;
	MessageQueue mockedMessageQueue;
	
	@Before
	public void init(){
		passcode = "1";
		greeting = "Hola!";
		mailbox = new Mailbox(passcode,greeting);
		mockedMessage = mock(Message.class);
		mockedMessageQueue = mock(MessageQueue.class);
	}
	
	@Test
    public void deberiaDevolverPasscodeTrue() {
		assertTrue(mailbox.checkPasscode("1"));
    }
	
	@Test
    public void deberiaDevolverPasscodeFalse() {
		assertFalse(mailbox.checkPasscode("2"));
    }
	
	@Test
    public void deberiaRetornarMensaje() {
		mailbox.addMessage(mockedMessage);
	    when(mockedMessage.getText()).thenReturn(greeting);
    }
	
	@Test
    public void deberiaRetornarNumeroDeMensajes() {
		int sizeMessages = 2;
		when(mockedMessageQueue.size()).thenReturn(sizeMessages);		
    }
	
	@Test
    public void deberiaRetornarMensajeActual() {
		mailbox.addMessage(mockedMessage);
		assertEquals(mockedMessage,mailbox.getCurrentMessage());
    }
	
	@Test
    public void deberiaRetornarNullSinMensajes() {
		assertEquals(null,mailbox.getCurrentMessage());
    }
	
	@Test
    public void deberiaRetornarAlRemoverMesajes() {
		mailbox.addMessage(mockedMessage);
		mailbox.addMessage(mockedMessage);
		assertEquals(mockedMessage,mailbox.removeCurrentMessage());
    }
	
	@Test
    public void deberiaRetornarNullAlRemoverMesajesPorVacio() {
		assertEquals(null,mailbox.removeCurrentMessage());
    }
	
	@Test
    public void deberiaGuardarMensajeActuak() {
		mailbox.addMessage(mockedMessage);
		assertEquals(mockedMessage,mailbox.getCurrentMessage());
		mailbox.saveCurrentMessage();
		assertEquals(mockedMessage,mailbox.getCurrentMessage());
    }

}
