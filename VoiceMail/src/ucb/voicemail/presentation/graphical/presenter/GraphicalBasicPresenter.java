package ucb.voicemail.presentation.graphical.presenter;

import ucb.voicemail.presentation.graphical.view.GraphicalView;
import ucb.voicemail.presentation.graphical.viewmodel.GraphicalViewModel;
import ucb.voicemail.presentation.presenter.BasicPresenter;

public class GraphicalBasicPresenter implements BasicPresenter {
    
    private GraphicalView view;
    private static final String MAILBOX_MENU = "Enter 1 to listen to your messages\r\n" + 
            "Enter 2 to change your passcode\r\n" + 
            "Enter 3 to change your greeting";
    private static final String MESSAGE_MENU = "Enter 1 to listen to the current message\r\n" + 
            "Enter 2 to save the current message\r\n" + 
            "Enter 3 to delete the current message\r\n" + 
            "Enter 4 to return to the main menu";
    private static final String INITIAL_PROMPT = "Enter mailbox number followed by #";
    private static final String GREETING_FORM = "Record your greeting, then press the # key";
    private static final String PASSCODE_FORM = "Enter new passcode followed by the # key";
    
    public GraphicalBasicPresenter(GraphicalView view) {
        this.view = view;
    }
    
    @Override
    public void displayPasscodeForm() {
        GraphicalViewModel model = new GraphicalViewModel();
        model.setText(PASSCODE_FORM);
        view.changeMainLabel(model);
    }

    @Override
    public void displayGreetingForm() {
        GraphicalViewModel model = new GraphicalViewModel();
        model.setText(GREETING_FORM);
        view.changeMainLabel(model);
    }

    @Override
    public void displayInitialPrompt() {
        GraphicalViewModel model = new GraphicalViewModel();
        model.setText(INITIAL_PROMPT);
        view.changeMainLabel(model);
    }

    @Override
    public void displayMailboxMenu() {
        GraphicalViewModel model = new GraphicalViewModel();
        model.setText(MAILBOX_MENU);
        view.changeMainLabel(model);
    }

    @Override
    public void displayMessageMenu() {
        GraphicalViewModel model = new GraphicalViewModel();
        model.setText(MESSAGE_MENU);
        view.changeMainLabel(model);
    }
}
