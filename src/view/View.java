package view;

import application.Model;
import application.Turtle;
import application.TurtleList;
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
	private LanguageChooser lang;//=new LanguageChooser(this);
	private ButtonBar btnz;//=new ButtonBar(new ColorChooser(display),lang);
	private PreviousCommands prev=new PreviousCommands();
	private EnterCommands enter=new EnterCommands(prev);
	private CommandGuide comm=new CommandGuide();
	private ImportDesign des= new ImportDesign();
	private CreateDesign cre = new CreateDesign(enter);
	private WorkspaceAdder workspaceAdder=new WorkspaceAdder();
	private Group root;
	private TurtleState tState = new TurtleState(new TurtleList());

	private Model m;
	
	public View(Model myModel) {
		enter.addObserver(myModel);
		lang=new LanguageChooser(this);
		btnz=new ButtonBar(new ColorChooser(display),lang);
		
		m=myModel;
	}
	public void start(Stage stage) {
		stage.setTitle("SLOGO");
		root=new Group();
		Scene scene = new Scene(root, SIZE_OF_WINDOW[0],SIZE_OF_WINDOW[1]);
		Button work=workspaceAdder.addButton();
		Button state = tState.state();
		VBox Sprint2Buttons = new VBox();
		Sprint2Buttons.getChildren().addAll(work,state);
		VBox veebz= btnz.makeButtonBar();
		HBox h=enter.makeBox();
		VBox t=prev.makeBox();
		VBox d= new VBox();
		d.getChildren().addAll(cre.SaveDesign(), des.openDesign());
		d.setLayoutY(100);

		Button c=comm.makeMyButton();
		root.getChildren().addAll(Sprint2Buttons,veebz,display.makeDisplay(SIZE_OF_TURTLE_DISPLAY[0],SIZE_OF_TURTLE_DISPLAY[1]),h,t,c,d);
		stage.setScene(scene);
		stage.show();
	}
	protected void addToRoot(Node n) {
		root.getChildren().add(n);
	}
	
	protected LanguageChooser getLang() {
		return lang;
	}
	
	public void addModelListeners(Model model){		
		model.errorMessageProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> arg0,
					String oldValue, String newValue) {
				displayError(newValue);
			}});	
		
		model.backgroundProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				
			}});
		
		model.penColorProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				
			}});
		
		model.penSizeProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				
			}});
		
		model.palletProperty().addListener(new ChangeListener<double[]>() {
			@Override
			public void changed(ObservableValue<? extends double[]> observable,
					double[] oldValue, double[] newValue) {
			}  
	      });
		
		model.clearScreenProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
			
			}});
		
		model.clearStampProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
			
			}});
		
		TurtleList tList = model.getTurtleList();
		tList.IDProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				addTurtleListeners(tList.getTurtle((double) newValue));
				tList.IDProperty().set(0);
			}});
		addTurtleListeners(tList.getTurtle(0));
		
		lang.getStringProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				// TODO Auto-generated method stub
				model.setLanguage(newValue);
				System.out.println(newValue);
				model.updateCommandPatterns();
			}  
	      });
	}
	
	public void addTurtleListeners(Turtle t){
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
		t.shapeProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				
			}
	      });
		t.stampProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0,
					Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
			
			}});
	}
	
	protected Model getModel() {
		return m;
	}
	public void displayError(String s) {
		ErrorDisplay error=new ErrorDisplay();
		Stage newStage=new Stage();
		newStage.setScene(error.display(s));
		newStage.show();
	}
}