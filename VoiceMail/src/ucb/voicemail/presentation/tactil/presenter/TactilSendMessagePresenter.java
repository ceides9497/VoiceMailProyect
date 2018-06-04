package ucb.voicemail.presentation.tactil.presenter;

import ucb.voicemail.domain.boundary.output.SendMessageInteractorOutput;
import ucb.voicemail.domain.dto.response.SendMessageResponse;
import ucb.voicemail.presentation.tactil.view.TactilInitialPromptView;
import ucb.voicemail.presentation.tactil.view.TactilShowGreetingView;
import ucb.voicemail.presentation.tactil.viewmodel.InitialPromptViewModel;

public class TactilSendMessagePresenter implements SendMessageInteractorOutput {

    private TactilInitialPromptView initialPromptView;
    private TactilShowGreetingView showGreetingView;
    
    public TactilSendMessagePresenter(TactilInitialPromptView initialPromptView, TactilShowGreetingView showGreetingView) {
        this.initialPromptView = initialPromptView;
        this.showGreetingView = showGreetingView;
    }

    @Override
    public void displayConfirmSendMessage(SendMessageResponse response) {
        InitialPromptViewModel model = new InitialPromptViewModel();
        model.setConnectButtonText("Conectarse");
        model.setInitialPromptMessage("Introduce el numero de mailbox");
        model.setQuitButtonText("Salir");
        model.setWindowTitle("Menu principal");
        showGreetingView.hideWiew();
        initialPromptView.display(model);
    }
    
}
