package ucb.voicemail.domain;

import java.sql.DriverManager;
import java.sql.SQLException;
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
            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + "arqui", "root", "mysql");
            MySqlMailboxRepository mysqlMailboxRepository = new MySqlMailboxRepository(connection);
            MySqlMessageRepository mysqlMessageRepository = new MySqlMessageRepository(connection);
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
            Connection c = new Connection(mysqlMailboxRepository, mysqlMessageRepository, new ConnectedState(), new InitialPromptPresenter());
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

    private static void initialise() {
		// TODO Auto-generated method stub
		
	}

	private static final int MAILBOX_COUNT = 20;
}
