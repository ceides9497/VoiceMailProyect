package ucb.voicemail.main;

import java.util.ArrayList;

public class Connection implements Subject {

    // ==================== CONSTRUCTOR ====================
    
    public Connection(MailboxRepository mailboxRepository, MessageRepository messageRepository, ConnectionState initialState) {
        this.initialState = initialState;
        this.mailboxRepository = mailboxRepository;
        this.messageRepository = messageRepository;
        this.userInterfaces = new ArrayList<Telephone>();
        resetConnection();
    }

    // ==================== FUNCTIONS ====================
    
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
    
    // ==================== SUBJECT FUNCTIONS ====================
    
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
    
    // ==================== HELPER FUNCTIONS ====================
    
    private void resetConnection() {
        currentRecording = "";
        accumulatedKeys = "";
        connectionState = initialState;
        speakToAll(INITIAL_PROMPT);
    }
    
    public void start() {
        resetConnection();
    }
    
    public void addMessageInCurrentMailbox() {
        messageRepository.addMessage(currentMailbox.getId(), new Message(currentRecording));
    }
    
    public void addRecordingText(String voice) {
        currentRecording += voice;
    }
    
    public void addAccumulatedKeysText(String key) {
        accumulatedKeys += key;
    }
    
    public Mailbox setCurrentMailboxByAccumulatedKeys() {
        return currentMailbox = mailboxRepository.findMailbox(accumulatedKeys);
    }
    
    // ==================== GET AND SET ====================
    
    public ArrayList<Telephone> getUserInterfaces() {
        return userInterfaces;
    }
    
    public MailboxRepository getMailSystem() {
        return mailboxRepository;
    }
    
    public String getCurrentRecording() {
        return currentRecording;
    }
    
    public void setCurrentRecording(String currentRecording) {
        this.currentRecording = currentRecording;
    }
    
    public String getAccumulatedKeys() {
        return accumulatedKeys;
    }
    
    public MailboxRepository getMailboxRepository() {
        return mailboxRepository;
    }
    
    public MessageRepository getMessageRepository() {
        return messageRepository;
    }
    
    public void setAccumulatedKeys(String accumulatedKeys) {
        this.accumulatedKeys = accumulatedKeys;
    }
    
    public Mailbox getCurrentMailbox() {
        return currentMailbox;
    }
    
    public void setConnectionState(ConnectionState connectionState) {
        this.connectionState = connectionState;
    }
    
    // ==================== VARIABLES ====================
    
    private MailboxRepository mailboxRepository;
    private MessageRepository messageRepository;
    private Mailbox currentMailbox;
    private String currentRecording;
    private String accumulatedKeys;
    private ArrayList<Telephone> userInterfaces;
    private ConnectionState connectionState;
    
    private final ConnectionState initialState;
    
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
