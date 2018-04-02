import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class MailboxTest {

	Mailbox mailbox;
	String passcode;
	String greeting;
	Message mockedMessage;
	
	@Before
	public void init(){
		passcode = "1";
		greeting = "Hola!";
		mailbox = new Mailbox(passcode,greeting);
		mockedMessage = mock(Message.class);
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

}
