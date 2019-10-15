import java.io.Serializable;

public class IAmObject implements Serializable{
    String s;
    int i;

    public IAmObject(String s, int i){
        this.s = s;
        this.i = i;
    }

    @Override
    public String toString(){
        return "the string is: " + s + ", and the integer is: " + i;
    }
}
