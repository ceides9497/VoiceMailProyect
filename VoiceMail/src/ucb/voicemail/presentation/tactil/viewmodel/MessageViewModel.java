package ucb.voicemail.presentation.tactil.viewmodel;

public class MessageViewModel {

    private String text;
    private String buttonAcceptName;
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public String getButtonAcceptName() {
        return buttonAcceptName;
    }
    
    public void setButtonAcceptName(String buttonAcceptName) {
        this.buttonAcceptName = buttonAcceptName;
    }
    
}
