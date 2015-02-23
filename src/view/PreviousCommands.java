package view;

import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PreviousCommands {

	//private EnterCommands enter;
	private TextArea text;
	
	protected TextArea makeBox() {
		text =new TextArea();
        text.setMaxWidth(200);
        text.setMinHeight(400);
        text.setTranslateX(600);
        text.setTranslateY(100);
		//text.setText(text.getText()+enter.getCommandText());
		//updateTextArea();
		return text;
	}
	
	protected void updateTextArea(String moreText) {
		text.setText(moreText);
	}
	
}
