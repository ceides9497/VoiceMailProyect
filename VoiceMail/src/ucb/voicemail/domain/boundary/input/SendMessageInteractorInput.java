package ucb.voicemail.domain.boundary.input;

import ucb.voicemail.domain.dto.request.SendMessageRequest;

public interface SendMessageInteractorInput {
    void sendMessage(SendMessageRequest request);
}
