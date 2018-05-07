package ucb.voicemail.main;

import ucb.voicemail.main.Connection;
import ucb.voicemail.main.ConnectionState;
import ucb.voicemail.main.Mailbox;
import ucb.voicemail.main.MailboxMenuState;
import ucb.voicemail.main.Message;

public class MessageMenuState implements ConnectionState {

	@Override
	public void dial(Connection connection, String key) {
	    Mailbox currentMailbox = connection.getCurrentMailbox();
        if (key.equals("1")) {
            String output = "";
            Message m = currentMailbox.getCurrentMessage();
            if (m == null) {
                output += "No messages." + "\n";
            }
            else {
                output += m.getText() + "\n";
            }
            output += Connection.MESSAGE_MENU_TEXT;
            connection.speakToAll(output);
        }
        else if (key.equals("2")) {
            currentMailbox.saveCurrentMessage();
            connection.speakToAll(Connection.MESSAGE_MENU_TEXT);
        }
        else if (key.equals("3")) {
            currentMailbox.removeCurrentMessage();
            connection.speakToAll(Connection.MESSAGE_MENU_TEXT);
        }
        else if (key.equals("4")) {
            connection.setConnectionState(new MailboxMenuState());
            connection.speakToAll(Connection.MAILBOX_MENU_TEXT);
        }
	}
	
	@Override
    public void record(Connection connection, String voice) {
        
    }
	
	@Override
    public void hangup(Connection connection) {
        
    }
}
