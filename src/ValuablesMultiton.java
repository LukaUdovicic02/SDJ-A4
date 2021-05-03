import java.util.HashMap;
import java.util.Map;

public class ValuablesMultiton {
    public String type;
    private static Map<String, ValuablesMultiton> allInstances = new HashMap<>();

    private ValuablesMultiton(String type){
        this.type = type;
    }

    public static ValuablesMultiton getInstance(String key){
        ValuablesMultiton instance = allInstances.get(key);
        if (instance == null){
            synchronized (allInstances){
                instance = allInstances.get(key);
                if (instance == null){
                    instance = new ValuablesMultiton(key);
                    allInstances.put(key, instance);
                }
            }
        }
        return instance;
    }

    public String getType(){
        return type;
    }
}
