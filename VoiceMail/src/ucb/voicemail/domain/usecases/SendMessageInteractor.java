package ucb.voicemail.domain.usecases;

import ucb.voicemail.domain.Message;
import ucb.voicemail.domain.MessageRepository;
import ucb.voicemail.domain.dto.SendMessageRequest;
import ucb.voicemail.domain.dto.SendMessageResponse;

public class SendMessageInteractor implements InputBoundary<SendMessageRequest, SendMessageResponse> {

	private MessageRepository messageRepository;
	
	public SendMessageInteractor(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}
	
	@Override
	public SendMessageResponse handle(SendMessageRequest request) {
		
		String ext = request.getExt();
		Message message = new Message(request.getMessage());
		
		messageRepository.addMessage(ext, message);
		
		SendMessageResponse response = new SendMessageResponse();
		response.setStatus(true);
		response.setMessage(message.getText());
		return response;
	}

}
