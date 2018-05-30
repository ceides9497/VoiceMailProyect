package ucb.voicemail.domain.boundary.output;

import ucb.voicemail.domain.dto.response.ChangeGreetingResponse;

public interface ChangeGreetingInteractorOutput {
    void displayConfirmChangeGreeting(ChangeGreetingResponse response);
}
