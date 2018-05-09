package ucb.voicemail.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import ucb.voicemail.state.ConnectedState;
import ucb.voicemail.main.Connection;
import ucb.voicemail.main.ArrayMailboxRepository;
import ucb.voicemail.main.ArrayMessageRepository;
import ucb.voicemail.main.Mailbox;
import ucb.voicemail.main.MainMenu;
import ucb.voicemail.main.GraphicalTelephone;

public class WindowTest {
	
	private Connection connection;
	private ArrayMailboxRepository mockMailboxRepository;
	private ArrayMessageRepository mockMessageRepository;
	private Mailbox mockMailbox;
	private MainMenu mockMainMenu;
	
	@Before
	public void init() {
		mockMailboxRepository = mock(ArrayMailboxRepository.class);
		mockMessageRepository = mock(ArrayMessageRepository.class);
		connection = new Connection(mockMailboxRepository, mockMessageRepository, new ConnectedState());
		mockMailbox = mock(Mailbox.class);
		mockMainMenu = mock(MainMenu.class);
	}
	
	@Test
	public void deberiaAgregarUnUserInterface() {
		GraphicalTelephone w = new GraphicalTelephone(mockMainMenu);
		connection.addUserInterface(w);
	}
	
	@Test
	public void deberiaActualizarElLabelPrincipal() {
		GraphicalTelephone w = new GraphicalTelephone(mockMainMenu);
		connection.addUserInterface(w);
		doNothing().when(mockMainMenu).show();
		when(mockMailboxRepository.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
	}
	
	@Test
	public void deberiaEjecutarRun() {
		GraphicalTelephone w = new GraphicalTelephone(mockMainMenu);
		connection.addUserInterface(w);
		doNothing().when(mockMainMenu).show();
		w.run(connection);
	}
}
