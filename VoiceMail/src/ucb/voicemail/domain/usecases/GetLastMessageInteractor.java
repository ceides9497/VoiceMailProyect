package ucb.voicemail.domain.usecases;

import ucb.voicemail.domain.Message;
import ucb.voicemail.domain.MessageRepository;
import ucb.voicemail.domain.dto.GetLastMessageRequest;
import ucb.voicemail.domain.dto.GetLastMessageResponse;

public class GetLastMessageInteractor implements InputBoundary<GetLastMessageRequest, GetLastMessageResponse> {

	private MessageRepository messageRepository;
	
	public GetLastMessageInteractor(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}
	
	@Override
	public GetLastMessageResponse handle(GetLastMessageRequest request) {
		
		String ext = request.getExt();
		
		Message message = messageRepository.getCurrentMessage(ext);
		
		GetLastMessageResponse response = new GetLastMessageResponse();
		
		response.setExt(ext);
		if(message != null) {
			response.setFounded(true);
			response.setMessage(message.getText());
		}
		else {
			response.setFounded(false);
			response.setMessage(null);
		}
		
		return response;
	}

}
