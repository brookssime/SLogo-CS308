package view;

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;


public class ColorChooser{
 
	
	private Color penColor;
	private Color backgroundColor;
	
	public ColorChooser() {
		penColor=Color.BLACK;
		backgroundColor=Color.WHITE;
	}
    
    public HBox formatColorSelector(String object){
    	HBox box=new HBox();
    	final ColorPicker colorOfObject = new ColorPicker();
        
    	colorOfObject.setValue(penColor);
        
        final Text displayColor = new Text("Color of "+object);
        displayColor.setFont(Font.font ("Verdana", 20));
        displayColor.setFill(colorOfObject.getValue());
        
        
        colorOfObject.setOnAction(e->
                displayColor.setFill(colorOfObject.getValue()));
        box.getChildren().addAll(colorOfObject,displayColor);
    	
    	return box;
    }
	
	public void makeColorChooserPopUp(Stage stage) {
        stage.setTitle("ColorPicker");
        Scene scene = new Scene(new VBox(20), 400, 100);
        VBox box = (VBox) scene.getRoot();
        
        box.getChildren().addAll(formatColorSelector("Pen"),formatColorSelector("Background"));
        stage.setScene(scene);
        stage.show();
    }
}
