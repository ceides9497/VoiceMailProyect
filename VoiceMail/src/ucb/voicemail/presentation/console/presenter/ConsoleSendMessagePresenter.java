package ucb.voicemail.presentation.console.presenter;

import ucb.voicemail.domain.boundary.output.SendMessageInteractorOutput;
import ucb.voicemail.domain.dto.response.SendMessageResponse;
import ucb.voicemail.presentation.console.view.ConsoleView;
import ucb.voicemail.presentation.console.viewmodel.ConsoleViewModel;

public class ConsoleSendMessagePresenter implements SendMessageInteractorOutput {

    private ConsoleView view;
    private static final String INITIAL_PROMPT = "Enter mailbox number followed by #";
    
    public ConsoleSendMessagePresenter(ConsoleView view) {
        this.view = view;
    }
    
    @Override
    public void displayConfirmSendMessage(SendMessageResponse response) {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText(INITIAL_PROMPT);
        view.display(model);
    }
    
}
