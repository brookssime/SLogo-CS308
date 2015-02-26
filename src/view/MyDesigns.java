package view;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


public class MyDesigns {
	private Label myLabel;
	private ComboBox<String> myCombo;
	private EnterCommands myEnter;
	private ErrorDisplay myError;
	private String output = "";

	public MyDesigns(EnterCommands e){
		myEnter = e;
	}
	protected VBox DesignBar(){
		VBox designSidebar = new VBox();
		Button save = SaveDesign();
		StackPane chooseDesign = DesignMenu();
		designSidebar.getChildren().addAll(save, chooseDesign);
		designSidebar.setAlignment(Pos.TOP_LEFT);
		return designSidebar;
	}
	private String TextChooser(String... options) {

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
				output = myCombo.getSelectionModel().getSelectedItem();
				if (output ==null){
					myError.QuitError();
				}
				if (output != null){
					//TODO Pick an old file
				}
			}
		});
		return output;

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
		designs.setOnAction(e -> nameFile());
		return designs;
	}


	private void nameFile() {
		VBox vbox = new VBox();
		Stage fileName = new Stage();
		TextArea writeName = new TextArea();
		Button ok = new Button ("OK");
		ok.setOnMouseClicked(new EventHandler<MouseEvent>(){
			@Override
			public void handle(MouseEvent event) {
				fileName.hide();
				try {
					saveFile(writeName.getText().trim());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}

		});
		vbox.getChildren().addAll(writeName, ok);
		vbox.setAlignment(Pos.CENTER);
		Scene text = new Scene(vbox, 100, 100);

		fileName.setScene(text);
		fileName.show();

		


	}
	
	
	public void saveFile (String s) throws IOException{
		File myDesign = new File(s + ".txt");
		myDesign.createNewFile();
		FileWriter myFileWriter = new FileWriter(myDesign.getAbsoluteFile());
		BufferedWriter myBufferedWriter = new BufferedWriter(myFileWriter);
		for (String h: myEnter.getHistory()){
			myBufferedWriter.write(h + " ");
		}
		myBufferedWriter.close();
	}
}