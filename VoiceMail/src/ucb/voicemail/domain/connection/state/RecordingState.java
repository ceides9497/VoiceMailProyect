package ucb.voicemail.domain.connection.state;

import ucb.voicemail.domain.Connection;
import ucb.voicemail.domain.ConnectionState;
import ucb.voicemail.domain.Mailbox;
import ucb.voicemail.domain.Message;
import ucb.voicemail.domain.MessageRepository;
import ucb.voicemail.domain.boundary.input.LoginMailboxInteractorInput;
import ucb.voicemail.domain.boundary.input.SendMessageInteractorInput;
import ucb.voicemail.domain.dto.request.ChangeGreetingRequest;
import ucb.voicemail.domain.dto.request.SendMessageRequest;

public class RecordingState implements ConnectionState {
	
	private LoginMailboxInteractorInput loginInteractor;
	private SendMessageInteractorInput sendMessageInteractor;
	
	public RecordingState(LoginMailboxInteractorInput loginInteractor, SendMessageInteractorInput sendMessageInteractor){
		this.loginInteractor = loginInteractor;
		this.sendMessageInteractor = sendMessageInteractor;
	}
	
	@Override
	public void dial(Connection connection, String key) {
	    Mailbox currentMailbox = connection.getCurrentMailbox();
	    if (key.equals("#")) {
           if (currentMailbox.checkPasscode(connection.getAccumulatedKeys())) {
                connection.setConnectionState(new MailboxMenuState());
                
                connection.speakToAll(Connection.MAILBOX_MENU_TEXT);
            }
            else {
                connection.speakToAll("Incorrect passcode. Try again!");
            }
	    	
            connection.setAccumulatedKeys("");
        }
        else {
            connection.addAccumulatedKeysText(key);
        }
	}
	
	@Override
    public void record(Connection connection, String voice) {
        connection.addRecordingText(voice);
    }
	
	@Override
    public void hangup(Connection connection) {
	    Mailbox currentMailbox = connection.getCurrentMailbox();
        MessageRepository messageRepository = connection.getMessageRepository();
        String currentRecording = connection.getCurrentRecording();
        messageRepository.addMessage(currentMailbox.getId(), new Message(currentRecording));
        
        SendMessageRequest request = new SendMessageRequest();
    	request.setExt(currentMailbox.getId());
    	request.setMessage(currentRecording);
    	sendMessageInteractor.sendMessage(request);
    }
}
