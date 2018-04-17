package ucb.voicemail.Test;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ucb.voicemail.Class.Connection;
import ucb.voicemail.Class.MailSystem;

public class ConnectionTest {
	private Connection connection;
	private MailSystem mailsystem;
	
	@Before
	public void init() {
		mailsystem = mock(MailSystem.class);
		connection = new Connection(mailsystem);
	}
	
	@Test
	public void deberiaLlamarAlMetodoConnect() {
		connection.dial("key to test");
	}
	
	@Test
	public void noDeberiaConcatenarElTextoACurrentRecording() {
		connection.record("texto");
	}
	
	@Test
	public void noDeberiaAgregarMensajeACurrentMailbox() {
		connection.hangup();
	}
}
