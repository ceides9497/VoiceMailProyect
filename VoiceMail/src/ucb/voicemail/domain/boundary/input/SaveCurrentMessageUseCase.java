package ucb.voicemail.domain.boundary.input;

import ucb.voicemail.domain.dto.request.SaveCurrentMessageRequest;

public interface SaveCurrentMessageUseCase {
	void saveCurrentMessage(SaveCurrentMessageRequest request);
}
