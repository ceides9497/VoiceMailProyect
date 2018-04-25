package ucb.voicemail.main;

public class ChangeGreetingState implements ConnectionState {

	@Override
	public void dial(Connection connection, String key) {
		connection.changeGreeting(key);
	}
	
	@Override
	public void record(Connection connection, String voice) {
	    connection.addRecordingText(voice);
	}
	
	@Override
	public void hangup(Connection connection) {
	    
	}
}
