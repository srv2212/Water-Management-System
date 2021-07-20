import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;

public class SupplierRegisterController {

    @FXML
    private TextField supplierNameTextField;

    @FXML
    private TextField companyNameTextField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField setPasswordField;

    @FXML
    private TextField confirmPasswordField;

    @FXML
    private Button registerButton;

    @FXML
    private Button closeButton;

    @FXML
    private Label registerLabel;

    

    @FXML
     public void registerButtonOnAction(ActionEvent event){

        if(setPasswordField.getText().equals(confirmPasswordField.getText()) && setPasswordField.getText().isBlank()==false ){
            registerSupplier();   
            DatabaseConnection connect=new DatabaseConnection();
            Connection con=connect.getConnection();
           
 
        String companyName=companyNameTextField.getText().toLowerCase().replace(' ', '_');
            String query="CREATE TABLE `watersupplymanagement`." +companyName+
            " ( item_name VARCHAR(30) NOT NULL, " + 
            " price INT NOT NULL, " + 
            " PRIMARY KEY ( item_name))";

            try{
            Statement stmt=con.createStatement();
            stmt.executeUpdate(query);
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        else{
            registerLabel.setText(" Password does not match");
        }
        
    }
    public void registerSupplier(){

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
        Stage stage=(Stage)closeButton.getScene().getWindow();
        stage.close();
    }

}
