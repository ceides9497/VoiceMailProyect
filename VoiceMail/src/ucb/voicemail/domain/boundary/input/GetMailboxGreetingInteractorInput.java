package ucb.voicemail.domain.boundary.input;

import ucb.voicemail.domain.dto.GetMailboxGreetingRequest;

public interface GetMailboxGreetingInteractorInput {
    void getMailboxGreeting(GetMailboxGreetingRequest request);
}
