package treasureRoom;

public interface TreasureRoomWrite<T> extends TreasureRoomRead<T>{
    void add(T element);
    T retrieve();
}
