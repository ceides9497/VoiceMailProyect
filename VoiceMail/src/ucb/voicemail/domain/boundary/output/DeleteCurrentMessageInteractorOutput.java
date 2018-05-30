package ucb.voicemail.domain.boundary.output;

import ucb.voicemail.domain.dto.DeleteCurrentMessageResponse;

public interface DeleteCurrentMessageInteractorOutput {
	void displayConfirmDeleteCurrentMessage(DeleteCurrentMessageResponse response);
}
