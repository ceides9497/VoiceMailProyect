package ucb.voicemail.domain;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import ucb.voicemail.presentation.Connection;
import ucb.voicemail.presentation.connection.state.ConnectedState;
import ucb.voicemail.presentation.console.ConsoleTelephone;
import ucb.voicemail.presentation.console.presenter.*;
import ucb.voicemail.presentation.console.view.ConsoleView;
import ucb.voicemail.presentation.console.view.DefaultConsoleView;
import ucb.voicemail.presentation.graphical.GraphicalTelephone;
import ucb.voicemail.presentation.graphical.presenter.*;
import ucb.voicemail.presentation.graphical.presenter.GraphicalChangeGreetingPresenter;
import ucb.voicemail.presentation.graphical.view.MainGraphicalView;
import ucb.voicemail.presentation.tactil.TactilTelephone;
import ucb.voicemail.presentation.tactil.presenter.TactilBasicPresenter;
import ucb.voicemail.presentation.tactil.presenter.TactilChangeGreetingPresenter;
import ucb.voicemail.presentation.tactil.presenter.TactilChangePasscodePresenter;
import ucb.voicemail.presentation.tactil.presenter.TactilDeleteCurrentMessagePresenter;
import ucb.voicemail.presentation.tactil.presenter.TactilGetLastMessagePresenter;
import ucb.voicemail.presentation.tactil.presenter.TactilGetMailboxGreetingPresenter;
import ucb.voicemail.presentation.tactil.presenter.TactilLoginMailboxPresenter;
import ucb.voicemail.presentation.tactil.presenter.TactilSaveCurrentMessagePresenter;
import ucb.voicemail.presentation.tactil.presenter.TactilSendMessagePresenter;
import ucb.voicemail.presentation.tactil.view.*;
import ucb.voicemail.presentation.tactil.view.TactilInitialPromptView;
import ucb.voicemail.presentation.tactil.viewmodel.InitialPromptViewModel;
import ucb.voicemail.repository.mailbox.*;
import ucb.voicemail.repository.message.*;

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
            TactilTelephone tactilGraphical = setTactilGraphical(connection);
            
            connection.start();
            
            basicGraphical.run(connection);
            tactilGraphical.run();
            console.run(connection);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static ConsoleTelephone setConsole(Connection connection) {
        Scanner console = new Scanner(System.in);
        ConsoleTelephone telephone = new ConsoleTelephone(console);
        ConsoleView view = new DefaultConsoleView();
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
        MainGraphicalView view = new MainGraphicalView();
        GraphicalTelephone telephone = new GraphicalTelephone(view);
        telephone.addRoute("BasicPresenter"      , new GraphicalBasicPresenter(view));
        telephone.addRoute("ChangeGreeting"      , new GraphicalChangeGreetingPresenter(view));
        telephone.addRoute("ChangePasscode"      , new GraphicalChangePasscodePresenter(view));
        telephone.addRoute("GetLastMessage"      , new GraphicalGetLastMessagePresenter(view));
        telephone.addRoute("SaveCurrentMessage"  , new GraphicalSaveCurrentMessagePresenter(view));
        telephone.addRoute("DeleteCurrentMessage", new GraphicalDeleteCurrentMessagePresenter(view));
        telephone.addRoute("LoginMailbox"        , new GraphicalLoginMailboxPresenter(view));
        telephone.addRoute("GetMailboxGreeting"  , new GraphicalGetMailboxGreetingPresenter(view));
        telephone.addRoute("SendMessage"         , new GraphicalSendMessagePresenter(view));
        connection.addUserInterface(telephone);
        return telephone;
    }
    
    public static TactilTelephone setTactilGraphical(Connection connection) {
        DefaultTactilChangeGreetingView changeGreetingView = new DefaultTactilChangeGreetingView(connection);
        DefaultTactilChangePasscodeView changePasscodeView = new DefaultTactilChangePasscodeView(connection);
        DefaultTactilInitialPromptView initialPromptView = new DefaultTactilInitialPromptView(connection);
        DefaultTactilMailboxMenuView mailboxMenuView = new DefaultTactilMailboxMenuView(connection);
        DefaultTactilMessageMenuView messageMenuView = new DefaultTactilMessageMenuView(connection);
        DefaultTactilMessageView messageView = new DefaultTactilMessageView();
        DefaultTactilShowGreetingView showGreetingView = new DefaultTactilShowGreetingView(connection);
        TactilTelephone telephone = new TactilTelephone(initialPromptView);
        telephone.addRoute("BasicPresenter"      , new TactilBasicPresenter(changePasscodeView, changeGreetingView, initialPromptView, mailboxMenuView, messageMenuView));
        telephone.addRoute("ChangeGreeting"      , new TactilChangeGreetingPresenter(mailboxMenuView));
        telephone.addRoute("ChangePasscode"      , new TactilChangePasscodePresenter(mailboxMenuView));
        telephone.addRoute("GetLastMessage"      , new TactilGetLastMessagePresenter(messageView));
        telephone.addRoute("SaveCurrentMessage"  , new TactilSaveCurrentMessagePresenter(messageView));
        telephone.addRoute("DeleteCurrentMessage", new TactilDeleteCurrentMessagePresenter(messageView));
        telephone.addRoute("LoginMailbox"        , new TactilLoginMailboxPresenter(mailboxMenuView, messageView, showGreetingView));
        telephone.addRoute("GetMailboxGreeting"  , new TactilGetMailboxGreetingPresenter(showGreetingView, messageView, initialPromptView));
        telephone.addRoute("SendMessage"         , new TactilSendMessagePresenter(initialPromptView, showGreetingView));
        connection.addUserInterface(telephone);
        return telephone;
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
