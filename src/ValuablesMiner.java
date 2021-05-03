import java.util.Random;

public class ValuablesMiner implements Runnable{

    private Mine mine;

    @Override
    public void run()
    {
        while(true)
        {
            mine.getValuable();

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
