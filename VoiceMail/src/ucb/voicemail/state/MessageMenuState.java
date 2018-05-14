package ucb.voicemail.state;

import ucb.voicemail.main.Connection;
import ucb.voicemail.main.ConnectionState;
import ucb.voicemail.main.Mailbox;
import ucb.voicemail.main.Message;
import ucb.voicemail.main.MessageRepository;

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
