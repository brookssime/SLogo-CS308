package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ButtonBar {

	private Group root;
	private ColorChooser colz;//=new ColorChooser();
	public ButtonBar(ColorChooser c) {
		colz=c;
	}
	
	private Button makeButton(String name) {
		Button btn = new Button(name);
		btn.setScaleX(2);
		btn.setScaleY(2);
		return btn;
	}
	
	protected VBox makeButtonBar() {
		VBox vbox = new VBox(30);
		Button setLang=makeButton("Set Lang");
		
		ErrorDisplay err=new ErrorDisplay();
		
		Button chooseColor=makeButton("Set Colors");
		chooseColor.setOnAction(c-> colz.makeColorChooserPopUp(new Stage()));
		Button setImage=makeButton("Set Image");
		setImage.setOnAction(s-> {Stage newStage=new Stage();
    	newStage.setScene(err.display("YO ERROR"));
    	newStage.show();});
//		vbox.setTranslateX(vbox.getMaxWidth()-100);
//		vbox.setTranslateY(15);

		vbox.getChildren().addAll(setLang,chooseColor,setImage);
		//hbox.setPrefWidth(view.getWindowSize());
		vbox.setAlignment(Pos.CENTER);
        vbox.setTranslateX(675);
        vbox.setTranslateY(550);
		return vbox;
	}
	
	
}
