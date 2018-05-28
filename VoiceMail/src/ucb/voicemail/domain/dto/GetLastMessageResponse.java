package ucb.voicemail.domain.dto;

public class GetLastMessageResponse {

	private String ext;
	private String message;
	private boolean isFounded;
	
	public String getExt() {
		return ext;
	}
	
	public void setExt(String ext) {
		this.ext = ext;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public boolean isFounded() {
		return isFounded;
	}
	
	public void setFounded(boolean isFounded) {
		this.isFounded = isFounded;
	}
	
}
