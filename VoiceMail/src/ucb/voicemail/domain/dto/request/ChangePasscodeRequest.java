package ucb.voicemail.domain.dto.request;

public class ChangePasscodeRequest {

	private String ext;
	private String passcode;
	
	public String getExt() {
		return ext;
	}
	
	public void setExt(String ext) {
		this.ext = ext;
	}
	
	public String getPasscode() {
		return passcode;
	}
	
	public void setPasscode(String passcode) {
		this.passcode = passcode;
	}
	
}
