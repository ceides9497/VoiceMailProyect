package ucb.voicemail.presentation.tactil.presenter;

import ucb.voicemail.domain.boundary.output.SendMessageInteractorOutput;
import ucb.voicemail.domain.dto.response.SendMessageResponse;
import ucb.voicemail.presentation.tactil.view.TactilInitialPromptView;
import ucb.voicemail.presentation.tactil.viewmodel.InitialPromptViewModel;

public class TactilSendMessagePresenter implements SendMessageInteractorOutput {

    private TactilInitialPromptView initialPromptView;
    
    public TactilSendMessagePresenter(TactilInitialPromptView initialPromptView) {
        this.initialPromptView = initialPromptView;
    }

    @Override
    public void displayConfirmSendMessage(SendMessageResponse response) {
        InitialPromptViewModel model = new InitialPromptViewModel();
        model.setConnectButtonText("Conectarse");
        model.setInitialPromptMessage("Introduce el numero de mailbox");
        model.setQuitButtonText("Salir");
        model.setWindowTitle("Menu principal");
        initialPromptView.display(model);
    }
    
}
