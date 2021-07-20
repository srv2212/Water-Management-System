import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SupplierRegisterController implements Initializable{

    @FXML
    private TextField supplierNameTextField;

    @FXML
    private TextField companyNameTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField setPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button registerButton;

    @FXML
    private Button closeButton;

    @FXML
    private Label registerLabel;

    private boolean suppliertest,companytest,usernametest,passwordtest;

    

    @FXML
     public void registerButtonOnAction(ActionEvent event){

        if(supplierNameTextField.getText().isEmpty()){
            supplierNameTextField.setStyle("-fx-border-color: red; -fx-border-width=2px;");
            new animatefx.animation.Shake(supplierNameTextField).play();
            suppliertest=false;            
        }
        else {
            supplierNameTextField.setStyle(null);
            suppliertest=true;
        }

        if(companyNameTextField.getText().isEmpty()){
            companyNameTextField.setStyle("-fx-border-color: red; -fx-border-width=2px;");
            new animatefx.animation.Shake(companyNameTextField).play();
            companytest=false;           
        }
        else {
            companyNameTextField.setStyle(null);
            companytest=true;
        }

        if(usernameTextField.getText().isEmpty()){
            usernameTextField.setStyle("-fx-border-color: red; -fx-border-width=2px;");
            new animatefx.animation.Shake(usernameTextField).play();
            usernametest=false;           
        }
        else {
            usernameTextField.setStyle(null);
            usernametest=true;
        }

        if(setPasswordField.getText().isEmpty()){
            setPasswordField.setStyle("-fx-border-color: red; -fx-border-width=2px;");
            new animatefx.animation.Shake(setPasswordField).play();
            passwordtest=false;           
        }
        else {
            setPasswordField.setStyle(null);
            passwordtest=true;
        }

        if(suppliertest && companytest && usernametest && passwordtest){

            if(setPasswordField.getText().equals(confirmPasswordField.getText()) && setPasswordField.getText().isBlank()==false ){
                registerSupplier(event);   
                DatabaseConnection connect=new DatabaseConnection();
                Connection con=connect.getConnection();
            
    
                String companyName=companyNameTextField.getText().toLowerCase().replace(' ', '_');
                String query="CREATE TABLE `demo_db`." +companyName+
                " (stock_id INT NOT NULL AUTO_INCREMENT,"+
                " item_name VARCHAR(30) NOT NULL, " + 
                " price INT NOT NULL, " + 
                " PRIMARY KEY ( stock_id))";

                 String orderQuery="CREATE TABLE `demo_db`." +companyName+"_orders"+
                " (stock_id INT NOT NULL AUTO_INCREMENT,"+
                " item_name VARCHAR(30) NOT NULL, " + 
                " price INT NOT NULL, " +
                " house_number VARCHAR(45) NOT NULL, "+
                " area VARCHAR(45) NOT NULL, "+
                " city VARCHAR(45) NOT NULL, "+
                " phone_number VARCHAR(45) NOT NULL, "+ 
                " user VARCHAR(45) NOT NULL, "+
                " PRIMARY KEY ( `stock_id`))";

                try{
                    Statement stmt=con.createStatement();
                    stmt.executeUpdate(query);
                }
                catch(Exception e){
                    System.out.println(e);
                }

                try{
                    Statement stmt=con.createStatement();
                    stmt.executeUpdate(orderQuery);
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
            else{
            registerLabel.setText(" Password does not match");
            }

        }
        
    }
    public void registerSupplier(ActionEvent event){

        DatabaseConnection connect=new DatabaseConnection();
        Connection con=connect.getConnection();

        String verifyLogin="SELECT count(1) FROM supplier WHERE username='"+usernameTextField.getText()+"'";
            try{
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery(verifyLogin);

                while(rs.next()){
                    
                    if(rs.getInt(1)>=1){
                        registerLabel.setText("Username Already Exists");
                    }
                    else{
                        if(usernameTextField.getText().isBlank()==false && supplierNameTextField.getText().isBlank()==false) {
                        String name = supplierNameTextField.getText();
                        String comp_name=companyNameTextField.getText();
                        String username=usernameTextField.getText();
                        String password=setPasswordField.getText();
                        String values= name+"','"+comp_name+"','"+username+"','"+password+ "')";
                        //Supplier sup=new Supplier(name,comp_name,username,password);
                        verifyLogin="INSERT INTO supplier(supplier_name,company_name,username,password) VALUES ('";
                        String insert=verifyLogin+values;

                        stmt=con.createStatement();
                        stmt.executeUpdate(insert);

                        registerLabel.setText("Registration Successful");

                        }
                    else{
                        registerLabel.setText("Username and SupplierName mandatory");

                        
                    }
                
            }
        }
            }catch(Exception e){
                System.out.println(e);
            }
    }

    @FXML
    public void closeButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("supplierLogin.fxml"));
            Scene scene = new Scene(root, 520, 400);
            Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
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
        
        supplierNameTextField.setTextFormatter(new TextFormatter<>(change -> {
            if(change.getText().matches("[0-9]"))
                return null;
            return change;
        
        }));
        
    }

}
