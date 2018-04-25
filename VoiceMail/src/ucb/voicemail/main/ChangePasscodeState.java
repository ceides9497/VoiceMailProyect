package ucb.voicemail.main;

public class ChangePasscodeState implements ConnectionState {
	
	@Override
	public void dial(Connection connection, String key) {
		connection.changePasscode(key);
	}
	
	@Override
    public void record(Connection connection, String voice) {
        
    }
	
	@Override
    public void hangup(Connection connection) {
        
    }
}
