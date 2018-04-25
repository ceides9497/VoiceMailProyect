package ucb.voicemail.main;

public class Message
{
	private String text;
	
	public Message(String messageText) {
		text = messageText;
	}

	public String getText() {
		return text;
	}
}
