package ucb.voicemail.domain.boundary.output;

import ucb.voicemail.domain.dto.response.ChangeGreetingResponse;

public interface ChangeGreetingPresenter {
    void displayConfirmChangeGreeting(ChangeGreetingResponse response);
}
