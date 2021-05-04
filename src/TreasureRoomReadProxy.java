public class TreasureRoomReadProxy<T> implements TreasureRoomRead<T>{
    private TreasureRoomRead<T> treasureRoom;
    private boolean hasAccess;

    public TreasureRoomReadProxy(TreasureRoomRead<T> treasureRoom){
        this.treasureRoom = treasureRoom;
        this.hasAccess = true;
    }

    public void restrictAccess(){
        this.hasAccess = false;
    }

    @Override
    public int read() {
        if (hasAccess){
            return treasureRoom.read();
        }
        else {
            return 0;
        }
    }
}
