package ucb.voicemail.main;

public class ChangePasscodeState implements ConnectionState {
	
	@Override
	public void dial(Connection connection, String key) {
		connection.changePasscode(key);
	}
}
