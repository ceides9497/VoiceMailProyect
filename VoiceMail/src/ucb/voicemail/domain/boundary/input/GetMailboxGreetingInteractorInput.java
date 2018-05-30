package ucb.voicemail.domain.boundary.input;

import ucb.voicemail.domain.dto.request.GetMailboxGreetingRequest;

public interface GetMailboxGreetingInteractorInput {
    void getMailboxGreeting(GetMailboxGreetingRequest request);
}
