package ucb.voicemail.repository.mailbox;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import ucb.voicemail.domain.Mailbox;
import ucb.voicemail.domain.MailboxRepository;

public class SQLiteMailboxRepository implements MailboxRepository {
	private Connection connection;
	
	public SQLiteMailboxRepository(Connection connection) {
		try {
			this.connection = connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	@Override
	public Mailbox findMailbox(String ext) {
		try {
			String query = "SELECT * FROM mailbox WHERE id=" + ext;
			Statement state = connection.createStatement();
			ResultSet resultSet = state.executeQuery(query);
			if (resultSet.next()) {
				return new Mailbox(resultSet.getString("id"), resultSet.getString("passcode"), resultSet.getString("greeting"));
			}
		} catch (Exception e) {
			// NADA
		}
		
		return null;
	}
	
	@Override
	public int getLengthMailbox() {
		try {
			int resp = 0;
			String query = "SELECT * FROM mailbox";
			Statement statement = connection.createStatement();
			ResultSet resultSet;
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				resp++;
			}
			return resp;
		} catch (Exception e) {
			// NADA
		}
		
		return 0;
	}
	
	@Override
    public void setMailboxPasscode(String id, String passcode) {
        try {
            String query = "UPDATE mailbox set passcode = " + passcode + " WHERE id = " + id;
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            // NADA
        }
    }

    @Override
    public void setMailboxGreeting(String id, String greeting) {
        try {
            String query = "UPDATE mailbox set greeting = " + greeting + " WHERE id = " + id;
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            // NADA
        }
    }
}
