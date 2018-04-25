package ucb.voicemail.main;

public class RecordingState implements ConnectionState {
	
	@Override
	public void dial(Connection connection, String key) {
		connection.login(key);
	}
	
	@Override
    public void record(Connection connection, String voice) {
        connection.addRecordingText(voice);
    }
	
	@Override
    public void hangup(Connection connection) {
        connection.addMessageInCurrentMailbox();
    }
}
