package treasureRoom;

import utility.collection.ListADT;

public class TreasureRoomWriteProxy<T> implements TreasureRoomWrite<T>{
    private TreasureRoomWrite<T> treasureRoom;
    private boolean hasAccess;

    public TreasureRoomWriteProxy(TreasureRoomWrite<T> treasureRoom){
        this.treasureRoom = treasureRoom;
        hasAccess = true;
    }

    public void restrictAccess(){
        this.hasAccess = false;
    }

    @Override
    public ListADT<T> read() {
        if (hasAccess){
            return treasureRoom.read();
        }
        return null;
    }

    @Override
    public void add(T element) {
        if (hasAccess){
            treasureRoom.add(element);
        }
    }

    @Override
    public T retrieve() {
        if (hasAccess){
            return treasureRoom.retrieve();
        }
        return null;
    }
}
