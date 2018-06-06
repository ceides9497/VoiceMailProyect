package ucb.voicemail.presentation.connection.state;

import ucb.voicemail.presentation.Connection;
import ucb.voicemail.presentation.ConnectionState;

public class MailboxMenuState implements ConnectionState {

	@Override
	public void dial(Connection connection, String key) {
		if (key.equals("1")) {
	        connection.generateConnectionPresenter().displayMessageMenu();
        }
        else if (key.equals("2")) {
            connection.generateConnectionPresenter().displayPasscodeForm();
        }
        else if (key.equals("3")) {
            connection.generateConnectionPresenter().displayGreetingForm();
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
