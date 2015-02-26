package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
public class EnterCommands extends Observable {
	
	public static final Integer[] LOCATION_OF_ENTERCOMMANDS_BOX={100,500};
	
	private TextArea text;
	private String commandText="";
	private PreviousCommands prev;
	private List<String> history;
	public EnterCommands(PreviousCommands p) {
		prev=p;
		history=new ArrayList<String>();
	}
	protected HBox makeBox() {
		text =new TextArea();
		Button runBtn = new Button("Run");
		runBtn.setOnAction(e-> handler((ActionEvent)e));
		HBox hbox=new HBox();
		hbox.getChildren().addAll(text,runBtn);
		hbox.setTranslateX(LOCATION_OF_ENTERCOMMANDS_BOX[0]);
		hbox.setTranslateY(LOCATION_OF_ENTERCOMMANDS_BOX[1]);
		hbox.setAlignment(Pos.CENTER);
		return hbox;
	}
	public void handler(Event e) {
		if ((text.getText().trim() != null && !text.getText().isEmpty())) {
			String myText=text.getText();
			String myFormatText=myText.replaceAll("\n", " \n ");
			history.add(myFormatText);
			commandText = commandText+myText.replaceAll("\n", " ")+"\n";
			text.clear();
			prev.updateTextArea(commandText);
			printStatement();

			setChanged();
			notifyObservers(myFormatText);

			//System.out.println(commandText);
		}
	}
	protected Button blah() {
		Button clearBtn=new Button("Clear");
		clearBtn.setOnAction(e->{
			prev.getTextArea().clear();
			resetHistory(history);
		});
		return clearBtn;
	}
	protected void resetHistory(List<String> s) {
		s.clear();
		//s=new ArrayList<String>();
	}
	protected void printStatement() {
		System.out.println("--");
		for (String s: history) {
			System.out.println(history.indexOf(s)+":"+s);
		}
		System.out.println("--");
	}
	public List<String> getHistory(){
		return history;
	}
}