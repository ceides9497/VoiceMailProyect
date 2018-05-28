package ucb.voicemail.domain.dto;

public class SendMessageResponse {

	private boolean status;
	private String message;
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
