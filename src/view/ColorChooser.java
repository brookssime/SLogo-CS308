package view;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;


public class ColorChooser{
 
	
	private Color penColor;
	private Color backgroundColor;
	private Display disp;
	
	public ColorChooser(Display d) {
		penColor=Color.BLACK;
		backgroundColor=Color.WHITE;
		disp=d;
	}
    
    public HBox formatColorSelector(String object){
    	HBox box=new HBox();
    	final ColorPicker colorOfObject = new ColorPicker();
        
        final Text displayColor = new Text("Color of "+object);
        displayColor.setFont(Font.font ("Verdana", 16));
        
        if (object.equals("Pen")){
        	colorOfObject.setValue(penColor);
        	colorOfObject.setOnAction(e-> {
            	displayColor.setFill(colorOfObject.getValue());
            	//disp.setBackgroundFill(colorOfObject.getValue());
            	penColor=colorOfObject.getValue();
            	}
            	);
        	//colorOfObject.setV
        }
        else {
        	colorOfObject.setValue(backgroundColor);
        	colorOfObject.setOnAction(e-> {
            	displayColor.setFill(colorOfObject.getValue());
            	disp.setBackgroundFill(colorOfObject.getValue());
            	backgroundColor=colorOfObject.getValue();
            	}
            	);
        	//disp.setBackgroundFill(colorOfObject.getValue());
        }
//        
//    	colorOfObject.setOnAction(e-> {
//    	displayColor.setFill(colorOfObject.getValue());
//    	disp.setBackgroundFill(colorOfObject.getValue());
//    	backgroundColor=colorOfObject.getValue();
//    	}
//    	);
        
    	displayColor.setFill(colorOfObject.getValue());
        box.getChildren().addAll(displayColor,colorOfObject);
    	
    	return box;
    }
	
	public void makeColorChooserPopUp(Stage stage) {
        stage.setTitle("ColorPicker");
        Scene scene = new Scene(new VBox(20), 400, 100);
        VBox box = (VBox) scene.getRoot();
        
        box.getChildren().addAll(formatColorSelector("Pen"),formatColorSelector("Background"));
        //stage.setOnCloseRequest(e->disp.setBackgroundFill(backgroundColor));
        stage.setScene(scene);
        stage.show();
    }

}
