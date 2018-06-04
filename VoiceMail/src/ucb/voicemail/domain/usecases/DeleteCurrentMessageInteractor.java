package ucb.voicemail.domain.usecases;

import ucb.voicemail.domain.MessageRepository;
import ucb.voicemail.domain.boundary.input.DeleteCurrentMessageUseCase;
import ucb.voicemail.domain.boundary.output.DeleteCurrentMessagePresenter;
import ucb.voicemail.domain.dto.request.DeleteCurrentMessageRequest;
import ucb.voicemail.domain.dto.response.DeleteCurrentMessageResponse;

public class DeleteCurrentMessageInteractor implements DeleteCurrentMessageUseCase {
	
	private MessageRepository messageRepository;
	private DeleteCurrentMessagePresenter output;
	
	public DeleteCurrentMessageInteractor(MessageRepository messageRepository, DeleteCurrentMessagePresenter output) {
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
