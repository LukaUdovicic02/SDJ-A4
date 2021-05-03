public class testTreasureRoom {
    public static void main(String[] args) {
        TreasureRoom<String> treasureRoom = new TreasureRoom<String>();
        TreasureRoomDoor<String> TreasureRoomGuardsman = new TreasureRoomGuardsman<String>(treasureRoom);
        TreasureRoomGuardsman.add("bob");
        System.out.println(TreasureRoomGuardsman.look());
    }
}
