package ucb.voicemail.domain.dto;

public class DeleteCurrentMessageResponse {

	private boolean status;
	private String ext;
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public String getExt() {
		return ext;
	}
	
	public void setExt(String ext) {
		this.ext = ext;
	}
	
}
