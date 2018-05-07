package ucb.voicemail.main;

import java.util.ArrayList;
import java.util.List;

import ucb.voicemail.main.ChangeGreetingState;
import ucb.voicemail.main.ChangePasscodeState;
import ucb.voicemail.main.ConnectedState;
import ucb.voicemail.main.Connection;
import ucb.voicemail.main.ConnectionState;
import ucb.voicemail.main.ConsoleTelephone;
import ucb.voicemail.main.GraphicalTelephone;
import ucb.voicemail.main.Mailbox;
import ucb.voicemail.main.MailboxMenuState;
import ucb.voicemail.main.MailSystem;
import ucb.voicemail.main.MailSystemTester;
import ucb.voicemail.main.MainMenu;
import ucb.voicemail.main.Message;
import ucb.voicemail.main.MessageMenuState;
import ucb.voicemail.main.MessageQueue;
import ucb.voicemail.main.RecordingState;
import ucb.voicemail.main.Subject;
import ucb.voicemail.main.Telephone;

public class MessageQueue {
	
	private List<Message> queue;

	public MessageQueue() {
		queue = new ArrayList<Message>();
	}

	public Message remove() {
		return queue.remove(0);
	}

	public void add(Message newMessage) {
		queue.add(newMessage);
	}

	public int size() {
		return queue.size();
	}

	public Message peek() {
		if (queue.size() == 0) {
			return null;
		} else {
			return queue.get(0);
		}
	}

}
