package ucb.voicemail.domain;

import java.util.ArrayList;
import java.util.HashMap;

import ucb.voicemail.presentation.presenter.ConnectionPrensenter;

public class Connection implements Subject {

    private HashMap<String, Object> presentersRoutes;
    
    public void addRoute(String route, Object presenter) {
        presentersRoutes.put(route, presenter);
    }
    
    public Object routePresenter(String route) {
        if (presentersRoutes.containsKey(route)) {
            return presentersRoutes.get(route);
        }
        return null;
    }
    
    // ==================== CONSTRUCTOR ====================
    
    public Connection(MailboxRepository mailboxRepository, MessageRepository messageRepository, ConnectionState initialState) {
        this.initialState = initialState;
        this.mailboxRepository = mailboxRepository;
        this.messageRepository = messageRepository;
        this.userInterfaces = new ArrayList<Telephone>();
        presentersRoutes = new HashMap<>();
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
        generateConnectionPresenter().displayInitialPrompt();
    }
    
    public void start() {
        resetConnection();
    }
    
    public void addRecordingText(String voice) {
        currentRecording += voice;
    }
    
    public void addAccumulatedKeysText(String key) {
        accumulatedKeys += key;
    }
    
    // ==================== GET AND SET ====================
    
    public ArrayList<Telephone> getUserInterfaces() {
        return userInterfaces;
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
    
    public void setConnectionState(ConnectionState connectionState) {
        this.connectionState = connectionState;
    }
    
    public ConnectionPrensenter generateConnectionPresenter() {
   		ConnectionPrensenter presenter = new ConnectionPrensenter(this);
   		for (Telephone telephone : userInterfaces) {
   			presenter.addTelephone(telephone);
		}
   		return presenter;
   	}
    
    public void setMailboxId(String mailboxId) {
        this.mailboxId = mailboxId;
    }
    
    public String getMailboxId() {
        return mailboxId;
    }
    
    // ==================== VARIABLES ====================
    
    private MailboxRepository mailboxRepository;
    private MessageRepository messageRepository;
    private String currentRecording;
    private String accumulatedKeys;
    private ArrayList<Telephone> userInterfaces;
    private ConnectionState connectionState;
    private String mailboxId;
    
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
