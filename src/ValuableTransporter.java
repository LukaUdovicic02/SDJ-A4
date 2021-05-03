import java.util.ArrayList;
import java.util.List;

public class ValuableTransporter implements Runnable{
    private DepositQueue<String> deposit;
    private int amount;
    private List<String> list;

    public ValuableTransporter(DepositQueue<String> deposit){
        this.deposit = deposit;
        amount = (int)(Math.random()*150+50);
        list = new ArrayList<>();
    }

    @Override
    public void run() {
        while (true) {
            while (list.size() < amount) {
                String s = deposit.take();
                list.add(s);
            }
            list.clear();
            LogSingleton.getInstance().addLog(Thread.currentThread().getName()+" emptied list");
        }
    }
}
