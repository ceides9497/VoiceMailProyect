package ucb.voicemail.domain.usecases;

import ucb.voicemail.domain.Mailbox;
import ucb.voicemail.domain.MailboxRepository;
import ucb.voicemail.domain.boundary.input.LoginMailboxUseCase;
import ucb.voicemail.domain.boundary.output.LoginMailboxPresenter;
import ucb.voicemail.domain.dto.request.LoginMailboxRequest;
import ucb.voicemail.domain.dto.response.LoginMailboxResponse;

public class LoginMailboxInteractor implements LoginMailboxUseCase {

	private MailboxRepository mailboxRepository;
	private LoginMailboxPresenter output;
	
	public LoginMailboxInteractor(MailboxRepository mailboxRepository, LoginMailboxPresenter output) {
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
