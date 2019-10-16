import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client{
  private ClientInterface iface;

  public Client(ClientInterface iface){
    this.iface = iface;
  }

  public void start(String ip, int port){
    try{
      Socket socket = new Socket(ip, port);
      System.out.println("found a server and connected!");

      ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
      outputStream.writeObject(new IAmObject("IamString", 16));

      new Thread(() -> {
        try{
          while(true){
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            Object obj = inputStream.readObject();

            System.out.println("client: " + obj);

            if(obj instanceof IAmObject){

            }else if(obj instanceof Image){
              iface.getVBox().getChildren().add((new ImageView((Image)obj)));
              System.out.println("look ma, no hands!");
            }
          }
        }catch(ClassNotFoundException | IOException e){
          e.printStackTrace();
        }
      }).start();
    }catch(IOException e){
      e.printStackTrace();
    }
  }
}