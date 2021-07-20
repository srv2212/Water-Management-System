import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class FeedbackPaneController implements Initializable{

    @FXML
    private Pane feedbackPane;

    @FXML
    private ComboBox<String> companyComboBox;

    @FXML
    private TextArea feedbackTextArea;

    @FXML
    private Label welcomelabel;

    @FXML
    private Button postButton;

    private String companyName="";

    public void companyComboBoxOnAction(ActionEvent event){
        companyName=companyComboBox.getValue();
    }

    @FXML
    void postButtonOnAction(ActionEvent event) {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String newcompany=companyName.toLowerCase().replace(' ', '_');;
        String insertFeedbackQuery="INSERT INTO feedback(company,user,written) VALUES('"+newcompany+"','"+Data.username+"','"+feedbackTextArea.getText()+"');";
        try{               
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertFeedbackQuery);
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
      }

      companyComboBox.getItems().clear();
      feedbackTextArea.clear();
      feedbackTextArea.setPromptText("Write Your Feedback Here");
      fillComboBox();

    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        welcomelabel.setText("Welcome "+Data.firstname+" "+Data.lastname);
        fillComboBox();
        
    }

    private void fillComboBox() {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String companyQuery = "SELECT company_name FROM supplier;" ;        
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(companyQuery);
            while(queryResult.next()){
                companyComboBox.getItems().add(queryResult.getString("company_name"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }
}
