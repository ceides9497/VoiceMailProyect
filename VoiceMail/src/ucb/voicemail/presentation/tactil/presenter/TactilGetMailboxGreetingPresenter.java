package ucb.voicemail.presentation.tactil.presenter;

import ucb.voicemail.domain.boundary.output.GetMailboxGreetingInteractorOutput;
import ucb.voicemail.domain.dto.response.GetMailboxGreetingResponse;
import ucb.voicemail.presentation.tactil.view.TactilMessageView;
import ucb.voicemail.presentation.tactil.view.TactilShowGreetingView;
import ucb.voicemail.presentation.tactil.viewmodel.MessageViewModel;
import ucb.voicemail.presentation.tactil.viewmodel.ShowGreetingViewModel;

public class TactilGetMailboxGreetingPresenter implements GetMailboxGreetingInteractorOutput {

    private TactilShowGreetingView showGreetingView;
    private TactilMessageView messageView;
    
    public TactilGetMailboxGreetingPresenter(TactilShowGreetingView showGreetingView, TactilMessageView messageView) {
        this.showGreetingView = showGreetingView;
        this.messageView = messageView;
    }

    @Override
    public void displayMailboxGreeting(GetMailboxGreetingResponse response) {
        ShowGreetingViewModel model = new ShowGreetingViewModel();
        model.setDefaultMessageLoginMailbox("Introduce la contraseņa");
        model.setDefaultMessageSendMessage("Introduce su mensaje");
        model.setGreeting(response.getGreeting());
        model.setLoginButtonName("Login");
        model.setLoginMailboxAcceptButtonName("Aceptar");
        model.setLoginMailboxCancelButtonName("Cancelar");
        model.setQuitButtonName("Salir");
        model.setSendMessageAcceptButtonName("Enviar");
        model.setSendMessageButtonName("Enviar mensaje");
        model.setSendMessageCancelButtonName("Cancelar");
        showGreetingView.display(model);
    }

    @Override
    public void displayGreetingError() {
        MessageViewModel model = new MessageViewModel();
        model.setButtonAcceptName("Aceptar");
        model.setText("Numero de mailbox incorrecto");
        messageView.display(model);
    }
    
}
