package ucb.voicemail.domain.usecases;

import ucb.voicemail.domain.MailboxRepository;
import ucb.voicemail.domain.boundary.input.ChangeGreetingInteractorInput;
import ucb.voicemail.domain.boundary.output.ChangeGreetingInteractorOutput;
import ucb.voicemail.domain.dto.ChangeGreetingRequest;
import ucb.voicemail.domain.dto.ChangeGreetingResponse;

public class ChangeGreetingInteractor implements ChangeGreetingInteractorInput {

	private MailboxRepository mailboxRepository;
	private ChangeGreetingInteractorOutput output;
	
	public ChangeGreetingInteractor(MailboxRepository mailboxRepository, ChangeGreetingInteractorOutput output) {
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
