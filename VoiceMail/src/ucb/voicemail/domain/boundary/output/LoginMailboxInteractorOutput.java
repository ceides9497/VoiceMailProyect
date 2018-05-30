package ucb.voicemail.domain.boundary.output;

import ucb.voicemail.domain.dto.response.LoginMailboxResponse;

public interface LoginMailboxInteractorOutput {
    void displayMailboxMenu(LoginMailboxResponse response);
    void displayLoginFailed();
}
