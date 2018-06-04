package ucb.voicemail.presentation.console.presenter;

import ucb.voicemail.domain.boundary.output.LoginMailboxInteractorOutput;
import ucb.voicemail.domain.dto.response.LoginMailboxResponse;
import ucb.voicemail.presentation.console.view.ConsoleView;
import ucb.voicemail.presentation.console.viewmodel.ConsoleViewModel;

public class ConsoleLoginMailboxPresenter implements LoginMailboxInteractorOutput {

    private ConsoleView view;
    private static final String MAILBOX_MENU = "Enter 1 to listen to your messages\r\n" + 
            "Enter 2 to change your passcode\r\n" + 
            "Enter 3 to change your greeting";
    private static final String INCORRECT_MAILBOX_PASSCODE = "Incorrect passcode. Try again!";
    
    public ConsoleLoginMailboxPresenter(ConsoleView view) {
        this.view = view;
    }
    
    @Override
    public void displayMailboxMenu(LoginMailboxResponse response) {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText(MAILBOX_MENU);
        view.display(model);
    }

    @Override
    public void displayLoginFailed() {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText(INCORRECT_MAILBOX_PASSCODE);
        view.display(model);
    }
    
}
