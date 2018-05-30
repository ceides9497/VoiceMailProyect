package ucb.voicemail.domain.connection.state;

import ucb.voicemail.domain.Connection;
import ucb.voicemail.domain.ConnectionState;
import ucb.voicemail.domain.Mailbox;
import ucb.voicemail.domain.MailboxRepository;

public class ChangePasscodeState implements ConnectionState {
	
	@Override
	public void dial(Connection connection, String key) {
	    Mailbox currentMailbox = connection.getCurrentMailbox();
        if (key.equals("#")) {
            MailboxRepository repository = connection.getMailboxRepository();
            repository.setMailboxPasscode(currentMailbox.getId(), connection.getAccumulatedKeys());
            connection.setConnectionState(new MailboxMenuState());
            connection.speakToAll(Connection.MAILBOX_MENU_TEXT);
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
