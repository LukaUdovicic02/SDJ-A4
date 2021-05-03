import utility.collection.ListADT;

public class TreasureRoomGuardsman<T> implements TreasureRoomDoor<T> {
  private TreasureRoom<T> treasureRoom;

   public TreasureRoomGuardsman(TreasureRoom<T> treasureRoom)
   {
       this.treasureRoom = treasureRoom;
   }

    @Override
    public void add(T element) {
       treasureRoom.add(element);
    }

    @Override
    public T retrieve(T element) {
        return treasureRoom.retrieve(element);
    }

    @Override
    public String look() {
        return treasureRoom.look();
    }
}
