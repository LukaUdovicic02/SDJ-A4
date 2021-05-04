import utility.collection.ListADT;

public class TreasureRoomWriteProxy implements TreasureRoomWrite{
    private TreasureRoomWrite treasureRoom;
    private boolean hasAccess;

    public TreasureRoomWriteProxy(TreasureRoomWrite treasureRoom){
        this.treasureRoom = treasureRoom;
        hasAccess = true;
    }

    public void restrictAccess(){
        this.hasAccess = false;
    }

    @Override
    public ListADT<Valuables> read() {
        if (hasAccess){
            return treasureRoom.read();
        }
        return null;
    }

    @Override
    public void add(Valuables element) {
        if (hasAccess){
            treasureRoom.add(element);
        }
    }

    @Override
    public Valuables retrieve() {
        if (hasAccess){
            return treasureRoom.retrieve();
        }
        return null;
    }
}
