import utility.collection.ArrayList;
import utility.collection.ListADT;


public class TreasureRoom<T> implements TreasureRoomWrite<T> {

    private ListADT<T> list;


    public TreasureRoom() {
        this.list = new ArrayList<>();

    }

    @Override
    public void add(T element) {
        list.add(element);
    }

    @Override
    public T retrieve() {
        return list.remove(0);
    }

    @Override
    public int read() {
        return list.size();
    }
}
