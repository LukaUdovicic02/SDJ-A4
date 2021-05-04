import logSingleton.Log;

public class Accountant implements Runnable
{
   private  TreasureRoomGuardsman<String> guard;
   private Log log;

   public Accountant(TreasureRoomGuardsman<String> guard)
   {
       this.guard = guard;
       log = Log.getInstance();
   }
    @Override
    public void run() {
        while (true){

            guard.acquireRead().read();
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int sum = 0;
            log.addLog("Total number of valuables: " + sum);
            guard.releaseRead();
            try {
                Thread.sleep(600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
