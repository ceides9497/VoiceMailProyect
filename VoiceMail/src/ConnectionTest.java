import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

class ConnectionTest {
	private Connection connection;
	private MailSystem mailsystem;
	private Telephone telephone;
	
	@Test
	public void deberiaEjecutarseElMetodoSpeakDeTelephoneAlCrearUnConnection() {
		mailsystem = mock(MailSystem.class);
		telephone = mock(Telephone.class);
		
		doNothing().when(telephone).speak(isA(String.class));
		
		telephone.speak("mensaje inicial");
		
		connection = new Connection(mailsystem, telephone);
		
		verify(telephone).speak("mensaje inicial");
	}
}
