package ucb.voicemail.main;

import java.util.ArrayList;

import ucb.voicemail.main.Mailbox;

public class ArrayMailboxRepository implements MailboxRepository {

    public ArrayMailboxRepository(int mailboxCount) {
        mailboxes = new ArrayList<Mailbox>();
        
        for (int i = 0; i < mailboxCount; i++) {
            String passcode = "" + (i + 1);
            String greeting = "You have reached mailbox " + (i + 1) + ". \nPlease leave a message now.";
            mailboxes.add(new Mailbox(passcode, greeting, new ArrayMessageRepository()));
        }
    }

    @Override
    public Mailbox findMailbox(String ext) {
        int i = Integer.parseInt(ext);
        if (1 <= i && i <= mailboxes.size()) {
            return  mailboxes.get(i - 1);
        }
        else {
            return null;
        }
    }

    @Override
    public int getLengthMailbox() {
        return mailboxes.size();
    }

    private ArrayList<Mailbox> mailboxes;
}
