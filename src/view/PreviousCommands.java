// This entire file is part of my masterpiece. // Brooks Sime

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
	
	private static final Integer[] SIZE_OF_PREVIOUSCOMMANDS_BOX={200,350};
	
	
	private TextArea text = new TextArea();
	private EnterCommands enter=new EnterCommands(this);

	protected TextArea getTextArea() {
		text.setEditable(false);
		text.setMaxWidth(SIZE_OF_PREVIOUSCOMMANDS_BOX[0]);
		text.setMinHeight(SIZE_OF_PREVIOUSCOMMANDS_BOX[1]);
		return text;
	}
	protected void updateTextArea(String moreText) {
		text.appendText(moreText);
	}
}