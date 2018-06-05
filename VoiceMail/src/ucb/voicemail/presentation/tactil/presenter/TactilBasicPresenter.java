package ucb.voicemail.presentation.tactil.presenter;

import ucb.voicemail.presentation.presenter.BasicPresenter;
import ucb.voicemail.presentation.tactil.view.TactilChangeGreetingView;
import ucb.voicemail.presentation.tactil.view.TactilChangePasscodeView;
import ucb.voicemail.presentation.tactil.view.TactilInitialPromptView;
import ucb.voicemail.presentation.tactil.view.TactilMailboxMenuView;
import ucb.voicemail.presentation.tactil.view.TactilMessageMenuView;
import ucb.voicemail.presentation.tactil.viewmodel.ChangeGreetingViewModel;
import ucb.voicemail.presentation.tactil.viewmodel.ChangePasscodeViewModel;
import ucb.voicemail.presentation.tactil.viewmodel.InitialPromptViewModel;
import ucb.voicemail.presentation.tactil.viewmodel.MailboxMenuViewModel;
import ucb.voicemail.presentation.tactil.viewmodel.MessageMenuViewModel;

public class TactilBasicPresenter implements BasicPresenter {

    private TactilChangePasscodeView changePasscodeView;
    private TactilChangeGreetingView changeGreetingView;
    private TactilInitialPromptView initialPromptView;
    private TactilMailboxMenuView mailboxMenuView;
    private TactilMessageMenuView messageMenuView;
    
    public TactilBasicPresenter(
        TactilChangePasscodeView changePasscodeView, 
        TactilChangeGreetingView changeGreetingView,
        TactilInitialPromptView initialPromptView,
        TactilMailboxMenuView mailboxMenuView,
        TactilMessageMenuView messageMenuView
        ) {
        this.changePasscodeView = changePasscodeView;
        this.changeGreetingView = changeGreetingView;
        this.initialPromptView = initialPromptView;
        this.mailboxMenuView = mailboxMenuView;
        this.messageMenuView = messageMenuView;
    }
    
    @Override
    public void displayPasscodeForm() {
        ChangePasscodeViewModel model = new ChangePasscodeViewModel();
        model.setButtonAcceptName("Aceptar");
        model.setButtonCancelName("Cancelar");
        model.setTitle("Introduce una nueva contrase�a");
        mailboxMenuView.hideWiew();
        changePasscodeView.display(model);
    }

    @Override
    public void displayGreetingForm() {
        ChangeGreetingViewModel model = new ChangeGreetingViewModel();
        model.setButtonAcceptName("Aceptar");
        model.setButtonCancelName("Cancelar");
        model.setTitle("Introduce un nuevo saludo");
        mailboxMenuView.hideWiew();
        changeGreetingView.display(model);
    }

    @Override
    public void displayInitialPrompt() {
        InitialPromptViewModel model = new InitialPromptViewModel();
        model.setConnectButtonText("Conectarse");
        model.setInitialPromptMessage("Introduce el numero de mailbox");
        model.setQuitButtonText("Salir");
        model.setWindowTitle("Menu principal");
        initialPromptView.display(model);
    }

    @Override
    public void displayMailboxMenu() {
        MailboxMenuViewModel model = new MailboxMenuViewModel();
        model.setChangeGreetingName("Cambiar saludo");
        model.setChangePasscodeName("Cambiar contrase�a");
        model.setOpenMessageMenuName("Menu de mensajes");
        model.setQuitName("Salir");
        messageMenuView.hideWiew();
        mailboxMenuView.display(model);
    }

    @Override
    public void displayMessageMenu() {
        MessageMenuViewModel model = new MessageMenuViewModel();
        model.setGetLastMessageName("Leer mensaje");
        model.setDeleteCurrentMessageName("Eliminar mensaje");
        model.setQuitName("Salir");
        model.setReturnName("Volver");
        model.setSaveCurrentMessageName("Guardar mensaje");
        mailboxMenuView.hideWiew();
        messageMenuView.display(model);
    }

}
