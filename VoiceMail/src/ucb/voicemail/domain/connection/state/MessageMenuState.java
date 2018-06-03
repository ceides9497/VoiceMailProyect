package ucb.voicemail.domain.connection.state;

import ucb.voicemail.domain.Connection;
import ucb.voicemail.domain.ConnectionState;
import ucb.voicemail.domain.boundary.input.DeleteCurrentMessageInteractorInput;
import ucb.voicemail.domain.boundary.input.GetLastMessageInteractorInput;
import ucb.voicemail.domain.boundary.input.SaveCurrentMessageInteractorInput;
import ucb.voicemail.domain.dto.request.DeleteCurrentMessageRequest;
import ucb.voicemail.domain.dto.request.GetLastMessageRequest;
import ucb.voicemail.domain.dto.request.SaveCurrentMessageRequest;
import ucb.voicemail.domain.usecases.DeleteCurrentMessageInteractor;
import ucb.voicemail.domain.usecases.GetLastMessageInteractor;
import ucb.voicemail.domain.usecases.SaveCurrentMessageInteractor;

public class MessageMenuState implements ConnectionState {
	
	@Override
	public void dial(Connection connection, String key) {
	    if (key.equals("1")) {
	        GetLastMessageInteractorInput interactor = new GetLastMessageInteractor(
	            connection.getMessageRepository(),
	            connection.generateConnectionPresenter()
	        );
	        
            GetLastMessageRequest request = new GetLastMessageRequest();
        	request.setExt(connection.getMailboxId());
        	interactor.getLastMessage(request);
        }
        else if (key.equals("2")) {
            SaveCurrentMessageInteractorInput interactor = new SaveCurrentMessageInteractor(
                connection.getMessageRepository(),
                connection.generateConnectionPresenter()
            );
            
        	SaveCurrentMessageRequest request = new SaveCurrentMessageRequest();
        	request.setExt(connection.getMailboxId());
        	interactor.saveCurrentMessage(request);
        }
        else if (key.equals("3")) {
            DeleteCurrentMessageInteractorInput interactor = new DeleteCurrentMessageInteractor(
                connection.getMessageRepository(),
                connection.generateConnectionPresenter()
            );
            
            DeleteCurrentMessageRequest request = new DeleteCurrentMessageRequest();
        	request.setExt(connection.getMailboxId());
        	interactor.deleteCurrentMessage(request);
        }
        else if (key.equals("4")) {
            connection.generateConnectionPresenter().displayMailboxMenu();
        }
	}
	
	@Override
    public void record(Connection connection, String voice) {
        
    }
	
	@Override
    public void hangup(Connection connection) {
	    connection.generateConnectionPresenter().displayInitialPrompt();
        connection.setAccumulatedKeys("");
        connection.setCurrentRecording("");
    }
}
