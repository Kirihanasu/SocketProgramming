import javafx.scene.image.Image;

import java.io.Serializable;

public class IAmObject implements Serializable{
    Image image;
    String s;
    int i;
    long l;

    public IAmObject(String s, int i){
        this.s = s;
        this.i = i;
    }

    public IAmObject(String s, long l){
        this.s = s;
        this.l = l;
    }

    public IAmObject(Image image){
        this.image = image;
        s = "büld";
        i = 420;
    }

    @Override
    public String toString(){
        if(l != 0) return "the string is: " + s + "\n, and the long is: " + l + ", image is: " + image + ",\ntime is: " + (System.currentTimeMillis() - l);
        return "the string is: " + s + ", and the integer is: " + i + ", image is: " + image;
    }

    public Image getImage(){
        return image;
    }
}
