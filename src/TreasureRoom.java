import utility.collection.ArrayList;
import utility.collection.ListADT;


public class TreasureRoom implements TreasureRoomWrite {

    private ListADT<Valuables> valuables;


    public TreasureRoom() {
        this.valuables = new ArrayList<>();

    }

    @Override
    public void add(Valuables valuable) {
        valuables.add(valuable);
    }

    @Override
    public Valuables retrieve() {
        return valuables.remove(0);
    }

    @Override
    public ListADT<Valuables> read() {
        return valuables;
    }
}
