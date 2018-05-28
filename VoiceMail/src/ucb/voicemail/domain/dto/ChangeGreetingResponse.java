package ucb.voicemail.domain.dto;

public class ChangeGreetingResponse {

	private boolean status;
	private String greeting;
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getGreeting() {
		return greeting;
	}
	
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	
}
