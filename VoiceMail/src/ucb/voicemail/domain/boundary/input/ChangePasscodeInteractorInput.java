package ucb.voicemail.domain.boundary.input;

import ucb.voicemail.domain.dto.ChangePasscodeRequest;

public interface ChangePasscodeInteractorInput {
	void changePasscode(ChangePasscodeRequest request);
}
