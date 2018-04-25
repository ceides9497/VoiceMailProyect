package ucb.voicemail.main;

public class ConnectedState implements ConnectionState {

	@Override
	public void dial(Connection connection, String key) {
		connection.connect(key);
	}
}
