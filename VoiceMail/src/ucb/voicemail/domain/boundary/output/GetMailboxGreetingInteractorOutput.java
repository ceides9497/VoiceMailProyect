package ucb.voicemail.domain.boundary.output;

import ucb.voicemail.domain.dto.response.GetMailboxGreetingResponse;

public interface GetMailboxGreetingInteractorOutput {
    void displayMailboxGreeting(GetMailboxGreetingResponse response);
    void displayGreetingError();
}
