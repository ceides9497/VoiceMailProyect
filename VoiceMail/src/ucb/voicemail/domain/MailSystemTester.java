package ucb.voicemail.domain;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import ucb.voicemail.domain.connection.state.ConnectedState;
import ucb.voicemail.presenters.InitialPromptPresenter;
import ucb.voicemail.presenters.MailboxMenuPresenter;
import ucb.voicemail.presenters.MessageMenuTextPresenter;
import ucb.voicemail.repository.mailbox.*;
import ucb.voicemail.repository.message.*;
import ucb.voicemail.view.ConsoleTelephone;
import ucb.voicemail.view.GraphicalTelephone;
import ucb.voicemail.view.MainMenu;

public class MailSystemTester {
	private static java.sql.Connection sqliteConnection;
    
    public static void main(String[] args) {
        try {
        	getConnectionSQLite();
        	MailboxRepository sqliteMailboxRepository = new SQLiteMailboxRepository(sqliteConnection);
        	MessageRepository sqliteMessageRepository = new SQLiteMessageRepository(sqliteConnection);
            /*Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "arqui", "root", "mysql");
            MySqlMailboxRepository mysqlMailboxRepository = new MySqlMailboxRepository(connection);
            MySqlMessageRepository mysqlMessageRepository = new MySqlMessageRepository(connection);*/
            GraphicalTelephone w = new GraphicalTelephone(new MainMenu());
            MailboxRepository mailboxRepository = new ArrayMailboxRepository(MAILBOX_COUNT);
            MessageRepository messageRepository = new ArrayMessageRepository(MAILBOX_COUNT);
            Scanner console = new Scanner(System.in);
            ConsoleTelephone p = new ConsoleTelephone(console);
            MailboxMenuPresenter mailboxMenuPresenter = new MailboxMenuPresenter();
            mailboxMenuPresenter.addOption("listen to your messages");
            mailboxMenuPresenter.addOption("change your passcode");
            mailboxMenuPresenter.addOption("change your greeting");
            MessageMenuTextPresenter messageMenuTextPresenter = new MessageMenuTextPresenter();
            messageMenuTextPresenter.addOption("listen to the current message");
            messageMenuTextPresenter.addOption("save the current message");
            messageMenuTextPresenter.addOption("delete the current message");
            messageMenuTextPresenter.addOption("return to the main menu");
            Connection c = new Connection(sqliteMailboxRepository, sqliteMessageRepository, new ConnectedState(), new InitialPromptPresenter());
            c.setMailBoxMenuPresenter(mailboxMenuPresenter);
            c.setMessageMenuTextPresenter(messageMenuTextPresenter);
            c.addUserInterface(p);
            c.addUserInterface(w);
            c.start();      // REINICIA LA CONEXION PARA QUE APAREZCA "Enter mailbox number followed by #"
            w.run(c);
            p.run(c);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void getConnectionSQLite() throws ClassNotFoundException, SQLException {
    	Class.forName("org.sqlite.JDBC");
    	sqliteConnection = DriverManager.getConnection("jdbc:sqlite:VoiceMailDB.db");
    	initialise();
    }

    private static void initialise() throws SQLException {
    	Statement state = sqliteConnection.createStatement();
    	Statement state2 = sqliteConnection.createStatement();
    	Statement state3 = sqliteConnection.createStatement();
    	String query = "SELECT name FROM sqlite_master WHERE type='table' AND name='mailbox'";
    	String query2 = "SELECT name FROM sqlite_master WHERE type='table' AND name='kept_message'";
    	String query3 = "SELECT name FROM sqlite_master WHERE type='table' AND name='new_message'";
    	ResultSet res = state.executeQuery(query);
    	ResultSet res2 = state2.executeQuery(query2);
    	ResultSet res3 = state3.executeQuery(query3);
    	
    	if (!res.next()) {
    		Statement state4 = sqliteConnection.createStatement();
			state4.execute("CREATE TABLE mailbox(id integer,"
					+ "passcode varchar(200)," + "greeting varchar(200),"
					+ "primary key(id));");
			
			for (int i = 0; i < 5; i++) {
				PreparedStatement prep = sqliteConnection.prepareStatement("INSERT INTO mailbox values(?,?,?);");
				prep.setString(2, "" + (i + 1));
				prep.setString(3, "You have reached mailbox " + (i + 1) + ". \r\n" + 
						"Please leave a message now.");
				prep.execute();
			}	
    	}
    	
    	if (!res2.next()) {
    		Statement state4  = sqliteConnection.createStatement();
			state4.execute("CREATE TABLE kept_message(id integer,"
					+ "text varchar(200)," + "mailbox_id integer,"
					+ "primary key(id));");
    	}
    	
    	if (!res3.next()) {
    		Statement state4  = sqliteConnection.createStatement();
			state4.execute("CREATE TABLE new_message(id integer,"
					+ "text varchar(200)," + "mailbox_id integer,"
					+ "primary key(id));");
    	}
    	
	}

	private static final int MAILBOX_COUNT = 20;
}
