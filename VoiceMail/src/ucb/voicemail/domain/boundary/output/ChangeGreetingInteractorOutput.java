package ucb.voicemail.domain.boundary.output;

import ucb.voicemail.domain.dto.ChangeGreetingResponse;

public interface ChangeGreetingInteractorOutput {
    void displayConfirmChangeGreeting(ChangeGreetingResponse response);
}
