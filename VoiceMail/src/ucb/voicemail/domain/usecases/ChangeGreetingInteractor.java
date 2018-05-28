package ucb.voicemail.domain.usecases;

import ucb.voicemail.domain.MailboxRepository;
import ucb.voicemail.domain.dto.ChangeGreetingRequest;
import ucb.voicemail.domain.dto.ChangeGreetingResponse;

public class ChangeGreetingInteractor implements InputBoundary<ChangeGreetingRequest, ChangeGreetingResponse>{

	private MailboxRepository mailboxRepository;
	
	public ChangeGreetingInteractor(MailboxRepository mailboxRepository) {
		this.mailboxRepository = mailboxRepository;
	}
	
	@Override
	public ChangeGreetingResponse handle(ChangeGreetingRequest request) {
		
		String greeting = request.getGreeting();
		String ext = request.getExt();
		
		mailboxRepository.setMailboxGreeting(ext, greeting);
		
		ChangeGreetingResponse response = new ChangeGreetingResponse();
		response.setGreeting(greeting);
		response.setStatus(true);
		
		return response;
	}

}
