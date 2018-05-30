package ucb.voicemail.domain.boundary.input;

import ucb.voicemail.domain.dto.DeleteCurrentMessageRequest;

public interface DeleteCurrentMessageInteractorInput {
	void deleteCurrentMessage(DeleteCurrentMessageRequest request);
}
