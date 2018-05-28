package ucb.voicemail.domain.usecases;

import ucb.voicemail.domain.Mailbox;
import ucb.voicemail.domain.MailboxRepository;
import ucb.voicemail.domain.dto.LoginMailboxRequest;
import ucb.voicemail.domain.dto.LoginMailboxResponse;

public class LoginMailboxInteractor implements InputBoundary<LoginMailboxRequest, LoginMailboxResponse> {

	private MailboxRepository mailboxRepository;
	
	public LoginMailboxInteractor(MailboxRepository mailboxRepository) {
		this.mailboxRepository = mailboxRepository;
	}

	@Override
	public LoginMailboxResponse handle(LoginMailboxRequest request) {
		
		String ext = request.getExt();
		String passcode = request.getPasscode();
		
		Mailbox mailbox = mailboxRepository.findMailbox(ext);
		
		LoginMailboxResponse response = new LoginMailboxResponse();
		
		response.setExt(ext);
		if(mailbox.checkPasscode(passcode)) {
			response.setStatus(true);
		}
		else {
			response.setStatus(false);
		}
		
		return response;
	}
	
}
