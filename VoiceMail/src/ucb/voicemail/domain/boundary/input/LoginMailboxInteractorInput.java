package ucb.voicemail.domain.boundary.input;

import ucb.voicemail.domain.dto.LoginMailboxRequest;

public interface LoginMailboxInteractorInput {
    void loginMailbox(LoginMailboxRequest request);
}
