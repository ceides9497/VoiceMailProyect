package ucb.voicemail.domain.boundary.output;

import ucb.voicemail.domain.dto.response.LoginMailboxResponse;

public interface LoginMailboxPresenter {
    void displayMailboxMenu(LoginMailboxResponse response);
    void displayLoginFailed();
}
