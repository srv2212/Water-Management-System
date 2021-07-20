import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HomePageController {

    @FXML
    private Button customerButton;

    @FXML
    private Button supplierButton;

    @FXML
    private Button exitButton;

    public void exitButtonOnAction(ActionEvent event) {
        Stage st=(Stage) exitButton.getScene().getWindow();
          st.close(); 
         
      }
    

    public void supplierButtonOnAction(ActionEvent event) {
        try{
        Parent root=FXMLLoader.load(getClass().getResource("supplierLogin.fxml"));
        Stage primaryStage=new Stage();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root,600,400));
        primaryStage.show();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    
  


}
