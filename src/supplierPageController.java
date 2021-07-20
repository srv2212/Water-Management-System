import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class supplierPageController {

    @FXML
    private Label cnameLabel;

    @FXML
    private Button logoutButton;

    @FXML
    private AnchorPane centerpane;

    public static String comp_name=" ";

    @FXML
    public void logoutButtonOnAction(ActionEvent event){
        try{
            Parent root=FXMLLoader.load(getClass().getResource("main.fxml"));
            Stage primaryStage=new Stage();
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(new Scene(root,600,400));
            primaryStage.show();
            
            }
            catch(Exception e){
                System.out.println(e);
            }

            Stage stage=(Stage)logoutButton.getScene().getWindow();
            stage.close();

    }

    @FXML
    void passCName(String c_name){
            cnameLabel.setText(c_name);  
            supplierPageController.comp_name=c_name;
    }

    @FXML
    void ordersButtonOnAction(ActionEvent event) {
        centerpane.getChildren().clear();
        fxmlLoader obj=new fxmlLoader();
        Pane view=obj.getPage("orders.fxml");
        centerpane.getChildren().add(view);
    }

    @FXML
    void updateStockButtonOnAction(ActionEvent event){
        centerpane.getChildren().clear();
        fxmlLoader obj=new fxmlLoader();
        Pane view=obj.getPage("updateStock.fxml");
        centerpane.getChildren().add(view);
    
    }

    @FXML
    void viewProfileButtonOnAction(ActionEvent event) {
        centerpane.getChildren().clear();
        fxmlLoader obj=new fxmlLoader();
        Pane view=obj.getPage("profile.fxml");
        centerpane.getChildren().add(view);

        
    }

}
