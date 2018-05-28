package ucb.voicemail.domain.usecases;

import ucb.voicemail.domain.MessageRepository;
import ucb.voicemail.domain.dto.SaveCurrentMessageRequest;
import ucb.voicemail.domain.dto.SaveCurrentMessageResponse;

public class SaveCurrentMessageInteractor implements InputBoundary<SaveCurrentMessageRequest, SaveCurrentMessageResponse> {

	private MessageRepository messageRepository;
	
	public SaveCurrentMessageInteractor(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}
	
	@Override
	public SaveCurrentMessageResponse handle(SaveCurrentMessageRequest request) {
		
		String ext = request.getExt();
		
		messageRepository.saveCurrentMessage(ext);
		
		SaveCurrentMessageResponse response = new SaveCurrentMessageResponse();
		response.setExt(ext);
		response.setStatus(true);
		
		return response;
	}

}
