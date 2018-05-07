package ucb.voicemail.main;

public class ArrayMessageRepository implements MessageRepository {

    public ArrayMessageRepository() {
        newMessages = new MessageQueue();
        keptMessages = new MessageQueue();
    }
    
    @Override
    public void addMessage(Message aMessage) {
        newMessages.add(aMessage);
    }
    
    @Override
    public Message getCurrentMessage() {
        if (newMessages.size() > 0) {
            return newMessages.peek();
        }
        else if (keptMessages.size() > 0) {
            return keptMessages.peek();
        }
        else {
            return null;
        }
    }
    
    @Override
    public Message removeCurrentMessage() {
        if (newMessages.size() > 0) {
            return newMessages.remove();
        }
        else if (keptMessages.size() > 0) {
            return keptMessages.remove();
        }
        else {
            return null;
        }
    }
    
    @Override
    public void saveCurrentMessage() {
        Message m = removeCurrentMessage();
        if (m != null) {
            keptMessages.add(m);
        }
    }
    
    private MessageQueue newMessages;
    private MessageQueue keptMessages;
}
