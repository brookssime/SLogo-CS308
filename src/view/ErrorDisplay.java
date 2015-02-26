package view;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;




import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ErrorDisplay{

	public VBox message (String s, Button...buttons){
		Label myMsg = new Label(s);
		myMsg.setTranslateX(120);

		HBox hBox = new HBox();
		hBox.setAlignment(Pos.BASELINE_CENTER);
		hBox.setSpacing(40.0);
		hBox.getChildren().addAll(buttons);

		VBox vBox = new VBox();
		vBox.setSpacing(40.0);
		vBox.getChildren().addAll(myMsg, hBox);

		return vBox;


	}


	public void QuitError(){
		Stage e = new Stage();
		e.setTitle("Error!");
		e.initModality(Modality.APPLICATION_MODAL);
		//Quit and close all windows
		Button yes = new Button();
		yes.setText("Yes");
		yes.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
		Button no = new Button();
		no.setText("No");
		no.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				e.close();

			}
		});

		e.setScene(new Scene(message("Do you want to quit?", yes, no), 400, 100 ));
		e.show();
	}


	

}
