package treasureRoom;

import utility.collection.ArrayList;
import utility.collection.ListADT;


public class TreasureRoom<T> implements TreasureRoomWrite<T> {

    private ListADT<T> valuables;


    public TreasureRoom() {
        this.valuables = new ArrayList<>();

    }

    @Override
    public void add(T element) {
        valuables.add(element);
    }

    @Override
    public T retrieve() {
        return valuables.remove(0);
    }

    @Override
    public ListADT<T> read() {
        return valuables;
    }
}
