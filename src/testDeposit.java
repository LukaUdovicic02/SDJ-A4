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



        new Thread(()->{

        }).start();

    }
}
