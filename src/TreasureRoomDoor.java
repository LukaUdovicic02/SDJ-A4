public interface TreasureRoomDoor<T>
{
  void add(T element);

  T retrieve(T element);

  String look();
}
