package view;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class View extends Application {
	
	private ButtonBar btnz=new ButtonBar(new ColorChooser());
	
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage stage) {
        stage.setTitle("SLOGO");
 
        
        VBox veebz= btnz.makeButtonBar();
        
        
        Group root=new Group();
        root.getChildren().addAll(veebz);
        Scene scene = new Scene(root, 800,800);
        stage.setScene(scene);
        stage.show();
    }
    
}
