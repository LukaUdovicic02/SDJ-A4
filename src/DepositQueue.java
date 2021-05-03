public interface DepositQueue<T>{
    void put(T element);
    T take();
    boolean isEmpty();
    boolean isFull();
    int size();
}
