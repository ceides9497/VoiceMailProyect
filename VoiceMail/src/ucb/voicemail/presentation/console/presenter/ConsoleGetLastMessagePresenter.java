package ucb.voicemail.presentation.console.presenter;

import ucb.voicemail.domain.boundary.output.GetLastMessagePresenter;
import ucb.voicemail.domain.dto.response.GetLastMessageResponse;
import ucb.voicemail.presentation.console.view.ConsoleView;
import ucb.voicemail.presentation.console.viewmodel.ConsoleViewModel;

public class ConsoleGetLastMessagePresenter implements GetLastMessagePresenter {
    
    private ConsoleView view;
    private static final String MESSAGE_MENU = "Enter 1 to listen to the current message\r\n" + 
            "Enter 2 to save the current message\r\n" + 
            "Enter 3 to delete the current message\r\n" + 
            "Enter 4 to return to the main menu";
    
    public ConsoleGetLastMessagePresenter(ConsoleView view) {
        this.view = view;
    }
    
    @Override
    public void presentMessage(GetLastMessageResponse response) {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText(response.getMessage() + "\r\n" + MESSAGE_MENU);
        view.display(model);
    }

    @Override
    public void presentNotFoundMessage() {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText("No messages.\r\n" + MESSAGE_MENU);
        view.display(model);
    }
    
}
