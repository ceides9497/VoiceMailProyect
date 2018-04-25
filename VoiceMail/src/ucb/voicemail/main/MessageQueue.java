package ucb.voicemail.main;
import java.util.ArrayList;

public class MessageQueue
{

   public MessageQueue()
   {
      queue = new ArrayList<Message>();
   }

   public Message remove()
   {
      return queue.remove(0);
   }

   public void add(Message newMessage)
   {
      queue.add(newMessage);
   }

   public int size()
   {
      return queue.size();
   }

   public Message peek()
   {
      if (queue.size() == 0) 
    	  return null;
      else 
    	  return queue.get(0);
   }

   private ArrayList<Message> queue;
}
