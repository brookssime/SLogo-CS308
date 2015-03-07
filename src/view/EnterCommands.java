package view;


import java.util.ArrayList;
import java.util.List;
import java.util.Observable;




import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
public class EnterCommands extends Observable {

	public static final Integer[] LOCATION_OF_ENTERCOMMANDS_BOX={100,500};

	protected TextArea text;
	private String commandText="";
	private PreviousCommands prev;
	private String myFormatText;
	public EnterCommands(PreviousCommands p) {
		prev=p;
		
	}
	protected HBox makeBox() {
		text =new TextArea();
		
		Button runBtn = new Button("Run");
		runBtn.setOnAction(e-> runCommand());
		HBox hbox=new HBox();
		hbox.getChildren().addAll(text,runBtn);
		hbox.setTranslateX(LOCATION_OF_ENTERCOMMANDS_BOX[0]);
		hbox.setTranslateY(LOCATION_OF_ENTERCOMMANDS_BOX[1]);
		hbox.setAlignment(Pos.CENTER);
		return hbox;
	}

	protected void runCommand(){

		String myText=text.getText();
		myFormatText=myText.replaceAll("\n", " \n ");
		commandText = myText.replaceAll("\n", " ")+"\n";
		text.clear();
		prev.updateTextArea(commandText);
		setChanged();
		notifyObservers(myFormatText);


	}
	protected Button clearHistory() {
		Button clearBtn=new Button("Clear");
		clearBtn.setOnAction(e->{
			prev.getTextArea().clear();
			
			
		});
		return clearBtn;
	}
	
	
	protected String getHistory(){
		return prev.getTextArea().getText();

	}
}