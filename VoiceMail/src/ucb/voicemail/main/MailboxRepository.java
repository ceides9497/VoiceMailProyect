package ucb.voicemail.main;

public interface MailboxRepository {
    Mailbox findMailbox(String ext);
    int getLengthMailbox();
}
