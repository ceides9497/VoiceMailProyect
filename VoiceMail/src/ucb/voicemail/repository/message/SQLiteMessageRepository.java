package ucb.voicemail.repository.message;

import java.sql.Connection;

public class SQLiteMessageRepository {
	private Connection connection;
	
	public SQLiteMessageRepository(Connection connection) {
		try {
			this.connection = connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
