public interface TreasureRoomWrite extends TreasureRoomRead{
    void add(Valuables valuable);
    Valuables retrieve();
}
