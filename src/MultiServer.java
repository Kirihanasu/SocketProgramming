import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer{
    private ServerSocket serverSocket;
    int counter = 0;

    public void start(int port){
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("started server, awaiting clients");

            new Thread(() -> {
                try{
                    while(true){
                        Socket socket = serverSocket.accept();
                        System.out.println("client found and connected with " + socket.getInetAddress());

                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeObject(new IAmObject("IamObject", counter));
                        counter++;
                    }
                }catch(IOException e){
                    e.printStackTrace();
                }
            }).start();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}