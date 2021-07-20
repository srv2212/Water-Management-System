import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class FxmlLoader{
    private Pane view;
    public Pane getPage(String filename){

        try{
            URL fileURL=App.class.getResource(filename);
            if(fileURL==null)
                throw new java.io.FileNotFoundException("FXML file cant be found");
            view = FXMLLoader.load(fileURL);
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return view;
    }
    
}
