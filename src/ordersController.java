import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ordersController implements Initializable {

    @FXML
    private TableView<modelTable> table;

    @FXML
    private TableColumn<modelTable, Integer> col_id;

    @FXML
    private TableColumn<modelTable, String> col_name;

    @FXML
    private TableColumn<modelTable, String>col_houseNo;

    @FXML
    private TableColumn<modelTable, String>col_streetName;

    @FXML
    private TableColumn<modelTable, String>col_area;

    @FXML
    private TextField orderidField;

    @FXML
    private Button deliveredButton;

    @FXML
    private Label d_label;

    ObservableList<modelTable> oblist=FXCollections.observableArrayList();

    @Override
    public void initialize(URL location,ResourceBundle resources){

        
        col_id.setCellValueFactory(new PropertyValueFactory<modelTable,Integer>("order_id"));
        col_name.setCellValueFactory(new PropertyValueFactory<modelTable,String>("name"));
        col_houseNo.setCellValueFactory(new PropertyValueFactory<modelTable,String>("house_no"));
        col_streetName.setCellValueFactory(new PropertyValueFactory<modelTable,String>("street_name"));
        col_area.setCellValueFactory(new PropertyValueFactory<modelTable,String>("area"));

        DatabaseConnection connect=new DatabaseConnection();
        Connection con=connect.getConnection();
        String query="select * from orders";
       try{
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery(query);
        while(rs.next()){
            oblist.add(new modelTable(rs.getInt("order_id"),rs.getString("name"),
            rs.getString("house_no"),rs.getString("street_name"),rs.getString("area")));
        }
       }
       catch(Exception e){
           System.out.print(e);
       }

        table.setItems(oblist);
    }

    @FXML
    void deliveredButtonOnAction(ActionEvent event) {

            int id=Integer.parseInt(orderidField.getText());
            DatabaseConnection connect=new DatabaseConnection();
        Connection con=connect.getConnection();
        String query="delete from orders where order_id="+id;
       try{
        Statement stmt=con.createStatement();
        stmt.executeUpdate(query);
       
       }
       catch(Exception e){
           System.out.print(e);
       }
       d_label.setText("Delivery Status Success");
    }

}

