import javafx.application.Platform;
import javafx.scene.image.Image;

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

            Platform.runLater(() -> {
                System.out.println(this);
                System.out.println(this.getClass());
                System.out.println(this.getClass().getResource(""));
            });

            new Thread(() -> {
                try{
                    while(true){
                        Socket socket = serverSocket.accept();
                        System.out.println("client found and connected with " + socket.getInetAddress());

                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                        objectOutputStream.writeObject(new IAmObject("IamObject", counter));



                        //System.out.println(this.getClass().getResource("/Big_Ass_Pic.jpg").getFile());
                        //System.out.println(new File(this.getClass().getResource("/Big_Ass_Pic.jpg").getFile()).exists());
                        //objectOutputStream.writeObject(new Image(this.getClass().getResource("/Big_Ass_Pic.jpg").getFile()));
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