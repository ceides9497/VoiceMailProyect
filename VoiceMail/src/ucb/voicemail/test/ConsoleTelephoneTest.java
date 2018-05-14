package ucb.voicemail.test;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import ucb.voicemail.domain.Connection;
import ucb.voicemail.view.ConsoleTelephone;

public class ConsoleTelephoneTest {
	
	private ConsoleTelephone consoleTelephone;
	private ConsoleTelephone mockConsoleTelephone;
	private PrintStream out;
	private Connection mockConnection;
	
	@Before
	public void setUp() {
		mockConsoleTelephone = mock(ConsoleTelephone.class);
		out = mock(PrintStream.class);
		mockConnection = mock(Connection.class);
		
		System.setOut(out);
		
		when(mockConsoleTelephone.getScannerNextLine()).thenReturn(null);
		Mockito.doCallRealMethod().when(mockConsoleTelephone).run(any(Connection.class));
		
		doNothing().when(mockConnection).hangup();
		doNothing().when(mockConnection).dial(any(String.class));
		doNothing().when(mockConnection).record(any(String.class));
	}
	
	@After
	public void tearDown() {
		System.setOut(System.out);
	}
	
	@Test
	public void deberiaMostrarTestRunning() {
		consoleTelephone = new ConsoleTelephone();
        
        consoleTelephone.speak("TestRunning");
        
        verify(out).println("TestRunning");
	}
	
	@Test
	public void deberiaEjecutarseHanupDeConnection() {
		Scanner scanner = getScannerWithText("H");
		ConsoleTelephone telephone = new ConsoleTelephone(scanner);
		
		telephone.run(mockConnection);
		
		verify(mockConnection).hangup();
	}
	
	@Test
	public void deberiaDarElValorDeFalseAMore() {		
		Scanner scanner = getScannerWithText("Q");
		ConsoleTelephone telephone = new ConsoleTelephone(scanner);
		
		telephone.run(mockConnection);
		
		verify(mockConnection, never()).hangup();
		verify(mockConnection, never()).dial(anyString());
		verify(mockConnection, never()).record(anyString());
	}
	
	@Test
	public void deberiaEjecutarElMetodoDialDeConnection() {		
		Scanner scanner = getScannerWithText("1");
		ConsoleTelephone telephone = new ConsoleTelephone(scanner);
		
		telephone.run(mockConnection);
		
		verify(mockConnection).dial(anyString());
	}
	
	@Test
	public void deberiaEjecutarElMetodoRecordDeConnection() {
		Scanner scanner = getScannerWithText("X");
		ConsoleTelephone telephone = new ConsoleTelephone(scanner);
		
		telephone.run(mockConnection);
		
		verify(mockConnection).record(anyString());
	}
	
	@Test
	public void deberiaEjecutarElMetodoDialDeConnectionConNumeral() {
		Scanner scanner = getScannerWithText("#");
		ConsoleTelephone telephone = new ConsoleTelephone(scanner);
		
		telephone.run(mockConnection);
		
		verify(mockConnection).dial(anyString());
	}
	
	@Test
	public void deberiaEjecutarElMetodoRecordDeConnectionCon2Numeros() {
		Scanner scanner = getScannerWithText("12");
		ConsoleTelephone telephone = new ConsoleTelephone(scanner);
		
		telephone.run(mockConnection);
		
		verify(mockConnection).record(anyString());
	}
	
	@Test
	public void deberiaDarElValorDeNullAInput() {
		mockConsoleTelephone.run(mockConnection);
		
		verify(mockConnection, never()).hangup();
		verify(mockConnection, never()).dial(anyString());
		verify(mockConnection, never()).record(anyString());
	}
	
	private Scanner getScannerWithText(String text) {
		text += "\nQ";
		InputStream inputStream = new ByteArrayInputStream(text.getBytes());
		System.setIn(inputStream);
		
		return new Scanner(System.in);
	}
	
}
