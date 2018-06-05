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
        this.mailboxRepository = mailboxRepository;
        this.messageRepository = messageRepository;
        this.connectionState = initialState;
        this.userInterfaces = new ArrayList<Telephone>();
        this.accumulatedKeys = "";
        this.currentRecording = "";
        presentersRoutes = new HashMap<>();
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
    
    // ==================== HELPER FUNCTIONS ====================
    
    public void start() {
        generateConnectionPresenter().displayInitialPrompt();
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
}
