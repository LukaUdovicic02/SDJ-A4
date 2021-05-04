import utility.collection.ListADT;

public class TreasureRoomGuardsman<T> implements TreasureRoomDoor<T> {
  private TreasureRoom<T> treasureRoom;
  private int readers;
  private int writers;

   public TreasureRoomGuardsman(TreasureRoom<T> treasureRoom)
   {
       this.treasureRoom = treasureRoom;
   }

    @Override
    public synchronized TreasureRoom<T> acquireRead(/*Missing type to check who is acquiring access*/) {
        while (writers > 0){
            try {
                //waiting for access to read
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        readers++;
        return treasureRoom;
    }

    @Override
    public synchronized void releaseRead() {
        readers--;
        if (readers == 0){
            notify();
        }
    }

    @Override
    public synchronized TreasureRoom<T> acquireWrite(/*Missing type to check who is acquiring access*/) {
        while (writers > 0 || readers > 0){
            try {
                //waiting for access to write
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        writers++;
        return treasureRoom;
    }

    @Override
    public synchronized void releaseWrite() {
        writers--;
        notifyAll();
    }


//    @Override
//    public void add(T element) {
//       treasureRoom.add(element);
//    }
//
//    @Override
//    public T retrieve(T element) {
//        return treasureRoom.retrieve(element);
//    }
//
//    @Override
//    public String look() {
//       return treasureRoom.look();
//    }
}
