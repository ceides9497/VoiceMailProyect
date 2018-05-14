package ucb.voicemail.repository.message;

import java.util.ArrayList;

import ucb.voicemail.domain.Message;

public class MessageQueue {
	
	private ArrayList<Message> queue;

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
