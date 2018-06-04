package ucb.voicemail.domain.usecases;

import ucb.voicemail.domain.Message;
import ucb.voicemail.domain.MessageRepository;
import ucb.voicemail.domain.boundary.input.GetLastMessageUseCase;
import ucb.voicemail.domain.boundary.output.GetLastMessagePresenter;
import ucb.voicemail.domain.dto.request.GetLastMessageRequest;
import ucb.voicemail.domain.dto.response.GetLastMessageResponse;

public class GetLastMessageInteractor implements GetLastMessageUseCase {

	private MessageRepository messageRepository;
	private GetLastMessagePresenter output;
	
	public GetLastMessageInteractor(MessageRepository messageRepository, GetLastMessagePresenter output) {
		this.messageRepository = messageRepository;
		this.output = output;
	}
	
	@Override
	public void getLastMessage(GetLastMessageRequest request) {
	    String ext = request.getExt();
		Message message = messageRepository.getCurrentMessage(ext);
		if(message != null) {
		    GetLastMessageResponse response = new GetLastMessageResponse();
		    response.setExt(ext);
			response.setMessage(message.getText());
			output.presentMessage(response);
		}
		else {
		    output.presentNotFoundMessage();
		}
	}

}
