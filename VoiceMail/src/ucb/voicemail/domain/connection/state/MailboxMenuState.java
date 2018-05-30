package ucb.voicemail.domain.connection.state;

import ucb.voicemail.domain.Connection;
import ucb.voicemail.domain.ConnectionState;
import ucb.voicemail.domain.usecases.ChangeGreetingInteractor;
import ucb.voicemail.domain.usecases.ChangePasscodeInteractor;
import ucb.voicemail.domain.usecases.DeleteCurrentMessageInteractor;
import ucb.voicemail.domain.usecases.GetLastMessageInteractor;
import ucb.voicemail.domain.usecases.SaveCurrentMessageInteractor;

public class MailboxMenuState implements ConnectionState {

	@Override
	public void dial(Connection connection, String key) {
		if (key.equals("1")) {
	        connection.setConnectionState(
	        		new MessageMenuState(
	        				new GetLastMessageInteractor(
	        						connection.getMessageRepository(), 
	        						connection.generateConnectionPresenter()
	        				),
	        				new SaveCurrentMessageInteractor(
	        						connection.getMessageRepository(),
	        						connection.generateConnectionPresenter())
	        				,
	        				new DeleteCurrentMessageInteractor(
	        						connection.getMessageRepository(), 
	        						connection.generateConnectionPresenter()
    						)
	        		)
    		);
	        //connection.speakToAll(Connection.MESSAGE_MENU_TEXT);
	        connection.generateConnectionPresenter().displayMessageMenu();
        }
        else if (key.equals("2")) {
            connection.setConnectionState(
            		new ChangePasscodeState(
            				new ChangePasscodeInteractor(
            						connection.getMailboxRepository(), 
            						connection.generateConnectionPresenter()
            				)
            		)		
    		);
            connection.generateConnectionPresenter().displayPasscodeForm();
            //connection.speakToAll("Enter new passcode followed by the # key");
        }
        else if (key.equals("3")) {
            connection.setConnectionState(
            	new ChangeGreetingState(
            		new ChangeGreetingInteractor(
            				connection.getMailboxRepository(), 
            				connection.generateConnectionPresenter()
            		)
            	)
            );
            connection.generateConnectionPresenter().displayGreetingForm();
            //connection.speakToAll("Record your greeting, then press the # key");
        }
	}
	
	@Override
    public void record(Connection connection, String voice) {
        
    }
	
	@Override
    public void hangup(Connection connection) {
        
    }
}
