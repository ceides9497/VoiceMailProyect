package ucb.voicemail.main;

import ucb.voicemail.main.Connection;

public interface ConnectionState {
	void dial(Connection connection, String key);
	void record(Connection connection, String voice);
	void hangup(Connection connection);
}
