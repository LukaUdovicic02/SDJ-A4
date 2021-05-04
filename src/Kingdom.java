import treasureRoom.TreasureRoom;
import treasureRoom.TreasureRoomDoor;
import treasureRoom.TreasureRoomGuardsman;
import treasureRoom.Valuables;

public class Kingdom {
    public static void main(String[] args) {
        DepositQueue<Valuables> deposit = new Deposit<>();
        Mine mine = new Mine();
        TreasureRoom treasureRoom = new TreasureRoom();
        TreasureRoomDoor TreasureRoomGuardsman = new TreasureRoomGuardsman(treasureRoom);



        // Miners
        for (int i = 0; i<5;i++) {
            new Thread(new Miner(deposit,mine),"Miner "+(i+1)).start();
        }

        // Transporters
        for (int i = 0; i<2;i++) {
            new Thread(new Transporter(deposit, TreasureRoomGuardsman), "Transporter " + (i + 1)).start();
        }

        // Accountants
        for (int i = 0; i<2;i++) {
            new Thread(new Accountant(TreasureRoomGuardsman), "Accountant " + (i + 1)).start();
        }

        // King
        new Thread(new King(TreasureRoomGuardsman), "King").start();
    }
}
