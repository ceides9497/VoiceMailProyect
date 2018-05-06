package ucb.voicemail.main;

import java.util.ArrayList;

public class Connection implements Subject {

    public Connection(MailSystem s) {
        system = s;
        userInterfaces = new ArrayList<Telephone>();
        resetConnection();
    }
   
    public void dial(String key) {
        connectionState.dial(this, key);
    }

    public void record(String voice) {
        connectionState.record(this, voice);
    }

    public void hangup() {
        connectionState.hangup(this);
        resetConnection();
    }

    private void resetConnection() {
        currentRecording = "";
        accumulatedKeys = "";
        connectionState = new ConnectedState();
        speakToAll(INITIAL_PROMPT);
    }
    
    @Override
    public void addUserInterface(Telephone userInterface) {
        userInterfaces.add(userInterface);
    }
	
    @Override
    public void deleteUserInterface(Telephone userInterface) {
        userInterfaces.remove(userInterface);
    }
	
    @Override
    public void speakToAll(String output) {
        for(Telephone userInterface : userInterfaces) {
            userInterface.speak(output);
        }
    }
   
    public void start() {
        resetConnection();
    }
	
    public String getCurrentRecording() {
        return currentRecording;
    }
	
    public String getAccumulatedKeys() {
        return accumulatedKeys;
    }
	
    public void setCurrentRecording(String currentRecording) {
        this.currentRecording = currentRecording;
    }
    
    public ArrayList<Telephone> getUserInterfaces() {
        return userInterfaces;
    }
    
    public void addMessageInCurrentMailbox() {
        currentMailbox.addMessage(new Message(currentRecording));
    }
    
    public void addRecordingText(String voice) {
        currentRecording += voice;
    }
    
    public void setAccumulatedKeys(String accumulatedKeys) {
        this.accumulatedKeys = accumulatedKeys;
    }
    
    public void addAccumulatedKeysText(String key) {
        accumulatedKeys += key;
    }
    
    public void setConnectionState(ConnectionState connectionState) {
        this.connectionState = connectionState;
    }
    
    public MailSystem getMailSystem() {
        return system;
    }
    
    public Mailbox getCurrentMailbox() {
        return currentMailbox;
    }
    
    public Mailbox setCurrentMailboxByAccumulatedKeys() {
        return currentMailbox = system.findMailbox(accumulatedKeys);
    }
    
    private MailSystem system;
    private Mailbox currentMailbox;
    private String currentRecording;
    private String accumulatedKeys;
    private ArrayList<Telephone> userInterfaces;
    private ConnectionState connectionState;

   	public static final String INITIAL_PROMPT = 
        "Enter mailbox number followed by #";
   	
   	public static final String MAILBOX_MENU_TEXT = 
        "Enter 1 to listen to your messages\n"
        + "Enter 2 to change your passcode\n"
        + "Enter 3 to change your greeting";
   	
   	public static final String MESSAGE_MENU_TEXT = 
        "Enter 1 to listen to the current message\n"
        + "Enter 2 to save the current message\n"
        + "Enter 3 to delete the current message\n"
        + "Enter 4 to return to the main menu";
}
