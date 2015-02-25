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
	private TextArea text;
	private EnterCommands enter=new EnterCommands(this);
	protected VBox makeBox() {
		text =new TextArea();
		text.setEditable(false);
		text.setMaxWidth(200);
		text.setMinHeight(350);
		VBox vbox=new VBox(10);
		Button clearBtn=enter.blah();
		// Button clearBtn=new Button("Clear");
		// clearBtn.setOnAction(e->{
		// text.clear();
		// });
		vbox.getChildren().addAll(text,clearBtn);
		vbox.setTranslateX(590);
		vbox.setTranslateY(100);
		vbox.setAlignment(Pos.CENTER);
		return vbox;
	}
	protected TextArea getTextArea() {
		return text;
	}
	protected void updateTextArea(String moreText) {
		text.setText(moreText);
	}
}