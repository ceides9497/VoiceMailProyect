package ucb.voicemail.presentation.tactil.viewmodel;

public class MessageMenuViewModel {
    
    private String getLastMessageName;
    private String saveCurrentMessageName;
    private String deleteCurrentMessageName;
    private String returnName;
    private String quitName;
    
    public String getReturnName() {
        return returnName;
    }

    public void setReturnName(String returnName) {
        this.returnName = returnName;
    }

    public String getGetLastMessageName() {
        return getLastMessageName;
    }
    
    public void setGetLastMessageName(String getLastMessageName) {
        this.getLastMessageName = getLastMessageName;
    }
    
    public String getSaveCurrentMessageName() {
        return saveCurrentMessageName;
    }
    
    public void setSaveCurrentMessageName(String saveCurrentMessageName) {
        this.saveCurrentMessageName = saveCurrentMessageName;
    }
    
    public String getDeleteCurrentMessageName() {
        return deleteCurrentMessageName;
    }
    
    public void setDeleteCurrentMessageName(String deleteCurrentMessageName) {
        this.deleteCurrentMessageName = deleteCurrentMessageName;
    }
    
    public String getQuitName() {
        return quitName;
    }
    
    public void setQuitName(String quitName) {
        this.quitName = quitName;
    }

}
