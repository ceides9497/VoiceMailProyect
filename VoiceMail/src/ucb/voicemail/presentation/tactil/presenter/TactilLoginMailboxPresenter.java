package ucb.voicemail.presentation.tactil.presenter;

import ucb.voicemail.domain.boundary.output.LoginMailboxPresenter;
import ucb.voicemail.domain.dto.response.LoginMailboxResponse;
import ucb.voicemail.presentation.tactil.view.TactilMailboxMenuView;
import ucb.voicemail.presentation.tactil.view.TactilMessageView;
import ucb.voicemail.presentation.tactil.view.TactilShowGreetingView;
import ucb.voicemail.presentation.tactil.viewmodel.MailboxMenuViewModel;
import ucb.voicemail.presentation.tactil.viewmodel.MessageViewModel;

public class TactilLoginMailboxPresenter implements LoginMailboxPresenter {

    private TactilMailboxMenuView mailboxMenuView;
    private TactilMessageView messageView;
    private TactilShowGreetingView showGreetingview;
    
    public TactilLoginMailboxPresenter(TactilMailboxMenuView mailboxMenuView, TactilMessageView messageView, TactilShowGreetingView showGreetingview) {
        this.mailboxMenuView = mailboxMenuView;
        this.messageView = messageView;
        this.showGreetingview = showGreetingview;
    }

    @Override
    public void displayMailboxMenu(LoginMailboxResponse response) {
        MailboxMenuViewModel model = new MailboxMenuViewModel();
        model.setChangeGreetingName("Cambiar saludo");
        model.setChangePasscodeName("Cambiar contrase�a");
        model.setOpenMessageMenuName("Menu de mensajes");
        model.setQuitName("Salir");
        showGreetingview.hideWiew();
        mailboxMenuView.display(model);
    }

    @Override
    public void displayLoginFailed() {
        MessageViewModel model = new MessageViewModel();
        model.setButtonAcceptName("Aceptar");
        model.setText("Contrase�a incorrecta");
        messageView.display(model);
    }
    
}
