package ucb.voicemail.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import ucb.voicemail.main.*;
import ucb.voicemail.presenters.InitialPromptPresenter;
import ucb.voicemail.presenters.MailboxMenuPresenter;
import ucb.voicemail.presenters.MessageMenuTextPresenter;
import ucb.voicemail.repository.mailbox.ArrayMailboxRepository;
import ucb.voicemail.repository.message.ArrayMessageRepository;
import ucb.voicemail.state.ConnectedState;

public class ConnectionTest {
	
	private Connection connection;
	private ArrayMailboxRepository mockMailboxRepository;
	private ArrayMessageRepository mockMessageRepository;
	private Mailbox mockMailbox;
	private Telephone mockUserInterface;
	
	@Before
	public void init() {
		mockMailboxRepository = mock(ArrayMailboxRepository.class);
		mockMessageRepository = mock(ArrayMessageRepository.class);
		mockMailbox = mock(Mailbox.class);
		mockUserInterface = mock(Telephone.class);
		MailboxMenuPresenter mailboxMenuPresenter = new MailboxMenuPresenter();
        mailboxMenuPresenter.addOption("listen to your messages");
        mailboxMenuPresenter.addOption("change your passcode");
        mailboxMenuPresenter.addOption("change your greeting");
        MessageMenuTextPresenter messageMenuTextPresenter = new MessageMenuTextPresenter();
        messageMenuTextPresenter.addOption("listen to the current message");
        messageMenuTextPresenter.addOption("save the current message");
        messageMenuTextPresenter.addOption("delete the current message");
        messageMenuTextPresenter.addOption("return to the main menu");
		connection = new Connection(mockMailboxRepository, mockMessageRepository, new ConnectedState(), new InitialPromptPresenter());
		connection.setMailBoxMenuPresenter(mailboxMenuPresenter);
		connection.setMessageMenuTextPresenter(messageMenuTextPresenter);
	}
	
	@Test
	public void deberiaBuscarUnMailBox() {
		connection.dial("#");
		verify(mockMailboxRepository).findMailbox(any(String.class));
	}
	
	@Test
	public void deberiaBuscarMailBoxEspecifico() {
		connection.dial("1");
		connection.dial("#");
		verify(mockMailboxRepository).findMailbox("1");
	}
	
	@Test
	public void noDeberiaConcatenarElTextoACurrentRecording() {
		connection.record("texto");
		assertEquals("", connection.getCurrentRecording());
	}
	
	@Test
	public void noDeberiaAgregarMensajeACurrentMailbox() {
		connection.dial("1");
		connection.hangup();
		assertEquals("", connection.getAccumulatedKeys());
	}
	
	@Test
	public void deberiaAgregarUnUserInterface() {
		
		connection.addUserInterface(mockUserInterface);
		assertEquals(1, connection.getUserInterfaces().size());
	}
	
	@Test
	public void deberiaEliminarUnUserInterface() {
		
		connection.addUserInterface(mockUserInterface);
		connection.deleteUserInterface(mockUserInterface);
		assertEquals(0, connection.getUserInterfaces().size());
	}
	
	@Test
	public void deberiaLlamarAlMetodoResetConnection() {
		connection.start();
		assertEquals("", connection.getCurrentRecording());
		assertEquals("", connection.getAccumulatedKeys());
	}

	@Test
	public void deberiaLlamarAlMetodoLogin() {
		when(mockMailboxRepository.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		connection.dial("1");
		connection.dial("#");
		verify(mockMailbox).checkPasscode("1");
	}
	
	@Test
	public void deberiaDarElValorDeMailBoxMenuAState() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailboxRepository.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		verify(mockUserInterface).speak(connection.getMailboxMenu());
	}
	
	@Test
	public void deberiaMostrarElMensajeDeIncorrectPasscode() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailboxRepository.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(false);
		connection.dial("#");
		verify(mockUserInterface).speak("Incorrect passcode. Try again!");
	}
	
	@Test
	public void deberiaEjecutarElMetodoMailboxMenu() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailboxRepository.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("1");
		verify(mockUserInterface).speak(connection.getMessageMenuTextPresenter());
	}
	
	@Test
	public void deberiaMostrarMensajeDeEnterNewPasscode() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailbox.getId()).thenReturn("1");
		when(mockMailboxRepository.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("2");
		connection.dial("NEW_PASSCODE");
		connection.dial("#");
		verify(mockUserInterface).speak("Enter new passcode followed by the # key");
		verify(mockMailboxRepository).setMailboxPasscode("1", "NEW_PASSCODE");
	}
	
	@Test
	public void deberiaEjecutarElMetodoChangeGreeting() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailbox.getId()).thenReturn("1");
		when(mockMailboxRepository.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("3");
		connection.record("NEW_GREETING");
		connection.dial("1");
		connection.dial("#");
		verify(mockUserInterface).speak("Record your greeting, then press the # key");
		verify(mockMailboxRepository).setMailboxGreeting("1", "NEW_GREETING");
	}
	
	@Test
	public void noDeberiaHacerNadaEnMailboxMenu() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailboxRepository.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("4");
		verify(mockUserInterface, never()).speak(connection.getMessageMenuTextPresenter());
		verify(mockUserInterface, never()).speak("Enter new passcode followed by the # key");
		verify(mockUserInterface, never()).speak("Record your greeting, then press the # key");
	}
	
	@Test
	public void deberiaEjecutarElMetodoMessageMenu() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailboxRepository.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("1");
		verify(mockUserInterface).speak(connection.getMessageMenuTextPresenter());
	}
	
	@Test
	public void deberiaMostarMensaje() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailbox.getId()).thenReturn("1");
		Message mockMessage = mock(Message.class);
		when(mockMessage.getText()).thenReturn("Not null");
		when(mockMailboxRepository.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		//when(mockMailbox.getCurrentMessage()).thenReturn(mockMessage);
		when(mockMessageRepository.getCurrentMessage("1")).thenReturn(mockMessage);
		connection.dial("#");
		connection.dial("1");
		connection.dial("1");
		verify(mockUserInterface).speak("Not null\n" + connection.getMessageMenuTextPresenter());
	}
	
	@Test 
	public void noDeberiaMostrarMensajes() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailbox.getId()).thenReturn("1");
		when(mockMailboxRepository.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		//when(mockMailbox.getCurrentMessage()).thenReturn(null);
		when(mockMessageRepository.getCurrentMessage("1")).thenReturn(null);
		connection.dial("#");
		connection.dial("1");
		connection.dial("1");
		verify(mockUserInterface).speak("No messages.\n" + connection.getMessageMenuTextPresenter());
	}
	
	@Test
	public void deberiaGrabarMensaje() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailbox.getId()).thenReturn("1");
		when(mockMailboxRepository.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		connection.record("Voice");
		connection.hangup();
		verify(mockMessageRepository).addMessage(eq("1"), any());
	}
	
	@Test
	public void deberiaGuardarUltimoMensaje() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailbox.getId()).thenReturn("1");
		when(mockMailboxRepository.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("1");
		connection.dial("2");
		verify(mockMessageRepository).saveCurrentMessage("1");
	}
	
	@Test
	public void deberiaEliminarUltimoMensaje() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailbox.getId()).thenReturn("1");
		when(mockMailboxRepository.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("1");
		connection.dial("3");
		verify(mockMessageRepository).removeCurrentMessage("1");
	}
	
	@Test
	public void deberiaSalirMessageMenu() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailboxRepository.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("1");
		connection.dial("4");
		verify(mockUserInterface, times(2)).speak(connection.getMailboxMenu());
	}
	
	@Test
	public void noDeberiaHacerNadaEnMessageMenu() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailbox.getId()).thenReturn("1");
		when(mockMailboxRepository.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("1");
		connection.dial("5");
		verify(mockMessageRepository, never()).saveCurrentMessage("1");
		verify(mockMessageRepository, never()).removeCurrentMessage("1");
	}
	
	@Test
	public void deberiaMostrarMensajeDeRecordYourGreeting() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailboxRepository.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("3");
		verify(mockUserInterface).speak("Record your greeting, then press the # key");
	}
	
	@Test
	public void deberiaRetornarElMailSystemAsignadoEnElConstructor() {
		assertEquals(mockMailboxRepository, connection.getMailSystem());
	}
}
