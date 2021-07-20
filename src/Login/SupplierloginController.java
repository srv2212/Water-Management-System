import java.net.URL;
import java.sql.*;
import java.util.concurrent.TimeUnit;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
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
           validateLogin(event); 
        }
        else{
            loginMessageLabel.setText("Please Enter Username and Password");
        }
    }
    void validateLogin(ActionEvent event){
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
                            while(res.next()){
                                cname=res.getString(1);
                                String name=res.getString(1);
                                Data.company=name.toLowerCase().replace(' ', '_');
                            }
                           
                            try {
                                Parent root = FXMLLoader.load(getClass().getResource("supplierPage.fxml"));
                                Scene scene = new Scene(root, 864, 510);
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

     @FXML
    public void registerButtonOnAction(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("supplierRegister.fxml"));
            Scene scene = new Scene(root, 512, 555);
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



}
