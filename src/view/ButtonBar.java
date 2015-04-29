// This entire file is part of my masterpiece. // Brooks Sime


package view;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ButtonBar {
	private ColorChooser colz;
	private ImageChooser img;
	private LanguageChooser lang;
	
	public ButtonBar(ColorChooser c,LanguageChooser l) {
		colz=c;
		lang=l;
		img=new ImageChooser(colz.getDisplay());
	}

	protected Button makeButton(String name, EventHandler<ActionEvent> myMethod, int x, int y) {
		Button btn = new Button(name);
		btn.setScaleX(x);
		btn.setScaleY(y);
		btn.setOnAction(myMethod);
		return btn;
	}
	
	protected VBox makeVButtonBar(int size, int x, int y) {
		VBox vbox = new VBox(size);
		vbox.setTranslateX(x);
		vbox.setTranslateY(y);
		return vbox;
	}
	
	protected HBox makeHButtonBar(int size, int x, int y) {
		HBox hbox = new HBox(size);
		hbox.setTranslateX(x);
		hbox.setTranslateY(y);
		return hbox;
	}
	
	protected void addtoVButtonBar(VBox myVBox, Control... controls){
		myVBox.getChildren().addAll(controls);
	}
	
	protected void addtoHButtonBar(HBox myHBox, Control... controls){
		myHBox.getChildren().addAll(controls);
	}
	


}
