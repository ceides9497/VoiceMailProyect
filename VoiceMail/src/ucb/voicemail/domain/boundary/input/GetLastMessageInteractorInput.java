package ucb.voicemail.domain.boundary.input;

import ucb.voicemail.domain.dto.GetLastMessageRequest;

public interface GetLastMessageInteractorInput {
    void getLastMessage(GetLastMessageRequest request);
}
