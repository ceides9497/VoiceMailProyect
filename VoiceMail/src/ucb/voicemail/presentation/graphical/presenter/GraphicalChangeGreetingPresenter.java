package ucb.voicemail.presentation.graphical.presenter;

import ucb.voicemail.domain.boundary.output.ChangeGreetingPresenter;
import ucb.voicemail.domain.dto.response.ChangeGreetingResponse;
import ucb.voicemail.presentation.graphical.view.GraphicalView;
import ucb.voicemail.presentation.graphical.viewmodel.GraphicalViewModel;

public class GraphicalChangeGreetingPresenter implements ChangeGreetingPresenter {
    
    private GraphicalView view;
    private static final String MAILBOX_MENU = "Enter 1 to listen to your messages\r\n" + 
            "Enter 2 to change your passcode\r\n" + 
            "Enter 3 to change your greeting";
    
    public GraphicalChangeGreetingPresenter(GraphicalView view) {
        this.view = view;
    }
    
    @Override
    public void displayConfirmChangeGreeting(ChangeGreetingResponse response) {
        GraphicalViewModel model = new GraphicalViewModel();
        model.setText(MAILBOX_MENU);
        view.changeMainLabel(model);
    }

}
