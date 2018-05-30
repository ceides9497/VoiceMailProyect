package ucb.voicemail.domain.connection.state;

import ucb.voicemail.domain.Connection;
import ucb.voicemail.domain.ConnectionState;
import ucb.voicemail.domain.Mailbox;
import ucb.voicemail.domain.MailboxRepository;
import ucb.voicemail.domain.boundary.input.ChangePasscodeInteractorInput;
import ucb.voicemail.domain.dto.request.ChangeGreetingRequest;
import ucb.voicemail.domain.dto.request.ChangePasscodeRequest;
import ucb.voicemail.domain.usecases.ChangePasscodeInteractor;

public class ChangePasscodeState implements ConnectionState {
	
	private ChangePasscodeInteractorInput interactor;
	
	public ChangePasscodeState(ChangePasscodeInteractorInput interactor) {
		this.interactor = interactor;
	}
	
	@Override
	public void dial(Connection connection, String key) {
	    Mailbox currentMailbox = connection.getCurrentMailbox();
        if (key.equals("#")) {
            //MailboxRepository repository = connection.getMailboxRepository();
            //repository.setMailboxPasscode(currentMailbox.getId(), connection.getAccumulatedKeys());
            connection.setConnectionState(new MailboxMenuState());
            
            ChangePasscodeRequest request = new ChangePasscodeRequest();
        	request.setExt(currentMailbox.getId());
        	request.setPasscode(connection.getAccumulatedKeys());
        	interactor.changePasscode(request);
            
            //connection.speakToAll(Connection.MAILBOX_MENU_TEXT);
            connection.setAccumulatedKeys("");
        }
        else {
            connection.addAccumulatedKeysText(key);
        }
	}
	
	@Override
    public void record(Connection connection, String voice) {
        
    }
	
	@Override
    public void hangup(Connection connection) {
        
    }
}
