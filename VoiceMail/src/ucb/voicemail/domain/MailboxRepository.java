package ucb.voicemail.domain;

public interface MailboxRepository {
    Mailbox findMailbox(String ext);
    int getLengthMailbox();
    void setMailboxPasscode(String id, String passcode);
    void setMailboxGreeting(String id, String greeting);
}
