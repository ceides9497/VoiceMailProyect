package ucb.voicemail.domain.usecases;

import ucb.voicemail.domain.Message;
import ucb.voicemail.domain.MessageRepository;
import ucb.voicemail.domain.boundary.input.SendMessageUseCase;
import ucb.voicemail.domain.boundary.output.SendMessagePresenter;
import ucb.voicemail.domain.dto.request.SendMessageRequest;
import ucb.voicemail.domain.dto.response.SendMessageResponse;

public class SendMessageInteractor implements SendMessageUseCase {

	private MessageRepository messageRepository;
	private SendMessagePresenter output;
	
	public SendMessageInteractor(MessageRepository messageRepository, SendMessagePresenter output) {
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
