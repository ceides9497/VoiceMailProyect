package ucb.voicemail.domain.boundary.input;

import ucb.voicemail.domain.dto.request.GetLastMessageRequest;

public interface GetLastMessageInteractorInput {
    void getLastMessage(GetLastMessageRequest request);
}
