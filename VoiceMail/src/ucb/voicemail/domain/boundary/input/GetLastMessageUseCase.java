package ucb.voicemail.domain.boundary.input;

import ucb.voicemail.domain.dto.request.GetLastMessageRequest;

public interface GetLastMessageUseCase {
    void getLastMessage(GetLastMessageRequest request);
}
