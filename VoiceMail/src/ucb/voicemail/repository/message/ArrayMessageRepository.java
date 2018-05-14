package ucb.voicemail.repository.message;

import java.util.ArrayList;

import ucb.voicemail.domain.Message;
import ucb.voicemail.domain.MessageRepository;

public class ArrayMessageRepository implements MessageRepository {

    public ArrayMessageRepository(int mailboxCount) {
        messages = new ArrayList<MessageQueue[]>();
        for (int i = 0; i < mailboxCount; i++) {
            MessageQueue[] array = { new MessageQueue(), new MessageQueue() };
            messages.add(array);
        }
    }
    
    private MessageQueue[] findMessages(String id) {
        int i = Integer.parseInt(id);
        if (1 <= i && i <= messages.size()) {
            return  messages.get(i - 1);
        }
        else {
            return null;
        }
    }
    
    @Override
    public void addMessage(String id, Message aMessage) {
        MessageQueue[] selectArray = findMessages(id);
        if(selectArray != null) {
            selectArray[0].add(aMessage);
        }
    }
    
    @Override
    public Message getCurrentMessage(String id) {
        MessageQueue[] selectArray = findMessages(id);
        if(selectArray != null) {
            if (selectArray[0].size() > 0) {
                return selectArray[0].peek();
            }
            else if (selectArray[1].size() > 0) {
                return selectArray[1].peek();
            }
            else {
                return null;
            }
        }
        return null;
    }
    
    @Override
    public Message removeCurrentMessage(String id) {
        MessageQueue[] selectArray = findMessages(id);
        if(selectArray != null) {
            if (selectArray[0].size() > 0) {
                return selectArray[0].remove();
            }
            else if (selectArray[1].size() > 0) {
                return selectArray[1].remove();
            }
            else {
                return null;
            }
        }
        return null;
    }
    
    @Override
    public void saveCurrentMessage(String id) {
        MessageQueue[] selectArray = findMessages(id);
        Message selectedMessage = removeCurrentMessage(id);
        if (selectedMessage != null) {
            selectArray[1].add(selectedMessage);
        }
    }
    
    private ArrayList<MessageQueue[]> messages;
}
