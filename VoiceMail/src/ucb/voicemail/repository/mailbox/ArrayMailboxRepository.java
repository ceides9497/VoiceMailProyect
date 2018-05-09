package ucb.voicemail.repository.mailbox;

import java.util.ArrayList;

import ucb.voicemail.main.Mailbox;
import ucb.voicemail.main.MailboxRepository;
import ucb.voicemail.main.MessageRepository;

public class ArrayMailboxRepository implements MailboxRepository {

    public ArrayMailboxRepository(int mailboxCount, MessageRepository messageRepository) {
        mailboxes = new ArrayList<Mailbox>();
        for (int i = 0; i < mailboxCount; i++) {
            String id = "" + (i + 1);
            String passcode = "" + (i + 1);
            String greeting = "You have reached mailbox " + (i + 1) + ". \nPlease leave a message now.";
            mailboxes.add(
                new Mailbox(id, passcode, greeting)
            );
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

    @Override
    public void setMailboxPasscode(String id, String passcode) {
        Mailbox selectedMailbox = findMailbox(id);
        if(selectedMailbox != null) {
            selectedMailbox.setPasscode(passcode);
        }
    }
    
    @Override
    public void setMailboxGreeting(String id, String greeting) {
        Mailbox selectedMailbox = findMailbox(id);
        if(selectedMailbox != null) {
            selectedMailbox.setGreeting(greeting);
        }
    }
    
    private ArrayList<Mailbox> mailboxes;
}
