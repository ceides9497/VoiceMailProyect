package ucb.voicemail.domain.connection.state;

import ucb.voicemail.domain.Connection;
import ucb.voicemail.domain.ConnectionState;
import ucb.voicemail.domain.Mailbox;
import ucb.voicemail.domain.MailboxRepository;
import ucb.voicemail.domain.boundary.input.ChangeGreetingInteractorInput;
import ucb.voicemail.domain.dto.request.ChangeGreetingRequest;
import ucb.voicemail.domain.usecases.ChangeGreetingInteractor;
import ucb.voicemail.presentation.presenter.ConnectionPrensenter;

public class ChangeGreetingState implements ConnectionState {

	private ChangeGreetingInteractorInput interactor;
	
	public ChangeGreetingState(ChangeGreetingInteractorInput interactor) {
		this.interactor = interactor;
	}
	
	@Override
	public void dial(Connection connection, String key) {
	    Mailbox currentMailbox = connection.getCurrentMailbox();
        if (key.equals("#")) {
            //MailboxRepository repository = connection.getMailboxRepository();
            //repository.setMailboxGreeting(currentMailbox.getId(), connection.getCurrentRecording());
            
        	ChangeGreetingRequest request = new ChangeGreetingRequest();
        	request.setExt(currentMailbox.getId());
        	request.setGreeting(connection.getCurrentRecording());
        	interactor.changeGreeting(request);
        	
        	connection.setCurrentRecording("");
            connection.setConnectionState(new MailboxMenuState());
            //connection.speakToAll(Connection.MAILBOX_MENU_TEXT);
        }
	}
	
	@Override
	public void record(Connection connection, String voice) {
	    connection.addRecordingText(voice);
	}
	
	@Override
	public void hangup(Connection connection) {
	    
	}
}
