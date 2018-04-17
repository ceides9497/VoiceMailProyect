package ucb.voicemail.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ucb.voicemail.Class.MailSystem;

public class MailSystemTest {
	MailSystem mailSystem;
	int mailBoxCount;
	
	@Before
	public void init(){
		mailBoxCount = 10;
		mailSystem = new MailSystem(mailBoxCount);		
	}
	
	@Test
    public void deberiaHaberCreadoLosMailboxes() {
		assertEquals(10, mailSystem.getLengthMailbox());
    }
	
	@Test
    public void deberiaDevolverNullPorMailboxInvalido() {
		assertEquals(null, mailSystem.findMailbox("23"));
    }
	
	@Test
    public void deberiaDevolverDiferenteDeNullPorMailboxValido() {
		assertNotEquals(null, mailSystem.findMailbox("8"));
    }
	
	@Test
    public void deberiaDevolverPasscodeDeMailboxTrue() {
		assertTrue(mailSystem.findMailbox("8").checkPasscode("8"));
    }
	
	@Test
    public void deberiaDevolverPasscodeDeMailboxFalse() {
		assertFalse(mailSystem.findMailbox("8").checkPasscode("9"));
    }
	
	@Test
    public void deberiaDevolverIgualElGrettingDelMailbox() {
		assertEquals("You have reached mailbox 8. \nPlease leave a message now.",mailSystem.findMailbox("8").getGreeting());
    }
	
	@Test
    public void deberiaDevolverNoIgualElGrettingDelMailbox() {
		assertNotEquals("You have reached mailbox 9. \nPlease leave a message now.",mailSystem.findMailbox("8").getGreeting());
    }

}
