package treasureRoom;

public interface TreasureRoomDoor
{
  TreasureRoomRead acquireRead();
  void releaseRead();
  TreasureRoomWrite acquireWrite();
  void releaseWrite();
}
