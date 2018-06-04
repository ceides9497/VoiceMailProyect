package ucb.voicemail.presentation.graphical.presenter;

import ucb.voicemail.domain.boundary.output.ChangePasscodeInteractorOutput;
import ucb.voicemail.domain.dto.response.ChangePasscodeResponse;
import ucb.voicemail.presentation.graphical.view.GraphicalView;
import ucb.voicemail.presentation.graphical.viewmodel.GraphicalViewModel;

public class GraphicalChangePasscodePresenter implements ChangePasscodeInteractorOutput {
    
    private GraphicalView view;
    private static final String MAILBOX_MENU = "Enter 1 to listen to your messages\r\n" + 
            "Enter 2 to change your passcode\r\n" + 
            "Enter 3 to change your greeting";
    
    public GraphicalChangePasscodePresenter(GraphicalView view) {
        this.view = view;
    }

    @Override
    public void displayConfirmChangePasscode(ChangePasscodeResponse response) {
        GraphicalViewModel model = new GraphicalViewModel();
        model.setText(MAILBOX_MENU);
        view.changeMainLabel(model);
    }
    
}
