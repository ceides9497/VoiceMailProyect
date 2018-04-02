import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class MailSystemTest {
	MailSystem mailSystem;
	int mailBoxCount;
	
	@Before
	public void init(){
		mailBoxCount = 10;
		mailSystem = new MailSystem(mailBoxCount);		
	}
	
	@Test
    public void deberiaEstarHaberCreadoLosMailboxes() {
		assertEquals(10, mailSystem.getLengthMailbox());
    }
	
	@Test
    public void deberiaDevolverNullPorMailboxInvalido() {
		assertEquals(null, mailSystem.findMailbox("23"));
    }
	
	@Test
    public void deberiaDevolverPasscodeDeMailboxBien() {
		assertTrue(mailSystem.findMailbox("8").checkPasscode("8"));
    }
	
	@Test
    public void deberiaDevolverPasscodeDeMailboxMal() {
		assertFalse(mailSystem.findMailbox("8").checkPasscode("9"));
    }
	
	@Test
    public void deberiaDevolverBienElGrettingDelMailbox() {
		assertEquals("You have reached mailbox 8. \nPlease leave a message now.",mailSystem.findMailbox("8").getGreeting());
    }
	
	@Test
    public void deberiaDevolverMalElGrettingDelMailbox() {
		assertNotEquals("You have reached mailbox 9. \nPlease leave a message now.",mailSystem.findMailbox("8").getGreeting());
    }

}
