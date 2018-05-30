package ucb.voicemail.domain.boundary.output;

import ucb.voicemail.domain.dto.response.DeleteCurrentMessageResponse;

public interface DeleteCurrentMessageInteractorOutput {
	void displayConfirmDeleteCurrentMessage(DeleteCurrentMessageResponse response);
}
