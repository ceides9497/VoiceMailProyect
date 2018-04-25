package ucb.voicemail.main;

public class MailboxMenuState implements ConnectionState {

	@Override
	public void dial(Connection connection, String key) {
		connection.mailboxMenu(key);
	}
}
