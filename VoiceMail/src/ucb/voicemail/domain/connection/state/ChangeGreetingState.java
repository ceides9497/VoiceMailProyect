package ucb.voicemail.domain.connection.state;

import ucb.voicemail.domain.Connection;
import ucb.voicemail.domain.ConnectionState;
import ucb.voicemail.domain.Mailbox;
import ucb.voicemail.domain.MailboxRepository;

public class ChangeGreetingState implements ConnectionState {

	@Override
	public void dial(Connection connection, String key) {
	    Mailbox currentMailbox = connection.getCurrentMailbox();
        if (key.equals("#")) {
            MailboxRepository repository = connection.getMailboxRepository();
            repository.setMailboxGreeting(currentMailbox.getId(), connection.getCurrentRecording());
            connection.setCurrentRecording("");
            connection.setConnectionState(new MailboxMenuState());
            connection.speakToAll(connection.getMailboxMenu());
        }
	}
	
	@Override
	public void record(Connection connection, String voice) {
	    connection.addRecordingText(voice);
	}
	
	@Override
	public void hangup(Connection connection) {
	    
	}
}