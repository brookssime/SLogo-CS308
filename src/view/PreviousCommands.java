package view;

import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class PreviousCommands {
	//private EnterCommands enter;
	
	public static final Integer[] SIZE_OF_PREVIOUSCOMMANDS_BOX={200,350};
	public static final Integer[] LOCATION_OF_PREVIOUSCOMMANDS_BOX={590,100};
	
	private TextArea text;
	private EnterCommands enter=new EnterCommands(this);
	protected VBox makeBox() {
		text =new TextArea();
		text.setEditable(false);
		text.setMaxWidth(SIZE_OF_PREVIOUSCOMMANDS_BOX[0]);
		text.setMinHeight(SIZE_OF_PREVIOUSCOMMANDS_BOX[1]);
		VBox vbox=new VBox(10);
		Button clearBtn=enter.clearHistory();
		vbox.getChildren().addAll(text,clearBtn);
		vbox.setTranslateX(LOCATION_OF_PREVIOUSCOMMANDS_BOX[0]);
		vbox.setTranslateY(LOCATION_OF_PREVIOUSCOMMANDS_BOX[1]);
		vbox.setAlignment(Pos.CENTER);
		return vbox;
	}
	protected TextArea getTextArea() {
		return text;
	}
	protected void updateTextArea(String moreText) {
		text.appendText(moreText);
	}
}