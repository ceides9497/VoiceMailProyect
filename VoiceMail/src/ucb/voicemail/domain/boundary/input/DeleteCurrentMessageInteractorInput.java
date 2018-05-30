package ucb.voicemail.domain.boundary.input;

import ucb.voicemail.domain.dto.request.DeleteCurrentMessageRequest;

public interface DeleteCurrentMessageInteractorInput {
	void deleteCurrentMessage(DeleteCurrentMessageRequest request);
}
