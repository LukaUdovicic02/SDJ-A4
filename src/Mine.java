public class Mine
{
    public static String[] VALUABLES = {"Diamond", "GoldNugget", "Jewel", "Ruby", "DogeCoin", "BitCoin"};

    public Mine()
    {

    }

    public String getValuable()
    {
         return Valuables.getInstance(VALUABLES[(int)(Math.random()*5)]).getType();
    }
}
