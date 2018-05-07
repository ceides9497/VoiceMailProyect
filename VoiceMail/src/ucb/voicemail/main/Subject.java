package ucb.voicemail.main;

import ucb.voicemail.main.Telephone;

public interface Subject {
	
	public void addUserInterface(Telephone userInterface);
	public void deleteUserInterface(Telephone userInterface);
	public void speakToAll(String output);
}
