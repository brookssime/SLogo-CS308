package view;

import application.Model;
import application.Turtle;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class View extends Application {
	private Display display=new Display();
	private ButtonBar btnz=new ButtonBar(new ColorChooser(display),new LanguageChooser());
	private PreviousCommands prev=new PreviousCommands();
	private EnterCommands enter=new EnterCommands(prev);
	private CommandGuide comm=new CommandGuide();
	private Group root;
	public static void main(String[] args) {
		launch(args);
	}
	public void start(Stage stage) {
		stage.setTitle("SLOGO");
		root=new Group();
		Scene scene = new Scene(root, 800,800);
		VBox veebz= btnz.makeButtonBar();
		HBox h=enter.makeBox();
		VBox t=prev.makeBox();
		Button c=comm.makeMyButton();
		root.getChildren().addAll(veebz,display.makeDisplay(375,375),h,t,c);
		stage.setScene(scene);
		stage.show();
	}
	protected void addToRoot(Node n) {
		root.getChildren().add(n);
	}
	public void addAllListeners(Model model){
		Turtle t = model.getActiveTurtle();
		t.getHeadingProperty().addListener(new ChangeListener(){
	        @Override public void changed(ObservableValue o,Object oldVal, 
	                 Object newVal){
	             System.out.println("Electric bill has changed!");
	        }
	      });
	}
}