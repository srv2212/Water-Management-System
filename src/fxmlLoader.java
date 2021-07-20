//import javax.print.DocFlavor.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import java.net.URL;

public class fxmlLoader {
    
    private Pane view; 

    public Pane getPage(String filename){

        try{
            URL fileurl=getClass().getResource(filename);
            if(fileurl==null){
                throw new Exception("FXML File Cant be found");
            }
        
        view=FXMLLoader.load(getClass().getResource(filename));
        }catch(Exception e){
            System.out.println(e);
        }
        return view;
    }
}
