package ucb.voicemail.main;

public interface MessageRepository {
    void addMessage(String id, Message aMessage);
    Message getCurrentMessage(String id);
    Message removeCurrentMessage(String id);
    void saveCurrentMessage(String id);
}
