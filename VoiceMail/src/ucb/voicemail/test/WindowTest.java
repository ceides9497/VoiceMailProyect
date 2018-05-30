package ucb.voicemail.test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ucb.voicemail.domain.Connection;
import ucb.voicemail.domain.Mailbox;
import ucb.voicemail.domain.connection.state.ConnectedState;
import ucb.voicemail.presenters.InitialPromptPresenter;
import ucb.voicemail.repository.mailbox.ArrayMailboxRepository;
import ucb.voicemail.repository.message.ArrayMessageRepository;
import ucb.voicemail.view.MainMenu;
import ucb.voicemail.view.GraphicalTelephone;

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
