import logSingleton.Log;
import treasureRoom.TreasureRoomDoor;
import treasureRoom.TreasureRoomWrite;
import utility.collection.ListADT;
import utility.collection.ArrayList;



public class Transporter implements Runnable{
    private DepositQueue<String> deposit;
    private int amount;
    private ListADT<String> list;
    private TreasureRoomDoor<String> guardsman;

    public Transporter(DepositQueue<String> deposit, TreasureRoomDoor<String> guardsman){
        this.deposit = deposit;
        this.guardsman = guardsman;

        list = new ArrayList<>();
    }

    @Override
    public void run() {
        while (true) {
            amount = (int)(Math.random()*150+50);
            while (list.size() < amount) {
                String s = deposit.take();
                try {
                    Thread.sleep(113);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add(s);
            }
            TreasureRoomWrite<String> treasureRoom = guardsman.acquireWrite();
//            for(int i = 0;i < list.size();i++)
            while (!list.isEmpty())
            {
                String valuable = list.remove(0);
                Log.getInstance().addLog(Thread.currentThread().getName()+" adding "+valuable+ " to the treasure room.");
                treasureRoom.add(valuable);
                try {
                    Thread.sleep(213);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.getInstance().addLog(Thread.currentThread().getName()+" done adding valuables to the treasure room. Count of valuables: "+treasureRoom.read().size());
            guardsman.releaseWrite();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
