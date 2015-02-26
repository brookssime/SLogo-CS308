package view;

import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
public class ColorChooser{
	
	public static final Integer[] SIZE_OF_COLORCHOOSER_POPUP={400,100};
	
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
				disp.setLineFill(colorOfObject.getValue());
				penColor=colorOfObject.getValue();
			}
					);
		}
		else {
			colorOfObject.setValue(backgroundColor);
			colorOfObject.setOnAction(e-> {
				displayColor.setFill(colorOfObject.getValue());
				disp.setBackgroundFill(colorOfObject.getValue());
				backgroundColor=colorOfObject.getValue();
			}
					);
		}
		displayColor.setFill(colorOfObject.getValue());
		box.getChildren().addAll(displayColor,colorOfObject);
		return box;
	}
	public void makeColorChooserPopUp(Stage stage) {
		stage.setTitle("ColorPicker");
		Scene scene = new Scene(new VBox(20), SIZE_OF_COLORCHOOSER_POPUP[0], SIZE_OF_COLORCHOOSER_POPUP[1]);
		VBox box = (VBox) scene.getRoot();
		box.getChildren().addAll(formatColorSelector("Pen"),formatColorSelector("Background"));
		//stage.setOnCloseRequest(e->disp.setBackgroundFill(backgroundColor));
		stage.setScene(scene);
		stage.show();
	}
	
	protected Display getDisplay() {
		return disp;
	}
}