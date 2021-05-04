public class Miner implements Runnable{
    private Mine mine;
    private DepositQueue<Valuables> deposit;

    public Miner(DepositQueue<Valuables> deposit, Mine mine) {
        this.deposit = deposit;
        this.mine = mine;
    }

    @Override
    public void run()
    {
        while(true)
        {
            Valuables valuable = mine.getValuable();
            deposit.put(valuable);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
