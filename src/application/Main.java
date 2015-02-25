package application;
	
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import commands.*;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SecurityException, ClassNotFoundException {
		launch(args);
	    /*Model myModel = new Model(100, 100);
	    Parser myParser = new Parser(myModel);
	    UserCommand myUserCommand = myParser.parse("towards lt 50 st");
	    System.out.println(myUserCommand.process(new ArrayList<Object>()));*/
	}
}
