package ucb.voicemail.main;

public interface MessageRepository {
    void addMessage(Message aMessage);
    Message getCurrentMessage();
    Message removeCurrentMessage();
    void saveCurrentMessage();
}
