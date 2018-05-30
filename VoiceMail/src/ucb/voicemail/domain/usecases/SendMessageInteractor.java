package ucb.voicemail.domain.usecases;

import ucb.voicemail.domain.Message;
import ucb.voicemail.domain.MessageRepository;
import ucb.voicemail.domain.boundary.input.SendMessageInteractorInput;
import ucb.voicemail.domain.boundary.output.SendMessageInteractorOutput;
import ucb.voicemail.domain.dto.request.SendMessageRequest;
import ucb.voicemail.domain.dto.response.SendMessageResponse;

public class SendMessageInteractor implements SendMessageInteractorInput {

	private MessageRepository messageRepository;
	private SendMessageInteractorOutput output;
	
	public SendMessageInteractor(MessageRepository messageRepository, SendMessageInteractorOutput output) {
		this.messageRepository = messageRepository;
		this.output = output;
	}
	
	@Override
	public void sendMessage(SendMessageRequest request) {
		String ext = request.getExt();
		Message message = new Message(request.getMessage());
		messageRepository.addMessage(ext, message);
		SendMessageResponse response = new SendMessageResponse();
		response.setMessage(message.getText());
		output.displayConfirmSendMessage(response);
	}

}
