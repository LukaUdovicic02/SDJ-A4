import java.util.HashMap;
import java.util.Map;

public class Valuables {
    public String type;
    private static Map<String, Valuables> allInstances = new HashMap<>();

    private Valuables(String type){
        this.type = type;
    }

    public static Valuables getInstance(String key){
        Valuables instance = allInstances.get(key);
        if (instance == null){
            synchronized (allInstances){
                instance = allInstances.get(key);
                if (instance == null){
                    instance = new Valuables(key);
                    allInstances.put(key, instance);
                }
            }
        }
        return instance;
    }

    public String getType(){
        return type;
    }

    @Override
    public String toString(){
        return type;
    }
}
