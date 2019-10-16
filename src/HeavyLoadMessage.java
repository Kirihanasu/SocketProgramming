import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;

public class HeavyLoadMessage extends PingMessage{
    String[] strings;

    public HeavyLoadMessage(long millis, String... strings){
        super(millis);
        this.strings = strings;
    }

    public HeavyLoadMessage(long millis, boolean answer, String... strings){
        super(millis, answer);
        this.strings = strings;
    }

    @Override
    public String toString(){
        String s = "HeavyLoadMessage:\n  millis: " + super.getMillis() + "\n  answer: " + super.isAnswer() + "\n  size: " + ObjectSizeCalculator.getObjectSize(this) + " bytes";
        return super.isAnswer() ? s + "\n  ping: " + (System.currentTimeMillis() - getMillis()) + " for back and forth communication" : s;
    }
}