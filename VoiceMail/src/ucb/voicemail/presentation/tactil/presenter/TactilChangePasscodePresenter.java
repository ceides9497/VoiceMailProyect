package ucb.voicemail.presentation.tactil.presenter;

import ucb.voicemail.domain.boundary.output.ChangePasscodeInteractorOutput;
import ucb.voicemail.domain.dto.response.ChangePasscodeResponse;
import ucb.voicemail.presentation.tactil.view.TactilMailboxMenuView;
import ucb.voicemail.presentation.tactil.viewmodel.MailboxMenuViewModel;

public class TactilChangePasscodePresenter implements ChangePasscodeInteractorOutput {

    private TactilMailboxMenuView mailboxMenuView;
    
    public TactilChangePasscodePresenter(TactilMailboxMenuView mailboxMenuView) {
        this.mailboxMenuView = mailboxMenuView;
    }

    @Override
    public void displayConfirmChangePasscode(ChangePasscodeResponse response) {
        MailboxMenuViewModel model = new MailboxMenuViewModel();
        model.setChangeGreetingName("Cambiar saludo");
        model.setChangePasscodeName("Cambiar contraseña");
        model.setOpenMessageMenuName("Menu de mensajes");
        model.setQuitName("Salir");
        mailboxMenuView.display(model);
    }
    
}
