package ucb.voicemail.presentation.console.presenter;

import ucb.voicemail.domain.boundary.output.ChangePasscodeInteractorOutput;
import ucb.voicemail.domain.dto.response.ChangePasscodeResponse;
import ucb.voicemail.presentation.console.view.ConsoleView;
import ucb.voicemail.presentation.console.viewmodel.ConsoleViewModel;

public class ConsoleChangePasscodePresenter implements ChangePasscodeInteractorOutput {
    
    private ConsoleView view;
    private static final String MAILBOX_MENU = "Enter 1 to listen to your messages\r\n" + 
            "Enter 2 to change your passcode\r\n" + 
            "Enter 3 to change your greeting";
    
    public ConsoleChangePasscodePresenter(ConsoleView view) {
        this.view = view;
    }

    @Override
    public void displayConfirmChangePasscode(ChangePasscodeResponse response) {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText(MAILBOX_MENU);
        view.display(model);
    }
    
}
