package ucb.voicemail.presentation.graphical.presenter;

import ucb.voicemail.domain.boundary.output.GetMailboxGreetingPresenter;
import ucb.voicemail.domain.dto.response.GetMailboxGreetingResponse;
import ucb.voicemail.presentation.graphical.view.GraphicalView;
import ucb.voicemail.presentation.graphical.viewmodel.GraphicalViewModel;

public class GraphicalGetMailboxGreetingPresenter implements GetMailboxGreetingPresenter {
    
    private GraphicalView view;
    private static final String MAILBOX_NUMBER_ERROR = "Incorrect mailbox number. Try again!";
    
    public GraphicalGetMailboxGreetingPresenter(GraphicalView view) {
        this.view = view;
    }
    
    @Override
    public void displayMailboxGreeting(GetMailboxGreetingResponse response) {
        GraphicalViewModel model = new GraphicalViewModel();
        model.setText(response.getGreeting());
        view.changeMainLabel(model);
    }

    @Override
    public void displayGreetingError() {
        GraphicalViewModel model = new GraphicalViewModel();
        model.setText(MAILBOX_NUMBER_ERROR);
        view.changeMainLabel(model);
    }
    
}
