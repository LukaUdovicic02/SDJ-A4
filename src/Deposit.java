import utility.collection.ArrayList;
import utility.collection.ListADT;

public class Deposit<T> implements ListADT<T>{
    private ListADT<T> list;

    public Deposit(){
        list = new ArrayList<>();
    }

    @Override
    public synchronized void add(int index, T element) {

    }

    @Override
    public synchronized void add(T element) {

    }

    @Override
    public synchronized void set(int index, T element) {

    }

    @Override
    public synchronized T get(int index) {
        return null;
    }

    @Override
    public synchronized T remove(int index) {
        return null;
    }

    @Override
    public synchronized T remove(T element) {
        return null;
    }

    @Override
    public synchronized int indexOf(T element) {
        return 0;
    }

    @Override
    public synchronized boolean contains(T element) {
        return false;
    }

    @Override
    public synchronized boolean isEmpty() {
        return false;
    }

    @Override
    public synchronized boolean isFull() {
        return false;
    }

    @Override
    public synchronized int size() {
        return 0;
    }
}

