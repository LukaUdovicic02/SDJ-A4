public interface TreasureRoomDoor<T>
{
  TreasureRoom<T> acquireRead();
  void releaseRead();
  TreasureRoom<T> acquireWrite();
  void releaseWrite();

//acquireRead
}
