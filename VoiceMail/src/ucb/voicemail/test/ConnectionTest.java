package ucb.voicemail.test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import ucb.voicemail.main.Connection;
import ucb.voicemail.main.MailSystem;
import ucb.voicemail.main.Mailbox;
import ucb.voicemail.main.Message;
import ucb.voicemail.main.Telephone;

public class ConnectionTest {
	
	private Connection connection;
	private MailSystem mockMailsystem;
	private Mailbox mockMailbox;
	
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
	
	// ====================================================================
	
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
	public void deberiaLlamarAlMetodoNotify() {
		Telephone t = new Telephone(new Scanner(System.in));
		connection.addUserInterface(t);
		connection.dial("#");
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
	
	@Test
	public void deberiaDarElValorDeMailBoxMenuAState() {
		Telephone t = new Telephone(new Scanner(System.in));
		connection.addUserInterface(t);
		
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		
		connection.dial("#");
		
		when(mockMailbox.checkPasscode(anyString())).thenReturn(true);
		
		connection.dial("#");
	}
	
	@Test
	public void deberiaMostrarElMensajeDeIncorrectPasscode() {
		Telephone t = new Telephone(new Scanner(System.in));
		connection.addUserInterface(t);
		
		PrintStream out = mock(PrintStream.class);
        System.setOut(out);
		
		when(mockMailsystem.findMailbox(anyString())).thenReturn(mockMailbox);
		
		connection.dial("#");
		
		when(mockMailbox.checkPasscode(anyString())).thenReturn(false);
		
		connection.dial("#");
		
		verify(out).println("Incorrect passcode. Try again!");
	}
	
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
