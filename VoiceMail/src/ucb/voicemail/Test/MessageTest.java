package ucb.voicemail.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import ucb.voicemail.Class.Message;

public class MessageTest {
	
	Message message;
	String text;
	
	@Before
	public void init(){
		text = "Hola soy mensaje";
		message = new Message(text);
	}

	@Test
    public void deberiaRetornarElMensaje() {
		assertEquals(text, message.getText());
    }

}
