package ucb.voicemail.domain.usecases;

import ucb.voicemail.domain.MessageRepository;
import ucb.voicemail.domain.dto.DeleteCurrentMessageRequest;
import ucb.voicemail.domain.dto.DeleteCurrentMessageResponse;

public class DeleteCurrentMessageInteractor implements InputBoundary<DeleteCurrentMessageRequest, DeleteCurrentMessageResponse> {
	
	private MessageRepository messageRepository;
	
	public DeleteCurrentMessageInteractor(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}
	
	@Override
	public DeleteCurrentMessageResponse handle(DeleteCurrentMessageRequest request) {
		
		String ext = request.getExt();
		
		messageRepository.removeCurrentMessage(ext);
		
		DeleteCurrentMessageResponse response = new DeleteCurrentMessageResponse();
		response.setExt(ext);
		response.setStatus(true);
		
		return response;
	}
}
