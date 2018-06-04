package ucb.voicemail.presentation.console.presenter;

import ucb.voicemail.domain.boundary.output.DeleteCurrentMessagePresenter;
import ucb.voicemail.domain.dto.response.DeleteCurrentMessageResponse;
import ucb.voicemail.presentation.console.view.ConsoleView;
import ucb.voicemail.presentation.console.viewmodel.ConsoleViewModel;

public class ConsoleDeleteCurrentMessagePresenter implements DeleteCurrentMessagePresenter {

    private ConsoleView view;
    private static final String MESSAGE_MENU = "Enter 1 to listen to the current message\r\n" + 
            "Enter 2 to save the current message\r\n" + 
            "Enter 3 to delete the current message\r\n" + 
            "Enter 4 to return to the main menu";
    
    public ConsoleDeleteCurrentMessagePresenter(ConsoleView view) {
        this.view = view;
    }
    
    @Override
    public void displayConfirmDeleteCurrentMessage(DeleteCurrentMessageResponse response) {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText(MESSAGE_MENU);
        view.display(model);
    }
    
}
