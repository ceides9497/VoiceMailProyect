package ucb.voicemail.presentation.connection.state;

import ucb.voicemail.domain.boundary.input.ChangePasscodeUseCase;
import ucb.voicemail.domain.dto.request.ChangePasscodeRequest;
import ucb.voicemail.domain.usecases.ChangePasscodeInteractor;
import ucb.voicemail.presentation.Connection;
import ucb.voicemail.presentation.ConnectionState;

public class ChangePasscodeState implements ConnectionState {
	
	@Override
	public void dial(Connection connection, String key) {
        if (key.equals("#")) {
            ChangePasscodeUseCase interactor = new ChangePasscodeInteractor(
                connection.getMailboxRepository(), 
                connection.generateConnectionPresenter()
            );
            
            ChangePasscodeRequest request = new ChangePasscodeRequest();
        	request.setExt(connection.getMailboxId());
        	request.setPasscode(connection.getAccumulatedKeys());
        	interactor.changePasscode(request);
        	
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
