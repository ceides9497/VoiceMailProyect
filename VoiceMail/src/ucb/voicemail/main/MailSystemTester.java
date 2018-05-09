package ucb.voicemail.main;

import java.util.Scanner;

import ucb.voicemail.repository.mailbox.*;
import ucb.voicemail.repository.message.*;
import ucb.voicemail.state.ConnectedState;
import ucb.voicemail.view.ConsoleTelephone;
import ucb.voicemail.view.GraphicalTelephone;
import ucb.voicemail.view.MainMenu;

public class MailSystemTester {
    
    public static void main(String[] args) {
        MySqlMailboxRepository mysqlMailboxRepository = new MySqlMailboxRepository("root", "mysql", "arqui");
        //System.out.println(mysqlMailboxRepository.findMailbox("1").getGreeting());
        GraphicalTelephone w = new GraphicalTelephone(new MainMenu());
        MessageRepository messageRepository = new ArrayMessageRepository(MAILBOX_COUNT);
        MailboxRepository mailboxRepository = new ArrayMailboxRepository(MAILBOX_COUNT);
        Scanner console = new Scanner(System.in);
        ConsoleTelephone p = new ConsoleTelephone(console);
        Connection c = new Connection(mailboxRepository, messageRepository, new ConnectedState());
        c.addUserInterface(p);
        c.addUserInterface(w);
        c.start();		// REINICIA LA CONEXION PARA QUE APAREZCA "Enter mailbox number followed by #"
        w.run(c);
        p.run(c);
    }

    private static final int MAILBOX_COUNT = 20;
}
