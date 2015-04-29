// This entire file is part of my masterpiece. // Brooks Sime

package view;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


public class CreateDesign {
	private EnterCommands myEnter;
	
	public CreateDesign(EnterCommands e){
		myEnter = e;
	}
	
	
	public void saveFile (){
		FileChooser saver = new FileChooser();
		saver.setInitialDirectory(new File(System.getProperty("user.dir")
				+ "/src/designs"));
		ExtensionFilter filter = new ExtensionFilter("TXT", "*.txt");
		saver.getExtensionFilters().add(filter);
	    File myDesign = saver.showSaveDialog(null);
		FileWriter myFileWriter;
		try {
			myFileWriter = new FileWriter(myDesign.getAbsoluteFile());
			BufferedWriter myBufferedWriter = new BufferedWriter(myFileWriter);
			myBufferedWriter.write(myEnter.getHistory());

			myBufferedWriter.close();
		} catch (IOException e) {
			ErrorDisplay error = new ErrorDisplay();
			error.QuitError();
		}
		
	}
}
