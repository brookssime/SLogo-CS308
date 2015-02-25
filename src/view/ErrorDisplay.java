package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ErrorDisplay {
	
	public Scene display(String s){
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		Label secondLabel = new Label(s);

		grid.getChildren().add(secondLabel);

		Scene secScene = new Scene(grid, 350, 150);
		return secScene;
	}
	
}