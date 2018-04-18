package ucb.voicemail.Test;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Test;

import ucb.voicemail.Class.Connection;
import ucb.voicemail.Class.Telephone;

public class TelephoneTest {
	private Telephone telephone;
	private Connection connection;
	
	@Test
	public void deberiaMostrarTestRunning() {
		telephone = new Telephone(new Scanner(System.in));
		
		PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        telephone.updateInterface("TestRunning");
        verify(out).println("TestRunning");
	}
	
	@Test
	public void deberiaEjecutarseHanupDeConnection() {
		Scanner scanner = getScannerWithText("H");
		Connection mockConnection = mock(Connection.class);
		Telephone telephone = new Telephone(scanner);
		
		doNothing().when(mockConnection).hangup();
		
		telephone.run(mockConnection);
		
		verify(mockConnection).hangup();
	}
	
	@Test
	public void deberiaDarElValorDeFalseAMore() {		
		Scanner scanner = getScannerWithText("Q");
		Connection mockConnection = mock(Connection.class);
		Telephone telephone = new Telephone(scanner);
		
		telephone.run(mockConnection);
	}
	
	@Test
	public void deberiaEjecutarElMetodoDialDeConnection() {		
		Scanner scanner = getScannerWithText("1");
		Connection mockConnection = mock(Connection.class);
		Telephone telephone = new Telephone(scanner);
		
		doNothing().when(mockConnection).dial(any(String.class));
		
		telephone.run(mockConnection);
		
		verify(mockConnection).dial(any(String.class));
	}
	
	@Test
	public void deberiaEjecutarElMetodoRecordDeConnection() {
		Scanner scanner = getScannerWithText("ZR");
		Connection mockConnection = mock(Connection.class);
		Telephone telephone = new Telephone(scanner);
		
		doNothing().when(mockConnection).record(any(String.class));
		
		telephone.run(mockConnection);
		
		verify(mockConnection).record(any(String.class));
	}
	
	@Test
	public void deberiaEjecutarElMetodoDialDeConnectionConNumeral() {
		Scanner scanner = getScannerWithText("#");
		Connection mockConnection = mock(Connection.class);
		Telephone telephone = new Telephone(scanner);
		
		doNothing().when(mockConnection).dial(any(String.class));
		
		telephone.run(mockConnection);
		
		verify(mockConnection).dial(any(String.class));
	}
	
	@Test
	public void deberiaEjecutarElMetodoRecordDeConnectionCon2Numeros() {
		Scanner scanner = getScannerWithText("12");
		Connection mockConnection = mock(Connection.class);
		Telephone telephone = new Telephone(scanner);
		
		doNothing().when(mockConnection).record(any(String.class));
		
		telephone.run(mockConnection);
		
		verify(mockConnection).record(any(String.class));
	}
	
	private Scanner getScannerWithText(String text) {
		text += "\nQ";
		InputStream inputStream = new ByteArrayInputStream(text.getBytes());
		System.setIn(inputStream);
		return new Scanner(System.in);
	}
}
