import logSingleton.Log;
import treasureRoom.TreasureRoomDoor;
import treasureRoom.TreasureRoomRead;
import utility.collection.ArrayList;

public class Accountant implements Runnable
{
   private TreasureRoomDoor<String> guard;
   private Log log;

   public Accountant(TreasureRoomDoor<String> guard)
   {
       this.guard = guard;
       log = Log.getInstance();
   }
    @Override
    public void run() {
        while (true){

            TreasureRoomRead<String> treasureRoom = guard.acquireRead();
            ArrayList<String> valuables = (ArrayList<String>) treasureRoom.read();
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
