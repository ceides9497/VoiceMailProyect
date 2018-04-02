import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MailboxTest {

	Mailbox mailbox;
	String passcode;
	String greeting;
	
	@Before
	public void init(){
		passcode = "1";
		greeting = "Hola!";
		mailbox = new Mailbox(passcode,greeting);
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
