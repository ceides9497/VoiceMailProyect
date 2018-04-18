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
		String input = "Q";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
		
		telephone = new Telephone(new Scanner(System.in));
		
		connection = mock(Connection.class);
		
		telephone.run(connection);
	}
	
	@Test
	public void deberiaEjecutarElMetodoDialDeConnection() {		
		String input = "1";
	    InputStream in = new ByteArrayInputStream(input.getBytes());
	    System.setIn(in);
		
		telephone = new Telephone(new Scanner(System.in));
		
		connection = mock(Connection.class);
		
		doNothing().when(connection).dial(isA(String.class));
	}
	
	private Scanner getScannerWithText(String text) {
		text += "\nQ";
		InputStream inputStream = new ByteArrayInputStream(text.getBytes());
		System.setIn(inputStream);
		return new Scanner(System.in);
	}
}
