package ucb.voicemail.main;

import ucb.voicemail.main.ChangeGreetingState;
import ucb.voicemail.main.ChangePasscodeState;
import ucb.voicemail.main.Connection;
import ucb.voicemail.main.ConnectionState;
import ucb.voicemail.main.MessageMenuState;

public class MailboxMenuState implements ConnectionState {

	@Override
	public void dial(Connection connection, String key) {
		if (key.equals("1")) {
	        connection.setConnectionState(new MessageMenuState());
	        connection.speakToAll(Connection.MESSAGE_MENU_TEXT);
        }
        else if (key.equals("2")) {
            connection.setConnectionState(new ChangePasscodeState());
            connection.speakToAll("Enter new passcode followed by the # key");
        }
        else if (key.equals("3")) {
            connection.setConnectionState(new ChangeGreetingState());
            connection.speakToAll("Record your greeting, then press the # key");
        }
	}
	
	@Override
    public void record(Connection connection, String voice) {
        
    }
	
	@Override
    public void hangup(Connection connection) {
        
    }
}
