package ucb.voicemail.domain.dto;

public class GetMailboxGreetingResponse {

	private String ext;
	private String greeting;
	
	public void setExt(String ext) {
		this.ext = ext;
	}
	
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	
	public String getExt() {
		return ext;
	}
	
	public String getGreeting() {
		return greeting;
	}
}
