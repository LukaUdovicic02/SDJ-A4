import logSingleton.Log;
import utility.collection.ArrayList;
import utility.collection.ListADT;


public class King implements Runnable{

    private TreasureRoomDoor<String> guardsman;
    private int amount;
    private ListADT<String> fundsForParty;
    private boolean putBack;
    private Log log;

    public King(TreasureRoomDoor<String> guardsman){
        this.guardsman = guardsman;
        fundsForParty = new ArrayList<>();
        putBack = false;
        log = Log.getInstance();
    }

    @Override
    public void run() {
        while(true){
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            amount = (int)(Math.random()*100+50);

            TreasureRoomWrite<String> treasureRoom = guardsman.acquireWrite();
            for(int x = 0; x < amount; x++){
                try{
                    fundsForParty.add(treasureRoom.retrieve());
                    String txt = " Taking valuables for party. Personal valuables: "+fundsForParty.size();
                    log.addLog(Thread.currentThread().getName() + txt);
                }
                catch (IndexOutOfBoundsException e){
                    String txt = " Not enough valuables for party, no party today. Personal valuables: "+fundsForParty.size();
                    log.addLog(Thread.currentThread().getName() + txt);
                    putBack = true;
                    break;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(putBack){
                for (int y = 0; y < fundsForParty.size(); y++){
                    String valuable = fundsForParty.remove(0);
                    treasureRoom.add(valuable);
                    String txt = " Putting back valuables to treasure. Personal valuables: "+fundsForParty.size();
                    log.addLog(Thread.currentThread().getName() + txt);
                    try{
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
            putBack = false;
            guardsman.releaseWrite();
            if(amount == fundsForParty.size()){
                String txt = " PARTY TIME. Personal valuables: "+fundsForParty.size();
                log.addLog(Thread.currentThread().getName() + txt);
                fundsForParty = new ArrayList<>();
            }
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
