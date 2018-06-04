package ucb.voicemail.domain.boundary.output;

import ucb.voicemail.domain.dto.response.GetLastMessageResponse;

public interface GetLastMessagePresenter {
    void presentMessage(GetLastMessageResponse response);
    void presentNotFoundMessage();
}
