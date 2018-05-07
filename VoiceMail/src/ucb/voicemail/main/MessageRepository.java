package ucb.voicemail.main;

import ucb.voicemail.main.Message;

public interface MessageRepository {
    Message remove();
    void add(Message newMessage);
    int size();
    Message peek();
}
