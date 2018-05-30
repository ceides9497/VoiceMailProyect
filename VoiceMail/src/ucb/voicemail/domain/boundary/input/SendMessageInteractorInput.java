package ucb.voicemail.domain.boundary.input;

import ucb.voicemail.domain.dto.SendMessageRequest;

public interface SendMessageInteractorInput {
    void sendMessage(SendMessageRequest request);
}
