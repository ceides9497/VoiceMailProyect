package ucb.voicemail.main;

public class RecordingState implements ConnectionState {
	
	@Override
	public void dial(Connection connection, String key) {
		connection.login(key);
	}
}
