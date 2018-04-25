package ucb.voicemail.main;

public class MessageMenuState implements ConnectionState {

	@Override
	public void dial(Connection connection, String key) {
		connection.messageMenu(key);
	}
	
	@Override
    public void record(Connection connection, String voice) {
        
    }
	
	@Override
    public void hangup(Connection connection) {
        
    }
}
