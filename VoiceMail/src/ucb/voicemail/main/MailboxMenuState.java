package ucb.voicemail.main;

public class MailboxMenuState implements ConnectionState {

	@Override
	public void dial(Connection connection, String key) {
		connection.mailboxMenu(key);
	}
	
	@Override
    public void record(Connection connection, String voice) {
        
    }
	
	@Override
    public void hangup(Connection connection) {
        
    }
}
