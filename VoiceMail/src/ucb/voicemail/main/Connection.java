package ucb.voicemail.main;
import java.util.ArrayList;
import java.util.List;
/**
   Connects a phone to the mail system. The purpose of this
   class is to keep track of the state of a connection, since
   the phone itself is just a source of individual key presses.
*/
public class Connection implements Subject
{
   /**
      Construct a Connection object.
      @param s a MailSystem object
      @param p a Telephone object
   */
   public Connection(MailSystem s)
   {
      system = s;
      userInterfaces = new ArrayList<Telephone>();
      resetConnection();
   }
   
   /**
      Respond to the user's pressing a key on the phone touchpad
      @param key the phone key pressed by the user
   */
   public void dial(String key)
   {
	   connectionState.dial(this, key);
   }

   /**
      Record voice.
      @param voice voice spoken by the user
   */
   public void record(String voice)
   {
      if (state == RECORDING || state == CHANGE_GREETING)
         currentRecording += voice;
   }

   /**
      The user hangs up the phone.
   */
   public void hangup()
   {
      if (state == RECORDING)
         currentMailbox.addMessage(new Message(currentRecording));
      resetConnection();
   }

   /**
      Reset the connection to the initial state and prompt
      for mailbox number
   */
   private void resetConnection()
   {
      currentRecording = "";
      accumulatedKeys = "";
      state = CONNECTED;
      connectionState = new ConnectedState();
      speakToAll(INITIAL_PROMPT);
   }

   /**
      Try to connect the user with the specified mailbox.
      @param key the phone key pressed by the user
   */
   public void connect(String key) // private -> public
   {
      if (key.equals("#"))
      {
         currentMailbox = system.findMailbox(accumulatedKeys);
         if (currentMailbox != null)
         {
            state = RECORDING;
            connectionState = new RecordingState();
            speakToAll(currentMailbox.getGreeting());
         }
         else
        	speakToAll("Incorrect mailbox number. Try again!");
         accumulatedKeys = "";
      }
      else
         accumulatedKeys += key;
   }

   /**
      Try to log in the user.
      @param key the phone key pressed by the user
   */
   public void login(String key) // private -> public
   {
      if (key.equals("#"))
      {
         if (currentMailbox.checkPasscode(accumulatedKeys))
         {
            state = MAILBOX_MENU;
            connectionState = new MailboxMenuState();
            speakToAll(MAILBOX_MENU_TEXT);
         }
         else
        	speakToAll("Incorrect passcode. Try again!");
         accumulatedKeys = "";
      }
      else
         accumulatedKeys += key;
   }

   /**
      Change passcode.
      @param key the phone key pressed by the user
   */
   public void changePasscode(String key) // private -> public
   {
      if (key.equals("#"))
      {
         currentMailbox.setPasscode(accumulatedKeys);
         state = MAILBOX_MENU;
         connectionState = new MailboxMenuState();
         speakToAll(MAILBOX_MENU_TEXT);
         accumulatedKeys = "";
      }
      else
         accumulatedKeys += key;
   }

   /**
      Change greeting.
      @param key the phone key pressed by the user
   */
   public void changeGreeting(String key) // private -> public
   {
      if (key.equals("#"))
      {
         currentMailbox.setGreeting(currentRecording);
         currentRecording = "";
         state = MAILBOX_MENU;
         connectionState = new MailboxMenuState();
         speakToAll(MAILBOX_MENU_TEXT);
      }
   }

   /**
      Respond to the user's selection from mailbox menu.
      @param key the phone key pressed by the user
   */
   public void mailboxMenu(String key) // private -> public
   {
      if (key.equals("1"))
      {
         state = MESSAGE_MENU;
         connectionState = new MessageMenuState();
         speakToAll(MESSAGE_MENU_TEXT);
      }
      else if (key.equals("2"))
      {
         state = CHANGE_PASSCODE;
         connectionState = new ChangePasscodeState();
         speakToAll("Enter new passcode followed by the # key");
      }
      else if (key.equals("3"))
      {
         state = CHANGE_GREETING;
         connectionState = new ChangeGreetingState();
         speakToAll("Record your greeting, then press the # key");
      }
   }

   /**
      Respond to the user's selection from message menu.
      @param key the phone key pressed by the user
   */
   public void messageMenu(String key) // private -> public
   {
      if (key.equals("1"))
      {
         String output = "";
         Message m = currentMailbox.getCurrentMessage();
         if (m == null) output += "No messages." + "\n";
         else output += m.getText() + "\n";
         output += MESSAGE_MENU_TEXT;
         speakToAll(output);
      }
      else if (key.equals("2"))
      {
         currentMailbox.saveCurrentMessage();
         speakToAll(MESSAGE_MENU_TEXT);
      }
      else if (key.equals("3"))
      {
         currentMailbox.removeCurrentMessage();
         speakToAll(MESSAGE_MENU_TEXT);
      }
      else if (key.equals("4"))
      {
         state = MAILBOX_MENU;
         connectionState = new MailboxMenuState();
         speakToAll(MAILBOX_MENU_TEXT);
      }
   }

    // =========================================================================
   
	@Override
	public void addUserInterface(Telephone userInterface) {
		userInterfaces.add(userInterface);
	}
	
	@Override
	public void deleteUserInterface(Telephone userInterface) {
		userInterfaces.remove(userInterface);
	}
	
	@Override
	public void speakToAll(String output) {
		for(Telephone userInterface : userInterfaces) {
			userInterface.speak(output);
		}
	}
   
	public void start() {
		resetConnection();
	}
	
	public String getCurrentRecording() {
		return currentRecording;
	}
	
	public String getAccumulatedKeys() {
		return accumulatedKeys;
	}
	
	public ArrayList<Telephone> getUserInterfaces() {
		return userInterfaces;
	}
	
    // =========================================================================
   
   
	private MailSystem system;
   	private Mailbox currentMailbox;
   	private String currentRecording;
   	private String accumulatedKeys;
   	private ConsoleTelephone phone;
   	private int state;
    private ArrayList<Telephone> userInterfaces;
    private ConnectionState connectionState;
    
    private static final int DISCONNECTED = 0;
   	private static final int CONNECTED = 1;
   	private static final int RECORDING = 2;
   	private static final int MAILBOX_MENU = 3;
   	private static final int MESSAGE_MENU = 4;
   	private static final int CHANGE_PASSCODE = 5;
   	private static final int CHANGE_GREETING = 6;

   	private static final String INITIAL_PROMPT = 
        "Enter mailbox number followed by #";
   	
   	private static final String MAILBOX_MENU_TEXT = 
        "Enter 1 to listen to your messages\n"
        + "Enter 2 to change your passcode\n"
        + "Enter 3 to change your greeting";
   	
   	private static final String MESSAGE_MENU_TEXT = 
        "Enter 1 to listen to the current message\n"
        + "Enter 2 to save the current message\n"
        + "Enter 3 to delete the current message\n"
        + "Enter 4 to return to the main menu";
}
