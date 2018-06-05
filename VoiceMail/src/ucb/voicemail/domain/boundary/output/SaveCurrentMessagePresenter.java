package ucb.voicemail.domain.boundary.output;

import ucb.voicemail.domain.dto.response.SaveCurrentMessageResponse;

public interface SaveCurrentMessagePresenter {
	void displayConfirmSaveCurrentMessage(SaveCurrentMessageResponse response);
}
