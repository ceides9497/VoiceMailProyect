package ucb.voicemail.domain.usecases;

import ucb.voicemail.domain.MessageRepository;
import ucb.voicemail.domain.boundary.input.DeleteCurrentMessageInteractorInput;
import ucb.voicemail.domain.boundary.output.DeleteCurrentMessageInteractorOutput;
import ucb.voicemail.domain.dto.DeleteCurrentMessageRequest;
import ucb.voicemail.domain.dto.DeleteCurrentMessageResponse;

public class DeleteCurrentMessageInteractor implements DeleteCurrentMessageInteractorInput {
	
	private MessageRepository messageRepository;
	private DeleteCurrentMessageInteractorOutput output;
	
	public DeleteCurrentMessageInteractor(MessageRepository messageRepository, DeleteCurrentMessageInteractorOutput output) {
		this.messageRepository = messageRepository;
		this.output = output;
	}
	
	@Override
	public void deleteCurrentMessage(DeleteCurrentMessageRequest request) {
		
		String ext = request.getExt();
		
		messageRepository.removeCurrentMessage(ext);
		
		DeleteCurrentMessageResponse response = new DeleteCurrentMessageResponse();
		
		output.displayConfirmDeleteCurrentMessage(response);
	}
}
