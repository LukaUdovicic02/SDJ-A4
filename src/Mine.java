import treasureRoom.Valuables;

public class Mine
{
    public static String[] VALUABLES = {"Diamond", "GoldNugget", "Jewel", "Ruby", "DogeCoin", "BitCoin"};

    public Mine()
    {

    }

    public Valuables getValuable()
    {
         return Valuables.getInstance(VALUABLES[(int)(Math.random()*5)]);
    }
}
