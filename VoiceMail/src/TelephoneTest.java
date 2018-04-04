import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

import java.io.PrintStream;

class TelephoneTest {
	private Telephone telephone;
	
	@Test
	public void deberiaMostrarTestRunning() {
		telephone = new Telephone(null);
		
		PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        telephone.speak("TestRunning");
        verify(out).println("TestRunning");
	}

}
