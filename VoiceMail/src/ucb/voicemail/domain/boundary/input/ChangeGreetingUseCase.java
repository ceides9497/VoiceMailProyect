package ucb.voicemail.domain.boundary.input;

import ucb.voicemail.domain.dto.request.ChangeGreetingRequest;

public interface ChangeGreetingUseCase {
    void changeGreeting(ChangeGreetingRequest request);
}
