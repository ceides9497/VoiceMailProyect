package ucb.voicemail.domain.dto;

public class SendMessageRequest {

	private String ext;
	private String message;
	
	public void setExt(String ext) {
		this.ext = ext;
	}
	
	public String getExt() {
		return ext;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
