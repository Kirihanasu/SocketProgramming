import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerInterface extends Application{
  Text text;

  public static void main(String[] args){
    launch();
  }

  @Override
  public void start(Stage primaryStage){
    Stage stage = new Stage();
    text = new Text("Enter port and click 'Start Server'.");
    TextField textField = new TextField();
    Button btnStart = new Button("Start Server");
    VBox vbox = new VBox(10, text, textField, btnStart);
    Scene scene = new Scene(vbox, 250, 100);
    Server server = new Server();
    MultiServer multiServer = new MultiServer();

    textField.setAlignment(Pos.CENTER);
    textField.setMaxWidth(60);
    vbox.setAlignment(Pos.CENTER);
    stage.setTitle("Start");
    stage.setScene(scene);
    stage.show();

    btnStart.setOnAction(e -> {
      try{
        int port = Integer.parseInt(textField.getText());
        vbox.getChildren().remove(textField);
        vbox.getChildren().remove(btnStart);

        //text.setText("Server started on ip " + InetAddress.getLocalHost().toString().split("/")[1] + ":" + port + "\nawaiting client...");
        //new Thread(() -> server.start(port)).start();

        text.setText("MultiServer started on ip " + InetAddress.getLocalHost().toString().split("/")[1] + ":" + port + "\nawaiting clients...");
        new Thread(() -> multiServer.start(port)).start();
      }catch(NumberFormatException ex){
        Alert alert = new Alert(Alert.AlertType.ERROR, textField.getText() + " is no valid port.");
        alert.show();
      }catch(UnknownHostException ex){
        Alert alert = new Alert(Alert.AlertType.ERROR, "Problem with local IP address");
        alert.show();
      }
    });
  }
}