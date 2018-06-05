package ucb.voicemail.presentation.tactil.presenter;

import ucb.voicemail.domain.boundary.output.GetLastMessagePresenter;
import ucb.voicemail.domain.dto.response.GetLastMessageResponse;
import ucb.voicemail.presentation.tactil.view.TactilMessageView;
import ucb.voicemail.presentation.tactil.viewmodel.MessageViewModel;

public class TactilGetLastMessagePresenter implements GetLastMessagePresenter {

    private TactilMessageView messageView;
    
    public TactilGetLastMessagePresenter(TactilMessageView messageView) {
        this.messageView = messageView;
    }

    @Override
    public void presentMessage(GetLastMessageResponse response) {
        MessageViewModel model = new MessageViewModel();
        model.setButtonAcceptName("Aceptar");
        model.setText(response.getMessage());
        messageView.display(model);
    }

    @Override
    public void presentNotFoundMessage() {
        MessageViewModel model = new MessageViewModel();
        model.setButtonAcceptName("Aceptar");
        model.setText("Sin mensajes.");
        messageView.display(model);
    }
    
}
