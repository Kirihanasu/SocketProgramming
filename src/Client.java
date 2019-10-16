import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client{

    public void start(String ip, int port){
        try{
            Socket socket = new Socket(ip, port);
            System.out.println("found a server and connected!");

            ObjectOutputStream objOutput = new ObjectOutputStream(socket.getOutputStream());
            objOutput.writeObject(new PingMessage(System.currentTimeMillis()));

            new Thread(() -> {
                try{
                    while(true){
                        ObjectInputStream objInput = new ObjectInputStream(socket.getInputStream());
                        System.out.println("Client:\n " + objInput.readObject());
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }).start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}