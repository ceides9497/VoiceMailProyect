package ucb.voicemail.domain;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import ucb.voicemail.domain.boundary.output.ChangeGreetingInteractorOutput;
import ucb.voicemail.domain.boundary.output.GetMailboxGreetingInteractorOutput;
import ucb.voicemail.domain.connection.state.ConnectedState;
import ucb.voicemail.presentation.console.presenter.*;
import ucb.voicemail.presentation.console.view.ConsoleView;
import ucb.voicemail.presentation.presenter.BasicPresenter;
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
        	
        	Connection connection = new Connection(sqliteMailboxRepository, sqliteMessageRepository, new ConnectedState());
            ConsoleTelephone console = setConsole(connection);
            GraphicalTelephone basicGraphical = setBasicGraphical(connection);
            connection.start();
            
            basicGraphical.run(connection);
            console.run(connection);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static ConsoleTelephone setConsole(Connection connection) {
        Scanner console = new Scanner(System.in);
        ConsoleTelephone telephone = new ConsoleTelephone(console);
        ConsoleView view = new ConsoleView();
        telephone.addRoute("BasicPresenter"      , new ConsoleBasicPresenter(view));
        telephone.addRoute("ChangeGreeting"      , new ConsoleChangeGreetingPresenter(view));
        telephone.addRoute("ChangePasscode"      , new ConsoleChangePasscodePresenter(view));
        telephone.addRoute("GetLastMessage"      , new ConsoleGetLastMessagePresenter(view));
        telephone.addRoute("SaveCurrentMessage"  , new ConsoleSaveCurrentMessagePresenter(view));
        telephone.addRoute("DeleteCurrentMessage", new ConsoleDeleteCurrentMessagePresenter(view));
        telephone.addRoute("LoginMailbox"        , new ConsoleLoginMailboxPresenter(view));
        telephone.addRoute("GetMailboxGreeting"  , new ConsoleGetMailboxGreetingPresenter(view));
        telephone.addRoute("SendMessage"         , new ConsoleSendMessagePresenter(view));
        connection.addUserInterface(telephone);
        return telephone;
    }
    
    public static GraphicalTelephone setBasicGraphical(Connection connection) {
        GraphicalTelephone basicGraphical = new GraphicalTelephone(new MainMenu());
        connection.addUserInterface(basicGraphical);
        return basicGraphical;
    }
    
    // ========= DATABASE CONFIG ========================================================================
    
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
}
