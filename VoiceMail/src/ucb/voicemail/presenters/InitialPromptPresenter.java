package ucb.voicemail.presenters;

import ucb.voicemail.main.MenuPresenter;

public class InitialPromptPresenter implements MenuPresenter {
	
	@Override
	public String getMenu() {
		return "Enter mailbox number followed by #";
	}
}
