package ucb.voicemail.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import ucb.voicemail.Class.Connection;
import ucb.voicemail.Class.MailSystem;
import ucb.voicemail.Class.Mailbox;
import ucb.voicemail.Class.Telephone;

public class ConnectionTest {
	private Connection connection;
	private MailSystem mockMailsystem;
	
	@Before
	public void init() {
		mockMailsystem = mock(MailSystem.class);
		connection = new Connection(mockMailsystem);
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
	
	@Test
	public void deberiaAgregarUnUserInterface() {
		Telephone t = new Telephone(new Scanner(System.in));
		connection.addUserInterface(t);
	}
	
	@Test
	public void deberiaMostrarElmensajeIncorrectmailboxnumberTryAgain() {
		Telephone t = new Telephone(new Scanner(System.in));
		connection.addUserInterface(t);
		
		PrintStream out = mock(PrintStream.class);
        System.setOut(out);
		
		connection.dial("#");
		
		verify(out).println("Incorrect mailbox number. Try again!");
	}
	
	@Test
	public void deberiaEliminarUnUserInterface() {
		Telephone telephone = new Telephone(new Scanner(System.in));
		connection.addUserInterface(telephone);
		connection.deleteUserInterface(telephone);
	}
	
	@Test
	public void deberiaLlamarAlMetodoResetConnection() {
		connection.start();
	}
	
	@Test
	public void deberiaLlamarAlMetodoLogin() {
		Telephone t = new Telephone(new Scanner(System.in));
		connection.addUserInterface(t);
		
		when(mockMailsystem.findMailbox(anyString())).thenReturn(new Mailbox("passcode", "greeting"));
		
		connection.dial("#");
		connection.dial("texto");
	}
}