package ucb.voicemail.main;

import ucb.voicemail.main.Message;

public class Mailbox {

    public Mailbox(String aPasscode, String aGreeting) {
        passcode = aPasscode;
        greeting = aGreeting;
        newMessages = new MessageQueue();
        keptMessages = new MessageQueue();
    }

    public boolean checkPasscode(String aPasscode) {
        return aPasscode.equals(passcode);
    }

    public void addMessage(Message aMessage) {
        newMessages.add(aMessage);
    }

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

    public void saveCurrentMessage() {
        Message m = removeCurrentMessage();
        if (m != null) {
            keptMessages.add(m);
        }
    }

    public void setGreeting(String newGreeting) {
        greeting = newGreeting;
    }

    public void setPasscode(String newPasscode) {
        passcode = newPasscode;
    }

    public String getGreeting() {
        return greeting;
    }

    private MessageQueue newMessages;
    private MessageQueue keptMessages;
    private String greeting;
    private String passcode;
}
