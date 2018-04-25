package ucb.voicemail.main;

public class ConnectedState implements ConnectionState {

	@Override
	public void dial(Connection connection, String key) {
		connection.connect(key);
	}
	
	@Override
    public void record(Connection connection, String voice) {
        
    }
	
	@Override
    public void hangup(Connection connection) {
        
    }
}
