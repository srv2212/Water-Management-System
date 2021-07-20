import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {

    public static void main(String[] args) throws Exception{
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root=FXMLLoader.load(getClass().getResource("main.fxml"));

        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root,532,387));
        primaryStage.show();
    }

        
}
