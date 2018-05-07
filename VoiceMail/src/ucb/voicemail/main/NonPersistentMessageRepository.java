package ucb.voicemail.main;

import java.util.ArrayList;

import ucb.voicemail.main.Message;

public class NonPersistentMessageRepository implements MessageRepository {
	
	private ArrayList<Message> queue;

	public NonPersistentMessageRepository() {
		queue = new ArrayList<Message>();
	}

	@Override
	public Message remove() {
		return queue.remove(0);
	}

	@Override
	public void add(Message newMessage) {
		queue.add(newMessage);
	}

	@Override
	public int size() {
		return queue.size();
	}

	@Override
	public Message peek() {
		if (queue.size() == 0) {
			return null;
		} else {
			return queue.get(0);
		}
	}

}
