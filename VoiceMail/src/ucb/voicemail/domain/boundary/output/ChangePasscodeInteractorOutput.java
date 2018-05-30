package ucb.voicemail.domain.boundary.output;

import ucb.voicemail.domain.dto.ChangePasscodeResponse;

public interface ChangePasscodeInteractorOutput {
	void displayConfirmChangePasscode(ChangePasscodeResponse response);
}
