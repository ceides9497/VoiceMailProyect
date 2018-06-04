package ucb.voicemail.presentation.tactil.presenter;

import ucb.voicemail.domain.boundary.output.ChangeGreetingInteractorOutput;
import ucb.voicemail.domain.dto.response.ChangeGreetingResponse;
import ucb.voicemail.presentation.tactil.view.TactilMailboxMenuView;
import ucb.voicemail.presentation.tactil.viewmodel.MailboxMenuViewModel;

public class TactilChangeGreetingPresenter implements ChangeGreetingInteractorOutput {

    private TactilMailboxMenuView mailboxMenuView;
    
    public TactilChangeGreetingPresenter(TactilMailboxMenuView mailboxMenuView) {
        this.mailboxMenuView = mailboxMenuView;
    }
    
    @Override
    public void displayConfirmChangeGreeting(ChangeGreetingResponse response) {
        MailboxMenuViewModel model = new MailboxMenuViewModel();
        model.setChangeGreetingName("Cambiar saludo");
        model.setChangePasscodeName("Cambiar contraseña");
        model.setOpenMessageMenuName("Menu de mensajes");
        model.setQuitName("Salir");
        mailboxMenuView.display(model);
    }

}
