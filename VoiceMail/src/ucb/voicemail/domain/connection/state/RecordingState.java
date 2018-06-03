package ucb.voicemail.domain.connection.state;

import ucb.voicemail.domain.Connection;
import ucb.voicemail.domain.ConnectionState;
import ucb.voicemail.domain.boundary.input.LoginMailboxInteractorInput;
import ucb.voicemail.domain.boundary.input.SendMessageInteractorInput;
import ucb.voicemail.domain.dto.request.LoginMailboxRequest;
import ucb.voicemail.domain.dto.request.SendMessageRequest;
import ucb.voicemail.domain.usecases.LoginMailboxInteractor;
import ucb.voicemail.domain.usecases.SendMessageInteractor;

public class RecordingState implements ConnectionState {
	
	@Override
	public void dial(Connection connection, String key) {
	    if (key.equals("#")) {
	        LoginMailboxInteractorInput interactor = new LoginMailboxInteractor(
	            connection.getMailboxRepository(),
	            connection.generateConnectionPresenter()
	        );
	        
	        LoginMailboxRequest request = new LoginMailboxRequest();
	        request.setExt(connection.getMailboxId());
	        request.setPasscode(connection.getAccumulatedKeys());
	        interactor.loginMailbox(request);
	        
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
	    SendMessageInteractorInput interactor = new SendMessageInteractor(
	        connection.getMessageRepository(),
	        connection.generateConnectionPresenter()
	    );
	    
        SendMessageRequest request = new SendMessageRequest();
    	request.setExt(connection.getMailboxId());
    	request.setMessage(connection.getCurrentRecording());
    	interactor.sendMessage(request);
    	
    	connection.setAccumulatedKeys("");
    	connection.setCurrentRecording("");
    }
}
