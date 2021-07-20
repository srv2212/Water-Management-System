import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class updateStockController implements Initializable{

    @FXML
    private TableView<modelTable> table;

    @FXML
    private TableColumn<modelTable, String> col_itemName;

    @FXML
    private TableColumn<modelTable, Integer> col_price;

    @FXML
    private TextField itemNameField;

    @FXML
    private TextField priceField;

    @FXML
    private Button addItemButton;

    @FXML
    private Button deleteItemButton;

    private String cname;

    @FXML
    void passCName(String name){
        this.cname= name;
    }
    @FXML
    void addItemButtonOnAction(ActionEvent event) {
            String item_name=itemNameField.getText();
            int p=Integer.parseInt(priceField.getText());

            DatabaseConnection connect=new DatabaseConnection();
            Connection con=connect.getConnection();
            String compname=supplierPageController.comp_name.toLowerCase().replace(' ', '_');
            String query="insert into "+compname+" (item_name,price) values"+"('"+item_name+"',"+p+")";
           try{
            Statement stmt=con.createStatement();
            stmt.executeUpdate(query);
            
           }
           catch(Exception e){
               System.out.print(e);
           }

    }

    @FXML
    void deleteItemButtonOnAction(ActionEvent event) {
        String item_name=itemNameField.getText();

        DatabaseConnection connect=new DatabaseConnection();
        Connection con=connect.getConnection();
        String compname=supplierPageController.comp_name.toLowerCase().replace(' ', '_');
        String query="delete from "+compname+" where item_name="+"'"+item_name+"'";
       try{
        Statement stmt=con.createStatement();
        stmt.executeUpdate(query);
        
       }
       catch(Exception e){
           System.out.print(e);
       }

    }

    ObservableList<modelTable> oblist=FXCollections.observableArrayList();

    @Override
    public void initialize(URL location,ResourceBundle resources){

        
        col_itemName.setCellValueFactory(new PropertyValueFactory<modelTable,String>("item_name"));
        col_price.setCellValueFactory(new PropertyValueFactory<modelTable,Integer>("price"));
     

        DatabaseConnection connect=new DatabaseConnection();
        Connection con=connect.getConnection();
        String compname=supplierPageController.comp_name.toLowerCase().replace(' ', '_');
        String query="select * from "+compname;
       try{
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        while(rs.next()){
            oblist.add(new modelTable(rs.getString("item_name"),rs.getInt("price")));
        }
       }
       catch(Exception e){
           System.out.print(e);
       }

        table.setItems(oblist);
    }

   


}
