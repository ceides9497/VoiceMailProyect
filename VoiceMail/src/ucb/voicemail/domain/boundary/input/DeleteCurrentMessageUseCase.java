package ucb.voicemail.domain.boundary.input;

import ucb.voicemail.domain.dto.request.DeleteCurrentMessageRequest;

public interface DeleteCurrentMessageUseCase {
	void deleteCurrentMessage(DeleteCurrentMessageRequest request);
}
