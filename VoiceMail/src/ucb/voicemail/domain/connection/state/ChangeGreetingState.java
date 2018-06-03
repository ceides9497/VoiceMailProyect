package ucb.voicemail.domain.connection.state;

import ucb.voicemail.domain.Connection;
import ucb.voicemail.domain.ConnectionState;
import ucb.voicemail.domain.boundary.input.ChangeGreetingInteractorInput;
import ucb.voicemail.domain.dto.request.ChangeGreetingRequest;
import ucb.voicemail.domain.usecases.ChangeGreetingInteractor;

public class ChangeGreetingState implements ConnectionState {
	
	@Override
	public void dial(Connection connection, String key) {
        if (key.equals("#")) {
            ChangeGreetingInteractorInput interactor = new ChangeGreetingInteractor(
                connection.getMailboxRepository(), 
                connection.generateConnectionPresenter()
            );
            
        	ChangeGreetingRequest request = new ChangeGreetingRequest();
        	request.setExt(connection.getMailboxId());
        	request.setGreeting(connection.getCurrentRecording());
        	interactor.changeGreeting(request);
        	
        	connection.setCurrentRecording("");
        }
	}
	
	@Override
	public void record(Connection connection, String voice) {
	    connection.addRecordingText(voice);
	}
	
	@Override
	public void hangup(Connection connection) {
	    connection.generateConnectionPresenter().displayInitialPrompt();
	    connection.setAccumulatedKeys("");
        connection.setCurrentRecording("");
	}
}
