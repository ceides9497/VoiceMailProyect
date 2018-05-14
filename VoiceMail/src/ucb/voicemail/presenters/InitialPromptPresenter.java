package ucb.voicemail.presenters;

import ucb.voicemail.domain.MenuPresenter;

public class InitialPromptPresenter implements MenuPresenter {
	
	@Override
	public String getMenu() {
		return "Enter mailbox number followed by #";
	}
}
