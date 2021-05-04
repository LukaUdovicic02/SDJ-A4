import logSingleton.Log;
import utility.collection.ArrayList;

public class Accountant implements Runnable
{
   private TreasureRoomDoor guard;
   private Log log;

   public Accountant(TreasureRoomDoor guard)
   {
       this.guard = guard;
       log = Log.getInstance();
   }
    @Override
    public void run() {
        while (true){

            TreasureRoomRead treasureRoom = guard.acquireRead();
            ArrayList<Valuables> valuables = (ArrayList<Valuables>) treasureRoom.read();
            int count = 0;
            for (int i = 0; i < valuables.size();i++) {
                count++;
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String txt = " valuables in treasure room count: "+count;
            log.addLog(Thread.currentThread().getName()+txt);
            guard.releaseRead();
            try {
                Thread.sleep(1600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
