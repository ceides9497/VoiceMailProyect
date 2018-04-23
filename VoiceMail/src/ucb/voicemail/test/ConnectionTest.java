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
	
	// ====================================================================
	
	@Test
	public void deberiaEjecutarElMetodoMailboxMenu() {
		Telephone t = new Telephone(new Scanner(System.in));
		connection.addUserInterface(t);
		
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		
		connection.dial("#");
		
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		
		connection.dial("#");
		
		connection.dial("1");
	}
	
	@Test
	public void deberiaMostrarMensajeDeEnterNewPasscode() {
		Telephone t = new Telephone(new Scanner(System.in));
		connection.addUserInterface(t);
		
		PrintStream out = mock(PrintStream.class);
        System.setOut(out);
		
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		
		connection.dial("#");
		
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		
		connection.dial("#");
		
		connection.dial("2");
		
		verify(out).println("Enter new passcode followed by the # key");
	}
	
	@Test
	public void deberiaEjecutarElMetodoChangePasscode() {
		Telephone t = new Telephone(new Scanner(System.in));
		connection.addUserInterface(t);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("2");
		connection.dial("NEW_PASSCODE");
		connection.dial("#");
		connection.dial("H");
	}
	
	@Test
	public void deberiaEjecutarElMetodoChangeGreeting() {
		Telephone t = new Telephone(new Scanner(System.in));
		connection.addUserInterface(t);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("3");
		connection.dial("NEW_GREETING");
		connection.dial("#");
		connection.dial("H");
	}
	
	@Test
	public void deberiaEjecutarElMetodoMessageMenu() {
		Telephone t = new Telephone(new Scanner(System.in));
		connection.addUserInterface(t);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("1");
		connection.dial("1");
	}
	
	@Test
	public void deberiaMostarMensaje() {
		Telephone t = new Telephone(new Scanner(System.in));
		connection.addUserInterface(t);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		when(mockMailbox.getCurrentMessage()).thenReturn(new Message("Not null"));
		connection.dial("#");
		connection.dial("1");
		connection.dial("1");
	}
	
	@Test
	public void deberiaRealizarGrabacionEnEstadoRecording() {
		Telephone t = new Telephone(new Scanner(System.in));
		connection.addUserInterface(t);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		connection.record("Voice");
	}
	
	@Test
	public void deberiaRealizarGrabacionEnEstadoChangeGreeting() {
		Telephone t = new Telephone(new Scanner(System.in));
		connection.addUserInterface(t);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("3");
		connection.record("Voice");
	}
	
	@Test
	public void deberiaGrabarMensaje() {
		Telephone t = new Telephone(new Scanner(System.in));
		connection.addUserInterface(t);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		connection.record("Voice");
		connection.hangup();
	}
	
	@Test
	public void deberiaGuardarUltimoMensaje() {
		Telephone t = new Telephone(new Scanner(System.in));
		connection.addUserInterface(t);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("1");
		connection.dial("2");
	}
	
	@Test
	public void deberiaEliminarUltimoMensaje() {
		Telephone t = new Telephone(new Scanner(System.in));
		connection.addUserInterface(t);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("1");
		connection.dial("3");
	}
	
	@Test
	public void deberiaSalirMessageMenu() {
		Telephone t = new Telephone(new Scanner(System.in));
		connection.addUserInterface(t);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("1");
		connection.dial("4");
	}
	
	@Test
	public void noDeberiaHacerNadaEnMessageMenu() {
		Telephone t = new Telephone(new Scanner(System.in));
		connection.addUserInterface(t);
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		connection.dial("#");
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		connection.dial("#");
		connection.dial("1");
		connection.dial("5");
	}
	
	@Test
	public void deberiaMostrarMensajeDeRecordYourGreeting() {
		Telephone t = new Telephone(new Scanner(System.in));
		connection.addUserInterface(t);
		
		PrintStream out = mock(PrintStream.class);
        System.setOut(out);
		
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		
		connection.dial("#");
		
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		
		connection.dial("#");
		
		connection.dial("3");
		
		verify(out).println("Record your greeting, then press the # key");
	}
	
	@Test
	public void noDeberiaMostrarMensajeAlEntrarAMailboxMenu() {
		Telephone t = new Telephone(new Scanner(System.in));
		connection.addUserInterface(t);
		
		PrintStream out = mock(PrintStream.class);
        System.setOut(out);
		
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		
		connection.dial("#");
		
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		
		connection.dial("#");
		
		connection.dial("4");
	}
}
