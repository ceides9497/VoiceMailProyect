package ucb.voicemail.domain.boundary.output;

import ucb.voicemail.domain.dto.response.DeleteCurrentMessageResponse;

public interface DeleteCurrentMessagePresenter {
	void displayConfirmDeleteCurrentMessage(DeleteCurrentMessageResponse response);
}
