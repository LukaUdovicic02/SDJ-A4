import java.util.ArrayList;
import java.util.List;


public class testDeposit {
    public static void main(String[] args) {
        DepositQueue<String> deposit = new Deposit<>();

        for (int i = 0; i<5;i++) {
            new Thread(() -> {
                while (true) {
                    deposit.put("Diamond");
                    try {
                        Thread.sleep(435);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        int amount = (int)(Math.random()*150+50);
        List<String> list = new ArrayList<>();

        new Thread(()->{
            while (true) {
                while (list.size() < amount) {
                    String s = deposit.take();
                    list.add(s);
                }
                list.clear();
                LogSingleton.getInstance().addLog(Thread.currentThread().getName()+" emptied list");
            }
        }).start();

    }
}
