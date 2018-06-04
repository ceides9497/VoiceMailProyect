package ucb.voicemail.presentation.console.presenter;

import ucb.voicemail.presentation.console.view.ConsoleView;
import ucb.voicemail.presentation.console.viewmodel.ConsoleViewModel;
import ucb.voicemail.presentation.presenter.BasicPresenter;

public class ConsoleBasicPresenter implements BasicPresenter {

    private ConsoleView view;

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
    
    public ConsoleBasicPresenter(ConsoleView view) {
        this.view = view;
    }
    
    @Override
    public void displayPasscodeForm() {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText(PASSCODE_FORM);
        view.display(model);
    }

    @Override
    public void displayGreetingForm() {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText(GREETING_FORM);
        view.display(model);
    }

    @Override
    public void displayInitialPrompt() {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText(INITIAL_PROMPT);
        view.display(model);
    }

    @Override
    public void displayMailboxMenu() {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText(MAILBOX_MENU);
        view.display(model);
    }

    @Override
    public void displayMessageMenu() {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText(MESSAGE_MENU);
        view.display(model);
    }
    
}
