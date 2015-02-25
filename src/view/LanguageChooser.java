package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LanguageChooser {

	private ComboBox<String> languageComboBox = new ComboBox<String>();
	
	protected void setLanguage(Stage stage) {

		stage.setTitle("Choose Language");
		Scene scene = new Scene(new Group(), 450, 250);

		languageComboBox.getItems().addAll(
				"English",
				"Chinese",
				"French",
				"German",
				"Italian",
				"Japanese",
				"Korean",
				"Portuguese",
				"Russian",
				"Spanish"				
				);

		//languageComboBox.set
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.BASELINE_CENTER);
		Label label=new Label("Choose the Language for Commands: ");
		
		//label.setAlignment(Pos.CENTER);
		grid.add(label,0,0);
		grid.add(languageComboBox, 0, 1);
		//grid.setCenterShape(true);
		grid.setPadding(new Insets (100,100,100,100));
		//grid.setAlignment(Pos.CENTER);
		Group root = (Group)scene.getRoot();
		root.getChildren().add(grid);
		stage.setScene(scene);
		stage.show();
	}
}
