package ucb.voicemail.main;

public interface ConnectionState {
	void dial(Connection connection, String key);
	void record(Connection connection, String voice);
	void hangup(Connection connection);
}
