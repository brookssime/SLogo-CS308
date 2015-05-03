// This entire file is part of my masterpiece. // Brooks Sime

package view;
import application.Model;
import application.Turtle;
import application.TurtleList;
import application.Wrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class View {
	public static final Integer[] SIZE_OF_WINDOW={800,800};
	public static final Integer[] SIZE_OF_TURTLE_DISPLAY={375,375};
	public static final Integer[] LOCATION_OF_ENTERCOMMANDS_BOX={100,500};
	public static final Integer[] LOCATION_OF_PREVIOUSCOMMANDS_BOX={590,100};
	public static final Integer[] LOCATION_OF_BUTTONBAR={675,550};
	public static final Integer[] LOCATION_OF_COMMANDGUIDE={0,500};
	private Display display=new Display();
	private LanguageChooser lang;
	private ColorChooser col;
	private ButtonBar myButtonBar;
	private PreviousCommands prev=new PreviousCommands();
	private EnterCommands enter=new EnterCommands(prev);
	private CommandGuide comm=new CommandGuide();
	private ImportDesign des= new ImportDesign(enter);
	private CreateDesign cre = new CreateDesign(enter);
	private Group root;
	private Model m;
	private TurtleState tState;
	private ImageChooser img;


	public View(Model myModel) {
		enter.addObserver(myModel);
		lang=new LanguageChooser(this);
		col = new ColorChooser(display);
		img = new ImageChooser(col.getDisplay());
		myButtonBar=new ButtonBar(col,lang);
		m=myModel;
		tState = new TurtleState(myModel);

	}
	public void start(Stage stage) {
		stage.setTitle("SLOGO");
		root=new Group();
		Scene scene = new Scene(root, SIZE_OF_WINDOW[0],SIZE_OF_WINDOW[1]);
		
//<<<<<<< HEAD
//		Button work=workspaceAdder.addButton();
//		Button state = tState.state();
//		VBox Sprint2Buttons = new VBox();
//		Sprint2Buttons.getChildren().addAll(work,state);
//		VBox veebz= btnz.makeButtonBar();
//		HBox h=enter.makeBox();
//		VBox t=prev.makeBox();
//		VBox d= new VBox();
//		d.getChildren().addAll(cre.SaveDesign(), des.openDesign());
//		d.setLayoutY(100);
//		Button c=comm.makeMyButton();
//		root.getChildren().addAll(Sprint2Buttons,veebz,display.makeDisplay(SIZE_OF_TURTLE_DISPLAY[0],SIZE_OF_TURTLE_DISPLAY[1],m.getTurtleList().getTurtle(0)),h,t,c,d);
//=======
		Model newWorkSpace = new Model(374/2, 374/2);
		
		Button run = myButtonBar.makeButton("Run", e-> {enter.runCommand();tState.refresh();}, 1, 1);
		Button clear = myButtonBar.makeButton("Clear", e->prev.getTextArea().clear(), 1, 1);
		Button work= myButtonBar.makeButton("Add New Workspace", w-> {Wrapper.getInstance().makeNewWindow(newWorkSpace,new View(newWorkSpace));}, 1, 1);
		Button state = myButtonBar.makeButton("TurtleState", n ->tState.initiateState(), 1, 1);
		Button setLang= myButtonBar.makeButton("Set Lang", l-> lang.setLanguage(new Stage()), 2, 2);
		Button chooseColor=myButtonBar.makeButton("Set Colors", a -> col.makeColorChooserPopUp(new Stage()), 2, 2);
		Button setImage=myButtonBar.makeButton("Set Image", s-> img.start(new Stage()), 2, 2);
		Button c= myButtonBar.makeButton("Command\n Guide", z -> comm.openCommandGuide(), 1,1);
		Button save = myButtonBar.makeButton("Save Design", r -> cre.saveFile(), 1, 1);
		Button open = myButtonBar.makeButton("Import Design",q -> des.chooseDesign(), 1, 1);
		c.setLayoutX(LOCATION_OF_COMMANDGUIDE[0]);
		c.setLayoutY(LOCATION_OF_COMMANDGUIDE[1]);
		
		VBox Sprint2Buttons = myButtonBar.makeVButtonBar(10, 0, 100);
		myButtonBar.addtoVButtonBar(Sprint2Buttons, work, state, save, open);

		VBox veebz =  myButtonBar.makeVButtonBar(30, LOCATION_OF_BUTTONBAR[0], LOCATION_OF_BUTTONBAR[1]);
		myButtonBar.addtoVButtonBar(veebz,setLang, chooseColor, setImage);
		VBox t = myButtonBar.makeVButtonBar(0, LOCATION_OF_PREVIOUSCOMMANDS_BOX[0], LOCATION_OF_PREVIOUSCOMMANDS_BOX[1]);
		myButtonBar.addtoVButtonBar(t, prev.getTextArea(), clear);
		t.setAlignment(Pos.CENTER);
	
		HBox h = myButtonBar.makeHButtonBar(0,LOCATION_OF_ENTERCOMMANDS_BOX[0], LOCATION_OF_ENTERCOMMANDS_BOX[1]);
		myButtonBar.addtoHButtonBar(h, enter.init(), run);
		
	
		
		root.getChildren().addAll(Sprint2Buttons,veebz,display.makeDisplay(SIZE_OF_TURTLE_DISPLAY[0],SIZE_OF_TURTLE_DISPLAY[1],m.getTurtleList().getTurtle(0)),h,t,c);
//>>>>>>> a29f0202773b5ad97c2a4f6988117ca06585a734
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
				
				display.getRoot().getChildren().clear();
				display.makeDisplay(SIZE_OF_TURTLE_DISPLAY[0],SIZE_OF_TURTLE_DISPLAY[1],model.getTurtleList().getTurtle(0));
			}});
//		model.stampProperty().addListener(new ChangeListener<Boolean>() {
//			@Override
//			public void changed(ObservableValue<? extends Boolean> arg0,
//					Boolean oldValue, Boolean newValue) {
//				// TODO Auto-generated method stub
//			}});
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
				if (!(display.getMap().containsKey(tList.getTurtle((double) newValue)))) {
					ImageView image=display.addTurtle(SIZE_OF_TURTLE_DISPLAY[0]/2-15, SIZE_OF_TURTLE_DISPLAY[1]/2-15, tList.getTurtle((double) newValue));
					display.updateMap(tList.getTurtle((double) newValue), image);
					display.addToRoot(image);
				}
				tList.IDProperty().set(0);
			}});
		addTurtleListeners(tList.getTurtle(0));
		lang.getStringProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				// TODO Auto-generated method stub
				model.setLanguage(newValue);
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
				display.updateTurtleHeading(newValue,t);
			}
		});
		t.getLocationProperty().addListener(new ChangeListener<double[]>() {
			@Override
			public void changed(ObservableValue<? extends double[]> observable,
					double[] oldValue, double[] newValue) {
				// TODO Auto-generated method stub
				display.updateTurtleLocation(newValue[0],newValue[1],t);
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
				display.updateTurtleShowing(newValue,t);
			}
		});
		t.shapeProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
			}
		});
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