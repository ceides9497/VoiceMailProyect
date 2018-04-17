package ucb.voicemail.Test;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

import org.junit.Test;

import ucb.voicemail.Class.Connection;
import ucb.voicemail.Class.MailSystem;
import ucb.voicemail.Class.Telephone;

public class ConnectionTest {
	private Connection connection;
	private MailSystem mailsystem;
	private Telephone telephone;
	
	@Test
	public void test1() {
		mailsystem = mock(MailSystem.class);
		
		connection = new Connection(mailsystem);
		
		connection.dial("key to test");
	}
}
