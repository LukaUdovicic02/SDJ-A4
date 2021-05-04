import logSingleton.Log;
import utility.collection.ArrayList;
import utility.collection.ListADT;


public class Deposit<T> implements DepositQueue<T> {
    private ListADT<T> list;
    private Log log;

    public Deposit(){
        list = new ArrayList<>();
        log = Log.getInstance();
    }

    @Override
    public synchronized void put(T element) {
        list.add(element);
        log.addLog(Thread.currentThread().getName() + " added " + element.toString() + " in deposit.");
        notify();
    }

    @Override
    public synchronized T take() {
        while (isEmpty()){
            try {
                log.addLog(Thread.currentThread().getName() + " waiting for valuables to take from deposit");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T element = list.remove(0);
        log.addLog(Thread.currentThread().getName() + " took "+element.toString()+" from deposit");
        return element;
    }

    @Override
    public synchronized boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public synchronized boolean isFull() {
        return list.isFull();
    }

    @Override
    public synchronized int size() {
        return list.size();
    }
}

