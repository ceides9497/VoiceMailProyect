package ucb.voicemail.presentation.graphical.presenter;

import ucb.voicemail.domain.boundary.output.LoginMailboxPresenter;
import ucb.voicemail.domain.dto.response.LoginMailboxResponse;
import ucb.voicemail.presentation.graphical.view.GraphicalView;
import ucb.voicemail.presentation.graphical.viewmodel.GraphicalViewModel;

public class GraphicalLoginMailboxPresenter implements LoginMailboxPresenter {

    private GraphicalView view;
    private static final String MAILBOX_MENU = "Enter 1 to listen to your messages\r\n" + 
            "Enter 2 to change your passcode\r\n" + 
            "Enter 3 to change your greeting";
    private static final String INCORRECT_MAILBOX_PASSCODE = "Incorrect passcode. Try again!";
    
    public GraphicalLoginMailboxPresenter(GraphicalView view) {
        this.view = view;
    }
    
    @Override
    public void displayMailboxMenu(LoginMailboxResponse response) {
        GraphicalViewModel model = new GraphicalViewModel();
        model.setText(MAILBOX_MENU);
        view.changeMainLabel(model);
    }

    @Override
    public void displayLoginFailed() {
        GraphicalViewModel model = new GraphicalViewModel();
        model.setText(INCORRECT_MAILBOX_PASSCODE);
        view.changeMainLabel(model);
    }
    
}
