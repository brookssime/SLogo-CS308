package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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

	public static final Integer[] SIZE_OF_POPUP={450,250};

	
	private ComboBox<String> languageComboBox = new ComboBox<String>();
	private String language="English";
	private View view;
	private StringProperty languageProp=new SimpleStringProperty(language);
	
	public LanguageChooser(View v) {
		view=v;
	}
	
	protected void setLanguage(Stage stage) {

		stage.setTitle("Choose Language");
		Scene scene = new Scene(new Group(), SIZE_OF_POPUP[0], SIZE_OF_POPUP[1]);
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
                //String/ObjectProperty bind what is in here to what is in model
            	//StringProperty string=
            	language=t1;
            	view.getModel().setLanguage(t1);// = t1; 
            	languageProp.setValue(t1);//=ov;
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
	
	public StringProperty getStringProperty() {
		return languageProp;
	}
	
	protected String getLanguage() {
		return language;
	}
}
