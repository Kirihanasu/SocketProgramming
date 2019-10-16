public class InformMessage implements Message{
    private String message;

    public InformMessage(String message){
        this.message = message;
    }

    @Override
    public String toString(){
        return "InformMessage:\n  " + message;
    }
}
