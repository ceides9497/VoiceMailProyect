package ucb.voicemail.domain;

public class Mailbox {

    public Mailbox(String id, String aPasscode, String aGreeting) {
        this.id = id;
        passcode = aPasscode;
        greeting = aGreeting;
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

    public String getId() {
        return id;
    }
    
    private String greeting;
    private String passcode;
    private String id;
}
