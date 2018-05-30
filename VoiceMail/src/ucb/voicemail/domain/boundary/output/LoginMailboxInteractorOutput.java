package ucb.voicemail.domain.boundary.output;

import ucb.voicemail.domain.dto.LoginMailboxResponse;

public interface LoginMailboxInteractorOutput {
    void displayMailboxMenu(LoginMailboxResponse response);
    void displayLoginFailed();
}
