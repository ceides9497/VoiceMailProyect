package ucb.voicemail.main;

import java.sql.DriverManager;
import java.util.Scanner;

import ucb.voicemail.presenters.InitialPromptPresenter;
import ucb.voicemail.repository.mailbox.*;
import ucb.voicemail.repository.message.*;
import ucb.voicemail.state.ConnectedState;
import ucb.voicemail.view.ConsoleTelephone;
import ucb.voicemail.view.GraphicalTelephone;
import ucb.voicemail.view.MainMenu;

public class MailSystemTester {
    
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
            Connection c = new Connection(mysqlMailboxRepository, mysqlMessageRepository, new ConnectedState(), new InitialPromptPresenter());
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

    private static final int MAILBOX_COUNT = 20;
}
