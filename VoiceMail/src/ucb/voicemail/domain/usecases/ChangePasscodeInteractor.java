package ucb.voicemail.domain.usecases;

import ucb.voicemail.domain.MailboxRepository;
import ucb.voicemail.domain.boundary.input.ChangePasscodeInteractorInput;
import ucb.voicemail.domain.boundary.output.ChangePasscodeInteractorOutput;
import ucb.voicemail.domain.dto.request.ChangePasscodeRequest;
import ucb.voicemail.domain.dto.response.ChangePasscodeResponse;

public class ChangePasscodeInteractor implements ChangePasscodeInteractorInput {

	private MailboxRepository mailboxRepository;
	private ChangePasscodeInteractorOutput output;
	
	public ChangePasscodeInteractor(MailboxRepository mailboxRepository, ChangePasscodeInteractorOutput output) {
		this.mailboxRepository = mailboxRepository;
		this.output = output;
	}
	
	@Override
	public void changePasscode(ChangePasscodeRequest request) {
		
		String passcode = request.getPasscode();
		String ext = request.getExt();
		
		mailboxRepository.setMailboxPasscode(ext, passcode);
		ChangePasscodeResponse response = new ChangePasscodeResponse();
		
		output.displayConfirmChangePasscode(response);
	}
		
}
