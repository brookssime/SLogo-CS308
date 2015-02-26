package view;

import java.io.File;

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

public class ListofDesigns {
//	private Label myLabel;
//	private ComboBox<String> myCombo;
//	private ErrorDisplay myError;
//	private String output = "";
//	
//
//	
//	private String TextChooser(String... options) {
//
//		myLabel = new Label();
//		myCombo = new ComboBox<String>();
//		myLabel.textProperty().bind(
//				myCombo.getSelectionModel().selectedItemProperty()
//				);
//		myLabel.visibleProperty().bind(
//				myCombo.visibleProperty().not()
//				);
//		myLabel.setPadding(new Insets(0, 0, 0, 9));
//		myCombo.setOnMouseClicked(new EventHandler <MouseEvent>(){
//			@Override
//			public void handle(MouseEvent event) {
//				// TODO Auto-generated method stub
//				output = myCombo.getSelectionModel().getSelectedItem();
//				if (output ==null){
//					myError.QuitError();
//				}
//				if (output != null){
//					//TODO Pick an old file
//				}
//			}
//		});
//		return output;
//
//	}
//	public StackPane DesignMenu (){
//		StackPane root = new StackPane();
//		//TODO Add names of files
//		TextChooser("The fuck is you?");
//		root.getChildren().addAll(myCombo, myLabel);
//		return root;
//	}
//
//
//}
	protected Button openDesign(){
		Button open = new Button("Import Design");
		open.setOnAction(e -> chooseDesign());
		return open;
	}
	
	private void chooseDesign(){
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Open Design File");
		chooser.setInitialDirectory(new File(System.getProperty("user.dir")
				+ "/src/designs"));
		ExtensionFilter filter = new ExtensionFilter("TXT", "*.txt");
		chooser.getExtensionFilters().add(filter);
		File selectedFile = chooser.showOpenDialog(null);

		if (selectedFile != null) {
			//TODO Connect to Parser
		}
		else{
			ErrorDisplay e = new ErrorDisplay();
			e.QuitError();
		}
	}
	
	
	
}