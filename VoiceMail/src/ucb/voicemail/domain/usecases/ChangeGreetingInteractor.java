package ucb.voicemail.domain.usecases;

import ucb.voicemail.domain.MailboxRepository;
import ucb.voicemail.domain.boundary.input.ChangeGreetingUseCase;
import ucb.voicemail.domain.boundary.output.ChangeGreetingPresenter;
import ucb.voicemail.domain.dto.request.ChangeGreetingRequest;
import ucb.voicemail.domain.dto.response.ChangeGreetingResponse;

public class ChangeGreetingInteractor implements ChangeGreetingUseCase {

	private MailboxRepository mailboxRepository;
	private ChangeGreetingPresenter output;
	
	public ChangeGreetingInteractor(MailboxRepository mailboxRepository, ChangeGreetingPresenter output) {
		this.mailboxRepository = mailboxRepository;
		this.output = output;
	}
	
	@Override
	public void changeGreeting(ChangeGreetingRequest request) {
		String greeting = request.getGreeting();
		String ext = request.getExt();
		mailboxRepository.setMailboxGreeting(ext, greeting);
		ChangeGreetingResponse response = new ChangeGreetingResponse();
		response.setGreeting(greeting);
		output.displayConfirmChangeGreeting(response);
	}

}
