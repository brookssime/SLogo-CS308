package view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View extends Application {
	private Display display=new Display();
	private ButtonBar btnz=new ButtonBar(new ColorChooser(display));
	private PreviousCommands prev=new PreviousCommands();
	private EnterCommands enter=new EnterCommands(prev);
	private MyDesigns design = new MyDesigns();
	
	
    public static void main(String[] args) {
        launch(args);
    }
    
    public void start(Stage stage) {
        stage.setTitle("SLOGO");
        Group root=new Group();
        Scene scene = new Scene(root, 800,800);
        VBox veebz= btnz.makeButtonBar();
        HBox h=enter.makeBox();
        TextArea t=prev.makeBox();
        VBox d = design.DesignBar();
        
        root.getChildren().addAll(veebz,display.makeDisplay(375,375),h,t, d);
        stage.setScene(scene);
        stage.show();
    }
    
}
