package ucb.voicemail.main;

import ucb.voicemail.main.ChangeGreetingState;
import ucb.voicemail.main.ChangePasscodeState;
import ucb.voicemail.main.ConnectedState;
import ucb.voicemail.main.Connection;
import ucb.voicemail.main.ConnectionState;
import ucb.voicemail.main.ConsoleTelephone;
import ucb.voicemail.main.GraphicalTelephone;
import ucb.voicemail.main.Mailbox;
import ucb.voicemail.main.MailboxMenuState;
import ucb.voicemail.main.MailSystem;
import ucb.voicemail.main.MailSystemTester;
import ucb.voicemail.main.MainMenu;
import ucb.voicemail.main.Message;
import ucb.voicemail.main.MessageMenuState;
import ucb.voicemail.main.MessageQueue;
import ucb.voicemail.main.RecordingState;
import ucb.voicemail.main.Subject;
import ucb.voicemail.main.Telephone;

/**
   A mailbox contains messages that can be listed, kept or discarded.
*/
public class Mailbox
{
   /**
      Creates Mailbox object.
      @param aPasscode passcode number
      @param aGreeting greeting string
   */
   public Mailbox(String aPasscode, String aGreeting)
   {
      passcode = aPasscode;
      greeting = aGreeting;
      newMessages = new MessageQueue();
      keptMessages = new MessageQueue();
   }

   /**
      Check if the passcode is correct.
      @param aPasscode a passcode to check
      @return true if the supplied passcode matches the mailbox passcode
   */
   public boolean checkPasscode(String aPasscode)
   {
      return aPasscode.equals(passcode);
   }

   /**
      Add a message to the mailbox.
      @param aMessage the message to be added
   */
   public void addMessage(Message aMessage)
   {
      newMessages.add(aMessage);
   }

   /**
      Get the current message.
      @return the current message
   */
   public Message getCurrentMessage()
   {
      if (newMessages.size() > 0)
         return newMessages.peek();
      else if (keptMessages.size() > 0)
         return keptMessages.peek();
      else
         return null;
   }

   /**
      Remove the current message from the mailbox.
      @return the message that has just been removed
   */
   public Message removeCurrentMessage()
   {
      if (newMessages.size() > 0)
         return newMessages.remove();
      else if (keptMessages.size() > 0)
         return keptMessages.remove();
      else
         return null;
   }

   /**
      Save the current message
   */
   public void saveCurrentMessage()
   {
      Message m = removeCurrentMessage();
      if (m != null)
         keptMessages.add(m);
   }

   /**
      Change mailbox's greeting.
      @param newGreeting the new greeting string
   */
   public void setGreeting(String newGreeting)
   {
      greeting = newGreeting;
   }

   /**
      Change mailbox's passcode.
      @param newPasscode the new passcode
   */
   public void setPasscode(String newPasscode)
   {
      passcode = newPasscode;
   }

   /**
      Get the mailbox's greeting.
      @return the greeting
   */
   public String getGreeting()
   {
      return greeting;
   }

   private MessageQueue newMessages;
   private MessageQueue keptMessages;
   private String greeting;
   private String passcode;
}
