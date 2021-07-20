import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class DefaultController implements Initializable {

    @FXML
    private Button bookButton,cancelOrderButton,feedbackButton,logoutButton;

    @FXML
    private BorderPane defaultPane;

    @FXML
    private Label priceLabel,displayMessageLabel;

    @FXML
    private TextField summaTextField;

    @FXML
    private ComboBox<String> companyComboBox,itemComboBox;

    public void bookButtonOnAction(ActionEvent event){        
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("bookPane.fxml");
        defaultPane.setCenter(view);    
    }

    public void cancelOrderButtonOnAction(ActionEvent event){     
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("cancelOrderPane.fxml");
        defaultPane.setCenter(view);
    }

    public void feedbackButtonOnAction(ActionEvent event){
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("feedbackPane.fxml");
        defaultPane.setCenter(view);
    }

    public void logoutButtonOnAction(ActionEvent event){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
            Scene scene = new Scene(root, 532, 387);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("bookPane.fxml");
        defaultPane.setCenter(view);
        
    }
    

}
