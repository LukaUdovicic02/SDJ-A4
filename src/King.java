import java.util.ArrayList;

public class King implements Runnable{

    private TreasureRoomDoor<String> guardsman;
    private int amount;
    private ArrayList<String> fundsForParty;
    private boolean putBack;

    public King(TreasureRoomGuardsman<String> guardsman){
        this.guardsman = guardsman;
        fundsForParty = new ArrayList();
        putBack = false;
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
            TreasureRoom<String> treasureRoom = guardsman.acquireWrite();
            for(int x = 0; x < amount; x++){
                try{
                    //fundsForParty.add(treasureRoom.retrieve());
                    Thread.sleep(500);
                }
                catch (InterruptedException e){
                    System.out.println("Not enough funds for party");
                    putBack = true;
                    break;
                }
            }
            if(putBack){
                for (int y = 0; y < fundsForParty.size(); y++){
                    treasureRoom.add(fundsForParty.get(0));
                    fundsForParty.remove(0);
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
                System.out.println("Party time");
            }
        }
    }
}
