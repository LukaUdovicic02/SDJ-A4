public class King implements Runnable{

    private TreasureRoomDoor<String> treasureRoomDoor;

    public King(TreasureRoomGuardsman<String> treasureRoomGuardsman){
        treasureRoomDoor = treasureRoomGuardsman;
    }

    @Override
    public void run() {
        while(true){
            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            //treasureRoomDoor.acquireWrite();
            //treasureRoomDoor.releasWrite();
        }
    }
}
