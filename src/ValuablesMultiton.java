import java.util.HashMap;
import java.util.Map;

public class ValuablesMultiton {
    private static Map<String, ValuablesMultiton> allInstances = new HashMap<>();

    private ValuablesMultiton(){

    }

    public static ValuablesMultiton getValuable(String key){
        ValuablesMultiton instance = allInstances.get(key);
        if (instance == null){
            synchronized (allInstances){
                instance = allInstances.get(key);
                if (instance == null){
                    instance = new ValuablesMultiton();
                    allInstances.put(key, instance);
                }
            }
        }
        return instance;
    }
}
