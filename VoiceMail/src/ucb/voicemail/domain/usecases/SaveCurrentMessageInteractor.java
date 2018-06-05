package ucb.voicemail.domain.usecases;

import ucb.voicemail.domain.MessageRepository;
import ucb.voicemail.domain.boundary.input.SaveCurrentMessageUseCase;
import ucb.voicemail.domain.boundary.output.SaveCurrentMessagePresenter;
import ucb.voicemail.domain.dto.request.SaveCurrentMessageRequest;
import ucb.voicemail.domain.dto.response.SaveCurrentMessageResponse;

public class SaveCurrentMessageInteractor implements SaveCurrentMessageUseCase {

	private MessageRepository messageRepository;
	private SaveCurrentMessagePresenter output;
	
	public SaveCurrentMessageInteractor(MessageRepository messageRepository, SaveCurrentMessagePresenter output) {
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
