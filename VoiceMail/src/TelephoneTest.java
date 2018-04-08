import static org.mockito.Mockito.*;

import java.io.PrintStream;

import org.junit.Test;

public class TelephoneTest {
	private Telephone telephone;
	
	@Test
	public void deberiaMostrarTestRunning() {
		telephone = new Telephone(null);
		
		PrintStream out = mock(PrintStream.class);
        System.setOut(out);
        telephone.updateInterface("TestRunning");
        verify(out).println("TestRunning");
	}

}
