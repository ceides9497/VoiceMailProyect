package ucb.voicemail.presentation.tactil.presenter;

import ucb.voicemail.domain.boundary.output.ChangePasscodePresenter;
import ucb.voicemail.domain.dto.response.ChangePasscodeResponse;
import ucb.voicemail.presentation.tactil.view.TactilMailboxMenuView;
import ucb.voicemail.presentation.tactil.viewmodel.MailboxMenuViewModel;

public class TactilChangePasscodePresenter implements ChangePasscodePresenter {

    private TactilMailboxMenuView mailboxMenuView;
    
    public TactilChangePasscodePresenter(TactilMailboxMenuView mailboxMenuView) {
        this.mailboxMenuView = mailboxMenuView;
    }

    @Override
    public void displayConfirmChangePasscode(ChangePasscodeResponse response) {
        MailboxMenuViewModel model = new MailboxMenuViewModel();
        model.setChangeGreetingName("Cambiar saludo");
        model.setChangePasscodeName("Cambiar contrase�a");
        model.setOpenMessageMenuName("Menu de mensajes");
        model.setQuitName("Salir");
        mailboxMenuView.display(model);
    }
    
}
