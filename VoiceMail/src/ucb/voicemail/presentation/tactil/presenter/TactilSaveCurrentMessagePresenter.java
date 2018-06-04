package ucb.voicemail.presentation.tactil.presenter;

import ucb.voicemail.domain.boundary.output.SaveCurrentMessageInteractorOutput;
import ucb.voicemail.domain.dto.response.SaveCurrentMessageResponse;
import ucb.voicemail.presentation.tactil.view.TactilMessageView;
import ucb.voicemail.presentation.tactil.viewmodel.MessageViewModel;

public class TactilSaveCurrentMessagePresenter implements SaveCurrentMessageInteractorOutput{

    private TactilMessageView messageView;
    
    public TactilSaveCurrentMessagePresenter(TactilMessageView messageView) {
        this.messageView = messageView;
    }

    @Override
    public void displayConfirmSaveCurrentMessage(SaveCurrentMessageResponse response) {
        MessageViewModel model = new MessageViewModel();
        model.setButtonAcceptName("Aceptar");
        model.setText("Se guardo el mensaje");
        messageView.display(model);
    }
}
