package ucb.voicemail.main;
public class Message
{
   public Message(String messageText)
   {
      text = messageText;
   }

   public String getText()
   {
      return text;
   }

   private String text;
}
