package ucb.voicemail.test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ucb.voicemail.main.Mailbox;
import ucb.voicemail.main.Message;
import ucb.voicemail.main.MessageQueue;

import static org.mockito.Mockito.*;

public class MailboxTest {

	Mailbox mailboxNotEmpty;
	Mailbox mailboxEmpty;
	String passcode;
	String greeting;
	Message mockedMessage;
	MessageQueue mockedMessageQueue;
	
	@Before
	public void init(){
		passcode = "1";
		greeting = "Hola!";
		mailboxNotEmpty = new Mailbox(passcode,greeting);
		mailboxEmpty = new Mailbox(passcode, greeting);
		mockedMessage = mock(Message.class);
		mockedMessageQueue = mock(MessageQueue.class);
		
		mailboxNotEmpty.addMessage(mockedMessage);
	    when(mockedMessage.getText()).thenReturn(greeting);
	}
	
	@Test
    public void deberiaDevolverPasscodeTrue() {
		assertTrue(mailboxEmpty.checkPasscode("1"));
    }
	
	@Test
    public void deberiaDevolverPasscodeFalse() {
		assertFalse(mailboxEmpty.checkPasscode("2"));
    }
	
	@Test
    public void deberiaRetornarMensaje() {
	    assertEquals(greeting,mailboxNotEmpty.getCurrentMessage().getText());
    }
	
	@Test
    public void deberiaRetornarMensajeActual() {
		mailboxEmpty.addMessage(mockedMessage);
		assertEquals(mockedMessage,mailboxNotEmpty.getCurrentMessage());
    }
	
	@Test
    public void deberiaRetornarNullSinMensajes() {
		assertEquals(null,mailboxEmpty.getCurrentMessage());
    }
	
	@Test
    public void deberiaRemoverCurrentMessage() {
		assertEquals(mockedMessage,mailboxNotEmpty.removeCurrentMessage());
    }
	
	@Test
    public void deberiaRemoverKeptMessage() {
		mailboxNotEmpty.saveCurrentMessage();
		assertEquals(mockedMessage,mailboxNotEmpty.removeCurrentMessage());
    }
	
	@Test
    public void deberiaRetornarNullAlRemoverMesajesPorNoTenerMensajes() {
		assertEquals(null,mailboxEmpty.removeCurrentMessage());
    }
	
	@Test
    public void deberiaGuardarMensajeActual() {
		mailboxNotEmpty.saveCurrentMessage();
		assertEquals(mockedMessage,mailboxNotEmpty.getCurrentMessage());
    }
	
	@Test
    public void deberiaNoHaberGuardadoMensajeActual() {
		mailboxEmpty.saveCurrentMessage();
		assertEquals(null,mailboxEmpty.getCurrentMessage());
    }
	
	@Test
    public void deberiaCambiarGreeting() {
		String newGreeting = "Bienvenido";
		mailboxNotEmpty.setGreeting(newGreeting);
		assertEquals(newGreeting,mailboxNotEmpty.getGreeting());
    }
	
	@Test
    public void deberiaCambiarPasscode() {
		String newPasscode = "3";
		mailboxNotEmpty.setPasscode(newPasscode);
		assertTrue(mailboxNotEmpty.checkPasscode(newPasscode));
    }

}
