package ucb.voicemail.domain.boundary.output;

import ucb.voicemail.domain.dto.response.ChangePasscodeResponse;

public interface ChangePasscodePresenter {
	void displayConfirmChangePasscode(ChangePasscodeResponse response);
}
