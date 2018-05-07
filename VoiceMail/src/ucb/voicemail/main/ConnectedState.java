package ucb.voicemail.main;

import ucb.voicemail.main.Connection;
import ucb.voicemail.main.Mailbox;
import ucb.voicemail.main.RecordingState;

public class ConnectedState implements ConnectionState {

	@Override
	public void dial(Connection connection, String key) {
	    if (key.equals("#")) {
            Mailbox currentMailbox = connection.setCurrentMailboxByAccumulatedKeys();
            if (currentMailbox != null) {
                connection.setConnectionState(new RecordingState());
                connection.speakToAll(currentMailbox.getGreeting());
            }
            else {
                connection.speakToAll("Incorrect mailbox number. Try again!");
            }
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
        
    }
}
