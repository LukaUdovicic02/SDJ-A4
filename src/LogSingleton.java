import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class LogSingleton {
    private ArrayList<LogLine> logs;
    private static LogSingleton instance;
    private static Object lock = new Object();

    private LogSingleton(){
        logs = new ArrayList<>();
    }

    public void addLog(String text){
        logs.add(new LogLine(text));
//        addToFile(logs.get(logs.size()-1));
    }

    public static LogSingleton getInstance(){
        if (instance == null){
            synchronized (lock){
                if (instance == null){
                    instance = new LogSingleton();
                }
            }
        }
        return instance;
    }

    public ArrayList<LogLine> getAll(){
        return logs;
    }

    @Override public String toString(){
        StringBuilder s = new StringBuilder("Logs:\n");
        for (LogLine l : logs){
            s.append(l.toString()).append("\n");
        }
        return s.toString();
    }

    // Appending a logLine to a file (for date 15/3/2021, the file is: "Log-2021-03-15.txt")
    private void addToFile(LogLine log)
    {
        if (log == null)
        {
            return;
        }
        BufferedWriter out = null;
        try
        {
            String filename = "Log-"
                    + log.getDateTime().getSortableDate() + ".txt";
            out = new BufferedWriter(new FileWriter(filename, true));
            out.write(log + "\n");
        }
        catch (Exception e) {e.printStackTrace();}
        finally
        {
            try
            {
                out.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
