import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ClientInterface extends Application{
  private VBox vbox;

  public static void main(String[] args){
    launch();
  }

  @Override
  public void start(Stage primaryStage) throws Exception{
    Stage stage = new Stage();
    Text text = new Text("Enter IP and click 'Connect'.");
    TextField textField = new TextField();
    Button btnStart = new Button("Connect");
    vbox = new VBox(10, text, textField, btnStart);
    Scene scene = new Scene(vbox, 250, 100);

    textField.setAlignment(Pos.CENTER);
    textField.setMaxWidth(150);
    vbox.setAlignment(Pos.CENTER);
    stage.setTitle("Connect");
    stage.setScene(scene);
    stage.show();

    btnStart.setOnAction(e -> {
      try{
        Client c = new Client(this);
        c.start(textField.getText().split(":")[0], Integer.parseInt(textField.getText().split(":")[1]));
      }catch(ArrayIndexOutOfBoundsException | NumberFormatException ex){
        Alert alert = new Alert(Alert.AlertType.ERROR, textField.getText() + " has to be of format\n123.456.789.012:12345");
        alert.show();
      }catch(Exception ex){
        ex.printStackTrace();
      }
    });
  }

  public VBox getVBox(){
    return vbox;
  }
}