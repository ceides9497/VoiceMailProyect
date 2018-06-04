package ucb.voicemail.presentation.graphical.presenter;

import ucb.voicemail.domain.boundary.output.GetLastMessagePresenter;
import ucb.voicemail.domain.dto.response.GetLastMessageResponse;
import ucb.voicemail.presentation.graphical.view.GraphicalView;
import ucb.voicemail.presentation.graphical.viewmodel.GraphicalViewModel;

public class GraphicalGetLastMessagePresenter implements GetLastMessagePresenter {
    
    private GraphicalView view;
    private static final String MESSAGE_MENU = "Enter 1 to listen to the current message\r\n" + 
            "Enter 2 to save the current message\r\n" + 
            "Enter 3 to delete the current message\r\n" + 
            "Enter 4 to return to the main menu";
    
    public GraphicalGetLastMessagePresenter(GraphicalView view) {
        this.view = view;
    }
    
    @Override
    public void presentMessage(GetLastMessageResponse response) {
        GraphicalViewModel model = new GraphicalViewModel();
        model.setText(response.getMessage() + "\r\n" + MESSAGE_MENU);
        view.changeMainLabel(model);
    }

    @Override
    public void presentNotFoundMessage() {
        GraphicalViewModel model = new GraphicalViewModel();
        model.setText("No messages.\r\n" + MESSAGE_MENU);
        view.changeMainLabel(model);
    }
    
}
