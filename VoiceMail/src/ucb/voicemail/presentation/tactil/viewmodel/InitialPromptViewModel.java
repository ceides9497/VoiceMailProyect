package ucb.voicemail.presentation.tactil.viewmodel;

public class InitialPromptViewModel {

    private String initialPromptMessage;
    private String connectButtonText;
    private String quitButtonText;
    private String windowTitle;
    
    public String getInitialPromptMessage() {
        return initialPromptMessage;
    }
    
    public void setInitialPromptMessage(String initialPromptMessage) {
        this.initialPromptMessage = initialPromptMessage;
    }
    
    public String getConnectButtonText() {
        return connectButtonText;
    }
    
    public void setConnectButtonText(String connectButtonText) {
        this.connectButtonText = connectButtonText;
    }
    
    public String getQuitButtonText() {
        return quitButtonText;
    }
    
    public void setQuitButtonText(String quitButtonText) {
        this.quitButtonText = quitButtonText;
    }
    
    public String getWindowTitle() {
        return windowTitle;
    }
    
    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
    }
    
}
