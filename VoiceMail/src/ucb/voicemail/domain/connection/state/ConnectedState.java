package ucb.voicemail.domain.connection.state;

import ucb.voicemail.domain.Connection;
import ucb.voicemail.domain.ConnectionState;
import ucb.voicemail.domain.Mailbox;
import ucb.voicemail.domain.MailboxRepository;
import ucb.voicemail.domain.usecases.LoginMailboxInteractor;
import ucb.voicemail.domain.usecases.SendMessageInteractor;

public class ConnectedState implements ConnectionState {
	
	@Override
	public void dial(Connection connection, String key) {
	    if (key.equals("#")) {
	        MailboxRepository mailboxRepository = connection.getMailboxRepository();
            Mailbox currentMailbox = mailboxRepository.findMailbox(connection.getAccumulatedKeys());
            connection.setCurrentMailbox(currentMailbox);
	        
	        if (currentMailbox != null) {
                connection.setConnectionState(
                		new RecordingState(
                				new LoginMailboxInteractor(connection.getMailboxRepository(), connection.generateConnectionPresenter()),
                				new SendMessageInteractor(connection.getMessageRepository(), connection.generateConnectionPresenter())
                		));
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
