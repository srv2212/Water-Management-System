import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class profileController implements Initializable {
    @FXML
    private Label label;

    public void initialize(URL location,ResourceBundle resources){
        DatabaseConnection connect=new DatabaseConnection();
        Connection con=connect.getConnection();
        String compname=supplierPageController.comp_name;
        String query="select supplier_name,company_name,username from supplier where company_name='"+compname+"'";
       try{
        Statement stmt=con.createStatement();
        ResultSet rs =stmt.executeQuery(query);
        while(rs.next()){
            label.setText("Name: "+rs.getString("supplier_name")+"\n\n"+"Company Name: "+rs.getString("company_name")+"\n\n"+"Username: "+rs.getString("username"));
        }
       
       }
       catch(Exception e){
           System.out.print(e);
       }

    }


}
