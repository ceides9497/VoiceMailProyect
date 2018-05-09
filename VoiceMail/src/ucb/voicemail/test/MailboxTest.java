package ucb.voicemail.test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
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
		mailboxNotEmpty = new Mailbox("1", passcode, greeting);
		mailboxEmpty = new Mailbox("1", passcode, greeting);
		mockedMessage = mock(Message.class);
		mockedMessageQueue = mock(MessageQueue.class);
		
		//mailboxNotEmpty.addMessage(mockedMessage);
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

	@Test
	public void deberiaObtenerElId() {
	    assertEquals("1", mailboxNotEmpty.getId());
	}
}
