public interface TreasureRoomDoor
{
  TreasureRoomRead acquireRead();
  void releaseRead();
  TreasureRoomWrite acquireWrite(Object o);
  void releaseWrite(Object o);
}
