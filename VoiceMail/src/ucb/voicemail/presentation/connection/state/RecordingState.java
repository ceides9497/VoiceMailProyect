package ucb.voicemail.presentation.connection.state;

import ucb.voicemail.domain.boundary.input.LoginMailboxUseCase;
import ucb.voicemail.domain.boundary.input.SendMessageUseCase;
import ucb.voicemail.domain.dto.request.LoginMailboxRequest;
import ucb.voicemail.domain.dto.request.SendMessageRequest;
import ucb.voicemail.domain.usecases.LoginMailboxInteractor;
import ucb.voicemail.domain.usecases.SendMessageInteractor;
import ucb.voicemail.presentation.Connection;
import ucb.voicemail.presentation.ConnectionState;

public class RecordingState implements ConnectionState {
	
	@Override
	public void dial(Connection connection, String key) {
	    if (key.equals("#")) {
	        LoginMailboxUseCase interactor = new LoginMailboxInteractor(
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
	    SendMessageUseCase interactor = new SendMessageInteractor(
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
