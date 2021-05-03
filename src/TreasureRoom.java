import utility.collection.ArrayList;
import utility.collection.ListADT;


public class TreasureRoom<T> implements TreasureRoomDoor<T> {

    private ListADT<T> list;

    public TreasureRoom() {
        this.list = new ArrayList<>();
    }

    @Override
    public void add(T element) {
        list.add(element);
    }

    @Override
    public T retrieve(T element) {
        return list.remove(element);
    }

    @Override
    public String look() {
        return list.toString();
    }
}
