import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class RegisterPersonalController implements Initializable{

    @FXML
    private ImageView regImageView;

    @FXML
    private TextField phoneNumberTextField,houseNumberTextField,areaTextField,cityTextField,pincodeTextField;

    @FXML
    private Button registerButton,gotoLoginButton;

    @FXML
    private Label registrationTwoMessageLabel,pincodeMessageLabel;

    private boolean phoneNumberTest,houseNumberTest,areaTest,cityTest,pincodeTest;

    private boolean registercorrection=false;

    @FXML
    public void gotoLoginButtonOnAction(ActionEvent event){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        
    }


    @FXML
    void registerButtonOnAction(ActionEvent event) {

        if(phoneNumberTextField.getText().isEmpty()){
            phoneNumberTextField.setStyle("-fx-border-color: red; -fx-border-width=2px;");
            new animatefx.animation.Shake(phoneNumberTextField).play();
            phoneNumberTest=false;            
        }
        else {
            phoneNumberTextField.setStyle(null);
            phoneNumberTest=true;
        }

        if(houseNumberTextField.getText().isEmpty()){
            houseNumberTextField.setStyle("-fx-border-color: red; -fx-border-width=2px;");
            new animatefx.animation.Shake(houseNumberTextField).play();
            houseNumberTest=false;            
        }
        else {
            houseNumberTextField.setStyle(null);
            houseNumberTest=true;
        }

        if(areaTextField.getText().isEmpty()){
            areaTextField.setStyle("-fx-border-color: red; -fx-border-width=2px;");
            new animatefx.animation.Shake(areaTextField).play();
            areaTest=false;            
        }
        else {
            areaTextField.setStyle(null);
            areaTest=true;
        }

        if(cityTextField.getText().isEmpty()){
            cityTextField.setStyle("-fx-border-color: red; -fx-border-width=2px;");
            new animatefx.animation.Shake(cityTextField).play();
            cityTest=false;            
        }
        else {
            cityTextField.setStyle(null);
            cityTest=true;
        }

        if(pincodeTextField.getText().isEmpty()){
            pincodeTextField.setStyle("-fx-border-color: red; -fx-border-width=2px;");
            new animatefx.animation.Shake(pincodeTextField).play();
            pincodeTest=false;            
        }
        else {
            pincodeTextField.setStyle(null);
            pincodeTest=true;
        }

        if(pincodeTest && phoneNumberTest && areaTest && houseNumberTest && cityTest){
            if(registercorrection==false){
                registerUser();
            }
            else{
                registrationTwoMessageLabel.setText("Already Registered");
            }
        }
    }

    public void registerUser(){

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String firstName=Data.firstname;
        String lastName=Data.lastname;
        String userName=Data.username;
        String password=Data.password;
        

        String insertFields="INSERT INTO user_account(firstname,lastname,username,password,phone_number,house_number,area,city,pincode) VALUES (";
        String insertValues= "'"+firstName+"','"+lastName+"','"+userName+"','"+password+"','"+phoneNumberTextField.getText()+"','"+houseNumberTextField.getText()+"','"+areaTextField.getText()+"','"+cityTextField.getText()+"','"+pincodeTextField.getText()+"');";
        String insertSQL=insertFields+insertValues;

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertSQL);
            registercorrection=true;
            registrationTwoMessageLabel.setText("Registered Successfully");
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

        String createQuery="CREATE TABLE `"+userName+"`( `order_id` INT NOT NULL AUTO_INCREMENT, `item_name` VARCHAR(45) NOT NULL, `price` INT NOT NULL, `company` VARCHAR(45) NOT NULL, PRIMARY KEY (`order_id`));";

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(createQuery);
        }catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

   }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        phoneNumberTextField.setTextFormatter(new TextFormatter<>(change -> {
            if(change.getText().matches("[0-9]*"))
                return change;
            return null;
        
        }));

        pincodeTextField.setTextFormatter(new TextFormatter<>(change -> {
            if(change.getText().matches("[0-9]*"))
                return change;
            return null;
        
        }));

        cityTextField.setTextFormatter(new TextFormatter<>(change -> {
            if(change.getText().matches("[0-9]"))
                return null;
            return change;
        
        }));


        
    }

}
