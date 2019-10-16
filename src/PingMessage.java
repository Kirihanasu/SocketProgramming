public class PingMessage implements Message{
    private long millis;
    private boolean answer;

    public PingMessage(long millis){
        this(millis, false);
    }

    public PingMessage(long millis, boolean answer){
        this.answer = answer;
        this.millis = millis;
    }

    @Override
    public String toString(){
        String s = "PingMessage:\n  millis: " + millis + "\n  answer: " + answer;
        return answer ? s + "\n  ping: " + (System.currentTimeMillis() - getMillis()) + " for back and forth communication" : s;
    }

    public long getMillis(){
        return millis;
    }

    public void setAnswer(boolean answer){
        this.answer = answer;
    }

    public boolean isAnswer(){
        return answer;
    }
}
