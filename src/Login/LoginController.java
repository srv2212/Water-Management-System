import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

public class LoginController{

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField enterPasswordField;

    @FXML
    private Button loginButton, registerButton,cancelButton;

    @FXML
    private Label loginMessageLabel;

    private boolean usernameTest, passwordTest; 

    
    public void cancelButtonOnAction(ActionEvent event){
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

    public void loginButtonOnAction(ActionEvent event){
        if(usernameTextField.getText().isEmpty()){
            usernameTextField.setStyle("-fx-border-color: red; -fx-border-width=2px;");
            new animatefx.animation.Shake(usernameTextField).play();            
        }
        else {
            usernameTextField.setStyle(null);
            usernameTest=true;
        }

        if(enterPasswordField.getText().isEmpty()){
            enterPasswordField.setStyle("-fx-border-color: red; -fx-border-width=2px;");
            new animatefx.animation.Shake(enterPasswordField).play();
            
        }
        else {
            enterPasswordField.setStyle(null);
            passwordTest=true;
        }
        
        if(usernameTest && passwordTest) {
            validateLogin(event);
        }

    }

    public void registerButtonOnAction(ActionEvent event){
        createAccountForm(event);
    }

    public void validateLogin(ActionEvent event){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String verifyLogin = "SELECT count(1) FROM user_account WHERE username =  '"+ usernameTextField.getText() + "' AND password = '"+ enterPasswordField.getText() + "'";
        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1)==1){
                    String firstnameQuery="SELECT firstname FROM user_account WHERE username ='"+usernameTextField.getText()+"';";
                    String lastnameQuery="SELECT lastname FROM user_account WHERE username ='"+usernameTextField.getText()+"';";
                    Statement statement2=connectDB.createStatement();
                    ResultSet queryResult2=statement2.executeQuery(firstnameQuery);
                    Data.username=usernameTextField.getText();

                    while(queryResult2.next()){
                        Data.firstname=queryResult2.getString("firstname");                       
                    }

                    Statement statement3=connectDB.createStatement();
                    ResultSet queryResult3=statement3.executeQuery(lastnameQuery);

                    while(queryResult3.next()){
                        Data.lastname=queryResult3.getString("lastname");                      
                    }

                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("default.fxml"));
                        Scene scene = new Scene(root, 904, 510);
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
                else{
                    loginMessageLabel.setText("Invalid Login. Please try again");
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void createAccountForm(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
            Scene scene = new Scene(root, 520, 492);
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

    

}