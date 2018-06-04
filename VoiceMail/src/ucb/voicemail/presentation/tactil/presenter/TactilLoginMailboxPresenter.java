package ucb.voicemail.presentation.tactil.presenter;

import ucb.voicemail.domain.boundary.output.LoginMailboxInteractorOutput;
import ucb.voicemail.domain.dto.response.LoginMailboxResponse;
import ucb.voicemail.presentation.tactil.view.TactilMailboxMenuView;
import ucb.voicemail.presentation.tactil.view.TactilMessageView;
import ucb.voicemail.presentation.tactil.viewmodel.MailboxMenuViewModel;
import ucb.voicemail.presentation.tactil.viewmodel.MessageViewModel;

public class TactilLoginMailboxPresenter implements LoginMailboxInteractorOutput {

    private TactilMailboxMenuView mailboxMenuView;
    private TactilMessageView messageView;
    
    public TactilLoginMailboxPresenter(TactilMailboxMenuView mailboxMenuView, TactilMessageView messageView) {
        this.mailboxMenuView = mailboxMenuView;
        this.messageView = messageView;
    }

    @Override
    public void displayMailboxMenu(LoginMailboxResponse response) {
        MailboxMenuViewModel model = new MailboxMenuViewModel();
        model.setChangeGreetingName("Cambiar saludo");
        model.setChangePasscodeName("Cambiar contraseña");
        model.setOpenMessageMenuName("Menu de mensajes");
        model.setQuitName("Salir");
        mailboxMenuView.display(model);
    }

    @Override
    public void displayLoginFailed() {
        MessageViewModel model = new MessageViewModel();
        model.setButtonAcceptName("Aceptar");
        model.setText("Contraseña incorrecta");
        messageView.display(model);
    }
    
}
