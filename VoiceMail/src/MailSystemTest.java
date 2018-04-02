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
    public void deberiaDevolverMailboxValido() {
		assertTrue(mailSystem.findMailbox("8").checkPasscode("8"));
    }

}
