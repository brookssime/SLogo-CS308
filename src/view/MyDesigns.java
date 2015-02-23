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

public class MyDesigns {
	private Label myLabel;
	private ComboBox<String> myCombo;
	private File myFile;
	
	protected VBox DesignBar(){
		VBox designSidebar = new VBox();
		Button save = SaveDesign();
		StackPane chooseDesign = DesignMenu();
		designSidebar.getChildren().addAll(save, chooseDesign);
		designSidebar.setAlignment(Pos.TOP_LEFT);
		return designSidebar;
	}
	
	private File TextChooser(String... options) {
		String output = new String();
		myLabel = new Label();
		myCombo = new ComboBox<String>();
		myLabel.textProperty().bind(
				myCombo.getSelectionModel().selectedItemProperty()
				);
		myLabel.visibleProperty().bind(
				myCombo.visibleProperty().not()
				);
		myLabel.setPadding(new Insets(0, 0, 0, 9));
		myCombo.setOnMouseClicked(new EventHandler <MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				String output = myCombo.getSelectionModel().getSelectedItem();
				if (output ==null){
				 // TODO Add error message
				}
				if (output != null){
					myFile = new File ("C:\\output.xml");
				}
			}
			
		});
		return myFile;

}
	public StackPane DesignMenu (){
		StackPane root = new StackPane();
		//TODO Add names of files
		TextChooser("The fuck is you?");
		root.getChildren().addAll(myCombo, myLabel);
		return root;
	}
	
	public Button SaveDesign (){
		Button designs = new Button("Save to My Designs");
		designs.setOnAction(e -> saveFile());
		return designs;
	}
	
	private File saveFile() {

		FileChooser saver = new FileChooser();
		saver.setTitle("Save Design File");
		saver.setInitialDirectory(new File(System.getProperty("user.dir")
				+ "/src/designs"));
		ExtensionFilter filter = new ExtensionFilter("XML", "*.xml");
		saver.getExtensionFilters().add(filter);
		File savedDesign = saver.showSaveDialog(null);
		return savedDesign;
	}
}
