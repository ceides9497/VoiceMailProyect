package ucb.voicemail.presentation.graphical.presenter;

import ucb.voicemail.domain.boundary.output.SendMessagePresenter;
import ucb.voicemail.domain.dto.response.SendMessageResponse;
import ucb.voicemail.presentation.graphical.view.GraphicalView;
import ucb.voicemail.presentation.graphical.viewmodel.GraphicalViewModel;

public class GraphicalSendMessagePresenter implements SendMessagePresenter {

    private GraphicalView view;
    private static final String INITIAL_PROMPT = "Enter mailbox number followed by #";
    
    public GraphicalSendMessagePresenter(GraphicalView view) {
        this.view = view;
    }
    
    @Override
    public void displayConfirmSendMessage(SendMessageResponse response) {
        GraphicalViewModel model = new GraphicalViewModel();
        model.setText(INITIAL_PROMPT);
        view.changeMainLabel(model);
    }
    
}
