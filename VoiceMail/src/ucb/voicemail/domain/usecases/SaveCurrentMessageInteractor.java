package ucb.voicemail.domain.usecases;

import ucb.voicemail.domain.MessageRepository;
import ucb.voicemail.domain.boundary.input.SaveCurrentMessageInteractorInput;
import ucb.voicemail.domain.boundary.output.SaveCurrentMessageInteractorOutput;
import ucb.voicemail.domain.dto.request.SaveCurrentMessageRequest;
import ucb.voicemail.domain.dto.response.SaveCurrentMessageResponse;

public class SaveCurrentMessageInteractor implements SaveCurrentMessageInteractorInput {

	private MessageRepository messageRepository;
	private SaveCurrentMessageInteractorOutput output;
	
	public SaveCurrentMessageInteractor(MessageRepository messageRepository, SaveCurrentMessageInteractorOutput output) {
		this.messageRepository = messageRepository;
		this.output = output;
	}
	
	@Override
	public void saveCurrentMessage(SaveCurrentMessageRequest request) {
		
		String ext = request.getExt();
		
		messageRepository.saveCurrentMessage(ext);
		
		SaveCurrentMessageResponse response = new SaveCurrentMessageResponse();
		
		output.displayConfirmSaveCurrentMessage(response);
	}

}
