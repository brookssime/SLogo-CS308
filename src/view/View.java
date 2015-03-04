package view;

import application.Model;
import application.Turtle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class View {
	
	public static final Integer[] SIZE_OF_WINDOW={800,800};
	public static final Integer[] SIZE_OF_TURTLE_DISPLAY={375,375};
	
	private Display display=new Display();
	private ButtonBar btnz=new ButtonBar(new ColorChooser(display),new LanguageChooser());
	private PreviousCommands prev=new PreviousCommands();
	private EnterCommands enter=new EnterCommands(prev);
	private CommandGuide comm=new CommandGuide();
	private ImportDesign des= new ImportDesign();
	private CreateDesign cre = new CreateDesign(enter);
	private Group root;

	public View(Model myModel) {
		enter.addObserver(myModel);
	}
	public void start(Stage stage) {
		stage.setTitle("SLOGO");
		root=new Group();
		Scene scene = new Scene(root, SIZE_OF_WINDOW[0],SIZE_OF_WINDOW[1]);
		VBox veebz= btnz.makeButtonBar();
		HBox h=enter.makeBox();
		VBox t=prev.makeBox();
		VBox d= new VBox();
		d.getChildren().addAll(cre.SaveDesign(), des.openDesign());
		d.setLayoutY(100);

		Button c=comm.makeMyButton();
		root.getChildren().addAll(veebz,display.makeDisplay(SIZE_OF_TURTLE_DISPLAY[0],SIZE_OF_TURTLE_DISPLAY[1]),h,t,c,d);
		stage.setScene(scene);
		stage.show();
	}
	protected void addToRoot(Node n) {
		root.getChildren().add(n);
	}
	public void addAllListeners(Model model){
		model.clearScreenProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean arg1, Boolean arg2) {
				// TODO Auto-generated method stub
				
			}});
		Turtle t = model.getActiveTurtle();
		t.getHeadingProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				display.updateTurtleHeading(newValue);
			}  
	      });

		t.getLocationProperty().addListener(new ChangeListener<double[]>() {
			@Override
			public void changed(ObservableValue<? extends double[]> observable,
					double[] oldValue, double[] newValue) {
				// TODO Auto-generated method stub
				System.out.println("hit");
				display.updateTurtleLocation(newValue[0],newValue[1]);
			}  
	      });
		t.getPenDownProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				display.setVisibility(newValue);
			}
	      });
		t.getShowingProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				System.out.println("showing changed");
				display.updateTurtleShowing(newValue);
			}
	      });
		t.getNodeProperty().addListener(new ChangeListener<Node>() {
			@Override
			public void changed(ObservableValue<? extends Node> observable,
					Node oldValue, Node newValue) {
				// TODO Auto-generated method stub
				
			}
	      });
	}
}