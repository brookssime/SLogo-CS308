package application;
	
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import view.View;
import commands.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	private View myView;
	@Override
	public void start(Stage primaryStage) {
		Model myModel = new Model(200, 200);
		myView = new View(myModel);
		myView.addAllListeners(myModel);
		myView.start(new Stage());
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, ClassNotFoundException {
		launch(args);
	    /*Model myModel = new Model(100, 100);
	    Parser myParser = new Parser(myModel);
	    UserCommand myUserCommand = myParser.parse("50 40 30");
	    System.out.println(myUserCommand.process(new ArrayList<Object>()));*/
	}
}
