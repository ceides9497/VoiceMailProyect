package ucb.voicemail.domain.dto.request;

public class ChangeGreetingRequest {

	private String ext;
	private String greeting;
	
	public String getExt() {
		return ext;
	}
	
	public void setExt(String ext) {
		this.ext = ext;
	}
	
	public String getGreeting() {
		return greeting;
	}
	
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	
}
