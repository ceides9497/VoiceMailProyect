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
	private ArrayList<UserInterface> mockArrayList;
	
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
		connection = new Connection(mockMailsystem);
		mockMailbox = mock(Mailbox.class);
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
		UserInterface mockUserInterface = mock(UserInterface.class);
		connection.addUserInterface(mockUserInterface);
		assertEquals(1, connection.getUserInterfaces().size());
	}
	
	@Test
	public void deberiaEliminarUnUserInterface() {
		UserInterface mockUserInterface = mock(UserInterface.class);
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
		UserInterface mockUserInterface = mock(UserInterface.class);
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		verify(mockUserInterface).updateInterface("Enter 1 to listen to your messages\n"
		        + "Enter 2 to change your passcode\n"
		        + "Enter 3 to change your greeting");
	}
	
	@Test
	public void deberiaMostrarElMensajeDeIncorrectPasscode() {
		UserInterface mockUserInterface = mock(UserInterface.class);
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(false);
		connection.dial("#");
		verify(mockUserInterface).updateInterface("Incorrect passcode. Try again!");
	}
	
	@Test
	public void deberiaEjecutarElMetodoMailboxMenu() {
		UserInterface mockUserInterface = mock(UserInterface.class);
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("1");
		verify(mockUserInterface).updateInterface(MESSAGE_MENU_TEXT);
	}
	
	@Test
	public void deberiaMostrarMensajeDeEnterNewPasscode() {
		UserInterface mockUserInterface = mock(UserInterface.class);
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("2");
		connection.dial("NEW_PASSCODE");
		connection.dial("#");
		verify(mockUserInterface).updateInterface("Enter new passcode followed by the # key");
		verify(mockMailbox).setPasscode("NEW_PASSCODE");
	}
	
	@Test
	public void deberiaEjecutarElMetodoChangeGreeting() {
		UserInterface mockUserInterface = mock(UserInterface.class);
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("3");
		connection.record("NEW_GREETING");
		connection.dial("1");
		connection.dial("#");
		verify(mockUserInterface).updateInterface("Record your greeting, then press the # key");
		verify(mockMailbox).setGreeting("NEW_GREETING");
	}
	
	@Test
	public void deberiaEjecutarElMetodoMessageMenu() {
		UserInterface mockUserInterface = mock(UserInterface.class);
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("1");
		verify(mockUserInterface).updateInterface(MESSAGE_MENU_TEXT);
	}
	
	@Test
	public void deberiaMostarMensaje() {
		UserInterface mockUserInterface = mock(UserInterface.class);
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		when(mockMailbox.getCurrentMessage()).thenReturn(new Message("Not null"));
		connection.dial("#");
		connection.dial("1");
		connection.dial("1");
		verify(mockUserInterface).updateInterface("Not null\n" + MESSAGE_MENU_TEXT);
	}
	
	@Test 
	public void noDeberiaMostrarMensajes() {
		UserInterface mockUserInterface = mock(UserInterface.class);
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		when(mockMailbox.getCurrentMessage()).thenReturn(null);
		connection.dial("#");
		connection.dial("1");
		connection.dial("1");
		verify(mockUserInterface).updateInterface("No messages.\n" + MESSAGE_MENU_TEXT);
	}
	
	@Test
	public void deberiaGrabarMensaje() {
		UserInterface mockUserInterface = mock(UserInterface.class);
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		connection.record("Voice");
		connection.hangup();
		verify(mockMailbox).addMessage(any());
	}
	
	@Test
	public void deberiaGuardarUltimoMensaje() {
		UserInterface mockUserInterface = mock(UserInterface.class);
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
		UserInterface mockUserInterface = mock(UserInterface.class);
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
		UserInterface mockUserInterface = mock(UserInterface.class);
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("1");
		connection.dial("4");
		verify(mockUserInterface, times(2)).updateInterface(MAILBOX_MENU_TEXT);
	}
	
	
	
	@Test
	public void noDeberiaHacerNadaEnMessageMenu() {
		UserInterface mockUserInterface = mock(UserInterface.class);
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
		UserInterface mockUserInterface = mock(UserInterface.class);
		connection.addUserInterface(mockUserInterface);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("3");
		verify(mockUserInterface).updateInterface("Record your greeting, then press the # key");
	}
}
