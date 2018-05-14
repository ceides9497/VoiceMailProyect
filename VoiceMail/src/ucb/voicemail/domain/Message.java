package ucb.voicemail.domain;

public class Message {
	
	private String text;
	
	public Message(String messageText) {
		text = messageText;
	}

	public String getText() {
		return text;
	}
	
}
