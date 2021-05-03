import java.util.ArrayList;
import java.util.List;


public class testDeposit {
    public static void main(String[] args) {
        DepositQueue<String> deposit = new Deposit<>();
        Mine mine = new Mine();

        for (int i = 0; i<10;i++) {
            new Thread(new ValuablesMiner(deposit,mine),"Miner "+(i+1)).start();
        }

        for (int i = 0; i<2;i++){
            new Thread(new ValuableTransporter(deposit),"Transporter "+(i+1)).start();
        }
    }
}
