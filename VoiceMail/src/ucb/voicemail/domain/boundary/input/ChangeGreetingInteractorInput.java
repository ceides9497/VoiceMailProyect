package ucb.voicemail.domain.boundary.input;

import ucb.voicemail.domain.dto.ChangeGreetingRequest;

public interface ChangeGreetingInteractorInput {
    void changeGreeting(ChangeGreetingRequest request);
}
