import java.util.ArrayList;
import java.util.List;

public class ValuableTransporter implements Runnable{
    private DepositQueue<String> deposit;
    private int amount;
    private List<String> list;
    private TreasureRoomDoor<String> treasureRoomDoor;

    public ValuableTransporter(DepositQueue<String> deposit,TreasureRoomDoor<String> treasureRoomDoor){
        this.deposit = deposit;
        this.treasureRoomDoor = treasureRoomDoor;
        amount = 5;
        list = new ArrayList<>();
    }

    @Override
    public void run() {
        while (true) {
            while (list.size() < amount) {
                String s = deposit.take();
                list.add(s);
            }
            System.out.println("ADDING TO VAULT" + list.toString());
            treasureRoomDoor.add(list.toString());
            list.clear();
            System.out.println(treasureRoomDoor.look() + " IN VAULT");
            LogSingleton.getInstance().addLog(Thread.currentThread().getName()+" emptied list");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
