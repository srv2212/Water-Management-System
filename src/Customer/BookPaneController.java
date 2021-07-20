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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class BookPaneController implements Initializable {

    @FXML
    private Pane bookPane;

    @FXML
    private ComboBox<String> companyComboBox,itemComboBox;

    @FXML
    private Label priceLabel,displayMessageLabel,bookLabel;

    @FXML
    private TableView<orders> viewTable;

    @FXML
    private TableColumn<orders, String> itemNameColumn;

    @FXML
    private Button bookButton,removeButton;

    @FXML
    private TableColumn<orders, Integer> priceColumn;

    private String itemName,companyName,phone,house,area,city;

    private int price;

    ObservableList<orders> listM;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        fillComboBox();
        displayMessageLabel.setText("Welcome "+Data.firstname+" "+Data.lastname);

        itemNameColumn.setCellValueFactory(new PropertyValueFactory<orders,String>("item_name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<orders,Integer>("price"));;

        listM=getDataOrders();
        viewTable.setItems(listM);

    }

    private void fillComboBox() {

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        String companyQuery = "SELECT company_name FROM supplier;" ;
        
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(companyQuery);

            while(queryResult.next()){
                companyComboBox.getItems().add(queryResult.getString("company_name"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

    public void companyComboBoxOnAction(ActionEvent event){

        bookLabel.setText("");

        companyName=((companyComboBox.getValue()).toLowerCase());
        char rep='_';
        itemComboBox.getItems().clear();
        companyName=companyName.replace(' ', rep);

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String itemQuery = "SELECT item_name FROM "+ companyName +";" ;

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(itemQuery);
            while(queryResult.next()){
                itemComboBox.getItems().add(queryResult.getString("item_name"));
            }
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

    public void itemComboBoxOnAction(ActionEvent event){
        
        itemName=itemComboBox.getValue();

        if(itemName!=null){
        
            String priceQuery = "SELECT price FROM "+companyName+" WHERE item_name='"+itemName+"';";
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();
          
            try{
                Statement statement = connectDB.createStatement();
                ResultSet queryResult = statement.executeQuery(priceQuery);
                while(queryResult.next()){
                    price=Integer.parseInt(queryResult.getString("price"));                
                }
            }
            catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }

            String insertQuery="INSERT INTO book_pane_table(item_name,price,company) VALUES('"+itemName+"',"+price+",'"+companyName+"');";

            try{       
                Statement statement = connectDB.createStatement();
                statement.executeUpdate(insertQuery);
            }
            catch(Exception e){
                e.printStackTrace();
                e.getCause();
            }

            itemNameColumn.setCellValueFactory(new PropertyValueFactory<orders,String>("item_name"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<orders,Integer>("price"));;
            listM=getDataOrders();
            viewTable.setItems(listM);

        }

    }

    public static ObservableList<orders> getDataOrders(){

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        ObservableList<orders> list = FXCollections.observableArrayList();

        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery("select * from book_pane_table;");

            while(queryResult.next()) {
                int id=Integer.parseInt(queryResult.getString("order_id"));
                String item=queryResult.getString("item_name");
                int price=Integer.parseInt(queryResult.getString("price"));
                orders order=new orders(id,item, price);
                list.add(order);
            }            
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

        return list;
    }

    public void bookButtonOnAction(ActionEvent event){

        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String updateCancelQuery="select * from book_pane_table";
        String getDetailsQuery="SELECT phone_number,house_number,area,city FROM user_account WHERE username='"+Data.username+"'";

        try{            
            Statement statement = connectDB.createStatement();
            ResultSet queryResult=statement.executeQuery(getDetailsQuery);
            while(queryResult.next()){
                phone=queryResult.getString("phone_number");
                house=queryResult.getString("house_number");
                area=queryResult.getString("area");
                city=queryResult.getString("city");
            }
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

        try{              
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(updateCancelQuery);

            while(queryResult.next()) {
                int id=Integer.parseInt(queryResult.getString("order_id"));
                String item=queryResult.getString("item_name");
                int price=Integer.parseInt(queryResult.getString("price"));
                String company=queryResult.getString("company");            
                String insertCancelQuery="INSERT INTO `"+Data.username+"`(order_id,item_name,price,company) VALUES("+id+",'"+item+"',"+price+",'"+company+"');";

                try{
                    Statement statement1 = connectDB.createStatement();
                    statement1.executeUpdate(insertCancelQuery);
                }
                catch(Exception e){
                    e.printStackTrace();
                    e.getCause();
                }

                String insertCompanyQuery="INSERT INTO "+company+"_orders(stock_id,item_name,price,house_number,area,city,phone_number,user) VALUES("+id+",'"+item+"',"+price+",'"+house+"','"+area+"','"+city+"','"+phone+"','"+Data.username+"');";

                try{              
                    Statement statement1 = connectDB.createStatement();
                    statement1.executeUpdate(insertCompanyQuery);
                }
                catch(Exception e){
                    e.printStackTrace();
                    e.getCause();
                }

            }
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

        String deleteQuery="DELETE FROM book_pane_table";
        try{                
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(deleteQuery);
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

        itemNameColumn.setCellValueFactory(new PropertyValueFactory<orders,String>("item_name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<orders,Integer>("price"));;
        listM=getDataOrders();
        viewTable.setItems(listM);

        bookLabel.setText("Order Booked");
    }

    public void removeButtonOnAction(ActionEvent event){

        orders selectedOrder=(viewTable.getSelectionModel().getSelectedItem());
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String companyOrder="delete from book_pane_table where order_id="+selectedOrder.getOrder_id()+";";

        try{                
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(companyOrder);
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

        itemNameColumn.setCellValueFactory(new PropertyValueFactory<orders,String>("item_name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<orders,Integer>("price"));;
        listM=getDataOrders();
        viewTable.setItems(listM);


    }

}
