package ucb.voicemail.main;

import java.util.Scanner;

public class MailSystemTester {
    
    public static void main(String[] args) {
        GraphicalTelephone w = new GraphicalTelephone(new MainMenu());
        MailSystem system = new MailSystem(MAILBOX_COUNT);
        Scanner console = new Scanner(System.in);
        ConsoleTelephone p = new ConsoleTelephone(console);
        Connection c = new Connection(system, new ConnectedState());
        c.addUserInterface(p);
        c.addUserInterface(w);
        c.start();		// REINICIA LA CONEXION PARA QUE APAREZCA "Enter mailbox number followed by #"
        w.run(c);
        p.run(c);
    }

    private static final int MAILBOX_COUNT = 20;
}
