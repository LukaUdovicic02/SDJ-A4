import logSingleton.Log;

import java.util.HashMap;
import java.util.Map;

public class TreasureRoomGuardsman implements TreasureRoomDoor {
  private TreasureRoom treasureRoom;
  private int readers;
  private int writers;
  private int waitingWriters;
    private Log log;
    private Map<Thread, TreasureRoomReadProxy> readProxies;
    private Map<Thread, TreasureRoomWriteProxy> writeProxies;

   public TreasureRoomGuardsman(TreasureRoom treasureRoom)
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
    public synchronized TreasureRoomRead acquireRead() {
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

        TreasureRoomReadProxy proxy = new TreasureRoomReadProxy(treasureRoom);
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

        TreasureRoomReadProxy proxy = readProxies.get(Thread.currentThread());
        if (proxy != null){
            proxy.restrictAccess();
            readProxies.remove(Thread.currentThread());
        }
    }

    @Override
    public synchronized TreasureRoomWrite acquireWrite(Object o) {
       if (o instanceof King || o instanceof Transporter) {
           waitingWriters++;
           while (writers > 0 || readers > 0) {
               try {
                   String txt = " waiting to get write access of the treasure room";
                   log.addLog(Thread.currentThread().getName() + txt);
                   wait();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
           waitingWriters--;
           writers++;
           String txt = " got write access to the treasure room";
           log.addLog(Thread.currentThread().getName() + txt);

           TreasureRoomWriteProxy proxy = new TreasureRoomWriteProxy(treasureRoom);
           writeProxies.put(Thread.currentThread(), proxy);
           return proxy;
       }
       else {
           System.err.println(o.getClass().getSimpleName() + " does not have write access");
           return null;
       }
    }

    @Override
    public synchronized void releaseWrite(Object o) {
        if (o instanceof King || o instanceof Transporter) {
            writers--;
            String txt = " released write access of the treasure room";
            log.addLog(Thread.currentThread().getName() + txt);
            notifyAll();

            TreasureRoomWriteProxy proxy = writeProxies.get(Thread.currentThread());
            if (proxy != null) {
                proxy.restrictAccess();
                writeProxies.remove(Thread.currentThread());
            }
        }
        else {
            System.err.println(o.getClass().getSimpleName() + " does not have write access");
        }
    }
}
