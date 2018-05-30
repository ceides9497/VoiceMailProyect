package ucb.voicemail.presentation.presenter;

import ucb.voicemail.domain.boundary.output.ChangeGreetingInteractorOutput;
import ucb.voicemail.domain.boundary.output.ChangePasscodeInteractorOutput;
import ucb.voicemail.domain.boundary.output.DeleteCurrentMessageInteractorOutput;
import ucb.voicemail.domain.boundary.output.GetLastMessageInteractorOutput;
import ucb.voicemail.domain.boundary.output.GetMailboxGreetingInteractorOutput;
import ucb.voicemail.domain.boundary.output.LoginMailboxInteractorOutput;
import ucb.voicemail.domain.boundary.output.SaveCurrentMessageInteractorOutput;
import ucb.voicemail.domain.boundary.output.SendMessageInteractorOutput;
import ucb.voicemail.domain.dto.response.ChangeGreetingResponse;
import ucb.voicemail.domain.dto.response.ChangePasscodeResponse;
import ucb.voicemail.domain.dto.response.DeleteCurrentMessageResponse;
import ucb.voicemail.domain.dto.response.GetLastMessageResponse;
import ucb.voicemail.domain.dto.response.GetMailboxGreetingResponse;
import ucb.voicemail.domain.dto.response.LoginMailboxResponse;
import ucb.voicemail.domain.dto.response.SaveCurrentMessageResponse;
import ucb.voicemail.domain.dto.response.SendMessageResponse;
import ucb.voicemail.presentation.view.ConsoleView;
import ucb.voicemail.presentation.viewmodel.ConsoleViewModel;

public class ConsolePresenter implements 
    ChangeGreetingInteractorOutput, 
    ChangePasscodeInteractorOutput,
    DeleteCurrentMessageInteractorOutput,
    GetLastMessageInteractorOutput,
    GetMailboxGreetingInteractorOutput,
    LoginMailboxInteractorOutput,
    SaveCurrentMessageInteractorOutput,
    SendMessageInteractorOutput {

    private ConsoleView view;

    private static final String MAILBOX_MENU = "Enter 1 to listen to your messages\r\n" + 
            "Enter 2 to change your passcode\r\n" + 
            "Enter 3 to change your greeting";
    
    private static final String MESSAGE_MENU = "Enter 1 to listen to the current message\r\n" + 
            "Enter 2 to save the current message\r\n" + 
            "Enter 3 to delete the current message\r\n" + 
            "Enter 4 to return to the main menu";
    
    private static final String MAILBOX_NUMBER_ERROR = "Incorrect mailbox number. Try again!";
    
    private static final String INCORRECT_MAILBOX_PASSCODE = "Incorrect passcode. Try again!";
    
    private static final String INITIAL_PROMPT = "Enter mailbox number followed by #";
    
    // =============================================================================
    
    @Override
    public void displayConfirmChangeGreeting(ChangeGreetingResponse response) {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText(MAILBOX_MENU);
        view.display(model);
    }
    
    // =============================================================================
    
    @Override
    public void displayConfirmChangePasscode(ChangePasscodeResponse response) {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText(MAILBOX_MENU);
        view.display(model);
    }
    
    // =============================================================================

    @Override
    public void displayConfirmDeleteCurrentMessage(DeleteCurrentMessageResponse response) {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText(MESSAGE_MENU);
        view.display(model);
    }
    
    // =============================================================================

    @Override
    public void presentMessage(GetLastMessageResponse response) {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText(response.getMessage() + "\r\n" + MESSAGE_MENU);
        view.display(model);
    }

    @Override
    public void presentNotFoundMessage() {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText("No messages.\r\n" + MESSAGE_MENU);
        view.display(model);
    }
    
    // =============================================================================

    @Override
    public void displayMailboxGreeting(GetMailboxGreetingResponse response) {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText(response.getGreeting());
        view.display(model);
    }

    @Override
    public void displayGreetingError() {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText(MAILBOX_NUMBER_ERROR);
        view.display(model);
    }
    
    // =============================================================================

    @Override
    public void displayMailboxMenu(LoginMailboxResponse response) {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText(MAILBOX_MENU);
        view.display(model);
    }

    @Override
    public void displayLoginFailed() {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText(INCORRECT_MAILBOX_PASSCODE);
        view.display(model);
    }
    
    // =============================================================================

    @Override
    public void displayConfirmSaveCurrentMessage(SaveCurrentMessageResponse response) {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText(MESSAGE_MENU);
        view.display(model);
    }
    
    // =============================================================================

    @Override
    public void displayConfirmSendMessage(SendMessageResponse response) {
        ConsoleViewModel model = new ConsoleViewModel();
        model.setText(INITIAL_PROMPT);
        view.display(model);
    }

}
