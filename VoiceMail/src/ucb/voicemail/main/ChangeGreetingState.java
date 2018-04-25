package ucb.voicemail.main;

public class ChangeGreetingState implements ConnectionState {

	@Override
	public void dial(Connection connection, String key) {
		connection.changeGreeting(key);
	}
}
