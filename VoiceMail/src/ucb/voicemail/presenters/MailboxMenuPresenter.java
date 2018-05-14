package ucb.voicemail.presenters;

import ucb.voicemail.main.MenuPresenter;

public class MailboxMenuPresenter implements MenuPresenter {
	private OptionsList optionsList;
	
	public MailboxMenuPresenter() {
		optionsList = new OptionsList();
	}
	
	public void addOption(String option) {
		optionsList.add(option);
	}
	
	@Override
	public String getMenu() {
		String menu = "";
		int optionsListSize = optionsList.size();
		
		for (int index = 0; index < optionsListSize; index++) {
			menu += "Enter ";
			menu += (index) + 1;
			menu += " to ";
			menu += optionsList.get(index);
			menu += "\n";
		}
		
		return menu;
	}
}
