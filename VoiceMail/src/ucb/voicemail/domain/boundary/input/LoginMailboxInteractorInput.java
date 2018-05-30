package ucb.voicemail.domain.boundary.input;

import ucb.voicemail.domain.dto.request.LoginMailboxRequest;

public interface LoginMailboxInteractorInput {
    void loginMailbox(LoginMailboxRequest request);
}
