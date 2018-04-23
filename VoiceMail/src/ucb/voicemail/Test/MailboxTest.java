package ucb.voicemail.Test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ucb.voicemail.Class.Mailbox;
import ucb.voicemail.Class.Message;
import ucb.voicemail.Class.MessageQueue;

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
	    assertEquals(greeting,mailbox.getCurrentMessage().getText());
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
    public void deberiaRemoverCurrentMessage() {
		mailbox.addMessage(mockedMessage);
		mailbox.addMessage(mockedMessage);
		assertEquals(mockedMessage,mailbox.removeCurrentMessage());
    }
	
	@Test
    public void deberiaRemoverKeptMessage() {
		mailbox.addMessage(mockedMessage);
		mailbox.saveCurrentMessage();
		assertEquals(mockedMessage,mailbox.removeCurrentMessage());
    }
	
	@Test
    public void deberiaRetornarNullAlRemoverMesajesPorNoTenerMensajes() {
		assertEquals(null,mailbox.removeCurrentMessage());
    }
	
	@Test
    public void deberiaGuardarMensajeActual() {
		mailbox.addMessage(mockedMessage);
		assertEquals(mockedMessage,mailbox.getCurrentMessage());
		mailbox.saveCurrentMessage();
		assertEquals(mockedMessage,mailbox.getCurrentMessage());
    }
	
	@Test
    public void deberiaNoHaberGuardadoMensajeActual() {
		mailbox.saveCurrentMessage();
		assertEquals(null,mailbox.getCurrentMessage());
    }
	
	@Test
    public void deberiaCambiarGreeting() {
		String newGreeting = "Bienvenido";
		mailbox.setGreeting(newGreeting);
		assertEquals(newGreeting,mailbox.getGreeting());
    }
	
	@Test
    public void deberiaCambiarPasscode() {
		String newPasscode = "3";
		mailbox.setPasscode(newPasscode);
		assertTrue(mailbox.checkPasscode(newPasscode));
    }

}
