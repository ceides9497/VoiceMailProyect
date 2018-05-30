package ucb.voicemail.domain.connection.state;

import ucb.voicemail.domain.Connection;
import ucb.voicemail.domain.ConnectionState;
import ucb.voicemail.domain.Mailbox;
import ucb.voicemail.domain.Message;
import ucb.voicemail.domain.MessageRepository;
import ucb.voicemail.domain.boundary.input.DeleteCurrentMessageInteractorInput;
import ucb.voicemail.domain.boundary.input.GetLastMessageInteractorInput;
import ucb.voicemail.domain.boundary.input.SaveCurrentMessageInteractorInput;
import ucb.voicemail.domain.dto.request.ChangePasscodeRequest;
import ucb.voicemail.domain.dto.request.DeleteCurrentMessageRequest;
import ucb.voicemail.domain.dto.request.GetLastMessageRequest;
import ucb.voicemail.domain.dto.request.SaveCurrentMessageRequest;
import ucb.voicemail.domain.usecases.GetLastMessageInteractor;

public class MessageMenuState implements ConnectionState {

	private GetLastMessageInteractorInput getLastMessageInteractor;
	private SaveCurrentMessageInteractorInput saveCurrentMessageInteractor;
	private DeleteCurrentMessageInteractorInput deleteCurrentMessageInteractor;
	
	public MessageMenuState(GetLastMessageInteractorInput getLastMessageInteractor,
							SaveCurrentMessageInteractorInput saveCurrentMessageInteractor,
							DeleteCurrentMessageInteractorInput deleteCurrentMessageInteractor) {
		this.getLastMessageInteractor = getLastMessageInteractor;
		this.saveCurrentMessageInteractor = saveCurrentMessageInteractor;
		this.deleteCurrentMessageInteractor = deleteCurrentMessageInteractor;
	}
	
	@Override
	public void dial(Connection connection, String key) {
	    Mailbox currentMailbox = connection.getCurrentMailbox();
        //MessageRepository messageRepository = connection.getMessageRepository();
	    if (key.equals("1")) {
            /*String output = "";
            Message m = messageRepository.getCurrentMessage(currentMailbox.getId());
            if (m == null) {
                output += "No messages." + "\n";
            }
            else {
                output += m.getText() + "\n";
            }
            output += Connection.MESSAGE_MENU_TEXT;
            connection.speakToAll(output);*/
            
            GetLastMessageRequest request = new GetLastMessageRequest();
        	request.setExt(currentMailbox.getId());
        	getLastMessageInteractor.getLastMessage(request);
            
        }
        else if (key.equals("2")) {
            /*messageRepository.saveCurrentMessage(currentMailbox.getId());
            connection.speakToAll(Connection.MESSAGE_MENU_TEXT);*/
        	
        	SaveCurrentMessageRequest request = new SaveCurrentMessageRequest();
        	request.setExt(currentMailbox.getId());
        	saveCurrentMessageInteractor.saveCurrentMessage(request);
        	
        }
        else if (key.equals("3")) {
            /*messageRepository.removeCurrentMessage(currentMailbox.getId());
            connection.speakToAll(Connection.MESSAGE_MENU_TEXT);*/
            
            DeleteCurrentMessageRequest request = new DeleteCurrentMessageRequest();
        	request.setExt(currentMailbox.getId());
        	deleteCurrentMessageInteractor.deleteCurrentMessage(request);
        }
        else if (key.equals("4")) {
            connection.setConnectionState(new MailboxMenuState());
            //connection.speakToAll(Connection.MAILBOX_MENU_TEXT);
            connection.generateConnectionPresenter().displayMailboxMenu();
        }
	}
	
	@Override
    public void record(Connection connection, String voice) {
        
    }
	
	@Override
    public void hangup(Connection connection) {
        
    }
}
