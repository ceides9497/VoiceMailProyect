package ucb.voicemail.presentation.graphical.presenter;

import ucb.voicemail.domain.boundary.output.SaveCurrentMessagePresenter;
import ucb.voicemail.domain.dto.response.SaveCurrentMessageResponse;
import ucb.voicemail.presentation.graphical.view.GraphicalView;
import ucb.voicemail.presentation.graphical.viewmodel.GraphicalViewModel;

public class GraphicalSaveCurrentMessagePresenter implements SaveCurrentMessagePresenter {

    private GraphicalView view;
    private static final String MESSAGE_MENU = "Enter 1 to listen to the current message\r\n" + 
            "Enter 2 to save the current message\r\n" + 
            "Enter 3 to delete the current message\r\n" + 
            "Enter 4 to return to the main menu";
    
    public GraphicalSaveCurrentMessagePresenter(GraphicalView view) {
        this.view = view;
    }
    
    @Override
    public void displayConfirmSaveCurrentMessage(SaveCurrentMessageResponse response) {
        GraphicalViewModel model = new GraphicalViewModel();
        model.setText(MESSAGE_MENU);
        view.changeMainLabel(model);
    }
    
}
