package ucb.voicemail.domain.boundary.output;

import ucb.voicemail.domain.dto.SendMessageResponse;

public interface SendMessageInteractorOutput {
    void displayConfirmSendMessage(SendMessageResponse response);
}
