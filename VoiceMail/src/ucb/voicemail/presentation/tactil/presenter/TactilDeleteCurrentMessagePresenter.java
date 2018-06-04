package ucb.voicemail.presentation.tactil.presenter;

import ucb.voicemail.domain.boundary.output.DeleteCurrentMessageInteractorOutput;
import ucb.voicemail.domain.dto.response.DeleteCurrentMessageResponse;
import ucb.voicemail.presentation.tactil.view.TactilMessageView;
import ucb.voicemail.presentation.tactil.viewmodel.MessageViewModel;

public class TactilDeleteCurrentMessagePresenter implements DeleteCurrentMessageInteractorOutput {

    private TactilMessageView messageView;
    
    public TactilDeleteCurrentMessagePresenter(TactilMessageView messageView) {
        this.messageView = messageView;
    }
    
    @Override
    public void displayConfirmDeleteCurrentMessage(DeleteCurrentMessageResponse response) {
        MessageViewModel model = new MessageViewModel();
        model.setButtonAcceptName("Aceptar");
        model.setText("Se elimino el mensaje");
        messageView.display(model);
    }
    
}
