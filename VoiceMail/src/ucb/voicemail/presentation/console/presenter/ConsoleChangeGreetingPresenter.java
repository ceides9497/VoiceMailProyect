package ucb.voicemail.presentation.console.presenter;

import ucb.voicemail.domain.boundary.output.ChangeGreetingInteractorOutput;
import ucb.voicemail.domain.dto.response.ChangeGreetingResponse;
import ucb.voicemail.presentation.console.view.ConsoleView;
import ucb.voicemail.presentation.console.viewmodel.ConsoleViewModel;

public class ConsoleChangeGreetingPresenter implements ChangeGreetingInteractorOutput {
    
    private ConsoleView view;
    private static final String MAILBOX_MENU = "Enter 1 to listen to your messages\r\n" + 
            "Enter 2 to change your passcode\r\n" + 
            "Enter 3 to change your greeting";
    
    public ConsoleChangeGreetingPresenter(ConsoleView view) {
        this.view = view;
    }
    
    @Override
    public void displayConfirmChangeGreeting(ChangeGreetingResponse response) {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText(MAILBOX_MENU);
        view.display(model);
    }

}
