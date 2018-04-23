package ucb.voicemail.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import ucb.voicemail.main.Connection;
import ucb.voicemail.main.MailSystem;
import ucb.voicemail.main.Mailbox;
import ucb.voicemail.main.MainMenu;
import ucb.voicemail.main.Message;
import ucb.voicemail.main.Telephone;
import ucb.voicemail.main.Window;

public class WindowTest {
	
	private Connection connection;
	private MailSystem mockMailsystem;
	private Mailbox mockMailbox;
	private MainMenu mockMainMenu;
	
	@Before
	public void init() {
		mockMailsystem = mock(MailSystem.class);
		connection = new Connection(mockMailsystem);
		mockMailbox = mock(Mailbox.class);
		mockMainMenu = mock(MainMenu.class);
	}
	
	@Test
	public void deberiaAgregarUnUserInterface() {
		Window w = new Window(mockMainMenu);
		connection.addUserInterface(w);
	}
	
	@Test
	public void deberiaActualizarElLabelPrincipal() {
		Window w = new Window(mockMainMenu);
		connection.addUserInterface(w);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
	}
	
	@Test
	public void deberiaEjecutarRun() {
		Window w = new Window(mockMainMenu);
		connection.addUserInterface(w);
		w.run(connection);
	}
}
