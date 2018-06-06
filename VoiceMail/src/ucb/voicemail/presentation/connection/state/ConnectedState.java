package ucb.voicemail.presentation.connection.state;

import ucb.voicemail.domain.boundary.input.GetMailboxGreetingUseCase;
import ucb.voicemail.domain.dto.request.GetMailboxGreetingRequest;
import ucb.voicemail.domain.usecases.GetMailboxGreetingInteractor;
import ucb.voicemail.presentation.Connection;
import ucb.voicemail.presentation.ConnectionState;

public class ConnectedState implements ConnectionState {
	
	@Override
	public void dial(Connection connection, String key) {
	    if (key.equals("#")) {
	        GetMailboxGreetingUseCase interactor = new GetMailboxGreetingInteractor (
	            connection.getMailboxRepository(), 
	            connection.generateConnectionPresenter()
	        );
	        
	        GetMailboxGreetingRequest request = new GetMailboxGreetingRequest();
	        request.setExt(connection.getAccumulatedKeys());
	        interactor.getMailboxGreeting(request);
	        
	        connection.setMailboxId(connection.getAccumulatedKeys());
            connection.setAccumulatedKeys("");
        }
        else {
            connection.addAccumulatedKeysText(key);
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
