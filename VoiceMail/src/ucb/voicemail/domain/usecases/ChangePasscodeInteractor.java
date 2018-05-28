package ucb.voicemail.domain.usecases;

import ucb.voicemail.domain.MailboxRepository;
import ucb.voicemail.domain.dto.ChangePasscodeRequest;
import ucb.voicemail.domain.dto.ChangePasscodeResponse;

public class ChangePasscodeInteractor implements InputBoundary<ChangePasscodeRequest, ChangePasscodeResponse> {

	private MailboxRepository mailboxRepository;
	
	public ChangePasscodeInteractor(MailboxRepository mailboxRepository) {
		this.mailboxRepository = mailboxRepository;
	}
	
	@Override
	public ChangePasscodeResponse handle(ChangePasscodeRequest request) {
		
		String passcode = request.getPasscode();
		String ext = request.getExt();
		
		mailboxRepository.setMailboxPasscode(ext, passcode);
		
		ChangePasscodeResponse response = new ChangePasscodeResponse();
		response.setStatus(true);
		
		return response;
	}
		
}
