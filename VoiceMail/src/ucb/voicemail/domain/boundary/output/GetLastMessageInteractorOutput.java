package ucb.voicemail.domain.boundary.output;

import ucb.voicemail.domain.dto.GetLastMessageResponse;

public interface GetLastMessageInteractorOutput {
    void presentMessage(GetLastMessageResponse response);
    void presentNotFoundMessage();
}
