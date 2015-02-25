package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LanguageChooser {

	private ComboBox<String> languageComboBox = new ComboBox<String>();
	private String language="";
	protected void setLanguage(Stage stage) {

		stage.setTitle("Choose Language");
		Scene scene = new Scene(new Group(), 450, 250);

		languageComboBox.getItems().addAll(
				"Chinese",
				"English",
				"French",
				"German",
				"Italian",
				"Japanese",
				"Korean",
				"Portuguese",
				"Russian",
				"Spanish"				
				);
        languageComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue ov, String t, String t1) {                
                language = t1;  
            }    
        });
		GridPane grid = new GridPane();
		Label label=new Label("Choose the Language for Commands: ");
		grid.add(label,0,0);
		grid.add(languageComboBox, 0, 1);
		grid.setPadding(new Insets (100,100,100,100));
		Group root = (Group)scene.getRoot();
		root.getChildren().add(grid);
		stage.setScene(scene);
		stage.show();
	}
}
