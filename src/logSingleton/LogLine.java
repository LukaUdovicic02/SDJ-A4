package logSingleton;

public class LogLine {
    private DateTime dateTime;
    private String text;

    public LogLine(String text){
        this.text = text;
        dateTime = new DateTime();
    }

    public String getText(){
        return text;
    }

    public DateTime getDateTime(){
        return dateTime;
    }

    @Override public String toString(){
        return dateTime.toString() + " " + text;
    }
}
