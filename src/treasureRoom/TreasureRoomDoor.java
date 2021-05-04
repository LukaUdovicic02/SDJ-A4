package treasureRoom;

public interface TreasureRoomDoor<T>
{
  TreasureRoomRead<T> acquireRead();
  void releaseRead();
  TreasureRoomWrite<T> acquireWrite();
  void releaseWrite();
}
