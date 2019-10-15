import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
  public void start(int port){
    try{
      ServerSocket serverSocket = new ServerSocket(port);
      System.out.println("awaiting client");
      Socket socket = serverSocket.accept();
      System.out.println("client connected.");

      try{
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(new IAmObject("IamObjectTwo", 23432));

        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream2.writeObject(new IAmObject("IamObjectTwo2", 234322));

        new Thread(() -> {
          try{
            while(true){
              ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
              IAmObject obj = (IAmObject) objectInputStream.readObject();
              System.out.println("server: " + obj);
            }
          }catch(ClassNotFoundException | IOException e){
            e.printStackTrace();
          }
        }).start();
      }catch(IOException e){
        e.printStackTrace();
      }
    }catch(IOException e){
      e.printStackTrace();
    }
  }
}
