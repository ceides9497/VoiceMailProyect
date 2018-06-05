package ucb.voicemail.domain.boundary.output;

import ucb.voicemail.domain.dto.response.SendMessageResponse;

public interface SendMessagePresenter {
    void displayConfirmSendMessage(SendMessageResponse response);
}
