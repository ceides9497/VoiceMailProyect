package ucb.voicemail.repository.mailbox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import ucb.voicemail.main.Mailbox;
import ucb.voicemail.main.MailboxRepository;

public class MySqlMailboxRepository implements MailboxRepository {

    private Connection connection;
    
    public MySqlMailboxRepository(String user, String password, String databaseName) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, user, password);
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
            Statement st = connection.createStatement();
            java.sql.ResultSet resultSet;
            resultSet = st.executeQuery(query);
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
            Statement st = connection.createStatement();
            ResultSet resultSet;
            resultSet = st.executeQuery(query);
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
            Statement st = connection.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            // NADA
        }
    }

    @Override
    public void setMailboxGreeting(String id, String greeting) {
        try {
            String query = "UPDATE mailbox set greeting = " + greeting + " WHERE id = " + id;
            Statement st = connection.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            // NADA
        }
    }

}
