package ucb.voicemail.main;

import ucb.voicemail.main.ChangeGreetingState;
import ucb.voicemail.main.ChangePasscodeState;
import ucb.voicemail.main.ConnectedState;
import ucb.voicemail.main.Connection;
import ucb.voicemail.main.ConnectionState;
import ucb.voicemail.main.ConsoleTelephone;
import ucb.voicemail.main.GraphicalTelephone;
import ucb.voicemail.main.Mailbox;
import ucb.voicemail.main.MailboxMenuState;
import ucb.voicemail.main.MailSystem;
import ucb.voicemail.main.MailSystemTester;
import ucb.voicemail.main.MainMenu;
import ucb.voicemail.main.Message;
import ucb.voicemail.main.MessageMenuState;
import ucb.voicemail.main.MessageQueue;
import ucb.voicemail.main.RecordingState;
import ucb.voicemail.main.Subject;
import ucb.voicemail.main.Telephone;

public class ChangeGreetingState implements ConnectionState {

	@Override
	public void dial(Connection connection, String key) {
	    Mailbox currentMailbox = connection.getCurrentMailbox();
        if (key.equals("#")) {
            currentMailbox.setGreeting(connection.getCurrentRecording());
            connection.setCurrentRecording("");
            connection.setConnectionState(new MailboxMenuState());
            connection.speakToAll(Connection.MAILBOX_MENU_TEXT);
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
