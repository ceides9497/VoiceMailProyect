package ucb.voicemail.domain.connection.state;

import ucb.voicemail.domain.Connection;
import ucb.voicemail.domain.ConnectionState;
import ucb.voicemail.domain.Mailbox;
import ucb.voicemail.domain.Message;
import ucb.voicemail.domain.MessageRepository;

public class MessageMenuState implements ConnectionState {

	@Override
	public void dial(Connection connection, String key) {
	    Mailbox currentMailbox = connection.getCurrentMailbox();
        MessageRepository messageRepository = connection.getMessageRepository();
	    if (key.equals("1")) {
            String output = "";
            Message m = messageRepository.getCurrentMessage(currentMailbox.getId());
            if (m == null) {
                output += "No messages." + "\n";
            }
            else {
                output += m.getText() + "\n";
            }
            output += connection.getMessageMenuTextPresenter();
            connection.speakToAll(output);
        }
        else if (key.equals("2")) {
            messageRepository.saveCurrentMessage(currentMailbox.getId());
            connection.speakToAll(connection.getMessageMenuTextPresenter());
        }
        else if (key.equals("3")) {
            messageRepository.removeCurrentMessage(currentMailbox.getId());
            connection.speakToAll(connection.getMessageMenuTextPresenter());
        }
        else if (key.equals("4")) {
            connection.setConnectionState(new MailboxMenuState());
            connection.speakToAll(connection.getMailboxMenu());
        }
	}
	
	@Override
    public void record(Connection connection, String voice) {
        
    }
	
	@Override
    public void hangup(Connection connection) {
        
    }
}
