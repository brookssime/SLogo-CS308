package view;


import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;








import application.Model;
import application.Parser;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
public class EnterCommands extends Observable {

	public static final Integer[] LOCATION_OF_ENTERCOMMANDS_BOX={100,500};

	private TextArea text = new TextArea();
	private String commandText="";
	private PreviousCommands prev;
	private String myFormatText;
	private ImportDesign upload = new ImportDesign();
	
	public EnterCommands(PreviousCommands p) {
		prev=p;
		
	}
	protected HBox makeBox() {
		Button runBtn = new Button("Run");
		runBtn.setOnAction(e-> runCommand());
		HBox hbox=new HBox();
		hbox.getChildren().addAll(text,runBtn);
		hbox.setTranslateX(LOCATION_OF_ENTERCOMMANDS_BOX[0]);
		hbox.setTranslateY(LOCATION_OF_ENTERCOMMANDS_BOX[1]);
		hbox.setAlignment(Pos.CENTER);
		return hbox;
	}
	
	protected TextArea init(){
		return text;
	}

	protected void runCommand(){
		String myText=init().getText();
		myFormatText=myText.replaceAll("\n", " \n ");
		commandText = myText.replaceAll("\n", " ")+"\n";
		init().clear();
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
	
	protected void uploadCommand(String s){
		String command = s;
		
		
	}
}