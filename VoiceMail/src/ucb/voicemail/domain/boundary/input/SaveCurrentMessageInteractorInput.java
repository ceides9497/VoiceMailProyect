package ucb.voicemail.domain.boundary.input;

import ucb.voicemail.domain.dto.SaveCurrentMessageRequest;

public interface SaveCurrentMessageInteractorInput {
	void saveCurrentMessage(SaveCurrentMessageRequest request);
}
