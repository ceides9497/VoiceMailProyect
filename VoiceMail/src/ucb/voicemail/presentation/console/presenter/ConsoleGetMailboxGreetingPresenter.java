package ucb.voicemail.presentation.console.presenter;

import ucb.voicemail.domain.boundary.output.GetMailboxGreetingPresenter;
import ucb.voicemail.domain.dto.response.GetMailboxGreetingResponse;
import ucb.voicemail.presentation.console.view.ConsoleView;
import ucb.voicemail.presentation.console.viewmodel.ConsoleViewModel;

public class ConsoleGetMailboxGreetingPresenter implements GetMailboxGreetingPresenter {
    
    private ConsoleView view;
    private static final String MAILBOX_NUMBER_ERROR = "Incorrect mailbox number. Try again!";
    
    public ConsoleGetMailboxGreetingPresenter(ConsoleView view) {
        this.view = view;
    }
    
    @Override
    public void displayMailboxGreeting(GetMailboxGreetingResponse response) {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText(response.getGreeting());
        view.display(model);
    }

    @Override
    public void displayGreetingError() {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText(MAILBOX_NUMBER_ERROR);
        view.display(model);
    }
    
}
