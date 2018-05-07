package ucb.voicemail.main;

public class Mailbox {

    public Mailbox(String aPasscode, String aGreeting, MessageRepository repository) {
        passcode = aPasscode;
        greeting = aGreeting;
        this.repository = repository;
    }

    public boolean checkPasscode(String aPasscode) {
        return aPasscode.equals(passcode);
    }

    public void setGreeting(String newGreeting) {
        greeting = newGreeting;
    }

    public void setPasscode(String newPasscode) {
        passcode = newPasscode;
    }

    public String getGreeting() {
        return greeting;
    }

    // ===== REPOSITORY FUNCTIONS =====
    
    public void addMessage(Message aMessage) {
        repository.addMessage(aMessage);
    }

    public Message getCurrentMessage() {
        return repository.getCurrentMessage();
    }

    public Message removeCurrentMessage() {
        return repository.removeCurrentMessage();
    }

    public void saveCurrentMessage() {
        repository.saveCurrentMessage();
    }
    
    // ===== REPOSITORY FUNCTIONS =====
    
    private MessageRepository repository;
    private String greeting;
    private String passcode;
}
