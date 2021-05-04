package treasureRoom;

import logSingleton.Log;

import java.util.HashMap;
import java.util.Map;

public class TreasureRoomGuardsman<T> implements TreasureRoomDoor<T> {
  private TreasureRoom<T> treasureRoom;
  private int readers;
  private int writers;
  private int waitingWriters;
    private Log log;
    private Map<Thread, TreasureRoomReadProxy<T>> readProxies;
    private Map<Thread, TreasureRoomWriteProxy<T>> writeProxies;

   public TreasureRoomGuardsman(TreasureRoom<T> treasureRoom)
   {
       readers = 0;
       writers = 0;
       waitingWriters = 0;
       readProxies = new HashMap<>();
       writeProxies = new HashMap<>();
       this.treasureRoom = treasureRoom;
       log = Log.getInstance();
   }

    @Override
    public synchronized TreasureRoomRead<T> acquireRead() {
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

        TreasureRoomReadProxy<T> proxy = new TreasureRoomReadProxy<>(treasureRoom);
        readProxies.put(Thread.currentThread(),proxy);
        return proxy;
    }

    @Override
    public synchronized void releaseRead() {
        readers--;
        String txt = " released read access of the treasure room";
        log.addLog(Thread.currentThread().getName()+txt);
        if (readers == 0){
            notifyAll();
        }

        TreasureRoomReadProxy<T> proxy = readProxies.get(Thread.currentThread());
        if (proxy != null){
            proxy.restrictAccess();
            readProxies.remove(Thread.currentThread());
        }
    }

    @Override
    public synchronized TreasureRoomWrite<T> acquireWrite() {
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

        TreasureRoomWriteProxy<T> proxy = new TreasureRoomWriteProxy<>(treasureRoom);
        writeProxies.put(Thread.currentThread(),proxy);
        return proxy;
    }

    @Override
    public synchronized void releaseWrite() {
        writers--;
        String txt = " released write access of the treasure room";
        log.addLog(Thread.currentThread().getName()+txt);
        notifyAll();

        TreasureRoomWriteProxy<T> proxy = writeProxies.get(Thread.currentThread());
        if (proxy!=null){
            proxy.restrictAccess();
            writeProxies.remove(Thread.currentThread());
        }
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
