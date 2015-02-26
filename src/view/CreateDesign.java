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
	protected Button SaveDesign (){
		Button designs = new Button("Save to My Designs");
		designs.setOnAction(e -> saveFile());
		return designs;
	}


//	private void nameFile() {
//		VBox vbox = new VBox();
//		Stage fileName = new Stage();
//		TextArea writeName = new TextArea();
//		Button ok = new Button ("OK");
//		ok.setOnMouseClicked(new EventHandler<MouseEvent>(){
//			@Override
//			public void handle(MouseEvent event) {
//				fileName.hide();
//				if ((writeName.getText().trim() != null && !writeName.getText().isEmpty())) {
//				try {
//					saveFile();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				
//			}
//			}
//		});
//		vbox.getChildren().addAll(writeName, ok);
//		vbox.setAlignment(Pos.CENTER);
//		Scene text = new Scene(vbox, 100, 100);
//
//		fileName.setScene(text);
//		fileName.show();
//
//		
//
//
//	}
	
	
	public void saveFile (){
		FileChooser chooser = new FileChooser();
		chooser.setInitialDirectory(new File(System.getProperty("user.dir")
				+ "/src/designs"));
		ExtensionFilter filter = new ExtensionFilter("TXT", "*.txt");
		chooser.getExtensionFilters().add(filter);
	    File myDesign = chooser.showSaveDialog(null);
		FileWriter myFileWriter;
		try {
			myFileWriter = new FileWriter(myDesign.getAbsoluteFile());
			BufferedWriter myBufferedWriter = new BufferedWriter(myFileWriter);
			for (String h: myEnter.getHistory()){
				myBufferedWriter.write(h);
				myBufferedWriter.write("\n");
			}
			myBufferedWriter.close();
		} catch (IOException e) {
			ErrorDisplay error = new ErrorDisplay();
			error.QuitError();
		}
		
	}
}
