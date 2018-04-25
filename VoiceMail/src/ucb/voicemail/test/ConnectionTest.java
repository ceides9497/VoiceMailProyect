package ucb.voicemail.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import ucb.voicemail.main.*;

public class ConnectionTest {
	
	private Connection connection;
	private MailSystem mockMailsystem;
	private Mailbox mockMailbox;
	private Telephone mockUserInterface;
	
	private static final String MESSAGE_MENU_TEXT = 
	        "Enter 1 to listen to the current message\n"
	        + "Enter 2 to save the current message\n"
	        + "Enter 3 to delete the current message\n"
	        + "Enter 4 to return to the main menu";
	
	private static final String MAILBOX_MENU_TEXT = 
	        "Enter 1 to listen to your messages\n"
	        + "Enter 2 to change your passcode\n"
	        + "Enter 3 to change your greeting";
	
	@Before
	public void init() {
		mockMailsystem = mock(MailSystem.class);
		mockMailbox = mock(Mailbox.class);
		mockUserInterface = mock(Telephone.class);
		connection = new Connection(mockMailsystem);
	}
	
	@Test
	public void deberiaBuscarUnMailBox() {
		connection.dial("#");
		verify(mockMailsystem).findMailbox(any(String.class));
	}
	
	@Test
	public void deberiaBuscarMailBoxEspecifico() {
		connection.dial("1");
		connection.dial("#");
		verify(mockMailsystem).findMailbox("1");
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
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		connection.dial("1");
		connection.dial("#");
		verify(mockMailbox).checkPasscode("1");
	}
	
	@Test
	public void deberiaDarElValorDeMailBoxMenuAState() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		verify(mockUserInterface).speak(MAILBOX_MENU_TEXT);
	}
	
	@Test
	public void deberiaMostrarElMensajeDeIncorrectPasscode() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(false);
		connection.dial("#");
		verify(mockUserInterface).speak("Incorrect passcode. Try again!");
	}
	
	@Test
	public void deberiaEjecutarElMetodoMailboxMenu() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("1");
		verify(mockUserInterface).speak(MESSAGE_MENU_TEXT);
	}
	
	@Test
	public void deberiaMostrarMensajeDeEnterNewPasscode() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("2");
		connection.dial("NEW_PASSCODE");
		connection.dial("#");
		verify(mockUserInterface).speak("Enter new passcode followed by the # key");
		verify(mockMailbox).setPasscode("NEW_PASSCODE");
	}
	
	@Test
	public void deberiaEjecutarElMetodoChangeGreeting() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("3");
		connection.record("NEW_GREETING");
		connection.dial("1");
		connection.dial("#");
		verify(mockUserInterface).speak("Record your greeting, then press the # key");
		verify(mockMailbox).setGreeting("NEW_GREETING");
	}
	
	@Test
	public void noDeberiaHacerNadaEnMailboxMenu() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("4");
		verify(mockUserInterface, never()).speak(MESSAGE_MENU_TEXT);
		verify(mockUserInterface, never()).speak("Enter new passcode followed by the # key");
		verify(mockUserInterface, never()).speak("Record your greeting, then press the # key");
	}
	
	@Test
	public void deberiaEjecutarElMetodoMessageMenu() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("1");
		verify(mockUserInterface).speak(MESSAGE_MENU_TEXT);
	}
	
	@Test
	public void deberiaMostarMensaje() {
		connection.addUserInterface(mockUserInterface);
		Message mockMessage = mock(Message.class);
		when(mockMessage.getText()).thenReturn("Not null");
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		when(mockMailbox.getCurrentMessage()).thenReturn(mockMessage);
		connection.dial("#");
		connection.dial("1");
		connection.dial("1");
		verify(mockUserInterface).speak("Not null\n" + MESSAGE_MENU_TEXT);
	}
	
	@Test 
	public void noDeberiaMostrarMensajes() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		when(mockMailbox.getCurrentMessage()).thenReturn(null);
		connection.dial("#");
		connection.dial("1");
		connection.dial("1");
		verify(mockUserInterface).speak("No messages.\n" + MESSAGE_MENU_TEXT);
	}
	
	@Test
	public void deberiaGrabarMensaje() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		connection.record("Voice");
		connection.hangup();
		verify(mockMailbox).addMessage(any());
	}
	
	@Test
	public void deberiaGuardarUltimoMensaje() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("1");
		connection.dial("2");
		verify(mockMailbox).saveCurrentMessage();
	}
	
	@Test
	public void deberiaEliminarUltimoMensaje() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("1");
		connection.dial("3");
		verify(mockMailbox).removeCurrentMessage();
	}
	
	@Test
	public void deberiaSalirMessageMenu() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("1");
		connection.dial("4");
		verify(mockUserInterface, times(2)).speak(MAILBOX_MENU_TEXT);
	}
	
	@Test
	public void noDeberiaHacerNadaEnMessageMenu() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("1");
		connection.dial("5");
		verify(mockMailbox, never()).saveCurrentMessage();
		verify(mockMailbox, never()).removeCurrentMessage();
	}
	
	@Test
	public void deberiaMostrarMensajeDeRecordYourGreeting() {
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("3");
		verify(mockUserInterface).speak("Record your greeting, then press the # key");
	}
}
