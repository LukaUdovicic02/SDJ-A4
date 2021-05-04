import utility.collection.ListADT;

public class TreasureRoomReadProxy implements TreasureRoomRead{
    private TreasureRoomRead treasureRoom;
    private boolean hasAccess;

    public TreasureRoomReadProxy(TreasureRoomRead treasureRoom){
        this.treasureRoom = treasureRoom;
        this.hasAccess = true;
    }

    public void restrictAccess(){
        this.hasAccess = false;
    }

    @Override
    public ListADT<Valuables> read() {
        if (hasAccess){
            return treasureRoom.read();
        }
        else {
            return null;
        }
    }
}
