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
	
	public Message getCurrentMessage(String id) {
        try {
            String query = "SELECT * FROM new_message WHERE mailbox_id=" + id + " LIMIT 1";
            Statement st = connection.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(query);
            if (!resultSet.next()) {
                query = "SELECT * FROM kept_message WHERE mailbox_id=" + id + " LIMIT 1";
                st = connection.createStatement();
                resultSet = st.executeQuery(query);
                if(resultSet.next()) {
                    return new Message(resultSet.getString("text"));
                }
                else {
                    return null;
                }
            }
            else {
                return new Message(resultSet.getString("text"));
            }
        } catch (Exception e) {
            // NADA
        }
        return null;
    }
}
