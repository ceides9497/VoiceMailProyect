package ucb.voicemail.domain.connection.state;

import ucb.voicemail.domain.Connection;
import ucb.voicemail.domain.ConnectionState;
import ucb.voicemail.domain.Mailbox;

public class RecordingState implements ConnectionState {
	
	@Override
	public void dial(Connection connection, String key) {
	    Mailbox currentMailbox = connection.getCurrentMailbox();
	    if (key.equals("#")) {
            if (currentMailbox.checkPasscode(connection.getAccumulatedKeys())) {
                connection.setConnectionState(new MailboxMenuState());
                connection.speakToAll(connection.getMailboxMenu());
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
        connection.addMessageInCurrentMailbox();
    }
}