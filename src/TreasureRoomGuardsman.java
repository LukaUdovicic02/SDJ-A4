import logSingleton.Log;
import utility.collection.ListADT;

public class TreasureRoomGuardsman<T> implements TreasureRoomDoor<T> {
  private TreasureRoom<T> treasureRoom;
  private int readers;
  private int writers;
  private int waitingWriters;
    private Log log;

   public TreasureRoomGuardsman(TreasureRoom<T> treasureRoom)
   {
       this.treasureRoom = treasureRoom;
       log = Log.getInstance();
   }

    @Override
    public synchronized TreasureRoomRead<T> acquireRead(/*Missing type to check who is acquiring access*/) {
        while (writers > 0 || waitingWriters > 0){
            try {
                String txt = " waiting to get read access of the treasure room";
                log.addLog(Thread.currentThread().getName()+txt);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        readers++;
        String txt = " got read access to the treasure room";
        log.addLog(Thread.currentThread().getName()+txt);
        return treasureRoom;
    }

    @Override
    public synchronized void releaseRead() {
        readers--;
        String txt = " released read access of the treasure room";
        log.addLog(Thread.currentThread().getName()+txt);
        if (readers == 0){
            notify();
        }
    }

    @Override
    public synchronized TreasureRoomWrite<T> acquireWrite(/*Missing type to check who is acquiring access*/) {
       waitingWriters++;
        while (writers > 0 || readers > 0){
            try {
                String txt = " waiting to get write access of the treasure room";
                log.addLog(Thread.currentThread().getName()+txt);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        waitingWriters--;
        writers++;
        String txt = " got write access to the treasure room";
        log.addLog(Thread.currentThread().getName()+txt);
        return treasureRoom;
    }

    @Override
    public synchronized void releaseWrite() {
        writers--;
        String txt = " released write access of the treasure room";
        log.addLog(Thread.currentThread().getName()+txt);
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
