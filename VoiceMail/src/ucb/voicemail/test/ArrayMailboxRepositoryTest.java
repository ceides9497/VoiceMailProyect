package ucb.voicemail.test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import ucb.voicemail.main.ArrayMailboxRepository;

public class ArrayMailboxRepositoryTest {
	ArrayMailboxRepository mailSystem;
	int mailBoxCount;
	
	@Before
	public void init() {
		mailBoxCount = 10;
		mailSystem = new ArrayMailboxRepository(mailBoxCount);		
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
    public void deberiaDevolverMailboxValido() {
		assertNotEquals(null, mailSystem.findMailbox("8"));
    }
	
	@Test
    public void deberiaDevolverTrueAlEncontrarVerificarPasscode() {
		assertTrue(mailSystem.findMailbox("8").checkPasscode("8"));
    }
	
	@Test
    public void deberiaDevolverFalseAlVerificarPasscode() {
		assertFalse(mailSystem.findMailbox("8").checkPasscode("9"));
    }
	
	@Test
    public void deberiaDevolverElMismoGrettingDelMailbox8() {
		assertEquals("You have reached mailbox 8. \nPlease leave a message now.",mailSystem.findMailbox("8").getGreeting());
    }
	
	@Test
    public void deberiaDevolverNoIgualPorDIferenteGreetingAlDelMailbox8() {
		assertNotEquals("You have reached mailbox 9. \nPlease leave a message now.",mailSystem.findMailbox("8").getGreeting());
    }

}
