package ucb.voicemail.presentation.tactil.viewmodel;

public class ShowGreetingViewModel {
    
    private String loginButtonName;
    private String sendMessageButtonName;
    private String quitButtonName;
    private String greeting;
    
    private String defaultMessageLoginMailbox;
    private String loginMailboxAcceptButtonName;
    private String loginMailboxCancelButtonName;
    
    private String defaultMessageSendMessage;
    private String sendMessageAcceptButtonName;
    private String sendMessageCancelButtonName;
    
    public String getLoginButtonName() {
        return loginButtonName;
    }
    
    public void setLoginButtonName(String loginButtonName) {
        this.loginButtonName = loginButtonName;
    }
    
    public String getSendMessageButtonName() {
        return sendMessageButtonName;
    }
    
    public void setSendMessageButtonName(String sendMessageButtonName) {
        this.sendMessageButtonName = sendMessageButtonName;
    }
    
    public String getQuitButtonName() {
        return quitButtonName;
    }
    
    public void setQuitButtonName(String quitButtonName) {
        this.quitButtonName = quitButtonName;
    }
    
    public String getGreeting() {
        return greeting;
    }
    
    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }
    
    public String getDefaultMessageLoginMailbox() {
        return defaultMessageLoginMailbox;
    }
    
    public void setDefaultMessageLoginMailbox(String defaultMessageLoginMailbox) {
        this.defaultMessageLoginMailbox = defaultMessageLoginMailbox;
    }
    
    public String getLoginMailboxAcceptButtonName() {
        return loginMailboxAcceptButtonName;
    }
    
    public void setLoginMailboxAcceptButtonName(String loginMailboxAcceptButtonName) {
        this.loginMailboxAcceptButtonName = loginMailboxAcceptButtonName;
    }
    
    public String getLoginMailboxCancelButtonName() {
        return loginMailboxCancelButtonName;
    }
    
    public void setLoginMailboxCancelButtonName(String loginMailboxCancelButtonName) {
        this.loginMailboxCancelButtonName = loginMailboxCancelButtonName;
    }
    
    public String getDefaultMessageSendMessage() {
        return defaultMessageSendMessage;
    }
    
    public void setDefaultMessageSendMessage(String defaultMessageSendMessage) {
        this.defaultMessageSendMessage = defaultMessageSendMessage;
    }
    
    public String getSendMessageAcceptButtonName() {
        return sendMessageAcceptButtonName;
    }
    
    public void setSendMessageAcceptButtonName(String sendMessageAcceptButtonName) {
        this.sendMessageAcceptButtonName = sendMessageAcceptButtonName;
    }
    
    public String getSendMessageCancelButtonName() {
        return sendMessageCancelButtonName;
    }
    
    public void setSendMessageCancelButtonName(String sendMessageCancelButtonName) {
        this.sendMessageCancelButtonName = sendMessageCancelButtonName;
    }
    
}
