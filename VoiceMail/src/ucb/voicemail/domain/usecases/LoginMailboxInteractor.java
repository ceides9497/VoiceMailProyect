package ucb.voicemail.domain.usecases;

import ucb.voicemail.domain.Mailbox;
import ucb.voicemail.domain.MailboxRepository;
import ucb.voicemail.domain.boundary.input.LoginMailboxInteractorInput;
import ucb.voicemail.domain.boundary.output.LoginMailboxInteractorOutput;
import ucb.voicemail.domain.dto.request.LoginMailboxRequest;
import ucb.voicemail.domain.dto.response.LoginMailboxResponse;

public class LoginMailboxInteractor implements LoginMailboxInteractorInput {

	private MailboxRepository mailboxRepository;
	private LoginMailboxInteractorOutput output;
	
	public LoginMailboxInteractor(MailboxRepository mailboxRepository, LoginMailboxInteractorOutput output) {
		this.mailboxRepository = mailboxRepository;
		this.output = output;
	}

	@Override
	public void loginMailbox(LoginMailboxRequest request) {
		
		String ext = request.getExt();
		String passcode = request.getPasscode();
		
		Mailbox mailbox = mailboxRepository.findMailbox(ext);
		
		LoginMailboxResponse response = new LoginMailboxResponse();
		
		if(mailbox.checkPasscode(passcode)) {
			output.displayMailboxMenu(response);
		}
		else {
			output.displayLoginFailed();
		}

	}
	
}
