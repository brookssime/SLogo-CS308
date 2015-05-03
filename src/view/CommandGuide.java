// This entire file is part of my masterpiece. // Brooks Sime

package view;


import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;


public class CommandGuide {
	
	
	public static final String PATH_TO_JAVADOC="doc/commands/package-summary.html";
	
	
	/**
	 * @returns stage that shows
	 * 
	 */
	protected void openCommandGuide() {
		WebView webView = new WebView();
		WebEngine webEngine = webView.getEngine();
		
		Path path = Paths.get(PATH_TO_JAVADOC);
		
		try {
			webEngine.load(path.toUri().toURL().toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Stage stage=new Stage();
		stage.setScene(new Scene(webView));
        stage.show();
	}
	
}
