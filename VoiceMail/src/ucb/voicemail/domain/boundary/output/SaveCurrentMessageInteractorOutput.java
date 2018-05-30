package ucb.voicemail.domain.boundary.output;

import ucb.voicemail.domain.dto.response.SaveCurrentMessageResponse;

public interface SaveCurrentMessageInteractorOutput {
	void displayConfirmSaveCurrentMessage(SaveCurrentMessageResponse response);
}
