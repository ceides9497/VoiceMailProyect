package ucb.voicemail.domain.usecases;

import ucb.voicemail.domain.Mailbox;
import ucb.voicemail.domain.MailboxRepository;
import ucb.voicemail.domain.boundary.input.GetMailboxGreetingInteractorInput;
import ucb.voicemail.domain.boundary.output.GetMailboxGreetingInteractorOutput;
import ucb.voicemail.domain.dto.request.GetMailboxGreetingRequest;
import ucb.voicemail.domain.dto.response.GetMailboxGreetingResponse;

public class GetMailboxGreetingInteractor implements GetMailboxGreetingInteractorInput {

	private MailboxRepository mailboxRepository;
	private GetMailboxGreetingInteractorOutput output;
	
	public GetMailboxGreetingInteractor(MailboxRepository mailboxRepository, GetMailboxGreetingInteractorOutput output) {
		this.mailboxRepository = mailboxRepository;
		this.output = output;
	}
	
	@Override
	public void getMailboxGreeting(GetMailboxGreetingRequest request) {
		String ext = request.getExt();
		Mailbox mailbox = mailboxRepository.findMailbox(ext);
		if(mailbox != null) {
		    GetMailboxGreetingResponse response = new GetMailboxGreetingResponse();
	        response.setGreeting(mailbox.getGreeting());
	        output.displayMailboxGreeting(response);
		}
		else {
		    output.displayGreetingError();
		}
	}

}
