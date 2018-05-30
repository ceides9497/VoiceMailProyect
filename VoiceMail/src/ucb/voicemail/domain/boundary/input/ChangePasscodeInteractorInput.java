package ucb.voicemail.domain.boundary.input;

import ucb.voicemail.domain.dto.request.ChangePasscodeRequest;

public interface ChangePasscodeInteractorInput {
	void changePasscode(ChangePasscodeRequest request);
}
