import java.util.Random;

public class ValuablesMiner implements Runnable{
    private Mine mine;
    private DepositQueue<String> deposit;

    public ValuablesMiner(DepositQueue<String> deposit, Mine mine) {
        this.deposit = deposit;
        this.mine = mine;
    }

    @Override
    public void run()
    {
        while(true)
        {
            String valuable = mine.getValuable();
            deposit.put(valuable);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
