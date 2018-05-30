package ucb.voicemail.domain.boundary.output;

import ucb.voicemail.domain.dto.response.GetLastMessageResponse;

public interface GetLastMessageInteractorOutput {
    void presentMessage(GetLastMessageResponse response);
    void presentNotFoundMessage();
}
