import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer{
    private ServerSocket serverSocket;

    public void start(int port) throws IOException{
        serverSocket = new ServerSocket(port);

        System.out.println("server on " + InetAddress.getLocalHost().toString().split("/")[1] + ":" + port);

        Runnable r = new Runnable(){
            @Override
            public void run(){
                try{
                    while(true)
                        new EchoClientHandler(serverSocket.accept()).start();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        };

        r.run();

    }

    public void stop() throws IOException{
        serverSocket.close();
    }

    private static class EchoClientHandler extends Thread{
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public EchoClientHandler(Socket socket){
            this.clientSocket = socket;
        }

        public void run(){
            try{
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                while((inputLine = in.readLine()) != null){
                    if(".".equals(inputLine)){
                        out.println("bye");
                        break;
                    }
                    out.println(inputLine);
                }

                in.close();
                out.close();
                clientSocket.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}