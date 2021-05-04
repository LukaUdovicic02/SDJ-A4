import logSingleton.Log;

import java.util.ArrayList;

public class King implements Runnable{

    private TreasureRoomDoor<String> guardsman;
    private int amount;
    private ArrayList<String> fundsForParty;
    private boolean putBack;
    private Log log;

    public King(TreasureRoomGuardsman<String> guardsman){
        this.guardsman = guardsman;
        fundsForParty = new ArrayList();
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
                    String txt = " Taking valuables for party";
                    log.addLog(Thread.currentThread().getName() + txt);
                    Thread.sleep(500);
                }
                catch (InterruptedException e){
                    String txt = " Not enough valuables for party, no party today";
                    log.addLog(Thread.currentThread().getName() + txt);
                    putBack = true;
                    break;
                }
            }
            if(putBack){
                for (int y = 0; y < fundsForParty.size(); y++){
                    treasureRoom.add(fundsForParty.get(0));
                    fundsForParty.remove(0);
                    String txt = " Putting back valuables to treasure";
                    log.addLog(Thread.currentThread().getName() + txt);
                    try{
                        Thread.sleep(500);
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
            guardsman.releaseWrite();
            if(amount == fundsForParty.size()){
                String txt = " PARTY TIME";
                log.addLog(Thread.currentThread().getName() + txt);
                fundsForParty.clear();
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
