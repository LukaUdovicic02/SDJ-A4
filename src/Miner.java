import java.util.Random;

public class Miner implements Runnable{
    private Mine mine;
    private DepositQueue<String> deposit;

    public Miner(DepositQueue<String> deposit, Mine mine) {
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
