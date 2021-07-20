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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class CancelOrderPaneController implements Initializable {

    @FXML
    private Pane cancelOrderPane;

    @FXML
    private TableView<orders> cancelOrderTable;

    @FXML
    private Button cancelOrderButton;

    @FXML
    private Label welcomelabel;

    @FXML
    private TableColumn<orders, String> itemNameColumn;

    @FXML
    private TableColumn<orders, Integer> priceColumn;

    @FXML
    private TableColumn<orders, String> companyColumn;

    ObservableList<orders> listM;

    String companyName;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {   
        
        welcomelabel.setText("Welcome "+Data.firstname+" "+Data.lastname);

        itemNameColumn.setCellValueFactory(new PropertyValueFactory<orders,String>("item_name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<orders,Integer>("price"));
        companyColumn.setCellValueFactory(new PropertyValueFactory<orders,String>("company"));
        listM=getDataOrders();
        cancelOrderTable.setItems(listM);    
    }

    public void cancelOrderButtonOnAction(ActionEvent event){
        orders selectedOrder=(cancelOrderTable.getSelectionModel().getSelectedItem());
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String companyOrder="SELECT company FROM `"+Data.username+"` WHERE order_id="+selectedOrder.getOrder_id()+";";

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(companyOrder);
            while(queryResult.next()){
                companyName=(queryResult.getString("company"));                
            }
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

        String deleteOrder="DELETE FROM `"+Data.username+"` WHERE order_id="+selectedOrder.getOrder_id()+";";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(deleteOrder);
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

        String deleteQuery="DELETE FROM "+companyName+"_orders WHERE stock_id="+selectedOrder.getOrder_id()+";";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(deleteQuery);
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

        itemNameColumn.setCellValueFactory(new PropertyValueFactory<orders,String>("item_name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<orders,Integer>("price"));
        companyColumn.setCellValueFactory(new PropertyValueFactory<orders,String>("company"));
        listM=getDataOrders();
        cancelOrderTable.setItems(listM);
           
    }

    public static ObservableList<orders> getDataOrders(){

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        ObservableList<orders> list = FXCollections.observableArrayList();
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery("select * from `"+Data.username+"`;");
            while(queryResult.next()) {
                int id=Integer.parseInt(queryResult.getString("order_id"));
                String item_name=queryResult.getString("item_name");
                int price=Integer.parseInt(queryResult.getString("price"));
                String compan=queryResult.getString("company");
                orders neworder=new orders(id, item_name, price, compan);
                list.add(neworder);
            }
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

        return list;
    }

}
