package ucb.voicemail.main;

import java.util.ArrayList;

import ucb.voicemail.main.Message;

public class NonPersistentMessageRepository {
	
	private ArrayList<Message> queue;

	public NonPersistentMessageRepository() {
		queue = new ArrayList<Message>();
	}

	//REMOVE FIRST MESSAGE
	public Message remove() {
		return queue.remove(0);
	}

	// ADD LAST MESSAGE
	public void add(Message newMessage) {
		queue.add(newMessage);
	}

	// GET COUNT OF MESSAGES
	public int size() {
		return queue.size();
	}

	// GET FIRST MESSAGE OR NULL
	public Message peek() {
		if (queue.size() == 0) {
			return null;
		} else {
			return queue.get(0);
		}
	}

}
