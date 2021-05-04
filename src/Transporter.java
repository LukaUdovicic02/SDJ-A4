import logSingleton.Log;
import utility.collection.ListADT;
import utility.collection.ArrayList;



public class Transporter implements Runnable{
    private DepositQueue<Valuables> deposit;
    private int amount;
    private ListADT<Valuables> list;
    private TreasureRoomDoor guardsman;

    public Transporter(DepositQueue<Valuables> deposit, TreasureRoomDoor guardsman){
        this.deposit = deposit;
        this.guardsman = guardsman;

        list = new ArrayList<>();
    }

    @Override
    public void run() {
        while (true) {
            amount = (int)(Math.random()*150+50);
            while (list.size() < amount) {
                Valuables valuable = deposit.take();
                try {
                    Thread.sleep(113);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add(valuable);
            }
            TreasureRoomWrite treasureRoom = guardsman.acquireWrite(this);
//            for(int i = 0;i < list.size();i++)
            while (!list.isEmpty())
            {
                Valuables valuable = list.remove(0);
                Log.getInstance().addLog(Thread.currentThread().getName()+" adding "+valuable+ " to the treasure room.");
                treasureRoom.add(valuable);
                try {
                    Thread.sleep(213);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.getInstance().addLog(Thread.currentThread().getName()+" done adding valuables to the treasure room. Count of valuables: "+treasureRoom.read().size());
            guardsman.releaseWrite(this);

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
