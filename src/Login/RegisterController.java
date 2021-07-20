import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import javafx.scene.Node;

public class RegisterController implements Initializable {

    @FXML
    private ImageView regImageView;

    @FXML
    private TextField firstnameTextField,lastnameTextField,usernameTextField;

    @FXML
    private PasswordField setPasswordField,setConfirmPasswordField;

    @FXML
    private Button continueButton;

    @FXML
    private Label registrationMessageLabel,confirmPasswordMessageLabel;

    private boolean firstnameTest,usernameTest,passwordTest;

    public void continueButtonOnAction(ActionEvent event) throws Exception{

        if(firstnameTextField.getText().isEmpty()){
            firstnameTextField.setStyle("-fx-border-color: red; -fx-border-width=2px;");
            new animatefx.animation.Shake(firstnameTextField).play();
            firstnameTest=false;            
        }
        else {
            firstnameTextField.setStyle(null);
            firstnameTest=true;
        }

        if(usernameTextField.getText().isEmpty()){
            usernameTextField.setStyle("-fx-border-color: red; -fx-border-width=2px;");
            new animatefx.animation.Shake(usernameTextField).play();
            usernameTest=false;           
        }
        else {
            usernameTextField.setStyle(null);
            usernameTest=true;
        }

        if(setPasswordField.getText().isEmpty()){
            setPasswordField.setStyle("-fx-border-color: red; -fx-border-width=2px;");
            new animatefx.animation.Shake(setPasswordField).play();
            passwordTest=false;           
        }
        else {
            setPasswordField.setStyle(null);
            passwordTest=true;
        }

        if(usernameTest && passwordTest && firstnameTest){
            try{
                DatabaseConnection connectNow = new DatabaseConnection();
                Connection connectDB = connectNow.getConnection();
                String userQuery="select username from user_account;";

                Statement statement = connectDB.createStatement();
                ResultSet queryResult = statement.executeQuery(userQuery);
                boolean usernameCheck=true;

                while(queryResult.next()){
                    if(queryResult.getString("username").equals(usernameTextField.getText())){
                        registrationMessageLabel.setText("Username already exists");
                        usernameCheck=false;
                    }
                }

                if(setPasswordField.getText().equals(setConfirmPasswordField.getText()) && usernameCheck){
                    try{
                        Data.firstname = firstnameTextField.getText();
                        Data.lastname = lastnameTextField.getText();
                        Data.username = usernameTextField.getText();
                        Data.password = setPasswordField.getText();
                        Parent root = FXMLLoader.load(getClass().getResource("registerpersonal.fxml"));
                        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
            
                    } catch(Exception e){
                        e.printStackTrace();
                        e.getCause();
                    }
                    
                }
                else if(usernameCheck==true){
                    registrationMessageLabel.setText("");
                    confirmPasswordMessageLabel.setText("Passwords does not match");
                }

            }
            catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }                   
            
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        firstnameTextField.setTextFormatter(new TextFormatter<>(change -> {
            if(change.getText().matches("[0-9]"))
                return null;
            return change;
        
        }));

        lastnameTextField.setTextFormatter(new TextFormatter<>(change -> {
            if(change.getText().matches("[0-9]"))
                return null;
            return change;
        
        }));

        

               
    }

}

