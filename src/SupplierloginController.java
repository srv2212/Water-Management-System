import java.net.URL;
import java.sql.*;
import java.util.concurrent.TimeUnit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class SupplierloginController  {

    @FXML
    private TextField usernameTextField;

    @FXML
    private TextField enterPasswordField;

    @FXML
    private Button loginButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Label loginMessageLabel;

    @FXML
    private Button registerButton;

    @FXML
    void loginButtonOnAction(ActionEvent event) {
       
        if(usernameTextField.getText().isBlank()==false && enterPasswordField.getText().isBlank()==false){
           validateLogin(); 
        }
        else{
            loginMessageLabel.setText("Please Enter Username and Password");
        }
    }
    void validateLogin(){
        DatabaseConnection connect=new DatabaseConnection();
        Connection con=connect.getConnection();

        String verifyLogin="SELECT count(1) FROM supplier WHERE username='"+usernameTextField.getText()+"' AND password='"+enterPasswordField.getText() +"'";
            try{
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery(verifyLogin);

                

                while(rs.next()){
                    if(rs.getInt(1)==1){
                        loginMessageLabel.setText("LOGIN Successfull");
                        
                        String query="select company_name from supplier where username='"+usernameTextField.getText()+"';";
                        Statement st=con.createStatement();
                            ResultSet res=st.executeQuery(query);
                            String cname=" ";
                            while(res.next())
                            cname=res.getString(1);
                           
                            try{
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("supplierPage.fxml"));
                                Parent root = loader.load();
                                supplierPageController supplierPage=loader.getController();
                                supplierPage.passCName(cname);
                                
                                Stage primaryStage=new Stage();
                                primaryStage.initStyle(StageStyle.UNDECORATED);
                                primaryStage.setScene(new Scene(root,799,420));
                                primaryStage.show();

                                }
                                catch(Exception e){
                                    System.out.println(e);
                                }
                            
                        
                    }
                    else{
                        loginMessageLabel.setText("Enter Valid Username and Password");
                    }
                }
            }catch(Exception e){
                System.out.println(e);
            }
    }

    @FXML
    void cancelButtonOnAction(ActionEvent event) {
        Stage stage=(Stage)cancelButton.getScene().getWindow();
        stage.close();
    }

     @FXML
    public void registerButtonOnAction(ActionEvent event) {
        try{
            Parent root=FXMLLoader.load(getClass().getResource("supplierRegister.fxml"));
            Stage primaryStage=new Stage();
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(new Scene(root,512,529));
            primaryStage.show();
            
            }
            catch(Exception e){
                System.out.println(e);
            }
    }



}
