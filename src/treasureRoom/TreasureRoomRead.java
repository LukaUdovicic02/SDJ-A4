package treasureRoom;

import utility.collection.ListADT;

public interface TreasureRoomRead<T> {
    ListADT<T> read();
}
