package ucb.voicemail.main;

import ucb.voicemail.main.Connection;
import ucb.voicemail.main.Mailbox;
import ucb.voicemail.main.MailboxMenuState;

public class RecordingState implements ConnectionState {
	
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
        connection.addMessageInCurrentMailbox();
    }
}
