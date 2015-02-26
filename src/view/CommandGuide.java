package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class CommandGuide {
	
	
	protected Button makeMyButton() {
		Button btn=new Button("Command\n Guide");
		btn.setLayoutX(25);
		btn.setLayoutY(575);
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
