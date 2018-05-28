package ucb.voicemail.domain.usecases;

import ucb.voicemail.domain.Mailbox;
import ucb.voicemail.domain.MailboxRepository;
import ucb.voicemail.domain.dto.GetMailboxGreetingRequest;
import ucb.voicemail.domain.dto.GetMailboxGreetingResponse;

public class GetMailboxGreetingInteractor implements InputBoundary<GetMailboxGreetingRequest, GetMailboxGreetingResponse> {

	private MailboxRepository mailboxRepository;
	
	public GetMailboxGreetingInteractor(MailboxRepository mailboxRepository) {
		this.mailboxRepository = mailboxRepository;
	}
	
	@Override
	public GetMailboxGreetingResponse handle(GetMailboxGreetingRequest request) {
		
		String ext = request.getExt();
		
		Mailbox mailbox = mailboxRepository.findMailbox(ext);
		
		GetMailboxGreetingResponse response = new GetMailboxGreetingResponse();
		response.setExt(ext);
		response.setGreeting(mailbox.getGreeting());
		
		return response;
	}

}

/*
 * get("/mailbox", (request, response) -> {
 *   
 *   GetMailboxGreetingRequest req = new GetMailboxGreetingRequest();
 *   req.setExt( request.get("ext") );
 *   
 *   OutputBoundary outputBoundary = new OutputBoundary(response);
 *   GetMailboxGreetingInteractor interactor = new GetMailboxGreetingInteractor(new SQLiteMailboxRepository);
 *   interactor.setPresenter(outputBoundary);
 *   
 *   return interactor.handle(req);
 * })
 * 
 * */
