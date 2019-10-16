import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    public void start(int port){
        try{
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("awaiting client");
            Socket socket = serverSocket.accept();
            System.out.println("client connected.");

            new Thread(() -> {
                try{
                    while(true){

                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }).start();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
