// This entire file is part of my masterpiece. // Brooks Sime

package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ImportDesign {
	
	private EnterCommands myEnterCommands;
	
	protected ImportDesign(EnterCommands e){
		myEnterCommands = e;
	}
	
	protected void chooseDesign(){
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Open Design File");
		chooser.setInitialDirectory(new File(System.getProperty("user.dir")
				+ "/src/designs"));
		ExtensionFilter filter = new ExtensionFilter("TXT", "*.txt");
		chooser.getExtensionFilters().add(filter);
		File selectedFile = chooser.showOpenDialog(null);
		
		try {
			String command = readFile(selectedFile);
			myEnterCommands.addCommand(command);	
		} catch (IOException e) {
			ErrorDisplay error = new ErrorDisplay();
			error.QuitError();
		}



	}
	private String readFile(File fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line + "\n");
	            line = br.readLine();
	        }
	       
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
	
	
	
	
}