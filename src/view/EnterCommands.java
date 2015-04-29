// This entire file is part of my masterpiece. // Brooks Sime

package view;



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
	private ImportDesign upload = new ImportDesign(this);
	
	public EnterCommands(PreviousCommands p) {
		prev=p;
		
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
	
	protected String getHistory(){
		return prev.getTextArea().getText();

	}
	
	protected void uploadCommand(String s){
		String command = s;
		System.out.println(command);
	}
	
	protected void addCommand(String command){
		text.appendText(command);
	}
}