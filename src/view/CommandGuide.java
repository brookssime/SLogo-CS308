package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class CommandGuide {
	
	public static final Integer[] LOCATION_OF_COMMANDGUIDE={15,575};
	
	protected Button makeMyButton() {
		Button btn=new Button("Command\n Guide");
		btn.setLayoutX(LOCATION_OF_COMMANDGUIDE[0]);
		btn.setLayoutY(LOCATION_OF_COMMANDGUIDE[1]);
		btn.setOnAction(b->openCommandGuide());
		return btn;
	}
	
	protected void openCommandGuide() {
		WebView webView = new WebView();
		WebEngine webEngine = webView.getEngine();
		webEngine.load("http://www.cs.duke.edu/courses/compsci308/spring15/assign/03_slogo/commands.php");
		Stage stage=new Stage();
		stage.setScene(new Scene(webView));
        stage.show();
	}
}
