package ucb.voicemail.repository.message;

import java.sql.Connection;
import java.sql.Statement;

import ucb.voicemail.domain.Message;

public class SQLiteMessageRepository {
	private Connection connection;
	
	public SQLiteMessageRepository(Connection connection) {
		try {
			this.connection = connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addMessage(String id, Message aMessage) {
        try {
            String query = "INSERT INTO new_message(text,mailbox_id) VALUES('" + aMessage.getText() + "'," + id + ")";
            Statement st = connection.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            // NADA
        }
    }
}
